package com.phoenix.base.controller.imp;

import com.phoenix.base.constant.BeanIds;
import com.phoenix.base.controller.AuthenticationController;
import com.phoenix.base.service.AuthenticationService;
import com.phoenix.core.controller.AbstractCoreController;
import com.phoenix.core.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController("AuthenticationController")
@RequestMapping("/auth")
public class AuthenticationControllerImp extends AbstractCoreController implements AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationControllerImp(
            @Qualifier(BeanIds.AUTHENTICATION_SERVICES) AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, Object> loginRequest, HttpSession session) throws ApplicationException {
        return sendResponse(authenticationService.login(loginRequest, session));
    }

    @Override
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody Map<String, Object> refreshTokenRequest, HttpSession session) throws ApplicationException {
        return sendResponse(authenticationService.refreshToken(refreshTokenRequest, session));
    }

    @Override
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        return sendResponse(authenticationService.logout(request, response, session));
    }
}
