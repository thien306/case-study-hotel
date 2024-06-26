package com.codegym.repository;

import com.codegym.model.Room;
import com.codegym.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IRoomRepository extends PagingAndSortingRepository<Room, Long> {

    Page<Room> findAllByTypeNameContaining(String typeName, Pageable pageable);

    Page<Room> findAllByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);

    Iterable<Room> findByCodeContaining(String code);

    Page<Room> findAllByOrderByPriceAsc(Pageable pageable);

    Page<Room> findAllByOrderByPriceDesc(Pageable pageable);

    Optional<Room> findById(Long id);

    @Query("SELECT r FROM Room r WHERE r.code LIKE %:search% OR r.description LIKE %:search%")
    Page<Room> findAllBySearch(@Param("search") String search, Pageable pageable);

    Room save(Room room);

    void deleteById(Long id);

    Iterable<Room> findAll();

    Iterable<Room> findByType(Type type);

    List<Room> findAllByType(Type type);



}
