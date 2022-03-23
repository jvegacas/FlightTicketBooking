package com.kodluyoruz.FlightTicketBooking.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUpdateUserRequest {
    private String userFirstName;

    private String userLastName;

    private String userIdentityNumber;

    private String userEmail;

    private String userPhoneNumber;

    private String userPassword;


}
