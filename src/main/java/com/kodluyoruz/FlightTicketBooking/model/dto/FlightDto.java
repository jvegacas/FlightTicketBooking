package com.kodluyoruz.FlightTicketBooking.model.dto;

import com.kodluyoruz.FlightTicketBooking.model.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightDto {
    private int id;

    private String flightCode;

    private Date depatureTime;

    private Date arrivalTime;

    private int rotationId;

    private int pilotId;

    private int airlineCompanyId;

    private int airplaneId;

    private int ticketPriceId;

    private boolean available;

    private int seatLeft;

}
