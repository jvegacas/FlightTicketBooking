package com.kodluyoruz.FlightTicketBooking.service;

import com.kodluyoruz.FlightTicketBooking.exception.BussinesException;
import com.kodluyoruz.FlightTicketBooking.exception.NotFoundException;
import com.kodluyoruz.FlightTicketBooking.model.dto.AirplaneDto;
import com.kodluyoruz.FlightTicketBooking.model.entity.Airplane;
import com.kodluyoruz.FlightTicketBooking.model.request.CreateUpdateAirplaneRequest;
import com.kodluyoruz.FlightTicketBooking.repository.AirplaneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kodluyoruz.FlightTicketBooking.model.mapper.AirplaneMapper.AIRPLANE_MAPPER;

@Service
@RequiredArgsConstructor
public class AirplaneService {
    private final AirplaneRepository repository;

    public AirplaneDto createAirplane(CreateUpdateAirplaneRequest request){
        Airplane airplane=AIRPLANE_MAPPER.createAirplane(request);
        return AIRPLANE_MAPPER.toAirplaneDto(repository.save(airplane));
    }
    public AirplaneDto updateAirplane(int id ,CreateUpdateAirplaneRequest request){
        Airplane airplane=repository.findById(id).orElseThrow(()->new NotFoundException("Not Found"));
        AIRPLANE_MAPPER.updateAirplane(airplane,request);
        Airplane updatedAirplane=repository.save(airplane);
        return AIRPLANE_MAPPER.toAirplaneDto(updatedAirplane);
    }
    public AirplaneDto getAirplaneById(int id){
        return AIRPLANE_MAPPER.toAirplaneDto(repository.findById(id).orElseThrow(()->new NotFoundException("Airplane not found")));
    }
    public List<AirplaneDto> getAirplaneList(){ return AIRPLANE_MAPPER.toAirplaneDtoList(repository.findAll()); }

    public void deleteAirplane(int id){  repository.deleteById(id);}

    public void setAvailabilty(int id){
        repository.setAvailable(id,false);
    }

}
