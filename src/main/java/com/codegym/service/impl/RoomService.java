package com.codegym.service.impl;

import com.codegym.model.Room;
import com.codegym.model.Type;
import com.codegym.repository.IRoomRepository;
import com.codegym.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    public Iterable<Room> findAllByTypeContaining(Type type) {
        return roomRepository.findAllByTypeContaining(type);
    }

    @Override
    public Iterable<Room> findAllByStatusContaining(boolean status) {
        return roomRepository.findAllByStatusContaining(status);
    }

    @Override
    public Iterable<Room> findAllByPriceContaining(BigDecimal price) {
        return roomRepository.findAllByPriceContaining(price);
    }

    @Override
    public Page<Room> findAllByTypeContaining(Type type, Pageable pageable) {
        return roomRepository.findAllByTypeContaining(type, pageable);
    }

    @Override
    public Page<Room> findAllByStatusContaining(boolean status, Pageable pageable) {
        return roomRepository.findAllByStatusContaining(status, pageable);
    }

    @Override
    public Page<Room> findAllByPriceContaining(BigDecimal price, Pageable pageable) {
        return roomRepository.findAllByPriceContaining(price, pageable);
    }

    @Override
    public Iterable<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public void save(Room room) {
        roomRepository.save(room);
    }

    @Override
    public Optional<Room> findById(Long id) {
        return roomRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        roomRepository.deleteById(id);
    }
}
