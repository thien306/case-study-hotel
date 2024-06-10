package com.codegym.formatter;//package com.codegym.formatter;
//
//import com.codegym.model.dto.RoleDto;
//import com.codegym.service.RoleService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.format.Formatter;
//import org.springframework.stereotype.Component;
//
//import java.text.ParseException;
//import java.util.Locale;
//import java.util.Optional;
//
//@Component
//public class RoleFormatter implements Formatter<RoleDto> {
//    @Autowired
//    private RoleService roleService;
//
//    @Autowired
//    public RoleFormatter(RoleService roleService) {
//        this.roleService = roleService;
//    }
//
//    @Override
//    public RoleDto parse(String text, Locale locale) throws ParseException {
//        Optional<RoleDto> roleDto = roleService.findById(Long.parseLong(text));
//        return roleDto.orElse(null);
//    }
//
//    @Override
//    public String print(RoleDto object, Locale locale) {
//        return "[" + object.getId() + ", "
//                + object.getName() + ", "
//                + object.getDescription() + "]";
//    }
//}
