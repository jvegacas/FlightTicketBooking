package com.kodluyoruz.FlightTicketBooking.service;

import com.kodluyoruz.FlightTicketBooking.exception.BussinesException;
import com.kodluyoruz.FlightTicketBooking.model.dto.*;
import com.kodluyoruz.FlightTicketBooking.model.entity.Flight;
import com.kodluyoruz.FlightTicketBooking.model.request.CreateUpdateFlightRequest;
import com.kodluyoruz.FlightTicketBooking.repository.FlightRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZonedDateTime;
import java.util.Date;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FlightServiceTest {

    @InjectMocks
    private FlightService sut;

    @Mock
    private FlightRepository repository;

    @Mock
    private AirplaneService airplaneService;

    @Mock
    private PilotService pilotService;

    private ArgumentCaptor <Flight> flightArgumentCaptor;

    private CreateUpdateFlightRequest request;

    private PilotDto pilotDto;

    private AirplaneDto airplaneDto;



    @BeforeEach
    void setUp() {
        Date departure = Date.from(ZonedDateTime.now().toInstant());
        Date arrival = Date.from(ZonedDateTime.now().plusHours(2).toInstant());
         this.request=CreateUpdateFlightRequest.builder()
                .airplaneId(1)
                .pilotId(1)
                .companyId(1)
                .arrivalTime(departure)
                .depatureTime(arrival)
                .rotationId(1)
                .flightCode("")
                .ticketPriceId(1)
                .build();

         pilotDto=PilotDto.builder()
                .id(1)
                .available(false)
                .pilotFirstName("")
                .pilotIdentityNumber("")
                .pilotLastName("")
                .pilotLicence("")
                .pilotPhoneNumber("")
                .build();

         airplaneDto=AirplaneDto.builder()
                 .id(1)
                 .airplaneCapacity(400)
                 .airplaneManufacturer("Turkey")
                 .airplaneDetails("")
                 .available(true)
                 .airplaneName("")
                 .build();

    }

    @Test
    void it_should_throw_exception_when_airplane_is_not_available() {
        when(airplaneService.getAirplaneById(eq(1))).thenReturn((airplaneDto));
        when(pilotService.getPilotById(eq(1))).thenReturn((pilotDto));
        Assertions.assertThrows(BussinesException.class,()->sut.createFlight(request));
    }
    @Test
    void it_should_throw_exception_when_pilot_is_not_available() {
        when(pilotService.getPilotById(eq(1))).thenReturn((pilotDto));
        Assertions.assertThrows(BussinesException.class,()->sut.createFlight(request));

    }

}
