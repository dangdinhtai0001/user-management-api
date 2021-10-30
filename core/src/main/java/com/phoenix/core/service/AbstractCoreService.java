package com.phoenix.core.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import com.phoenix.common.structure.DefaultTuple;
import com.phoenix.common.structure.imp.TriDefaultTupleImpl;
import com.phoenix.common.util.ReflectionUtil;
import com.phoenix.core.exception.ApplicationException;
import com.phoenix.core.model.DefaultException;
import com.phoenix.core.model.pagination.PaginationOption;
import com.phoenix.core.model.query.OrderBy;
import com.phoenix.core.model.query.OrderDirection;
import com.phoenix.core.model.query.SearchCriteria;
import com.phoenix.core.model.query.SearchCriteriaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractCoreService implements CoreService {
    protected Map<String, DefaultException<Long>> exceptionTranslator;

    @Override
    public ApplicationException getApplicationException(String code) {
        DefaultException<Long> exception = findExceptionByCode(code);

        return new ApplicationException(exception.getMessage(), code,
                HttpStatus.valueOf(exception.getHttpCode()));
    }

    @SuppressWarnings("unchecked")
    @Override
    public DefaultException<Long> findExceptionByCode(String code) {
        return exceptionTranslator.getOrDefault(code, null);
    }

    @Override
    public <T> T getPropertyOfRequestBodyByKey(Map<?,?> requestBody, String key, Class<T> typeOfResponse) {
        Object value = requestBody.getOrDefault(key,null);

        return (T) value;
    }

    @Override
    public List<SearchCriteria> getListOfSearchCriteria(List<SearchCriteriaRequest> listConditionRequests) {
        if (listConditionRequests == null || listConditionRequests.isEmpty()) {
            return null;
        }
        return listConditionRequests.stream().map(SearchCriteriaRequest::getSearchCriteria).collect(Collectors.toList());
    }

    @Override
    public DefaultTuple convertObjectToTuple(Object object, String... fields) throws NoSuchFieldException, IllegalAccessException {
        Class<?> objectClass = object.getClass();

        Class<?>[] types = new Class[fields.length];
        Object[] args = new Object[fields.length];

        for (int i = 0; i < fields.length; i++) {
            types[i] = ReflectionUtil.getTypeOfFieldByName(objectClass, fields[i]);
            if (types[i] != null) {
                args[i] = ReflectionUtil.getFieldValue(object, fields[i], types[i]);
            }
        }

        return new TriDefaultTupleImpl(fields, types, args);
    }

    @Override
    public <T> List<DefaultTuple> convertListObjectToListTuple(List<T> list, String... fields) throws NoSuchFieldException, IllegalAccessException {
        if (list == null || list.isEmpty()) {
            return null;
        }

        List<DefaultTuple> defaultTuples = new LinkedList<>();

        for (Object instance : list) {
            defaultTuples.add(convertObjectToTuple(instance, fields));
        }

        return defaultTuples;
    }

    @Override
    public OrderBy getOrderBy(PaginationOption paginationOption, OrderDirection direction) {
        String[] sortBy = new String[0];
        if (direction == OrderDirection.ASC) {
            sortBy = paginationOption.getSortAscending();
        }
        if (direction == OrderDirection.DESC) {
            sortBy = paginationOption.getSortDescending();
        }

        return new OrderBy(direction, sortBy);
    }

    @Override
    public <T> T convert2Object(LinkedTreeMap<?, ?> object, Class<T> instanceClass) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.toJsonTree(object).getAsJsonObject();
        return gson.fromJson(jsonObject, instanceClass);
    }
}
