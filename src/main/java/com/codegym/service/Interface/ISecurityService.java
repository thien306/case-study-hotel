package com.codegym.service.Interface;

public interface ISecurityService {
    boolean isAuthenticated();
    boolean isValidToken(String token);
}
