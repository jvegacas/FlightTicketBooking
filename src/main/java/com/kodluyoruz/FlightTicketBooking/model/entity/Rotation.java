package com.kodluyoruz.FlightTicketBooking.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rotations")
@SuperBuilder
public class Rotation  extends BaseEntity{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(name = "departure_airport_id")
    private Integer departureAirportId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departure_airport_id",insertable = false,updatable = false,nullable = false)
    private Airport departureAirport;

    @Column(name = "arrival_airport_id")
    private Integer arrivalAirportId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arrival_airport_id",insertable = false,updatable = false,nullable = false)
    private Airport arrivalAirport;

    @OneToMany(mappedBy = "rotation",fetch = FetchType.LAZY)
    private List<Flight> flightList;


}
