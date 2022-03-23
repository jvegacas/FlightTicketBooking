package com.kodluyoruz.FlightTicketBooking.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private int id;

    private String userFirstName;

    private String userLastName;

    private String userIdentityNumber;

    private String userEmail;

    private String userPhoneNumber;

    private String userRole;
}
