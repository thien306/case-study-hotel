//package com.codegym.model;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.validation.Errors;
//import org.springframework.validation.Validator;
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class Type implements Validator {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false, unique = true)
//    private String name;
//
//
//    @Override
//    public boolean supports(Class<?> clazz) {
//        return Type.class.isAssignableFrom(clazz);
//    }
//
//    @Override
//    public void validate(Object target, Errors errors) {
//        Type type = (Type) target;
//
//        if (type.getName() == null || type.getName().isEmpty()) {
//            errors.rejectValue("name", "name.empty");
//        } else if (type.getName().length() < 10) {
//            errors.rejectValue("name", "name.length");
//        } else if (!type.getName().matches("^[a-zA-Z\\s]+$")) {
//            errors.rejectValue("name", "name.invalidFormat");
//        }
//    }
//}
