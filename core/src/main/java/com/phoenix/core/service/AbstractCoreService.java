package com.phoenix.core.service;

import com.phoenix.core.exception.ApplicationException;
import com.phoenix.core.model.DefaultException;
import org.springframework.http.HttpStatus;

import java.util.Map;

public abstract class AbstractCoreService implements CoreService {
    protected Map<String, DefaultException<Long>> exceptionTranslator;

    @Override
    public ApplicationException getApplicationException(String code) {
        DefaultException<Long> exception = exceptionTranslator.getOrDefault(code, null);

        return new ApplicationException(exception.getMessage(), code,
                HttpStatus.valueOf(exception.getHttpCode()));
    }
}
