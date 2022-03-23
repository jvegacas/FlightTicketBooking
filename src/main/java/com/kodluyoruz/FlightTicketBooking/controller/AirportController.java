package com.kodluyoruz.FlightTicketBooking.controller;

import com.kodluyoruz.FlightTicketBooking.model.dto.AirportDto;
import com.kodluyoruz.FlightTicketBooking.model.request.CreateUpdateAirportRequest;
import com.kodluyoruz.FlightTicketBooking.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/airports")
@RequiredArgsConstructor
public class AirportController {
    private final AirportService service;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AirportDto createAirport(@RequestBody CreateUpdateAirportRequest request){
        return service.createAirport(request);
    }

    @GetMapping("{id}")
    public AirportDto getAirport(@PathVariable int id) {
        return service.getAirportById(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping("{id}")
    public AirportDto updateAirport(@PathVariable int id, @RequestBody CreateUpdateAirportRequest request){
        return service.updateAirport(id,request);
    }

    @GetMapping
    public List<AirportDto> getAirportList() {
        return service.getAirportList();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAirport(@PathVariable  int id){
        service.deleteAirport(id);
    }
}
