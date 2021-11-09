package com.phoenix.core.repository2;

import com.phoenix.common.reflection.ConstructorUtils;
import com.phoenix.common.reflection.FieldUtils;
import com.phoenix.common.util.StringUtils;
import com.phoenix.core.model.query.SearchCriteria;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.sql.RelationalPathBase;
import com.querydsl.sql.SQLQueryFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    // region abstract methods
    public abstract String getDefaultSchemaName();

    public abstract SQLQueryFactory getDefaultSQLQueryFactory();

    protected abstract void setDefaultSQLQueryFactory(SQLQueryFactory queryFactory);

    // endregion

    /**
     * <h1> Lấy query source </h1>
     *
     * <p> Sử dụng hàm này để lấy query source ( chính là tên bảng trong câu lệnh sql)</p>
     * <p> Tránh được lỗi không nhận được tên bản </p>
     * <p> Sử dụng reflection để thực hiện, nên sẽ trả về null nếu xảy ra lỗi </p>
     * <ul>
     *     <li> NoSuchMethodException: {@link ConstructorUtils#invokeConstructor(java.lang.Class, java.lang.Object[])} </li>
     *     <li> IllegalAccessException: {@link ConstructorUtils#invokeConstructor(java.lang.Class, java.lang.Object[])} </li>
     *     <li> InvocationTargetException: {@link ConstructorUtils#invokeConstructor(java.lang.Class, java.lang.Object[])}</li>
     *     <li> InstantiationException: {@link ConstructorUtils#invokeConstructor(java.lang.Class, java.lang.Object[])}</li>
     * </ul>
     *
     * @param type   Class của query source (Các class đc querydsl plugin generate)
     * @param source Tên bảng của query source
     * @param <T>    Kiểu của class đc querydsl plugin generate
     * @return Đối tượng được dùng cho hàm {@code ProjectableSQLQuery#form} và null nếu gặp lỗi
     */
    public <T> T getQuerySource(Class<T> type, String source) {
        try {
            return ConstructorUtils.invokeConstructor(type, source, getDefaultSchemaName());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }

    // region Predicate

    public <T> BooleanExpression[] getPredicate(List<SearchCriteria> searchCriteriaList, Class<T> pathBuilderType, String alias) {
        //List<BooleanExpression> predicateList = new ArrayList<>(searchCriteriaList.size());

        BooleanExpression[] predicateArray = new BooleanExpression[searchCriteriaList.size()];

        int index = 0;
        for (SearchCriteria searchCriteria : searchCriteriaList) {
            //predicateList.add(getPredicate(searchCriteria, pathBuilderType, alias));
            predicateArray[index++] = getPredicate(searchCriteria, pathBuilderType, alias);
        }

        return predicateArray;
    }

    public <T> BooleanExpression getPredicate(SearchCriteria searchCriteria, Class<T> pathBuilderType, String alias) {
        // Validate search criteria
        if (searchCriteria == null
                || StringUtils.isEmpty(searchCriteria.getKey())
                || searchCriteria.getSearchOperation() == null
                || searchCriteria.getArguments().isEmpty()
        ) {
            return null;
        }

        // Get Path builder
        if (alias == null) {
            alias = "";
        }
        PathBuilder<T> entityPath = new PathBuilder<>(pathBuilderType, alias);

        // get isNumeric
        boolean isNumeric = StringUtils.isNumeric(searchCriteria.getArguments().get(0).toString());

        // Get predicate
        if (searchCriteria.getArguments().size() == 1) {
            return getPredicateWithSingleArguments(searchCriteria, entityPath, isNumeric);
        } else {
            return getPredicateWithMultipleArguments(searchCriteria, entityPath, isNumeric);
        }
    }

    public <T> BooleanExpression getPredicateWithMultipleArguments(SearchCriteria searchCriteria, PathBuilder<T> entityPath, boolean isNumeric) {
        if (isNumeric) {
            NumberPath<Integer> path = entityPath.getNumber(searchCriteria.getKey(), Integer.class);

            List<Integer> value = searchCriteria.getArguments().stream()
                    .map(e -> Integer.parseInt(String.valueOf(e)))
                    .collect(Collectors.toList());

            //noinspection DuplicatedCode
            switch (searchCriteria.getSearchOperation()) {
                case IN:
                    return path.in(value);
                case NOT_IN:
                    return path.notIn(value);
                case BETWEEN:
                    return path.between(value.get(0), value.get(1));
            }
        } else {
            StringPath path = entityPath.getString(searchCriteria.getKey());

            List<String> value = searchCriteria.getArguments().stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());

            //noinspection DuplicatedCode
            switch (searchCriteria.getSearchOperation()) {
                case IN:
                    return path.in(value);
                case NOT_IN:
                    return path.notIn(value);
                case BETWEEN:
                    return path.between(value.get(0), value.get(1));
            }
        }


        return null;
    }

    public <T> BooleanExpression getPredicateWithSingleArguments(SearchCriteria searchCriteria, PathBuilder<T> entityPath, boolean isNumeric) {
        if (isNumeric) {
            NumberPath<Integer> path = entityPath.getNumber(searchCriteria.getKey(), Integer.class);
            int value = Integer.parseInt(searchCriteria.getArguments().get(0).toString());

            switch (searchCriteria.getSearchOperation()) {
                case EQUAL:
                    return path.eq(value);
                case NOT_EQUAL:
                    return path.ne(value);
                case GREATER_THAN_OR_EQUAL:
                    return path.goe(value);
                case GREATER_THAN:
                    return path.gt(value);
                case LESS_THAN_OR_EQUAL:
                    return path.loe(value);
                case LESS_THAN:
                    return path.lt(value);
            }
        } else {
            StringPath path = entityPath.getString(searchCriteria.getKey());
            String value = searchCriteria.getArguments().get(0).toString();
            switch (searchCriteria.getSearchOperation()) {
                case EQUAL:
                    return path.containsIgnoreCase(value);
                case LIKE:
                    return path.like(value);
                case NOT_LIKE:
                    return path.notLike(value);
            }
        }
        return null;
    }

    //endregion

    // region getPath

    public <T extends RelationalPathBase<T>> Field getRelationalPathBaseField(Class<T> type) {
        List<Field> fields = FieldUtils.getAllFieldsList(type);
        for (Field field : fields) {
            if (field.getType().equals(type)) {
                return field;
            }
        }
        return null;
    }


    public <T extends RelationalPathBase<T>, E extends Path<T>> E getPath(
            Class<T> type, String fieldName) throws IllegalAccessException {
        Field sField = getRelationalPathBaseField(type);
        //noinspection unchecked
        T obj = (T) FieldUtils.readStaticField(sField);

        Field field = FieldUtils.getField(type, fieldName);

        //noinspection unchecked
        return (E) FieldUtils.readField(field, obj);
    }

    public <T extends RelationalPathBase<T>, E extends Path<T>> List<E> getPath(
            Class<T> type, String... fieldNames) throws IllegalAccessException {
        Field sField = getRelationalPathBaseField(type);
        //noinspection unchecked
        T obj = (T) FieldUtils.readStaticField(sField);

        List<E> elements = new ArrayList<>(fieldNames.length);

        Field field;
        for (String fieldName : fieldNames) {
            field = FieldUtils.getField(type, fieldName);
            //noinspection unchecked
            elements.add((E) FieldUtils.readField(field, obj));
        }

        return elements;
    }

    // endregion

    // region convert

    /**
     * <h1> Chuyển đổi 1 list tuple thành 1 list Map</h1>
     * <p></p>
     *
     * @param tuples      Danh sách tuple cần chuyển đổi
     * @param columnNames Danh sách tên các cột
     * @param columnTypes Danh sách kiểu của các cột
     * @return Danh sách gồm các đối tượng Map với các thuộc tính tương ứng với các cột
     * @see AbstractCoreQueryDslRepository#convertTuple2Map(Tuple, String[], Class[])
     */
    public List<Map<String, Object>> convertListTuple2ListMap(List<Tuple> tuples, String[] columnNames, Class<?>[]
            columnTypes) {
        List<Map<String, Object>> list = new ArrayList<>(tuples.size());

        for (Tuple tuple : tuples) {
            list.add(convertTuple2Map(tuple, columnNames, columnTypes));
        }
        return list;
    }

    /**
     * <h1> Chuyển đổi từ Tuple thành Map </h1>
     *
     * <p> Chuyển đổi đối tượng {@link Tuple} thành {@link Map}</p>
     * <p> Map có dạng: key: {@code String}, value: {@code Object}</p>
     * <p> Thứ tự của các kết quả của các trường trong {@code tuple}, {@code columnNames} và {@code columnTypes} phải khớp với nhau</p>
     * <p> {@code tuple} thường sẽ là kết quả của một câu lệnh sql</p>
     *
     * @param tuple       Đối tượng cần chuyển đổi
     * @param columnNames Danh sách tên các cột
     * @param columnTypes Danh sách kiểu của các cột
     * @return Đối tượng Map với các thuộc tính tương ứng
     */
    public Map<String, Object> convertTuple2Map(Tuple tuple, String[] columnNames, Class<?>[] columnTypes) {
        Map<String, Object> map = new HashMap<>(columnNames.length);

        for (int i = 0; i < columnNames.length; i++) {
            map.put(columnNames[i], tuple.get(i, columnTypes[i]));
        }

        return map;
    }

    // endregion

}
