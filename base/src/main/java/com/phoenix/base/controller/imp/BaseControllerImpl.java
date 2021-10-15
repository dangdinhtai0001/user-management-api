package com.phoenix.base.controller.imp;

import com.google.gson.internal.LinkedTreeMap;
import com.phoenix.base.constant.BeanIds;
import com.phoenix.base.controller.BaseController;
import com.phoenix.base.model.ResourceActionModel;
import com.phoenix.common.util.ReflectionUtil;
import com.phoenix.core.config.DefaultExceptionCode;
import com.phoenix.core.controller.AbstractCoreController;
import com.phoenix.core.exception.ApplicationException;
import com.phoenix.core.model.DefaultException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

@RestController(value = BeanIds.BASE_CONTROLLER)
@Log4j2
public class BaseControllerImpl extends AbstractCoreController implements BaseController {

    private final ApplicationContext applicationContext;
    private final Map<String, ResourceActionModel> serviceMetadata;
    private final Map<String, DefaultException<Long>> exceptionTranslator;

    public BaseControllerImpl(ApplicationContext applicationContext,
                              @Qualifier(BeanIds.APPLICATION_SERVICE_METADATA) Map<String, ResourceActionModel> serviceMetadata,
                              @Qualifier(BeanIds.EXCEPTION_TRANSLATOR) Map<String, DefaultException<Long>> exceptionTranslator
    ) {
        this.applicationContext = applicationContext;
        this.serviceMetadata = serviceMetadata;
        this.exceptionTranslator = exceptionTranslator;
    }

    @RequestMapping(value = "/ping")
    public ResponseEntity<String> ping() {
        return sendResponse("pong");
    }

    /**
     * @param resource    Tên service
     * @param action      Tên hàm trong service đó
     * @param request     Đối tượng request (Để lấy method của request)
     * @param requestBody Body
     * @return Hàm này sẽ thực hiện
     * Hứng request đến path /resource/action
     * Validate thông tin dựa trên metadata đc lưu trong database
     * Gọi đến các service tương ứng.
     * @throws ApplicationException Khi validate sai hoặc service throw ra exception
     */
    @RequestMapping(value = "/{resource}/{action}")
    public ResponseEntity<?> control(
            @PathVariable(name = "resource") String resource,
            @PathVariable(name = "action") String action,
            @RequestBody(required = false) Object requestBody,
            HttpServletRequest request
    ) throws ApplicationException {
        Object result = null;

        try {
            ResourceActionModel metadata = validateRequest(resource, action, request);

            Object beanObject = applicationContext.getBean(metadata.getBeanName());

            if (requestBody == null) {
                result = ReflectionUtil.invokeMethod(beanObject, action);
            } else {
                result = ReflectionUtil.invokeMethod(beanObject, action, requestBody);
            }
        } catch (NoSuchMethodException | NoSuchBeanDefinitionException e) {
            log.warn(e.getMessage());
            //log.warn(e);
            throw getApplicationException(DefaultExceptionCode.BAD_REQUEST);
        } catch (InvocationTargetException e) {
            if (e.getCause() instanceof ApplicationException) {
                throw (ApplicationException) e.getCause();
            } else {
                log.error(e.getMessage());
                throw getApplicationException(DefaultExceptionCode.INTERNAL_ERROR);
            }
        } catch (IllegalAccessException e) {
            log.error(e.getMessage());
            throw getApplicationException(DefaultExceptionCode.INTERNAL_ERROR);
        }

        return sendResponse(result);
    }

    private ResourceActionModel validateRequest(String resource, String action, HttpServletRequest request) throws ApplicationException {
        String key = resource + "|" + action;
        ResourceActionModel metadata = serviceMetadata.getOrDefault(key, null);

        // Nếu resource không có trong database thì trả về lỗi NOT_FOUND
        if (metadata == null) {
            throw getApplicationException(DefaultExceptionCode.NOT_FOUND);
        }

        // Nếu metadata có enabled == false thì trả về lỗi NOT_ACCEPTABLE
        if (!metadata.getEnabled()) {
            throw getApplicationException(DefaultExceptionCode.NOT_ACCEPTABLE);
        }

        // Nếu request có method không đúng thì trả về lỗi METHOD_NOT_ALLOW
        if (!request.getMethod().equals(metadata.getHttpMethod())) {
            throw getApplicationException(DefaultExceptionCode.METHOD_NOT_ALLOWED);
        }

        return metadata;
    }

    private ApplicationException getApplicationException(String code) {
        DefaultException<Long> exception = exceptionTranslator.getOrDefault(code, null);

        return new ApplicationException(exception.getMessage(), code,
                HttpStatus.valueOf(exception.getHttpCode()));
    }
}
