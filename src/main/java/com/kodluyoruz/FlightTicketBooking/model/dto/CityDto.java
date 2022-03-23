package com.kodluyoruz.FlightTicketBooking.model.dto;


import com.kodluyoruz.FlightTicketBooking.model.entity.Country;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityDto {
    private int id;

    private String cityName;

    private int  countryId;
}
