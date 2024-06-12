package com.codegym.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String fullName;
    private String username;
    private String email;
    private String password;
    private String address;
    private String phone;
    private String avatar;
    private Boolean activated;
    private String rememberToken;

}
