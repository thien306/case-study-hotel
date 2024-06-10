package com.codegym.service.Interface;


import com.codegym.model.User;
import com.codegym.model.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<UserDto> getUsers();
    UserDto getUserById(Long userId);
    Iterable<UserDto> findAll();
    Optional<UserDto> findById(Long id);
    void save(User user);
    void remove(Long id);
    void save(UserDto userDto);
}
