package com.codegym.repository;

import com.codegym.model.Room;
import com.codegym.model.Type;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface IRoomRepository extends PagingAndSortingRepository<Room, Long> {

    Iterable<Room> findAllByTypeContaining(Type type);

    Iterable<Room> findAllByStatusContaining(boolean status);

    Iterable<Room> findAllByPriceContaining(BigDecimal price);

    Page<Room> findAllByTypeContaining(Type type, Pageable pageable);

    Page<Room> findAllByStatusContaining(boolean status, Pageable pageable);

    Page<Room> findAllByPriceContaining(BigDecimal price, Pageable pageable);

    Iterable<Room> findAll();

    void save(Room room);

    Optional<Room> findById(Long id);

    void deleteById(java.lang.Long id);
}
