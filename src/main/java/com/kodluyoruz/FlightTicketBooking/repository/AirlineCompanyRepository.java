package com.kodluyoruz.FlightTicketBooking.repository;

import com.kodluyoruz.FlightTicketBooking.model.entity.AirlineCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirlineCompanyRepository extends JpaRepository<AirlineCompany,Integer> {
}
