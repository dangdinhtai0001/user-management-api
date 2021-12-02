package com.phoenix.core.service;

import com.google.common.collect.Multimap;
import com.phoenix.core.exception.ApplicationException;
import com.phoenix.core.model.DefaultException;
import com.phoenix.core.model.query.SearchCriteria;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

public abstract class AbstractCoreService implements CoreService {
    protected Map<String, DefaultException<Long>> exceptionTranslator;

    @Override
    public ApplicationException getApplicationException(String code) {
        DefaultException<Long> exception = exceptionTranslator.getOrDefault(code, null);

        return new ApplicationException(exception.getMessage(), code,
                HttpStatus.valueOf(exception.getHttpCode()));
    }

    protected abstract Multimap<String, SearchCriteria> formatSearchCriteriaList(List<Map<String, Object>> searchCriteria);
}
