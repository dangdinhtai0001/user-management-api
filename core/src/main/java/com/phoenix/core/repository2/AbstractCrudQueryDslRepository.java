package com.phoenix.core.repository2;

import com.phoenix.common.reflection.FieldUtils;
import com.phoenix.common.reflection.MethodUtils;
import com.phoenix.core.model.query.SearchCriteria;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.sql.RelationalPathBase;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.dml.SQLDeleteClause;
import com.querydsl.sql.dml.SQLInsertClause;
import com.querydsl.sql.dml.SQLUpdateClause;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.repository.CrudRepository;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@Log4j2
public abstract class AbstractCrudQueryDslRepository<E extends RelationalPathBase<E>>
        extends AbstractCoreQueryDslRepository implements CrudQueryDslRepository<E> {

    protected final Class<E> defaultType;

    protected AbstractCrudQueryDslRepository(Class<E> defaultType) {
        this.defaultType = defaultType;
    }

    // region create

    @Override
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

    @Override
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

    @Override
    public <T extends RelationalPathBase<T>> long update(
            Class<T> type, String[] columnNames, Object[] value, List<SearchCriteria> searchCriteriaList
    ) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        // Lấy Path từ danh sách các cột
        List<Path<Object>> expressions = this.getPath(type, columnNames);

        // Lấy danh sách các biểu thức điều kiện từ searchCriteriaList
        BooleanExpression[] predicateArray = this.getPredicateFromSearchCriteria(searchCriteriaList, type);

        SQLUpdateClause sqlUpdateClause = this.getDefaultSQLQueryFactory()
                .update(this.getQuerySource(type, this.getTableName(type)))
                .where(predicateArray);

        for (int i = 0; i < columnNames.length; i++) {
            sqlUpdateClause.set(expressions.get(i), value[i]).addBatch();
        }

        log.debug("Update query: {}", sqlUpdateClause.toString());

        return sqlUpdateClause.execute();
    }

    // endregion

    // region findByCondition

    @Override
    public <T extends RelationalPathBase<T>> List<Map<String, Object>> findByCondition(
            Class<T> type, String[] columnNames, Class<?>[] columnTypes, List<SearchCriteria> searchCriteriaList
    ) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        // Lấy Path từ danh sách các cột cần tìm kiếm
        List<Path<T>> expressions = this.getPath(type, columnNames);

        // Lấy danh sách các biểu thức điều kiện từ searchCriteriaList
        BooleanExpression[] predicateArray = this.getPredicateFromSearchCriteria(searchCriteriaList, type);

        //Lấy source name từ class
        String source = this.getTableName(type);

        SQLQuery<Tuple> query = this.getDefaultSQLQueryFactory()
                .select(expressions.toArray(new Path[0]))
                .from(this.getQuerySource(type, source))
                .where(predicateArray);

        log.debug("Query: {}", query.toString());

        List<Tuple> tuples = query.fetch();

        return this.convertListTuple2ListMap(tuples, columnNames, columnTypes);
    }

    @Override
    public <T extends RelationalPathBase<T>> List<Map<String, Object>> findByCondition(
            Class<T> type, String[] columnNames, Class<?>[] columnTypes)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return this.findByCondition(type, columnNames, columnTypes, null);
    }

    @Override
    public List<Map<String, Object>> findByCondition(String[] columnNames, Class<?>[] columnTypes)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        return this.findByCondition(defaultType, columnNames, columnTypes, null);
    }

    @Override
    public List<Map<String, Object>> findByCondition(String[] columnNames, Class<?>[] columnTypes,
                                                     List<SearchCriteria> searchCriteriaList
    ) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        return this.findByCondition(defaultType, columnNames, columnTypes, searchCriteriaList);
    }

    // endregion

    // region delete

    @Override
    public <T extends RelationalPathBase<T>> long delete(Class<T> type, List<SearchCriteria> searchCriteriaList)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        // Lấy danh sách các biểu thức điều kiện từ searchCriteriaList
        BooleanExpression[] predicateArray = this.getPredicateFromSearchCriteria(searchCriteriaList, type);

        SQLDeleteClause deleteClause = this.getDefaultSQLQueryFactory()
                .delete(this.getQuerySource(type, this.getTableName(type)))
                .where(predicateArray);

        return deleteClause.execute();
    }

    @Override
    public <T extends RelationalPathBase<T>> long delete(List<SearchCriteria> searchCriteriaList)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return this.delete(defaultType, searchCriteriaList);
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
