package com.codegym.service.user;

import com.codegym.model.User;

import java.util.List;

public interface IUserService {
    List<User> getUserList();

    User getUserById(Long id);

    void createUser(User user);
}
