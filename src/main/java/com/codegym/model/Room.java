package com.codegym.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import java.math.BigDecimal;
import java.util.regex.Pattern;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String description;

    private String image;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private Type type;

    @Override
    public boolean supports(Class<?> clazz) {
        return Room.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Room room = (Room) target;

        if (room.getCode() == null || room.getCode().isEmpty()) {
            errors.rejectValue("code", "code.empty");
        } else if (!Pattern.matches("^\\d{3,4}$", room.getCode())) {
            errors.rejectValue("code", "code.invalidFormat");
        }

        if (room.getDescription() == null || room.getDescription().isEmpty()) {
            errors.rejectValue("description", "description.empty");
        } else if (room.getDescription().length() < 10) {
            errors.rejectValue("description", "description.length");
        } else if (!type.getName().matches("^[a-zA-Z\\s]+$")) {
            errors.rejectValue("name", "name.invalidFormat");
        }

        if (room.getImage() == null || room.getImage().isEmpty()) {
            errors.rejectValue("image", "image.empty");
        }

        if (room.getPrice() == null || room.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            errors.rejectValue("price", "price.invalid");
        }

        if (room.getType() == null) {
            errors.rejectValue("type", "type.null");
        }
    }

}