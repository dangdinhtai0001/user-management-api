package com.phoenix.base.service;

import com.phoenix.base.constant.BeanIds;
import com.phoenix.core.model.DefaultAuthenticationToken;
import com.phoenix.core.model.DefaultException;
import com.phoenix.core.service.AbstractCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

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

    public Map<String, DefaultException<Long>> getExceptionTranslator() {
        return exceptionTranslator;
    }
}
