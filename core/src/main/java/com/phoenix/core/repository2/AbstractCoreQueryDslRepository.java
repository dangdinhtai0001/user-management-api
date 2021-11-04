package com.phoenix.core.repository2;

import com.phoenix.common.reflection.ConstructorUtils;
import com.phoenix.common.reflection.FieldUtils;
import com.querydsl.core.QueryFactory;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.sql.RelationalPathBase;
import com.querydsl.sql.SQLQueryFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h1>Những phương thức chung khi sử dụng Querydsl tương tác với database</h1>
 *
 * <p> Use the the cascading methods of the SQLQuery class like this </p>
 * <ul>
 *     <li> select: Set the projection of the query. (Not necessary if created via query factory)</li>
 *     <li> from: Add the query sources here.</li>
 *     <li> innerJoin, join, leftJoin, rightJoin, fullJoin, on: Add join elements using these constructs. For the join methods the first argument is the join source and the second the target (alias).</li>
 *     <li> where: Add query filters, either in varargs form separated via commas or cascaded via the and-operator.</li>
 *     <li> groupBy: Add group by arguments in varargs form.</li>
 *     <li> having: Add having filter of the "group by" grouping as an varags array of Predicate expressions.</li>
 *     <li> orderBy: Add ordering of the result as an varargs array of order expressions. Use asc() and desc() on numeric, string and other comparable expression to access the OrderSpecifier instances.</li>
 *     <li> limit, offset, restrict: Set the paging of the result. Limit for max results, offset for skipping rows and restrict for defining both in one call.</li>
 * </ul>
 */
public abstract class AbstractCoreQueryDslRepository {

    public abstract String getDefaultSchemaName();

    public abstract SQLQueryFactory getDefaultSQLQueryFactory();

    protected abstract void setDefaultSQLQueryFactory(SQLQueryFactory queryFactory);

    public <T> T getTable(Class<T> type, String tableName) {
        try {
            return ConstructorUtils.invokeConstructor(type, tableName, getDefaultSchemaName());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T extends RelationalPathBase<T>, E extends Expression<T>> E getExpression(
            Class<T> type, String staticFiledName, String fieldName) throws IllegalAccessException {
        Field sField = FieldUtils.getField(type, staticFiledName);
        //noinspection unchecked
        T obj = (T) FieldUtils.readStaticField(sField);

        Field field = FieldUtils.getField(type, fieldName);

        //noinspection unchecked
        return (E) FieldUtils.readField(field, obj);
    }

    public <T extends RelationalPathBase<T>, E extends Expression<T>> List<E> getExpressions(
            Class<T> type, String staticFiledName, String... fieldNames) throws IllegalAccessException {
        Field sField = FieldUtils.getField(type, staticFiledName);
        //noinspection unchecked
        T obj = (T) FieldUtils.readStaticField(sField);

        List<E> elements = new ArrayList<>(fieldNames.length);

        Field field = null;
        for (String fieldName : fieldNames) {
            field = FieldUtils.getField(type, fieldName);
            //noinspection unchecked
            elements.add((E) FieldUtils.readField(field, obj));
        }

        //noinspection unchecked
        return elements;
    }

    public List<Map<String, Object>> convertListTuple2ListMap(List<Tuple> tuples, String[] columnNames, Class<?>[] columnTypes) {
        List<Map<String, Object>> list = new ArrayList<>(tuples.size());

        for (Tuple tuple : tuples) {
            list.add(convertTuple2Map(tuple, columnNames, columnTypes));
        }
        return list;
    }

    public Map<String, Object> convertTuple2Map(Tuple tuple, String[] columnNames, Class<?>[] columnTypes) {
        Map<String, Object> map = new HashMap<>(columnNames.length);

        for (int i = 0; i < columnNames.length; i++) {
            map.put(columnNames[i], tuple.get(i, columnTypes[i]));
        }

        return map;
    }
}
