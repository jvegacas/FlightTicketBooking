package com.kodluyoruz.FlightTicketBooking.service;

import com.kodluyoruz.FlightTicketBooking.exception.BussinesException;
import com.kodluyoruz.FlightTicketBooking.exception.NotFoundException;
import com.kodluyoruz.FlightTicketBooking.model.dto.AddressDto;
import com.kodluyoruz.FlightTicketBooking.model.entity.Address;
import com.kodluyoruz.FlightTicketBooking.model.request.CreateUpdateAddressRequest;
import com.kodluyoruz.FlightTicketBooking.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kodluyoruz.FlightTicketBooking.model.mapper.AddressMapper.ADDRESS_MAPPER;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository repository;

    public AddressDto createAddress(CreateUpdateAddressRequest request){
        Address address=ADDRESS_MAPPER.createAddress(request);
        return ADDRESS_MAPPER.toAddressDto(repository.save(address));
    }
    public AddressDto updateAddress(int id ,CreateUpdateAddressRequest request){
        Address address=repository.findById(id).orElseThrow(()->new NotFoundException("Not Found"));
        ADDRESS_MAPPER.updateAddress(address,request);

        Address updatedAddress=repository.save(address);
        return ADDRESS_MAPPER.toAddressDto(updatedAddress);
    }
    public AddressDto getAddressById(int id){
        return ADDRESS_MAPPER.toAddressDto(repository.findById(id).orElseThrow(()->new NotFoundException("Address Not Found")));
    }
    public List<AddressDto> getAddressList(){
        return ADDRESS_MAPPER.toAddressDtoList(repository.findAll());
    }

    public void deleteAddress(int id){
        repository.deleteById(id);
    }

    public Address toAddress(int id){
        return repository.findById(id).orElseThrow(()->new BussinesException(""));
    }


}
