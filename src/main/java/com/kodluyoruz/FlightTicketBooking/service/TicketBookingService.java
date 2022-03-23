package com.kodluyoruz.FlightTicketBooking.service;

import com.kodluyoruz.FlightTicketBooking.exception.BussinesException;
import com.kodluyoruz.FlightTicketBooking.exception.NotFoundException;

import com.kodluyoruz.FlightTicketBooking.model.dto.TicketBookingDto;
import com.kodluyoruz.FlightTicketBooking.model.entity.Airplane;
import com.kodluyoruz.FlightTicketBooking.model.entity.Flight;
import com.kodluyoruz.FlightTicketBooking.model.entity.TicketBooking;
import com.kodluyoruz.FlightTicketBooking.model.request.CreateUpdateTicketBookingRequest;
import com.kodluyoruz.FlightTicketBooking.repository.AirplaneRepository;
import com.kodluyoruz.FlightTicketBooking.repository.FlightRepository;
import com.kodluyoruz.FlightTicketBooking.repository.TicketBookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import static com.kodluyoruz.FlightTicketBooking.model.mapper.TicketBookingMapper.TICKET_BOOKING_MAPPER;

@Service
@RequiredArgsConstructor
public class TicketBookingService {
    private final TicketBookingRepository repository;
    private final AirplaneRepository airplaneRepository;
    private final FlightRepository flightRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public TicketBookingDto createTicketBooking(CreateUpdateTicketBookingRequest request){
        validetaTicketBooking(request);
        TicketBooking ticketBooking=TICKET_BOOKING_MAPPER.createTicketBooking(request);
        Flight flight=flightRepository.findById(request.getFlightId()).orElseThrow(()-> new NotFoundException("Flight is Not Found"));
        ticketBooking.setTotalPaidPrice(flight.getTicketPrice().getAmount()*ticketBooking.getNumberOfTicket());
        return TICKET_BOOKING_MAPPER.toTicketBookingDto(repository.save(ticketBooking));
    }
    public TicketBookingDto updateTicketBooking(int id, CreateUpdateTicketBookingRequest request){
        TicketBooking rotation=repository.findById(id).orElseThrow(()->new NotFoundException("TicketBooking not found."));
        TICKET_BOOKING_MAPPER.updateTicketBooking(rotation,request);
        TicketBooking updatedrotation=repository.save(rotation);
        return TICKET_BOOKING_MAPPER.toTicketBookingDto(updatedrotation);
    }
    public TicketBookingDto getTicketBookingById(int id){
        return TICKET_BOOKING_MAPPER.toTicketBookingDto(repository.findById(id).orElseThrow(()-> new NotFoundException("TicketBooking  not found.")));
    }
    public List<TicketBookingDto> getTicketBookingList(){
        return  TICKET_BOOKING_MAPPER.toTicketBookingDtoList(repository.findAll());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteTicketBooking(int id){
        TicketBooking ticketBooking= repository.findById(id).orElseThrow(()->new BussinesException("ticket could not cancelled"));
        Flight flight=flightRepository.findById(ticketBooking.getFlightId()).orElseThrow(()->new BussinesException("ticket could not cancelled"));
        List<TicketBooking> numOfTicket=repository.findByFlightId(flight.getId());
        int ticketSold= numOfTicket
                .stream()
                .mapToInt(ticketBookings -> ticketBookings.getNumberOfTicket())
                .sum();
        Airplane airplane=airplaneRepository.getById(flight.getAirplaneId());
        ticketSold-=ticketBooking.getNumberOfTicket();
        flightRepository.setSeatLeft(flight.getId(),(airplane.getAirplaneCapacity()-ticketSold));
        repository.deleteById(id);

    }

    public void validetaTicketBooking(CreateUpdateTicketBookingRequest request){
        Flight flight=flightRepository.findById(request.getFlightId()).orElseThrow(()->new BussinesException(""));
        List<TicketBooking> numOfTicket=repository.findByFlightId(flight.getId());
        int ticketSold= numOfTicket
                .stream()
                .mapToInt(ticketBooking -> ticketBooking.getNumberOfTicket())
                .sum();
        Airplane airplane=airplaneRepository.getById(flight.getAirplaneId());
        int leftSeat=airplane.getAirplaneCapacity()-ticketSold;
        if(leftSeat<=0){
            flightRepository.setAvailable(flight.getId(),false);
            throw new BussinesException("There is no empty Left");
        }else if(leftSeat>0&&(leftSeat-request.getNumberOfTicket()<0)){
            throw new BussinesException("There is no empty Left");
        }
        flightRepository.setSeatLeft(flight.getId(),(leftSeat-request.getNumberOfTicket()));

    }
}
