package com.codegym.service.Interface;

import com.codegym.model.Booking;
import com.codegym.model.Room;
import com.codegym.repository.IBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

public interface IBookingService {
    List<Booking> findAllByCheckinDate(LocalDate checkinDate);
    Boolean isAvaiable(Booking booking);
    void doBooking(Booking booking);

}