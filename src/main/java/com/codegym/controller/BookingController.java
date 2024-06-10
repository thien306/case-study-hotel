package com.codegym.controller;

import com.codegym.model.Booking;
import com.codegym.model.dto.BookingRequest;
import com.codegym.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(value = "*")
public class BookingController {
    @Autowired
    private IBookingService bookingService;

//    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET})
    @PostMapping
    public ResponseEntity<Booking> addBooking(@RequestBody BookingRequest bookingRequest) {
        Booking booking = bookingService.createBooking(bookingRequest);
        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }
}
