package com.kodluyoruz.FlightTicketBooking.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ticket_bookings")
@SuperBuilder
public class TicketBooking extends BaseEntity{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "flight_id")
    private Integer flightId;

    @Column
    private Integer numberOfTicket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id",insertable = false,updatable = false,nullable = false)
    private Flight flight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",insertable = false,updatable = false,nullable = false)
    private User user;

    @Column
    private Double totalPaidPrice;


}
