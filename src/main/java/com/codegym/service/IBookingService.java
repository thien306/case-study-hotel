package com.codegym.service;

import com.codegym.model.Booking;
import com.codegym.model.dto.BookingRequest;

public interface IBookingService {
    Booking createBooking(BookingRequest bookingRequest);
}
