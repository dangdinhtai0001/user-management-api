package com.phoenix.base.service;

import com.google.common.collect.Multimap;
import com.phoenix.base.constant.BeanIds;
import com.phoenix.core.model.DefaultAuthenticationToken;
import com.phoenix.core.model.DefaultException;
import com.phoenix.core.model.query.SearchCriteria;
import com.phoenix.core.service.AbstractCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class BaseService extends AbstractCoreService {
    @Override
    public DefaultAuthenticationToken getCurrentSecurityToken() {
        return null;
    }

    @Autowired
    public void setExceptionTranslator(
            @Qualifier(BeanIds.EXCEPTION_TRANSLATOR) Map<String, DefaultException<Long>> exceptionTranslator) {
        this.exceptionTranslator = exceptionTranslator;
    }


    @Override
    protected Multimap<String, SearchCriteria> formatSearchCriteriaList(List<Map<String, Object>> searchCriteria) {
        return null;
    }
}
