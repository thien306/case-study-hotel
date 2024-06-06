package com.codegym.service.room;

import com.codegym.model.Room;

import java.util.List;

public interface IRoomService {
    List<Room> getAllRoom();

    Room getRoomById(Long id);

    void saveRoom(Room room);

    void updateRoom(Long id);

    void deleteRoom(Room room);
}
