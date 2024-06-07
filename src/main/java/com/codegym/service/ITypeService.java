package com.codegym.service;

import com.codegym.model.Type;

import java.util.Optional;

public interface ITypeService {

    Iterable<Type> findAll();

    void save(Type type);

    Optional<Type> findById(Long id);

    void remove(Long id);
}
