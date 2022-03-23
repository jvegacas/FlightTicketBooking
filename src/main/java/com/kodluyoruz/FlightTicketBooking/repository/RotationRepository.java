package com.kodluyoruz.FlightTicketBooking.repository;

import com.kodluyoruz.FlightTicketBooking.model.entity.Rotation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RotationRepository extends JpaRepository<Rotation,Integer> {
}
