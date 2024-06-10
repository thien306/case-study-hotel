package com.codegym.service.impl;

import com.codegym.model.Booking;
import com.codegym.model.Room;
import com.codegym.repository.IBookingRepository;
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
    @Override
    public boolean isBookingValid(Booking booking) {
        Room room = booking.getRoom();
        LocalDate checkinDate = booking.getCheckinDate();
        LocalDate checkoutDate = booking.getCheckoutDate();
        List<Booking> bookingList = bookingRepository.findAllByRoomId(booking.getId());
        List <Booking> roomsNotAvailable = new ArrayList<>();
        for(Booking booking1 : bookingList){
            if(booking1.isStatus()){
                LocalDate checkinDate1 = booking1.getCheckinDate();
                LocalDate checkoutDate1 = booking1.getCheckoutDate();
                if(checkinDate1.equals(checkinDate) || checkoutDate1.equals(checkoutDate) || checkinDate1.isBefore(checkinDate) && checkoutDate1.isAfter(checkinDate) || checkinDate1.isBefore(checkoutDate) && checkoutDate1.isAfter(checkoutDate) || checkinDate1.isAfter(checkinDate) && checkoutDate1.isBefore(checkoutDate) || checkinDate1.isBefore(checkinDate) && checkoutDate1.isAfter(checkoutDate)){
                    roomsNotAvailable.add(booking1);
                }
            }
        }

        return booking.getRoomQuantity() <= room.getQuantity() - roomsNotAvailable.size();

    }
}
