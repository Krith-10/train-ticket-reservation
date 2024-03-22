package com.krith.reservation.api;

import com.krith.reservation.dto.SeatDetailsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "Section Details")
public interface SectionDetailsApi {

    @Operation(summary = "Get Seat and User Details By Section", operationId="getSeatAndUserDetailsBySection",
                description = "API provides information about the seats that are occupied by the users for the" +
                        " provided section")
    @GetMapping(value = "section/{section}/details", produces = {"application/json"})
    ResponseEntity<List<SeatDetailsDto>> getSeatAndUserDetailsBySection(@PathVariable String section);
}
