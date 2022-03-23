package com.kodluyoruz.FlightTicketBooking.repository;

import com.kodluyoruz.FlightTicketBooking.model.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,Integer> {
}
