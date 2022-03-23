package com.kodluyoruz.FlightTicketBooking.service;
import com.kodluyoruz.FlightTicketBooking.exception.BussinesException;
import com.kodluyoruz.FlightTicketBooking.exception.NotFoundException;
import com.kodluyoruz.FlightTicketBooking.model.dto.AirplaneDto;
import com.kodluyoruz.FlightTicketBooking.model.dto.FlightDto;
import com.kodluyoruz.FlightTicketBooking.model.dto.PilotDto;
import com.kodluyoruz.FlightTicketBooking.model.entity.Flight;
import com.kodluyoruz.FlightTicketBooking.model.request.CreateUpdateFlightRequest;
import com.kodluyoruz.FlightTicketBooking.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.kodluyoruz.FlightTicketBooking.model.mapper.FlightMapper.FLIGHT_MAPPER;

@RequiredArgsConstructor
@Service
public class FlightService {
    private final FlightRepository repository;
    private final AirplaneService airplaneService;
    private final PilotService pilotService;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public FlightDto createFlight(CreateUpdateFlightRequest request){
        validateAirplane(request);
        validatePilot(request);
        Flight flight=FLIGHT_MAPPER.createFlight(request);
        AirplaneDto airplane=airplaneService.getAirplaneById(flight.getAirplaneId());
        flight.setSeatLeft(airplane.getAirplaneCapacity());
        return FLIGHT_MAPPER.toFlightDto(repository.save(flight));
    }

    public FlightDto updateFlight(int id,CreateUpdateFlightRequest request){
        Flight flight=repository.findById(id).orElseThrow(()->new NotFoundException("Flight  not found."));
        FLIGHT_MAPPER.updateFlight(flight,request);
        Flight updatedflight=repository.save(flight);
        return FLIGHT_MAPPER.toFlightDto(updatedflight);
    }

    public FlightDto getFlightById(int id){
        return FLIGHT_MAPPER.toFlightDto(repository.findById(id).orElseThrow(()-> new NotFoundException("Flight not found.")));
    }

    public List<FlightDto> getAvailableFlightList(){
        return  FLIGHT_MAPPER.toFlightDtoList(repository.findByAvailableTrue());
    }

    public void deleteFlight(int id){
        repository.deleteById(id);
    }


    public void validatePilot(CreateUpdateFlightRequest request){
        int pilotId=request.getPilotId();
        PilotDto pilot=pilotService.getPilotById(pilotId);
        if(!pilot.isAvailable())
            throw new BussinesException("Pilot is not Available");

    }
    public void validateAirplane(CreateUpdateFlightRequest request){
        int airplaneId=request.getAirplaneId();
        AirplaneDto airplane=airplaneService.getAirplaneById(airplaneId);
        if(!airplane.isAvailable())
            throw new BussinesException("airplane is not Available");
    }



}


