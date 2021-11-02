package com.phoenix.core.controller;

import com.phoenix.core.exception.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The AbstractBaseController class implements common functionality for all Controller
 * classes. The <code>@ExceptionHandler</code> methods provide a consistent
 * response when Exceptions are thrown from <code>@RequestMapping</code>
 * annotated Controller methods.
 *
 * @author Matt Warman from https://github.com/leanstacks/spring-boot-fundamentals/blob/HEAD/src/main/java/org/example/ws/web/api/BaseController.java
 */
public abstract class AbstractCoreController implements CoreController {

    private final String CODE_KEY = "code";
    private final String MESSAGE_KEY = "message";
    private final String TIMESTAMP_KEY = "timestamp";
    private final String STATUS_KEY = "status";


    /**
     * The <code>@ExceptionHandler</code> method provides a consistent response
     * when Exceptions are thrown from <code>@RequestMapping</code> annotated
     * Controller methods.
     *
     * @param exception the Exception that was thrown
     * @return the ResponseEntity with the exception message
     */
    @SuppressWarnings("unchecked")
    @Override
    @ExceptionHandler({ApplicationException.class})
    public ResponseEntity<?> handleException(ApplicationException exception) {
        Map<String, Object> responseBody = new LinkedHashMap<>();


        responseBody.put(CODE_KEY, exception.getCode());
        responseBody.put(MESSAGE_KEY, exception.getMessage());
        responseBody.put(TIMESTAMP_KEY, System.currentTimeMillis());
        responseBody.put(STATUS_KEY, exception.getHttpStatus());


        if (exception.getHttpStatus() == null) {
            exception.setHttpStatus(HttpStatus.FOUND);
        }
        return new ResponseEntity<>(responseBody, exception.getHttpStatus());
    }

    @Override
    public <T> ResponseEntity<T> sendResponse(T response) {
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
