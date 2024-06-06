package com.codegym.controller;

import com.codegym.model.Room;
import com.codegym.service.room.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin("*")
public class RoomController {

    @Autowired
    private IRoomService roomService;

    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        System.out.println("ab");
        return new ResponseEntity<>(roomService.getAllRoom(), HttpStatus.OK);
    }


}
