package com.codegym.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private int phoneNumber;

    private String avatar;

    @OneToMany(mappedBy = "customer")
    private Set<Booking> bookings;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @PrePersist
    protected void prePersist() {
        if (role == null) {
            role = new Role();
            role.setId(3L);
            role.setName("ROLE_CUSTOMER");
        }
    }
}
