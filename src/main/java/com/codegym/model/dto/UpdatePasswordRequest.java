package com.codegym.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdatePasswordRequest {
    private String username;
    private String newPassword;

    public UpdatePasswordRequest() {
    }

    public UpdatePasswordRequest(String username, String newPassword) {
        this.username = username;
        this.newPassword = newPassword;
    }
}
