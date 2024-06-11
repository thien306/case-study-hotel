package com.codegym.service.impl;

import com.codegym.converter.RoomConverter;
import com.codegym.model.Booking;
import com.codegym.model.Room;
import com.codegym.model.dto.ResponsePage;
import com.codegym.model.dto.RoomRequestDto;
import com.codegym.repository.IBookingRepository;
import com.codegym.repository.IRoomRepository;
import com.codegym.repository.ITypeRepository;
import com.codegym.service.Interface.IRoomService;
import com.codegym.util.Validation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import com.codegym.model.Type;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements IRoomService {
    private final static Logger LOGGER = LoggerFactory.getLogger(RoomServiceImpl.class);
    private final RoomConverter roomConverter;
    private final ITypeRepository typeRepository;
    @Autowired
    IBookingRepository bookingRepository;

    @Autowired
    private IRoomRepository roomRepository;

    @Override
    public Page<Room> findAll(Pageable pageable) {
        return roomRepository.findAll(pageable);
    }

    @Override
    public Page<Room> findAllBySearch(String search, Pageable pageable) {
        return roomRepository.findAllBySearch(search, pageable);
    }

    @Override
    public Page<Room> findAllByStatusContaining(Boolean status, Pageable pageable) {
        return null;
    }

    @Override
    public Page<Room> findAllByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable) {
        return roomRepository.findAllByPriceBetween(minPrice, maxPrice, pageable);
    }

    @Override
    public Page<Room> findAllByTypeNameContaining(String typeName, Pageable pageable) {
        return roomRepository.findAllByTypeNameContaining(typeName, pageable);
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
    public ResponsePage save(RoomRequestDto roomRequestDto) {
        LOGGER.info("RoomServiceImpl -> save invoked!!!");
        Room room = roomConverter.dtoToEntity(roomRequestDto);
        try {
            Optional<Type> type = typeRepository.findById(roomRequestDto.getType());
            if (type.isEmpty()){
                throw new Exception("type not id");
            }
            room.setType(type.get());
            roomRepository.save(room);
            return ResponsePage.builder()
                    .data(null)
                    .message("create room success")
                    .status(HttpStatus.OK)
                    .build();
        }catch (Exception exception){
            return ResponsePage.builder()
                    .data(null)
                    .message(exception.getMessage())
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    @Override
    public void remove(Long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public Iterable<Room> findByCodeContaining(String code) {
        return roomRepository.findByCodeContaining(code);
    }

    @Override
    public Iterable<Room> findByDate(Date checkin, Date checkout, String type) {
        return null;
    }


    @Override
    public Iterable<Room> findByType(Type type) {
        Iterable<Room> roomList = roomRepository.findByType(type);
        return null;
    }

    @Override
    public List<Room> getAllRoomBooked(Booking booking) {
        return null;
    }

//    @Override
//    public List<Room> getAllRoomBooked(Booking booking) {
//        LocalDate checkinDate = booking.getCheckinDate();
//        List<Room> roomBookedList = bookingRepository.findAllByCheckinDate(checkinDate);
//        return roomBookedList;
//    }

}
