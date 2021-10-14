package com.phoenix.base.config;


import com.google.common.collect.Multimap;
import com.phoenix.base.constant.ApplicationConstant;
import com.phoenix.base.constant.BeanIds;
import com.phoenix.base.entry_point.DefaultAccessDeniedEntryPoint;
import com.phoenix.base.entry_point.JwtAuthenticationEntryPoint;
import com.phoenix.base.filter.JwtAuthenticationFilter;
import com.phoenix.base.model.RawPasswordEncoder;
import com.phoenix.common.auth.JwtProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.*;

/**
 * Application security config
 */
@Configuration
@EnableWebSecurity()
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final JwtProvider tokenProvider;
    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final DefaultAccessDeniedEntryPoint defaultAccessDeniedEntryPoint;
    private final Multimap<String, String> applicationParameters;


    public SecurityConfiguration(
            @Qualifier(BeanIds.JWT_PROVIDER) JwtProvider tokenProvider,
            @Qualifier(BeanIds.DEFAULT_USER_DETAIL_SERVICES) UserDetailsService userDetailsService,
            @Qualifier(BeanIds.JWT_AUTHENTICATION_ENTRY_POINT) JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
            @Qualifier(BeanIds.DEFAULT_ACCESS_DENIED_ENTRY_POINT) DefaultAccessDeniedEntryPoint defaultAccessDeniedEntryPoint,
            @Qualifier(BeanIds.APPLICATION_PARAMETER) Multimap<String, String> applicationParameters
    ) {
        this.tokenProvider = tokenProvider;
        this.userDetailsService = userDetailsService;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.defaultAccessDeniedEntryPoint = defaultAccessDeniedEntryPoint;
        this.applicationParameters = applicationParameters;
    }

    @Bean(name = BeanIds.JWT_AUTHENTICATION_FILTER)
    @DependsOn(BeanIds.APPLICATION_PARAMETER)
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        String[] publicUrlsMatcher = applicationParameters.get(ApplicationConstant.PARAM_KEY_PUBLIC_URLS_MATCHER).toArray(new String[]{});

        return new JwtAuthenticationFilter(tokenProvider, userDetailsService, publicUrlsMatcher);
    }

    @Bean(name = BeanIds.DEFAULT_AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean(name = BeanIds.PASSWORD_ENCODER)
    public PasswordEncoder delegatingPasswordEncoder() {
        String idForEncode = ApplicationConstant.PASSWORD_ENCODER_BCRYPT_ID;
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put(ApplicationConstant.PASSWORD_ENCODER_BCRYPT_ID, new BCryptPasswordEncoder());
        encoders.put(ApplicationConstant.PASSWORD_ENCODER_PBKDF2_ID, new Pbkdf2PasswordEncoder());
        encoders.put(ApplicationConstant.PASSWORD_ENCODER_SCRYPT_ID, new SCryptPasswordEncoder());
        encoders.put(ApplicationConstant.PASSWORD_ENCODER_RAW_ID, new RawPasswordEncoder());

        return new DelegatingPasswordEncoder(idForEncode, encoders);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] publicUrlsMatcher = applicationParameters.get(ApplicationConstant.PARAM_KEY_PUBLIC_URLS_MATCHER).toArray(new String[]{});

        http.cors().and().csrf().disable();

        http
                .authorizeRequests()
//                .antMatchers(ApplicationConstant.PUBLIC_URLS_MATCHER).permitAll()
                .antMatchers(publicUrlsMatcher).permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .exceptionHandling().accessDeniedHandler(defaultAccessDeniedEntryPoint)
                .and()
                .sessionManagement((sessionManagement) -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                        .maximumSessions(2)
                );

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    // Fix the CORS errors
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // Don't do this in production, use a proper list  of allowed origins
        config.setAllowedOrigins(Collections.singletonList("http://127.0.0.1:3000"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
