package com.phoenix.base.repository2.querydsl;

import com.google.gson.Gson;
import com.phoenix.base.model.querydsl.QFwException;
import com.phoenix.core.model.query.SearchCriteria;
import com.phoenix.core.model.query.SearchOperation;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@SpringBootTest
@ActiveProfiles(value = "dev")
public class AbstractCoreQueryDslRepositoryTest {

    @Autowired
    private AbstractCoreQueryDslRepositoryImp repository;

    @Test
    @Transactional
    public void testSelect() throws IllegalAccessException {
        String[] columnNames = {"id", "code_", "message_", "httpCode"};
        Class<?>[] columnTypes = {Long.class, String.class, String.class, Integer.class};

        List<Path<QFwException>> expression = repository.getPath(QFwException.class, columnNames);

        List<Tuple> list = repository.getDefaultSQLQueryFactory()
                .select(expression.toArray(new Expression[0]))
                .from(repository.getQuerySource(QFwException.class, "fw_exception"))
                .fetch();

        List<Map<String, Object>> results = repository.convertListTuple2ListMap(list, columnNames, columnTypes);

        Gson gson = new Gson();
        System.out.println(gson.toJson(results));
    }

    @Test
    @Transactional
    public void testSelectWithCondition() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        String[] columnNames = {"id", "code_", "message_", "httpCode"};
        Class<?>[] columnTypes = {Long.class, String.class, String.class, Integer.class};
        List<SearchCriteria> searchCriteriaList = List.of(
//                new SearchCriteria("id", SearchOperation.GREATER_THAN, 1L),
//                new SearchCriteria("id", SearchOperation.LESS_THAN, "4")
                new SearchCriteria("id", SearchOperation.BETWEEN, 2, "4")
        );

        //---------------------------------------------------------

        List<Path<QFwException>> expression = repository.getPath(QFwException.class, columnNames);
        BooleanExpression[] predicateArray = repository.getPredicate(searchCriteriaList, QFwException.class, null);

        List<Tuple> list = repository.getDefaultSQLQueryFactory()
                .select(expression.toArray(new Expression[0]))
                .from(repository.getQuerySource(QFwException.class, "fw_exception"))
                .where(predicateArray)
                .fetch();

        List<Map<String, Object>> results = repository.convertListTuple2ListMap(list, columnNames, columnTypes);

        Gson gson = new Gson();
        System.out.println(gson.toJson(results));

    }

    @Test
    public void testGetRelationalPathBaseField(){
        Field field = this.repository.getRelationalPathBaseField(QFwException.class);
        System.out.println(field.getName());
    }
}
