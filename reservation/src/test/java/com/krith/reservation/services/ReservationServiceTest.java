package com.krith.reservation.services;

import com.krith.reservation.dto.ReservationDto;
import com.krith.reservation.dto.UserDto;
import com.krith.reservation.entity.Reservation;
import com.krith.reservation.repository.ReservationRepository;
import com.krith.reservation.service.ReservationService;
import com.krith.reservation.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    void getReservationDetailsTest(){
        UserDto userDto = new UserDto();
        Reservation reservation = new Reservation(1,"London","Paris",1,"A",3,5);
        doReturn(userDto).when(userService).getUserById(Mockito.isA(Long.class));
        doReturn(reservation).when(reservationRepository).findByUserId(Mockito.isA(Long.class));
        ReservationDto response = reservationService.getReservationDetails(1L);
        assertEquals(reservation.getId(), response.getId());
        assertEquals(reservation.getSource(), response.getFrom());
        assertEquals(reservation.getDestination(), response.getTo());
        assertEquals(reservation.getSection(), response.getSection());
        assertEquals(reservation.getSeat(), response.getSeat());
        assertEquals(reservation.getPrice(), response.getPrice());
    }

    @Test
    void deleteReservationByUserIdTest(){
        Reservation reservation = new Reservation(1,"London","Paris",1,"A",3,5);
        doReturn(reservation).when(reservationRepository).findByUserId(Mockito.isA(Long.class));
        reservationService.deleteReservationByUserId(1L);
        userService.deleteUserById(Mockito.isA(Long.class));
        verify(reservationRepository, times(1)).deleteById(Mockito.isA(Long.class));
    }

    @Test
    void convertReservationToDtoTest(){
        Reservation reservation = new Reservation();
        UserDto userDto = new UserDto();
        ReservationDto response = reservationService.convertReservationToDto(reservation, userDto);
        assertNotNull(response);
    }
}
