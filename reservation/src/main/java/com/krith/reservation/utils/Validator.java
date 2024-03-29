package com.krith.reservation.utils;

import com.krith.reservation.dto.ReservationDto;
import com.krith.reservation.dto.UserDto;

/*
Helper class to validate methods
 */
public class Validator {

    public static boolean isValidReservationDto(ReservationDto reservationDto){
        if(reservationDto.getFrom() == null || reservationDto.getTo() == null)
            return false;
        if(reservationDto.getUser() != null){
            UserDto userDto = reservationDto.getUser();
            return userDto.getEmail() != null && userDto.getFirstName() != null && userDto.getLastName() != null;
        }
        return true;
    }

}
