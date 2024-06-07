package com.codegym.service;

public interface ISecurityService {
    boolean isAuthenticated();
    boolean isValidToken(String token);
}
