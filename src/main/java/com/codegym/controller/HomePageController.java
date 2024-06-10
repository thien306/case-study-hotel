package com.codegym.controller;

import com.codegym.model.Room;
import com.codegym.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/homepage")
@CrossOrigin("*")
public class HomePageController {

    @Autowired
    private IRoomService roomService;

    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        return new ResponseEntity<>(roomService.getAllRoom(), HttpStatus.OK);
    }

    @GetMapping("/room/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(roomService.getRoomById(id), HttpStatus.OK);
    }

    @GetMapping("/available-room")
    public ResponseEntity<List<Room>> getAvailableRooms(
            @RequestParam("checkin") @DateTimeFormat(pattern = "yyy-MM-dd") Date checkinDate,
            @RequestParam("checkout")@DateTimeFormat(pattern = "yyy-MM-dd") Date checkoutDate) {
        List<Room> availableRooms = roomService.getAvailableRooms(checkinDate, checkoutDate);
        return new ResponseEntity<>(availableRooms, HttpStatus.OK);
    }
}
