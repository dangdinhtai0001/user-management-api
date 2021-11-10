package com.phoenix.base.repository2.querydsl;

import com.phoenix.base.model.querydsl.QFwException;
import com.phoenix.base.model.querydsl.QFwParameter;
import com.phoenix.core.model.query.SearchCriteria;
import com.phoenix.core.model.query.SearchOperation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
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

    @Test
    @Transactional
    public void testFindWithConditionAndDefaultTypes() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        String[] columns = {"id", "code_"};
        Class<?>[] types = {String.class, String.class};
        List<SearchCriteria> searchCriteriaList = List.of(
                new SearchCriteria("id", SearchOperation.IN, 1, 4, 7)
        );

        List<Map<String, Object>> result =
                repository.findByCondition(columns, types, searchCriteriaList);

        System.out.println(result);
    }

    @Test
    @Transactional
    public void testInsert() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        String[] columnNames = {"key_", "value_"};
        Object[] values = {"key1", "value1"};

        long result = this.repository.create(QFwParameter.class, columnNames, values);

        System.out.println(result);
    }

    @Test
    @Transactional
    public void testBatchInsert() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        String[] columnNames = {"key_", "value_"};

        Object[] value0 = {"key0", "value0"};
        Object[] value1 = {"key1", "value1"};
        Object[] value2 = {"key2", "value2"};

        List<Object[]> values = List.of(value0, value1, value2);

        QFwParameter qFwParameter = QFwParameter.fwParameter;
        long result = this.repository.create(QFwParameter.class, columnNames, values);

        System.out.println(result);
    }

    @Test
    @Transactional
    public void testUpdate() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        String[] columnNames = {"key_", "value_"};
        Object[] values = {"key1", "value1"};

        long result = this.repository.update(QFwParameter.class, columnNames, values, null);

        System.out.println(result);

        System.out.println(this.repository.findByCondition(QFwParameter.class, columnNames, null));
    }
}
