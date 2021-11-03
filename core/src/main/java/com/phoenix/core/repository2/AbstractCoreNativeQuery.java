package com.phoenix.core.repository2;

import org.springframework.dao.InvalidDataAccessResourceUsageException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class AbstractCoreNativeQuery {
    protected EntityManager entityManager;

    public abstract void setEntityManager(EntityManager entityManager);

    /**
     * @param sql    Câu lệnh sql cần thực hiện
     * @param params Tham số của lệnh
     * @return Danh sách kết quả
     * @throws InvalidDataAccessResourceUsageException : Khi số param truyền vào và số param định nghĩa không khớp
     */
    public List<?> executeNativeQuery(String sql, Object... params) throws InvalidDataAccessResourceUsageException {
        Query query = createNativeQuery(sql, params);
        return query.getResultList();
    }

    /**
     * @param sql    Lệnh sql
     * @param params Parameters của lệnh
     * @return {@link Query}
     */
    private Query createNativeQuery(String sql, Object... params) {
        Query query = entityManager.createNativeQuery(sql);

        int index = 1;
        for (Object param : params) {
            query.setParameter(index++, param);
        }

        return query;
    }

    public Map<String, Object> parseResult(Object result, String[] keys) {
        Map<String, Object> map = new LinkedHashMap<>(keys.length);
        if (result instanceof Object[]) {
            Object[] objects = (Object[]) result;
            for (int i = 0; i < keys.length; i++) {
                map.put(keys[i], objects[i]);
            }
        } else {
            map.put(keys[0], result);
        }

        return map;
    }

    public List<Map<String, Object>> parseResult(List<?> results, String[] keys) {
        List<Map<String, Object>> list = new LinkedList<>();

        for (Object result : results) {
            list.add(parseResult(result, keys));
        }

        return list;
    }
}
