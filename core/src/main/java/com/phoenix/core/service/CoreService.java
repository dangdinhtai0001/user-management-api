package com.phoenix.core.service;

import com.google.gson.internal.LinkedTreeMap;
import com.phoenix.common.structure.DefaultTuple;
import com.phoenix.core.exception.ApplicationException;
import com.phoenix.core.model.DefaultAuthenticationToken;
import com.phoenix.core.model.DefaultException;
import com.phoenix.core.model.pagination.PaginationOption;
import com.phoenix.core.model.query.OrderBy;
import com.phoenix.core.model.query.OrderDirection;
import com.phoenix.core.model.query.SearchCriteria;
import com.phoenix.core.model.query.SearchCriteriaRequest;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface CoreService {
    /**
     * @param code Mã lỗi lưu ở bảng fw_exception
     * @return {@link ApplicationException}
     */
    ApplicationException getApplicationException(String code);

    <T> T getPropertyOfRequestBodyByKey(Map<?, ?> requestBody, String key, Class<T> typeOfResponse);

    List<SearchCriteria> getListOfSearchCriteria(List<SearchCriteriaRequest> listConditionRequests);


    DefaultTuple convertObjectToTuple(Object object, String... fields) throws NoSuchFieldException, IllegalAccessException;

    <T> List<DefaultTuple> convertListObjectToListTuple(List<T> list, String... fields) throws NoSuchFieldException, IllegalAccessException;

    OrderBy getOrderBy(PaginationOption paginationOption, OrderDirection direction);

    <T> T convert2Object(LinkedTreeMap<?, ?> object, Class<T> instanceClass);

    DefaultAuthenticationToken getCurrentSecurityToken();

}
