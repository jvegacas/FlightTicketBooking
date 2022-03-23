package com.kodluyoruz.FlightTicketBooking.repository;

import com.kodluyoruz.FlightTicketBooking.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRespository extends JpaRepository<User,Integer> {
    Optional<User> findByUserEmail(String email);

}
