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
 *
 * <p> Thông tin thêm </p>
 *
 * <ul>
 *     <li> <p> Querydsl plugin sẽ generate ra các file class cần thiết để map với các đối tượng trong db khi compile. </p></li>
 *     <li> <p> Mỗi class sẽ tương ứng với mỗi bảng, view,...  </p></li>
 *     <li> <p> Mỗi trường sẽ tương ứng với mỗi field trong class đấy.</p></li>
 *     <li> <p> Để sử dụng các thao tác truy vấn với trường đấy thì cần lấy đc Path của nó</p></li>
 * </ul>
 */
public abstract class AbstractCoreQueryDslRepository implements CoreQueryDslRepository {
    protected SQLQueryFactory queryFactory;

    @Override
    public SQLQueryFactory getDefaultSQLQueryFactory() {
        return this.queryFactory;
    }

    // region abstract methods
    public abstract String getDefaultSchemaName();

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
    @Override
    public <T extends RelationalPathBase<T>> T getQuerySource(Class<T> type, String source) {
        try {
            return ConstructorUtils.invokeConstructor(type, source, getDefaultSchemaName());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }

    // region Predicate

    @Override
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

    @Override
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

    protected <T> BooleanExpression getPredicateWithMultipleArguments(SearchCriteria searchCriteria, PathBuilder<T> entityPath, boolean isNumeric) {
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

    protected <T> BooleanExpression getPredicateWithSingleArguments(SearchCriteria searchCriteria, PathBuilder<T> entityPath, boolean isNumeric) {
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

    /**
     * <h1> Hàm lấy trường Relational Path Base </h1>
     * <p>
     * Hàm lấy ra Field ứng với trường static của QueryDsl class (class được auto generate khi compile bằng querydsl plugin)
     * </p>
     *
     * @param type : Class cần lấy path (Kiểu T)
     * @param <T>  : Class cần lấy path
     * @return Field tương ứng
     */
    protected <T extends RelationalPathBase<T>> Field getRelationalPathBaseField(Class<T> type) {
        List<Field> fields = FieldUtils.getAllFieldsList(type);
        for (Field field : fields) {
            if (field.getType().equals(type)) {
                return field;
            }
        }
        return null;
    }

    /**
     * <h1> Hàm lấy giá trị trường Relational Path Base </h1>
     *
     * @param type Class cần lấy path (Kiểu T)
     * @param <T>  Class cần lấy path
     * @return Giá trị của field tương ứng
     * @throws IllegalAccessException {@link Field#get(Object)}
     */
    protected <T extends RelationalPathBase<T>> T getRelationalPathBaseFieldValue(Class<T> type)
            throws IllegalAccessException {
        Field field = getRelationalPathBaseField(type);

        if (field == null) {
            return null;
        } else {
            //noinspection unchecked
            return (T) FieldUtils.readStaticField(field);
        }
    }

    public <T extends RelationalPathBase<T>, E extends Path<?>> E getPath(
            Class<T> type, String fieldName) throws IllegalAccessException {
        T obj = getRelationalPathBaseFieldValue(type);

        Field field = FieldUtils.getField(type, fieldName);

        //noinspection unchecked
        return (E) FieldUtils.readField(field, obj);
    }

    /**
     * <h1> Lấy ra Path của trường tương ứng trong Querydsl class </h1>
     *
     * @param type       Class mà querydsl tạo ra (extend từ {@link RelationalPathBase})
     * @param fieldNames MẢng các trường cần lấy
     * @param <T>        Kiểu của class mà querydsl tạo ra
     * @param <E>        Kiểu của trường cần lấy (extend từ {@link Path})
     * @return {@code List<E>} List các {@link Path} tương ứng
     * @throws IllegalAccessException Thường xảy ra khi class được auto-generate bị lỗi {@link FieldUtils#readStaticField(Field)}
     */
    public <T extends RelationalPathBase<T>, E extends Path<?>> List<E> getPath(
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
    @Override
    public List<Map<String, Object>> convertListTuple2ListMap(List<Tuple> tuples, String[] columnNames, Class<?>[]
            columnTypes) {
        List<Map<String, Object>> list = new ArrayList<>(tuples.size());

        if (columnTypes == null || columnTypes.length == 0) {
            for (Tuple tuple : tuples) {
                list.add(convertTuple2Map(tuple, columnNames));
            }
        } else {
            for (Tuple tuple : tuples) {
                list.add(convertTuple2Map(tuple, columnNames, columnTypes));
            }
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
    @Override
    public Map<String, Object> convertTuple2Map(Tuple tuple, String[] columnNames, Class<?>[] columnTypes) {
        Map<String, Object> map = new HashMap<>(columnNames.length);

        for (int i = 0; i < columnNames.length; i++) {
            map.put(columnNames[i], tuple.get(i, columnTypes[i]));
        }

        return map;
    }

    /**
     * <h1> Chuyển đổi từ Tuple thành Map </h1>
     * <p>
     * Tương tự {@link AbstractCoreQueryDslRepository#convertTuple2Map(Tuple, String[], Class[])}
     * nhưng không cần truyền vào columTypes
     *
     * @param tuple       Đối tượng cần chuyển đổi
     * @param columnNames Danh sách tên các cột
     * @return Đối tượng Map với các thuộc tính tương ứng
     */
    @Override
    public Map<String, Object> convertTuple2Map(Tuple tuple, String[] columnNames) {
        Map<String, Object> map = new HashMap<>(columnNames.length);

        for (int i = 0; i < columnNames.length; i++) {
            map.put(columnNames[i], tuple.get(i, Object.class));
        }

        return map;
    }

    // endregion

}
