package com.kodluyoruz.FlightTicketBooking.service;

import com.kodluyoruz.FlightTicketBooking.exception.BussinesException;
import com.kodluyoruz.FlightTicketBooking.exception.NotFoundException;
import com.kodluyoruz.FlightTicketBooking.model.dto.CityDto;
import com.kodluyoruz.FlightTicketBooking.model.entity.City;
import com.kodluyoruz.FlightTicketBooking.model.request.CreateUpdateCityRequest;
import com.kodluyoruz.FlightTicketBooking.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kodluyoruz.FlightTicketBooking.model.mapper.CityMapper.CITY_MAPPER;


@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository repository;

    public CityDto createCity(CreateUpdateCityRequest request){
        City city=CITY_MAPPER.createCity(request);
        return CITY_MAPPER.toCityDto(repository.save(city));
    }
    public CityDto updateCity(int id,CreateUpdateCityRequest request){
       City city=repository.findById(id).orElseThrow(()->new BussinesException("City is not found."));
        CITY_MAPPER.updateCity(city,request);
       City updatedcity=repository.save(city);
        return CITY_MAPPER.toCityDto(updatedcity);
    }
    public CityDto getCityById(int id){
        return CITY_MAPPER.toCityDto(repository.findById(id).orElseThrow(()-> new NotFoundException("City  not found.")));
    }
    public List<CityDto> getCityList(){
        return  CITY_MAPPER.toCityDtoList(repository.findAll());
    }
    public void deleteCity(int id){
        repository.deleteById(id);
    }
}
