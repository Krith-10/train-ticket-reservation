package com.krith.reservation.repository;

import com.krith.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    public Reservation findByUserId(long userId);

    public List<Reservation> findAllBySection(String section);

}
