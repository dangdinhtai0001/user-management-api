package com.phoenix.base.repository.imp;

import com.phoenix.base.constant.BeanIds;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles(value = "dev")
class DefaultUserDetailsRepositoryTest {
    @Autowired
    @Qualifier(BeanIds.BASE_USER_REPOSITORY_IMP)
    private DefaultUserDetailsRepository repository;

    @Test
    public void testIsExistsUsername() {
        System.out.println(repository.isExistsUsername("user_2"));
    }
}