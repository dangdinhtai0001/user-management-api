package com.phoenix.core.repository2;

import com.phoenix.common.reflection.FieldUtils;
import com.phoenix.common.reflection.MethodUtils;
import com.phoenix.core.model.query.SearchCriteria;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.sql.RelationalPathBase;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public abstract class AbstractCrudQueryDslRepository extends AbstractCoreQueryDslRepository {
    // region findByCondition

    public <T extends RelationalPathBase<T>> List<Map<String, Object>> findByCondition(
            Class<T> type, String[] columnNames, Class<?>[] columnTypes, List<SearchCriteria> searchCriteriaList
    ) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        // Lấy Path từ danh sách các cột cần tìm kiếm
        List<Path<T>> expressions = this.getPath(type, columnNames);

        // Lấy danh sách các biểu thức điều kiện từ searchCriteriaList
        BooleanExpression[] predicateArray;
        if (searchCriteriaList == null || searchCriteriaList.isEmpty()) {
            predicateArray = new BooleanExpression[0];
        } else {
            predicateArray = this.getPredicate(searchCriteriaList, type, null);
        }

        //Lấy source name từ class
        String source = this.getTableName(type);

        List<Tuple> tuples = this.getDefaultSQLQueryFactory()
                .select(expressions.toArray(new Path[0]))
                .from(this.getQuerySource(type, source))
                .where(predicateArray)
                .fetch();

        return this.convertListTuple2ListMap(tuples, columnNames, columnTypes);
    }

    public <T extends RelationalPathBase<T>> List<Map<String, Object>> findByCondition(
            Class<T> type, String[] columnNames, Class<?>[] columnTypes)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return this.findByCondition(type, columnNames, columnTypes, null);
    }

    // endregion

    protected <T extends RelationalPathBase<T>> String getTableName(Class<T> type)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Field field = this.getRelationalPathBaseField(type);
        //noinspection unchecked
        T obj = (T) FieldUtils.readStaticField(field);
        return String.valueOf(MethodUtils.invokeMethod(obj, "getTableName"));
    }

}
