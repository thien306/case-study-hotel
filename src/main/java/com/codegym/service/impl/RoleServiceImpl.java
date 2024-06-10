package com.codegym.service.impl;

import com.codegym.model.Role;
import com.codegym.model.dto.RoleDto;
import com.codegym.repository.IRoleRepository;
import com.codegym.service.Interface.IRoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
@ComponentScan(basePackageClasses = ModelMapper.class)
public class RoleServiceImpl implements IRoleService {
    private final IRoleRepository IRoleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleServiceImpl(IRoleRepository IRoleRepository, ModelMapper modelMapper) {
        this.IRoleRepository = IRoleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Iterable<RoleDto> findAll() {
        Iterable<Role> entities = IRoleRepository.findAll();
        return StreamSupport.stream(entities.spliterator(), true)
                .map(entity -> modelMapper.map(entity, RoleDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RoleDto> findById(Long id) {
        Role entity = IRoleRepository.findById(id).orElse(null);
        return Optional.ofNullable(modelMapper.map(entity, RoleDto.class));
    }

    @Override
    public void save(RoleDto roleDto) {
        Role role = modelMapper.map(roleDto, Role.class);
        IRoleRepository.save(role);
    }

    @Override
    public void remove(Long id) {
        IRoleRepository.deleteById(id);
    }
}
