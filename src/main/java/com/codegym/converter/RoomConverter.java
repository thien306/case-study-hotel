package com.codegym.converter;

import com.codegym.model.Room;
import com.codegym.model.dto.RoomRequestDto;

public interface RoomConverter {
    Room dtoToEntity(RoomRequestDto roomRequestDto);
}
