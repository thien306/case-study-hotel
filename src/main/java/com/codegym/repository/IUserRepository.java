package com.codegym.repository;

import com.codegym.model.Role;
import com.codegym.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface IUserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

    List<Role> findRolesByUsername(String username);
}
