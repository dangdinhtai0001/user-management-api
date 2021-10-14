package com.phoenix.base.model;


import com.phoenix.base.constant.BeanIds;
import com.phoenix.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DefaultAuthenticationProvider implements AuthenticationProvider {
    //~ Instance fields ================================================================================================
    private UserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder;

    //~ Getter/ setter ========================================================================================================
    @Autowired
    public void setUserDetailsService(@Qualifier(BeanIds.DEFAULT_USER_DETAIL_SERVICES) UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void setPasswordEncoder(@Qualifier(BeanIds.PASSWORD_ENCODER) PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    //~ Methods ========================================================================================================
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        DefaultUserDetails userDetails = (DefaultUserDetails) userDetailsService.loadUserByUsername(username);
        if (userDetails == null) {
            throw new BadCredentialsException("External system authentication failed");
        }
        String encodedPassword = passwordEncoder.encode(password);

        if (userDetails.getUsername().equals(username) && "pass".equals(password)) {
            return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
        } else {
            throw new BadCredentialsException("External system authentication failed");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

}
