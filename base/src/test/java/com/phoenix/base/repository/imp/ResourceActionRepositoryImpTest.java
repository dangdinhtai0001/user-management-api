package com.phoenix.base.repository.imp;

import com.phoenix.base.model.ResourceActionModel;
import com.phoenix.core.model.query.SearchCriteria;
import com.phoenix.core.model.query.SearchOperation;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.LinkedList;
import java.util.List;

@SpringBootTest
@ActiveProfiles(value = "dev")
class ResourceActionRepositoryImpTest {

    @Autowired
    private ResourceActionRepositoryImp repository;


    @Test
    public void testFindAll() {
        List<Object> listClassName =  new LinkedList<>();// Arrays.asList(new String[]{"com.phoenix.base.service.imp.CommonServiceImpl"});
        List<Object> allMethodsNamesList = new LinkedList<>(); // Arrays.asList(new String[]{"ping"});

        listClassName.add("com.phoenix.base.service.imp.CommonServiceImpl");
        allMethodsNamesList.add("ping");

        List<SearchCriteria> criteriaList = new LinkedList<>();
        criteriaList.add(new SearchCriteria("resource", SearchOperation.IN, listClassName));
        criteriaList.add(new SearchCriteria("action", SearchOperation.IN, allMethodsNamesList));

        String[] fields = {"id", "action", "resource", "beanName", "displayAction", "displayResource", "description"};
        List<ResourceActionModel> exitsResourceAction = repository.findAll(criteriaList, fields);

        System.out.println(exitsResourceAction);

    }

}