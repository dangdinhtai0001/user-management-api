package com.phoenix.base.repository.imp;

import com.google.common.collect.Multimap;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(value = "dev")
class ParameterRepositoryImplTest {

    @Autowired
    private ParameterRepositoryImpl repository;

    @Test
    public void testFindAll(){
        Multimap<String, String> multimap =  repository.findAll();

        System.out.println(multimap.get("PUBLIC_URLS_MATCHER"));
    }

}