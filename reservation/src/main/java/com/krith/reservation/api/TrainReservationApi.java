package com.krith.reservation.api;

import com.krith.reservation.dto.ReservationDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Ticket Reservation")
public interface TrainReservationApi {

    @Operation(summary = "Create a reservation", operationId = "createReservation",
                description = "This API allows to create reservation. Only a single reservation for a user" +
                        " is allowed to create")
    @PostMapping(value = "/reservation", produces = {"application/json"}, consumes = {"application/json"})
    ResponseEntity<ReservationDto> createReservation(@RequestBody ReservationDto reservationDto);

    @Operation(summary = "Get reservation of the user", operationId = "getReservationOfUser",
                description = "Get the reservation based on the user")
    @GetMapping(value = "/reservation/{userId}", produces = {"application/json"})
    ResponseEntity<ReservationDto> getReservationDetails(@PathVariable Long userId);

    @Operation(summary = "Delete reservation of the user", operationId = "deleteReservationOfUser",
                description = "Delete the reservation based on user")
    @DeleteMapping(value = "/reservation/{userId}", produces = {"application/json"})
    ResponseEntity<Void> deleteReservationForUser(@PathVariable Long userId);

    @Operation(summary = "Modify allocated seat for a user", operationId= "modifyAllocatedSeateForUser",
                description = "Modify the existing seat of an user. This operation would be performed" +
                        " only if seats are available")
    @PatchMapping(value = "/reservation/{userId}/seats/modify", produces = {"application/json"})
    ResponseEntity<ReservationDto> modifySeating(@PathVariable Long userId);
}
