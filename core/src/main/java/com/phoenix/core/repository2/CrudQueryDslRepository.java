package com.phoenix.core.repository2;

import com.phoenix.core.model.query.SearchCriteria;
import com.querydsl.sql.RelationalPathBase;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public interface CrudQueryDslRepository<E extends RelationalPathBase<E>> extends CoreQueryDslRepository {
    <T extends RelationalPathBase<T>> long create(
            Class<T> type, String[] columnNames, Object[] value
    ) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException;

    <T extends RelationalPathBase<T>> long create(
            Class<T> type, String[] columnNames, List<Object[]> values
    ) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException;

    <T extends RelationalPathBase<T>> long update(
            Class<T> type, String[] columnNames, Object[] value, List<SearchCriteria> searchCriteriaList
    ) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException;

    <T extends RelationalPathBase<T>> List<Map<String, Object>> findByCondition(
            Class<T> type, String[] columnNames, Class<?>[] columnTypes, List<SearchCriteria> searchCriteriaList
    ) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException;

    <T extends RelationalPathBase<T>> List<Map<String, Object>> findByCondition(
            Class<T> type, String[] columnNames, Class<?>[] columnTypes)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException;

    List<Map<String, Object>> findByCondition(String[] columnNames, Class<?>[] columnTypes)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException;

    List<Map<String, Object>> findByCondition(String[] columnNames, Class<?>[] columnTypes,
                                              List<SearchCriteria> searchCriteriaList
    ) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException;

    <T extends RelationalPathBase<T>> long delete(Class<T> type, List<SearchCriteria> searchCriteriaList)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException;

    <T extends RelationalPathBase<T>> long delete(List<SearchCriteria> searchCriteriaList)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException;
}
