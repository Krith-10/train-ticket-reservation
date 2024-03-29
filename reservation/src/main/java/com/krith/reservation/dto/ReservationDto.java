package com.krith.reservation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationDto {

    private long id;
    private String from;
    private String to;
    private UserDto user;
    private float price;
    private String section;
    private int seat;

    public ReservationDto(){
    }

    public ReservationDto(long id, String from, String to, float price, String section,int seat, UserDto user) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.price = price;
        this.section = section;
        this.seat = seat;
        this.user = user;
    }
}
