//package com.codegym.util;
//
//import com.codegym.model.Room;
//import org.springframework.validation.Errors;
//import java.math.BigDecimal;
//import java.util.regex.Pattern;
//
//public class Validation {
//
//    public static void checkRoom(Room room, Errors errors) {
//        if (room.getCode() == null || room.getCode().isEmpty()) {
//            errors.rejectValue("code", "code.empty", "Room code cannot be empty");
//        } else if (!Pattern.matches("^\\d{3,4}$", room.getCode())) {
//            errors.rejectValue("code", "code.invalidFormat", "Room code must be 3 or 4 digits");
//        }
//
//        if (room.getDescription() == null || room.getDescription().isEmpty()) {
//            errors.rejectValue("description", "description.empty", "Description cannot be empty");
//        } else if (room.getDescription().length() < 5) {
//            errors.rejectValue("description", "description.length", "Description must be at least 10 characters");
//        }
//
//        if (room.getImage() == null || room.getImage().isEmpty()) {
//            errors.rejectValue("image", "image.empty", "Image cannot be empty");
//        }
//
//        if (room.getPrice() == null || room.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
//            errors.rejectValue("price", "price.invalid", "Price must be greater than zero");
//        }
//
//        if (room.getType() == null) {
//            errors.rejectValue("type", "type.null", "Type cannot be null");
//        }
//    }
//}
