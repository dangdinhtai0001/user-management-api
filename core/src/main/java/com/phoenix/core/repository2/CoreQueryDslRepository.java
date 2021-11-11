package com.phoenix.core.repository2;

import com.phoenix.core.model.query.SearchCriteria;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.sql.RelationalPathBase;
import com.querydsl.sql.SQLQueryFactory;

import java.util.List;
import java.util.Map;

public interface CoreQueryDslRepository {
    SQLQueryFactory getDefaultSQLQueryFactory();

    <T extends RelationalPathBase<T>> T getQuerySource(Class<T> type, String source);

    <T> BooleanExpression[] getPredicate(List<SearchCriteria> searchCriteriaList, Class<T> pathBuilderType, String alias);

    <T> BooleanExpression getPredicate(SearchCriteria searchCriteria, Class<T> pathBuilderType, String alias);

    List<Map<String, Object>> convertListTuple2ListMap(List<Tuple> tuples, String[] columnNames, Class<?>[] columnTypes);

    Map<String, Object> convertTuple2Map(Tuple tuple, String[] columnNames, Class<?>[] columnTypes);

    Map<String, Object> convertTuple2Map(Tuple tuple, String[] columnNames);
}
