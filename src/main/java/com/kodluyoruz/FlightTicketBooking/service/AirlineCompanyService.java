package com.kodluyoruz.FlightTicketBooking.service;

import com.kodluyoruz.FlightTicketBooking.exception.BussinesException;
import com.kodluyoruz.FlightTicketBooking.exception.NotFoundException;
import com.kodluyoruz.FlightTicketBooking.model.dto.AirlineCompanyDto;
import com.kodluyoruz.FlightTicketBooking.model.entity.AirlineCompany;
import com.kodluyoruz.FlightTicketBooking.model.request.CreateUpdateAirlineCompanyRequest;
import com.kodluyoruz.FlightTicketBooking.repository.AirlineCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kodluyoruz.FlightTicketBooking.model.mapper.AirlineCompanyMapper.AIRLINE_COMPANY_MAPPER;

@Service
@RequiredArgsConstructor
public class  AirlineCompanyService {

    private final AirlineCompanyRepository repository;

    public AirlineCompanyDto createAirlineCompany(CreateUpdateAirlineCompanyRequest request){
        AirlineCompany airlineCompany=AIRLINE_COMPANY_MAPPER.createAirlineCompany(request);
        return AIRLINE_COMPANY_MAPPER.toAirlaneCompanyDto(repository.save(airlineCompany));
    }
    public AirlineCompanyDto updateAirlineCompany(int id,CreateUpdateAirlineCompanyRequest request){
        AirlineCompany airlineCompany=repository.findById(id).orElseThrow(()->new NotFoundException("Not Found"));
        AIRLINE_COMPANY_MAPPER.updateAirlineCompany(airlineCompany,request);
        AirlineCompany updatedAirlineCompany=repository.save(airlineCompany);
        return AIRLINE_COMPANY_MAPPER.toAirlaneCompanyDto(updatedAirlineCompany);
    }

    public AirlineCompanyDto getAirlineCompanyById(int id){
        return AIRLINE_COMPANY_MAPPER.toAirlaneCompanyDto(repository.findById(id).orElseThrow(()->new NotFoundException("Airline Company Not Found")));
    }

    public List<AirlineCompanyDto> getAirlineCompanyList(){
        return  AIRLINE_COMPANY_MAPPER.toAirlaneCompanyDtoList(repository.findAll());
    }
    public void deleteAirlineCompany(int id){
        repository.deleteById(id);
    }

}
