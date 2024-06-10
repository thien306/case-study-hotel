package com.codegym.service.impl;

import com.codegym.model.Type;
import com.codegym.repository.ITypeRepository;
import com.codegym.service.Interface.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.Optional;

@Service
public class TypeServiceImpl implements ITypeService {

    @Autowired
    private ITypeRepository typeRepository;

    @Override
    public Iterable<Type> findAll() {
        return typeRepository.findAll();
    }

    @Override
    public Type save(Type type) {
        Errors errors = new BeanPropertyBindingResult(type, "type");

        if (errors.hasErrors()) {
            throw new IllegalArgumentException(errors.getAllErrors().toString());
        }
        return typeRepository.save(type);
    }

    @Override
    public Optional<Type> findById(Long id) {
        return typeRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        typeRepository.deleteById(id);
    }
}
