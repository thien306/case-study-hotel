package com.codegym.repository;

import com.codegym.model.Booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface IBookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByCheckinDate(LocalDate checkinDate);



}
