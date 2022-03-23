package com.kodluyoruz.FlightTicketBooking.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@SuperBuilder
public class User extends BaseEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(nullable = false)
    private String userFirstName;

    @Column(nullable = false)
    private String userLastName;

    @Column(nullable = false,unique = true)
    private String userIdentityNumber;

    @Column(unique = true,nullable = false)
    private String userEmail;

    @Column(unique = true,nullable = false)
    private String userPhoneNumber;

    @Column(unique = true,nullable = false)
    private String userPassword;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    private List<TicketBooking> ticketBooking;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;


}
