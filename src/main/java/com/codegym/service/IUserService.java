package com.codegym.service;


import com.codegym.model.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<UserDto> getUsers();
    UserDto getUserById(Long userId);
    Iterable<UserDto> findAll();
    Optional<UserDto> findById(Long id);
    void save(UserDto userDto);
    void remove(Long id);
}
