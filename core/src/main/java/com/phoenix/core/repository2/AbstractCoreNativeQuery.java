package com.phoenix.core.repository2;

import com.phoenix.common.structure.DefaultTuple;
import com.phoenix.common.structure.imp.TripleDefaultTupleImpl;
import org.springframework.dao.InvalidDataAccessResourceUsageException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

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

    public DefaultTuple parseResult(Object result, String[] keys, Class<?>[] types) {
        DefaultTuple tuple;
        if (result instanceof Object[]) {
            Object[] objects = (Object[]) result;
            tuple = new TripleDefaultTupleImpl(keys, types, objects);
        } else {
            tuple = new TripleDefaultTupleImpl(keys, types, result);
        }

        return tuple;
    }

}
