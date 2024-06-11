package com.codegym.service.impl;

import com.codegym.model.Booking;
import com.codegym.model.Room;
import com.codegym.model.Type;
import com.codegym.repository.IBookingRepository;
import com.codegym.repository.IRoomRepository;
import com.codegym.service.Interface.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceImpl implements IBookingService {
    @Autowired
    IBookingRepository bookingRepository;
    @Autowired
    IRoomRepository roomRepository;

    @Override
    public List<Booking> findAllByCheckinDate(LocalDate checkinDate) {
        List<Booking> bookingList = bookingRepository.findAllByCheckinDate(checkinDate);
        return bookingList;
    }


    @Override
    public Boolean isAvaiable(Booking booking) {
        int bookedQuantity = 0;
        Long id = booking.getRoom().getId();
        Room bookingroom = roomRepository.findById(id).get();

        //Booking Information
        LocalDate bookingCheckinDate = booking.getCheckinDate();
        LocalDate bookingCheckoutDate = booking.getCheckoutDate();
        int bookingRoomQuantity = booking.getRoomQuantity();
        Type bookingType = booking.getRoom().getType();


        //Room Information
        int roomQuantity = bookingroom.getQuantity();

        //Booked Information
        List<Booking> bookedList = bookingRepository.findAll();
        List<Booking> BookedListWithFilterType = new ArrayList<>();

        List<Booking> roomsNotAvaiable = new ArrayList<>();

        for (Booking booked : bookedList) {
            if (booked.getRoom().getType().equals(bookingroom.getType())) {
                BookedListWithFilterType.add(booked);
                LocalDate bookedCheckinDate = booked.getCheckinDate();
                LocalDate bookedCheckoutDate = booked.getCheckoutDate();
                if (bookedCheckinDate.equals(bookingCheckinDate) || bookedCheckoutDate.equals(bookingCheckoutDate) || bookedCheckinDate.isBefore(bookingCheckinDate) && bookedCheckoutDate.isAfter(bookingCheckinDate) || bookedCheckinDate.isBefore(bookingCheckoutDate) && bookedCheckoutDate.isAfter(bookingCheckoutDate) || bookedCheckinDate.isAfter(bookingCheckinDate) && bookedCheckoutDate.isBefore(bookingCheckoutDate) || bookedCheckinDate.isBefore(bookingCheckinDate) && bookedCheckoutDate.isAfter(bookingCheckoutDate)) {
                    roomsNotAvaiable.add(booked);
                }
            }

        }
        for (Booking booking2 : roomsNotAvaiable) {
            bookedQuantity += booking2.getRoomQuantity();
        }
        return (roomQuantity - bookedQuantity) >= bookingRoomQuantity;
    }

    @Override
    public void doBooking(Booking booking) {
        if (isAvaiable(booking)) {
            bookingRepository.save(booking);
        } else {
            System.out.println("Out of room");
        }
    }


}


