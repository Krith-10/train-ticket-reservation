package com.krith.reservation.controller;

import com.krith.reservation.api.TrainReservationApi;
import com.krith.reservation.dto.ReservationDto;
import com.krith.reservation.service.ReservationService;
import com.krith.reservation.service.UserService;
import com.krith.reservation.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class TrainReservationController implements TrainReservationApi {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<ReservationDto> createReservation(ReservationDto reservationDto) {
        if(!Validator.isValidReservationDto(reservationDto))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Payload provided is invalid. Please provide valid body");
        ReservationDto responseDto= reservationService.createTicketReservation(reservationDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ReservationDto> getReservationDetails(Long userId) {
        if(userId == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Payload provided is invalid. Please provide valid userId");
        ReservationDto responseDto = reservationService.getReservationDetails(userId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteReservationForUser(Long userId) {
        if(userId == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Payload provided is invalid. Please provide valid userId");
        reservationService.deleteReservationByUserId(userId);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<ReservationDto> modifySeating(Long userId) {
        if(userId == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Payload provided is invalid. Please provide valid userId");
        ReservationDto responseDto = reservationService.modifySeating(userId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
