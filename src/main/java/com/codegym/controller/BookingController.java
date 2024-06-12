package com.codegym.controller;

import com.codegym.model.Booking;
import com.codegym.model.Room;
import com.codegym.model.dto.BookingDto;
import com.codegym.service.Interface.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    IBookingService bookingService;
    @GetMapping
    public ResponseEntity<List<Booking>> showBookedRoomList(@RequestBody BookingDto bookingDto){
        List<Booking> bookings = bookingService.findAllByCheckinDate(bookingDto.getCheckinDate());
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }
    @PostMapping("/doBooking")
    public ResponseEntity<?>doBooking(@RequestBody Booking booking){
        bookingService.doBooking(booking);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}