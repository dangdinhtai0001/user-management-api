package com.phoenix.base.repository2.querydsl;

import com.phoenix.base.model.querydsl.QFwException;
import com.phoenix.core.model.query.SearchCriteria;
import com.phoenix.core.model.query.SearchOperation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@SpringBootTest
@ActiveProfiles(value = "dev")
public class AbstractCrudQueryDslRepositoryTest {
    @Autowired
    private AbstractCrudQueryDslRepositoryImp repository;

    @Test
    @Transactional
    public void testFindAll() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        String[] columns = {"id", "code_"};
        Class<?>[] types = {String.class, String.class};

        List<Map<String, Object>> result =
                repository.findByCondition(QFwException.class, columns, types);

        System.out.println(result);

    }

    @Test
    @Transactional
    public void testFindWithCondition() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        String[] columns = {"id", "code_"};
        Class<?>[] types = {String.class, String.class};
        List<SearchCriteria> searchCriteriaList = List.of(
                new SearchCriteria("id", SearchOperation.IN, 1, 4, 7)
        );

        List<Map<String, Object>> result =
                repository.findByCondition(QFwException.class, columns, types, searchCriteriaList);

        System.out.println(result);

    }
}
