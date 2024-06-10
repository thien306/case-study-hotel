package com.codegym.service.impl;

import com.codegym.model.Booking;
import com.codegym.model.Customer;
import com.codegym.model.Room;
import com.codegym.model.dto.BookingRequest;
import com.codegym.repository.IBookingRepository;
import com.codegym.repository.ICustomerRepository;
import com.codegym.repository.IRoomRepository;
import com.codegym.service.IBookingService;
import com.codegym.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookingService implements IBookingService {

    @Autowired
    private IBookingRepository bookingRepository;

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private IRoomRepository roomRepository;

    @Autowired
    private IRoomService roomService;

    @Override
    public Booking createBooking(BookingRequest bookingRequest) {
        Customer customer = customerRepository.findById(bookingRequest.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        List<Room> availableRooms = roomService.getAvailableRooms(bookingRequest.getCheckinDate(), bookingRequest.getCheckoutDate());
        Set<Long> availableRoomIds = availableRooms.stream().map(Room::getId).collect(Collectors.toSet());

//        if (bookingRequest.getRoomIds() == null || bookingRequest.getRoomIds().isEmpty()) {
//            throw new IllegalArgumentException("At least one room ID must be provided.");
//        }

        for (Long roomId : bookingRequest.getRoomIds()) {
            if (!availableRoomIds.contains(roomId)) {
                throw new RuntimeException("Room with id " + roomId + " is not available for the selected dates");
            }
        }

        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setCheckinDate(bookingRequest.getCheckinDate());
        booking.setCheckoutDate(bookingRequest.getCheckoutDate());

        Set<Room> rooms = bookingRequest.getRoomIds()
                .stream()
                .map(roomId -> roomRepository
                        .findById(roomId)
                        .orElseThrow(() -> new RuntimeException("Room Not Found")))
                .collect(Collectors.toSet());
        booking.setRooms(rooms);

        return bookingRepository.save(booking);
    }
}
