package com.codegym.service;

import com.codegym.model.dto.RoleDto;


import java.util.Optional;

public interface RoleService {
    Iterable<RoleDto> findAll();
    Optional<RoleDto> findById(Long id);
    void save(RoleDto roleDto);
    void remove(Long id);
}
