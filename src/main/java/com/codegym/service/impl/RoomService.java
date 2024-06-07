package com.codegym.service.impl;

import com.codegym.model.Room;
import com.codegym.repository.IRoomRepository;
import com.codegym.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService implements IRoomService {

    @Autowired
    private IRoomRepository roomRepository;

    @Override
    public Page<Room> findAll(Pageable pageable) {
        return roomRepository.findAll(pageable);
    }

    @Override
    public Page<Room> findAllByTypeNameContaining(String typeName, Pageable pageable) {
        return roomRepository.findAllByTypeNameContaining(typeName, pageable);
    }

    @Override
    public Page<Room> findAllByStatusContaining(boolean status, Pageable pageable) {
        return roomRepository.findAllByStatusContaining(status, pageable);
    }

    @Override
    public Page<Room> findAllByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable) {
        return roomRepository.findAllByPriceBetween(minPrice, maxPrice, pageable);
    }

    @Override
    public Iterable<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Optional<Room> findById(Long id) {
        return roomRepository.findById(id);
    }

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public void remove(Long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public Iterable<Room> findByCodeContaining(String code) {
        return roomRepository.findByCodeContaining(code);
    }


}

