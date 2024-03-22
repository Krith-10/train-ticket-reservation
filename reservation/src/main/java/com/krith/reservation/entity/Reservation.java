package com.krith.reservation.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "reservation")
public class Reservation {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="source")
    private String source;

    @Column(name="destination")
    private String destination;

    @Column(name="user_id", unique = true)
    private long userId;

    @Column(name="section")
    private String section;

    @Column(name = "seat")
    private int seat;

    @Column(name = "price")
    private float price;

    public Reservation() {
    }

    public Reservation(String source, String destination, float price) {
        this.source = source;
        this.destination = destination;
        this.price = price;
    }

    public Reservation(long id, String source, String destination, long userId, String section, int seat, float price) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.userId = userId;
        this.section = section;
        this.seat = seat;
        this.price = price;
    }
}
