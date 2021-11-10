package com.phoenix.core.repository2;

import com.phoenix.common.reflection.FieldUtils;
import com.phoenix.common.reflection.MethodUtils;
import com.phoenix.core.model.query.SearchCriteria;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.sql.RelationalPathBase;
import com.querydsl.sql.dml.SQLInsertClause;
import com.querydsl.sql.dml.SQLUpdateClause;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public abstract class AbstractCrudQueryDslRepository<E extends RelationalPathBase<E>> extends AbstractCoreQueryDslRepository {

    protected final Class<E> defaultType;

    protected AbstractCrudQueryDslRepository(Class<E> defaultType) {
        this.defaultType = defaultType;
    }

    // region create

    public <T extends RelationalPathBase<T>> long create(
            Class<T> type, String[] columnNames, Object[] value
    ) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        // Lấy Path từ danh sách các cột
        List<Path<T>> expressions = this.getPath(type, columnNames);

        return this.getDefaultSQLQueryFactory()
                .insert(this.getQuerySource(type, this.getTableName(type)))
                .columns(expressions.toArray(new Path[0]))
                .values(value)
                .execute();
    }

    public <T extends RelationalPathBase<T>> long create(
            Class<T> type, String[] columnNames, List<Object[]> values
    ) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        // Lấy Path từ danh sách các cột
        List<Path<T>> expressions = this.getPath(type, columnNames);

        SQLInsertClause insertClause = this.getDefaultSQLQueryFactory()
                .insert(this.getQuerySource(type, this.getTableName(type)));

        for (Object[] objects : values) {
            insertClause.columns(expressions.toArray(new Path[0])).values(objects).addBatch();
        }

        return insertClause.execute();

    }

    // endregion

    // region update

    public <T extends RelationalPathBase<T>> long update(
            Class<T> type, String[] columnNames, Object[] value, List<SearchCriteria> searchCriteriaList
    ) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        // Lấy Path từ danh sách các cột
        List<Path<T>> expressions = this.getPath(type, columnNames);

        // Lấy danh sách các biểu thức điều kiện từ searchCriteriaList
        BooleanExpression[] predicateArray = this.getPredicateFromSearchCriteria(searchCriteriaList, type);

        SQLUpdateClause sqlUpdateClause = this.getDefaultSQLQueryFactory()
                .update(this.getQuerySource(type, this.getTableName(type)))
                .where(predicateArray);

        for (int i = 0; i < columnNames.length; i++) {
            sqlUpdateClause.set(expressions.get(i), expressions.get(i));
        }

        return sqlUpdateClause.execute();
    }

    // endregion

    // region findByCondition

    public <T extends RelationalPathBase<T>> List<Map<String, Object>> findByCondition(
            Class<T> type, String[] columnNames, Class<?>[] columnTypes, List<SearchCriteria> searchCriteriaList
    ) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        // Lấy Path từ danh sách các cột cần tìm kiếm
        List<Path<T>> expressions = this.getPath(type, columnNames);

        // Lấy danh sách các biểu thức điều kiện từ searchCriteriaList
        BooleanExpression[] predicateArray = this.getPredicateFromSearchCriteria(searchCriteriaList, type);

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

    public List<Map<String, Object>> findByCondition(String[] columnNames, Class<?>[] columnTypes)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        return this.findByCondition(defaultType, columnNames, columnTypes, null);
    }

    public List<Map<String, Object>> findByCondition(String[] columnNames, Class<?>[] columnTypes,
                                                     List<SearchCriteria> searchCriteriaList
    ) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        return this.findByCondition(defaultType, columnNames, columnTypes, searchCriteriaList);
    }

    // endregion

    protected <T extends RelationalPathBase<T>> String getTableName(Class<T> type)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Field field = this.getRelationalPathBaseField(type);
        //noinspection unchecked
        T obj = (T) FieldUtils.readStaticField(field);
        return String.valueOf(MethodUtils.invokeMethod(obj, "getTableName"));
    }

    protected BooleanExpression[] getPredicateFromSearchCriteria(List<SearchCriteria> searchCriteriaList, Class<?> type) {
        // Lấy danh sách các biểu thức điều kiện từ searchCriteriaList
        BooleanExpression[] predicateArray;
        if (searchCriteriaList == null || searchCriteriaList.isEmpty()) {
            predicateArray = new BooleanExpression[0];
        } else {
            predicateArray = this.getPredicate(searchCriteriaList, type, null);
        }

        return predicateArray;
    }
}
