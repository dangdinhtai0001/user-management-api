package com.phoenix.core.controller;

import com.phoenix.core.exception.ApplicationException;
import org.springframework.http.ResponseEntity;

public interface CoreController {
    <T> ResponseEntity<T> handleException(ApplicationException exception);

    <T> ResponseEntity<T> sendResponse(T response);
}
