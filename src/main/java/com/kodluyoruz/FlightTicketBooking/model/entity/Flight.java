package com.kodluyoruz.FlightTicketBooking.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "flights")
@SuperBuilder
public class Flight extends BaseEntity{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(unique = true,nullable = false)
    private String flightCode;

    @Column
    private Date depatureTime;

    @Column
    private Date arrivalTime;

    @Column(name = "rotation_id")
    private Integer rotationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rotation_id",nullable = false,insertable = false,updatable = false)
    private Rotation rotation;

    @Column(name = "pilot_id")
    private Integer pilotId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pilot_id",nullable = false,insertable = false,updatable = false)
    private Pilot pilot;

    @Column(name = "company_id")
    private Integer companyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id",nullable = false,insertable = false,updatable = false)
    private AirlineCompany company;

    @Column(name = "airplane_id")
    private Integer airplaneId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airplane_id",nullable = false,insertable = false,updatable = false)
    private Airplane airplane;

    @Column(name = "ticket_price_id")
    private Integer ticketPriceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_price_id",nullable = false,insertable = false,updatable = false)
    private TicketPrice ticketPrice;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "flight",cascade = CascadeType.ALL)
    private List<TicketBooking> ticketBookingList;

    @Builder.Default
    private Boolean available=true;

    @Column
    private Integer seatLeft;





}
