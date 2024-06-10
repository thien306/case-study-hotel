package com.codegym.repository;

import com.codegym.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByRoomId(Long id);
}
