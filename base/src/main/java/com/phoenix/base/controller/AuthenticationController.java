package com.phoenix.base.controller;

import com.phoenix.core.exception.ApplicationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public interface AuthenticationController {

    ResponseEntity<?> login(Map<String, Object> loginRequest, HttpSession session) throws ApplicationException;

    ResponseEntity<?> refreshToken(Map<String, Object> refreshTokenRequest, HttpSession session) throws ApplicationException;

    ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response, HttpSession session);
}

