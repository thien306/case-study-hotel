package com.codegym.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;

@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double price;
    private int nights;
    private LocalDate checkinDate;
    private LocalDate checkoutDate;
    private String customerName;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
