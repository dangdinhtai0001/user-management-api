package com.phoenix.core.repository2;

import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

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
     * <h1> Thực thi câu lệnh sql </h1>
     *
     * <p> Hàm này dùng cho các lệnh ko cần thực hiện transaction như select. </p>
     *
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
     * <h1> Thực thi câu lệnh sql </h1>
     * <p> Hàm này chỉ dùng cho các lệnh cần thực hiện transaction như insert, update </p>
     *
     * @param sql    Câu lệnh sql cần thực hiện
     * @param params các param để truyền vào
     * @return số row bị tác động bởi lệnh sql
     * @throws InvalidDataAccessResourceUsageException : Khi số param truyền vào và số param định nghĩa không khớp
     */
    @Modifying
    @Transactional
    public int updateNativeQuery(String sql, String... params) throws InvalidDataAccessResourceUsageException {
        Query query = createNativeQuery(sql, params);
        return query.executeUpdate();
    }

    /**
     * <h1>Tạo đối tượng {@code Query} </h1>
     *
     * <p> Tạo đối tượng {@code Query} với danh sách các tham số tương ứng</p>
     *
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

    /**
     * <h1> Hàm parse kết quả </h1>
     *
     * <p> Chuyển đổi kết quả nhận được từ câu lệnh sql sau khi được gọi hàm execute() thành Map</p>
     *
     * @param result Một dòng trong kết quả trả về từ câu lệnh sql
     * @param keys   Danh sách các key của dòng trả về
     * @return Một map chứa các key và giá trị tương ứng
     */
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


    /**
     * <h1> Hàm parse kết quả theo danh sách </h1>
     *
     * <p> Chuyển đổi kết quả nhận được từ câu lệnh sql sau khi được gọi hàm execute() thành danh sách các Map</p>
     *
     * @param results Danh sách kết quả trả về từ câu lệnh sql
     * @param keys    Danh sách các key của dòng trả về
     * @return Danh sách các map chứa các key và giá trị tương ứng
     */
    public List<Map<String, Object>> parseResult(List<?> results, String[] keys) {
        List<Map<String, Object>> list = new LinkedList<>();

        for (Object result : results) {
            list.add(parseResult(result, keys));
        }

        return list;
    }
}
