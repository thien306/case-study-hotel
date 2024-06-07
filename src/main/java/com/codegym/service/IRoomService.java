package com.codegym.service;


import com.codegym.model.Customer;
import com.codegym.model.Room;
import com.codegym.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


public interface IRoomService {
    Page<Room> findAll(Pageable pageable);

    Page<Room> findAllByTypeNameContaining(String typeName, Pageable pageable);

    Page<Room> findAllByStatusContaining(boolean status, Pageable pageable);

    Page<Room> findAllByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);

    Iterable<Room> findAll();

    Optional<Room> findById(Long id);

    Room save(Room room);

    void remove(Long id);

    Iterable<Room> findByCodeContaining(String code);

}
