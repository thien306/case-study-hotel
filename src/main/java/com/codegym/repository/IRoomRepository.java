package com.codegym.repository;

import com.codegym.model.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoomRepository extends JpaRepository<Room, Long> {

//    Page<Room> findAllByTypeNameContaining(String typeName, Pageable pageable);

    Page<Room> findAllByStatusContaining(boolean status, Pageable pageable);

    Page<Room> findAllByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);

    Iterable<Room> findByCodeContaining(String code);

    Optional<Room> findById(Long id);

    @Query("SELECT r FROM Room r WHERE r.code LIKE %:search% OR r.description LIKE %:search%")
    Page<Room> findAllBySearch(@Param("search") String search, Pageable pageable);

    Room save(Room room);

    void deleteById(Long id);

    List<Room> findAll();

    @Query("select r from Room r where r.id not in " +
            "(select rm.id from Room rm join rm.bookings b " +
            "where b.checkinDate <= :checkoutDate and b.checkoutDate >= :checkinDate)")
    List<Room> findAvailableRooms(@Param("checkinDate") Date checkinDate, @Param("checkoutDate") Date checkoutDate);
}
