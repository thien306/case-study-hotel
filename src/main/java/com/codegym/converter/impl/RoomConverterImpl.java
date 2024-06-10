package com.codegym.converter.impl;

import com.codegym.converter.RoomConverter;
import com.codegym.model.Room;
import com.codegym.model.dto.RoomRequestDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoomConverterImpl implements RoomConverter {
    private final static Logger LOGGER = LoggerFactory.getLogger(RoomConverterImpl.class);
    @Override
    public Room dtoToEntity(RoomRequestDto roomRequestDto) {
        LOGGER.debug("RoomConverterImpl -> dtoToEntity");
        Room room = new Room();
        BeanUtils.copyProperties(roomRequestDto,room);
        return room;
    }
}
