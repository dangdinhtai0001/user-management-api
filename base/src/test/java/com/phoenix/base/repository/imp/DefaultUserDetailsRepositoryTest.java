package com.phoenix.base.repository.imp;

import com.phoenix.base.constant.BeanIds;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles(value = "dev")
class DefaultUserDetailsRepositoryTest {
    @Autowired
    @Qualifier(BeanIds.BASE_USER_REPOSITORY_IMP)
    private DefaultUserDetailsRepository repository;

    @Autowired
    @Qualifier(BeanIds.PASSWORD_ENCODER)
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Test
    public void testFindUserByUsername() throws IllegalAccessException {
        System.out.println(repository.findUserPrincipalByUsername("user_0"));
    }

    @Transactional
    @Test
    public void testFindGroupIdsByUsername() {
        System.out.println(repository.findGroupIdsByUsername("user_0"));
    }

    @Transactional
    @Test
    public void testUpdateRefreshTokenByUsername() {
        System.out.println(repository.updateRefreshTokenByUsername("refresh-token", "user_0"));
        System.out.println(repository.updateRefreshTokenByUsername("refresh-token", "abc123"));
    }

    @Transactional
    @Test
    public void testFindRefreshTokenByUsername() {
        System.out.println(repository.findRefreshTokenByUsername("user_0"));
        System.out.println(repository.findRefreshTokenByUsername("abc123"));
    }

    @Transactional
    @Test
    public void testCreateUser() {
//        System.out.println(repository.createUser("user_0", passwordEncoder.encode("123456")));
        Assertions.assertEquals(repository.createUser("user_000", passwordEncoder.encode("123456")), 1);
    }

    @Transactional
    @Test
    public void testIsExistsUsername(){
        System.out.println(repository.isExistsUsername("user_2"));
    }

}