package com.krith.reservation.controller;

import com.krith.reservation.api.SectionDetailsApi;
import com.krith.reservation.dto.SeatDetailsDto;
import com.krith.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class SectionDetailsController implements SectionDetailsApi {

    @Autowired
    private ReservationService reservationService;

    @Override
    public ResponseEntity<List<SeatDetailsDto>> getSeatAndUserDetailsBySection(String section) {
        if(section.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Payload provided is invalid");
        return new ResponseEntity<>(reservationService.getSeatAndUserDetailsBySection(section), HttpStatus.OK);
    }

}
