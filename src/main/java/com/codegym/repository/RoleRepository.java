package com.codegym.repository;

import com.codegym.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
