package com.kodluyoruz.FlightTicketBooking.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketBookingDto {
    private int id;

    private int userId;

    private int flightId;

    private int numberOfTicket;
}
