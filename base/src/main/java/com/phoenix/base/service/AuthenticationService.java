package com.phoenix.base.service;

import com.phoenix.core.exception.ApplicationException;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

public interface AuthenticationService {
    Map<String, Object> login(Map<String, Object> loginRequest, HttpSession session) throws ApplicationException;

    Map<String, Object> logout(HttpServletRequest request, HttpServletResponse response, HttpSession session);

    Map<String, Object> refreshToken(Map<String, Object> refreshTokenRequest, HttpSession session) throws ApplicationException;
}
