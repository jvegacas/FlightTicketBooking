package com.kodluyoruz.FlightTicketBooking.repository;

import com.kodluyoruz.FlightTicketBooking.model.entity.TicketPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketPriceRepository extends JpaRepository<TicketPrice,Integer> {
}
