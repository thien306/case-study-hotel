package com.codegym.service;

public interface SecurityService {
    boolean isAuthenticated();
    boolean isValidToken(String token);
}
