package com.phoenix.base.repository2.querydsl;

import com.google.gson.Gson;
import com.phoenix.base.model.querydsl.QFwException;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
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
        Class[] columnTypes = {Long.class, String.class, String.class, Integer.class};

        List<Expression<QFwException>> expression = repository.getExpressions(QFwException.class,
                "fwException", columnNames);

        List<Tuple> list = repository.getDefaultSQLQueryFactory()
                .select(expression.toArray(new Expression[0]))
                .from(repository.getTable(QFwException.class, "fw_exception"))
                .fetch();

        List<Map<String, Object>> results = repository.convertListTuple2ListMap(list, columnNames, columnTypes);

        Gson gson = new Gson();
        System.out.println(gson.toJson(results));
    }
}
