package com.codegym.service;

import com.codegym.model.Booking;
import com.codegym.model.Room;
import com.codegym.repository.IRoomRepository;
import com.codegym.service.impl.BookingServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
public class RoomBookedServiceImpl {
    private final IRoomRepository roomRepository;
    private final BookingServiceImpl bookingService;

    public RoomBookedServiceImpl(IRoomRepository roomRepository, BookingServiceImpl bookingService) {
        this.roomRepository = roomRepository;
        this.bookingService = bookingService;
    }
    public List<Room> displayListRoom(Booking booking) {
        Iterable<Room> rooms = roomRepository.findAll();
        List<Room> availableRooms = new ArrayList<>();

        int personPerRoom =  booking.getRoomQuantity();
        LocalDate checkoutDate = booking.getCheckinDate().plusDays(booking.getNights());

        for (Room room : rooms) {
            if (isRoomAvailable(room, booking.getCheckinDate(), checkoutDate, booking.getRoomQuantity(), personPerRoom)) {
                availableRooms.add(room);
            }
        }

        return availableRooms;
    }

    private boolean isRoomAvailable(Room room,
                                    LocalDate checkinDate,
                                    LocalDate checkoutDate,
                                    int roomQuantity, int personPerRoom) {
        Booking booking = new Booking();
        booking.setCheckinDate(checkinDate);
        booking.setCheckoutDate(checkoutDate);
        booking.setRoomQuantity(roomQuantity);
        booking.setRoom(room);

        return bookingService.isBookingValid(booking);
    }
}
