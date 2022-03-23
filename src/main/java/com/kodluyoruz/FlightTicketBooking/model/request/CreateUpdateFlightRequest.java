package com.kodluyoruz.FlightTicketBooking.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUpdateFlightRequest {

    private String flightCode;

    private Date depatureTime;

    private Date arrivalTime;

    private int rotationId;

    private int pilotId;

    private int companyId;

    private int airplaneId;

    private int ticketPriceId;

}
