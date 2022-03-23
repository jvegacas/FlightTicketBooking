package com.kodluyoruz.FlightTicketBooking.model.request;

import com.kodluyoruz.FlightTicketBooking.model.entity.Airport;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUpdateRotationRequest {
    private int departureAirportId;

    private int arrivalAirportId;


}
