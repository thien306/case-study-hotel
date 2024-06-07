package com.codegym.repository;

import com.codegym.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface IRoomRepository extends JpaRepository<Room, Long> {

    Page<Room> findAllByTypeNameContaining(String typeName, Pageable pageable);

    Page<Room> findAllByStatusContaining(boolean status, Pageable pageable);

    Page<Room> findAllByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);

    Iterable<Room> findByCodeContaining(String code);

}

