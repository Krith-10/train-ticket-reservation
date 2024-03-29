package com.krith.reservation.service;

import com.krith.reservation.dto.ReservationDto;
import com.krith.reservation.dto.SeatDetailsDto;
import com.krith.reservation.dto.UserDto;
import com.krith.reservation.entity.Reservation;
import com.krith.reservation.repository.ReservationRepository;
import com.krith.reservation.utils.SeatGenerator;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserService userService;

    public ReservationDto createTicketReservation(ReservationDto reservationDto){
        UserDto userDto = userService.getUserById(reservationDto.getUser().getId());
        if(userDto == null) {
            try {
                userDto = userService.createUser(reservationDto.getUser());
            } catch (Exception ex) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "The email of the user already exists");
            }
        }
        Reservation reservation = new Reservation(reservationDto.getFrom(), reservationDto.getTo(), reservationDto.getPrice());
        reservation.setUserId(userDto.getId());
        Pair<String, Integer> sectionAndSeat = null;
        try {
            sectionAndSeat = SeatGenerator.generateSeat();
        } catch (Exception e) {
            userService.deleteUserById(userDto.getId());
            throw new ResponseStatusException(HttpStatus.CONFLICT, "All seats have been reserved. No more seats available");
        }
        reservation.setSection(sectionAndSeat.getValue0());
        reservation.setSeat(sectionAndSeat.getValue1());
        Reservation reservationEntity = reservationRepository.save(reservation);
        return convertReservationToDto(reservationEntity, userDto);
    }

    public ReservationDto getReservationDetails(Long userId){
        UserDto userDto = userService.getUserById(userId);
        if(userDto == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User doesn't exists");
        Reservation reservation = reservationRepository.findByUserId(userId);
        return convertReservationToDto(reservation, userDto);
    }

    public void deleteReservationByUserId(Long userId){
        userService.deleteUserById(userId);
        Reservation reservation = reservationRepository.findByUserId(userId);
        reservationRepository.deleteById(reservation.getId());
        SeatGenerator.makeSeatAvailable(reservation.getSection(), reservation.getSeat());
    }

    public ReservationDto modifySeating(Long userId){
        UserDto userDto = userService.getUserById(userId);
        if(userDto == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The provided user is not found");
        Reservation reservation = reservationRepository.findByUserId(userId);
        Pair<String, Integer>  sectionAndSeat = null;
        try {
            sectionAndSeat = SeatGenerator.modifySeat(reservation.getSection(), reservation.getSeat());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "All seats have been reserved. Unable to modify seat");
        }
        reservation.setSection(sectionAndSeat.getValue0());
        reservation.setSeat(sectionAndSeat.getValue1());
        Reservation reservationEntity = reservationRepository.save(reservation);
        return convertReservationToDto(reservationEntity, userDto);
    }

    public List<SeatDetailsDto> getSeatAndUserDetailsBySection(String section){
        List<Reservation> reservationsOfASection = reservationRepository.findAllBySection(section);
        return reservationsOfASection.stream()
                .map(this::mapSeatAndUserDetails)
                .collect(Collectors.toList());
    }

    private SeatDetailsDto mapSeatAndUserDetails(Reservation reservation){
        UserDto userDto = userService.getUserById(reservation.getUserId());
        return new SeatDetailsDto(reservation.getSeat(), userDto);
    }

    public ReservationDto convertReservationToDto(Reservation reservation, UserDto userDto){

        return new ReservationDto(
                reservation.getId(),
                reservation.getSource(),
                reservation.getDestination(),
                reservation.getPrice(),
                reservation.getSection(),
                reservation.getSeat(),
                userDto
        );
    }
}

