package com.codegym.controller;

import com.codegym.model.Booking;
import com.codegym.model.Room;

import com.codegym.service.Interface.ITypeService;
import com.codegym.model.dto.ResponsePage;
import com.codegym.model.dto.RoomRequestDto;
import com.codegym.service.Interface.IRoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/rooms")
public class    RoomController {


    @Autowired
    private IRoomService roomService;

    @Autowired
    private ITypeService typeService;


    @GetMapping
    public ResponseEntity<Page<Room>> getAllRooms(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(required = false) Boolean status,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) String typeName,
            @PageableDefault(page = 0, size = 3) Pageable pageable) {

        Page<Room> roomPage;
        if (!search.isEmpty()) {
            roomPage = roomService.findAllBySearch(search, pageable);
        } else if (status != null) {
            roomPage = roomService.findAllByStatusContaining(status, pageable);
        } else if (minPrice != null && maxPrice != null) {
            roomPage = roomService.findAllByPriceBetween(minPrice, maxPrice, pageable);
        } else if (typeName != null && !typeName.isEmpty()) {
            roomPage = roomService.findAllByTypeNameContaining(typeName, pageable);
        } else {
            roomPage = roomService.findAll(pageable);
        }

        if (!roomPage.hasContent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(roomPage, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<ResponsePage> createRoom(@RequestBody RoomRequestDto roomRequestDto) {
        ResponsePage responsePage= roomService.save(roomRequestDto);
        return new ResponseEntity<>(responsePage, responsePage.getStatus());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id, RedirectAttributes attributes) {
        Optional<Room> roomOptional = roomService.findById(id);
        if (roomOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        roomService.remove(id);
        attributes.addFlashAttribute("message", "Room deleted successfully");

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@RequestBody Room room, @PathVariable Long id, RedirectAttributes attributes) {
        Optional<Room> optionalRoom = roomService.findById(id);
        if (optionalRoom.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        room.setId(id);
//        Room updatedRoom = roomService.save(room);
        attributes.addFlashAttribute("message", "Room updated successfully");

        return new ResponseEntity<>( HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<Iterable<Room>> searchRoom(@RequestParam("search") Optional<String> search) {
        Iterable<Room> rooms;
        if (search.isPresent()) {
            rooms = roomService.findByCodeContaining(search.get());
            if (!rooms.iterator().hasNext()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } else {
            rooms = roomService.findAll();
        }
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }



}