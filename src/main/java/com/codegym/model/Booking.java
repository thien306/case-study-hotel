package com.codegym.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate checkinDate;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate checkoutDate;
    int roomQuantity;
    private boolean status;
    private Integer totalMoney;
    int nights;

}
