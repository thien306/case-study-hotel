package com.codegym.service.room;

import com.codegym.model.Room;
import com.codegym.repository.IRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService implements IRoomService {

    @Autowired
    IRoomRepository roomRepo;

    @Override
    public List<Room> getAllRoom() {
        return roomRepo.findAll();
    }

    @Override
    public Room getRoomById(Long id) {
        return roomRepo.findById(id).orElse(null);
    }

    @Override
    public void saveRoom(Room room) {
        roomRepo.save(room);
    }

    @Override
    public void updateRoom(Long id) {
    }

    @Override
    public void deleteRoom(Room room) {
        roomRepo.delete(room);
    }
}
