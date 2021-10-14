package com.phoenix.base.model;


import com.phoenix.base.constant.BeanIds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component(BeanIds.AUTHENTICATION_PROVIDER)
@DependsOn(BeanIds.PASSWORD_ENCODER)
public class DefaultAuthenticationProvider implements AuthenticationProvider {
    //~ Instance fields ================================================================================================
    private UserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder;
    private Map<String, PasswordEncoder> passwordEncoderMap;

    //~  Autowired ========================================================================================================
    @Autowired
    public void setUserDetailsService(@Qualifier(BeanIds.DEFAULT_USER_DETAIL_SERVICES) UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void setPasswordEncoder(@Qualifier(BeanIds.PASSWORD_ENCODER) PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setPasswordEncoderMap(@Qualifier(BeanIds.PASSWORD_ENCODER_MAP) Map<String, PasswordEncoder> passwordEncoderMap) {
        this.passwordEncoderMap = passwordEncoderMap;
    }

    //~ Methods ========================================================================================================
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String rawPassword = authentication.getCredentials().toString();

        DefaultUserDetails userDetails = (DefaultUserDetails) userDetailsService.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException("External system authentication failed");
        }

        String encoderId = "{" + userDetails.getHashAlgorithm() + "}";

        PasswordEncoder passwordEncoder_ = passwordEncoderMap.get(userDetails.getHashAlgorithm());
        String encodedPassword = encoderId + passwordEncoder_.encode(rawPassword);

        if (userDetails.getUsername().equals(username) &&
                passwordEncoder.matches(rawPassword, encodedPassword)
        ) {
            return new UsernamePasswordAuthenticationToken(username, rawPassword, userDetails.getAuthorities());
        } else {
            throw new BadCredentialsException("External system authentication failed");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
