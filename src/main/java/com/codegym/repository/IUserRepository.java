package com.codegym.repository;

import com.codegym.model.Role;
import com.codegym.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

    List<Role> findRolesByUsername(String username);
}
