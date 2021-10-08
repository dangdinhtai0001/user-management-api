package com.phoenix.base.controller.imp;

import com.phoenix.base.controller.BaseController;
import com.phoenix.common.util.ReflectionUtil;
import com.phoenix.core.controller.AbstractCoreController;
import com.phoenix.core.exception.ApplicationException;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

@RestController
public class BaseControllerImpl extends AbstractCoreController implements BaseController {

    private final ApplicationContext applicationContext;

    public BaseControllerImpl(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @RequestMapping(value = "/ping")
    public ResponseEntity<String> ping() {
        return sendResponse("pong");
    }

    @RequestMapping(value = "/{service}/{action}")
    public ResponseEntity<?> control(
            @PathVariable(name = "service") String service,
            @PathVariable(name = "action") String action,
            HttpServletRequest request
    ) throws ApplicationException {
        Object beanObject = applicationContext.getBean("PingServiceImpl");

        Object result;
        try {
            result = ReflectionUtil.invokeMethod(beanObject, action);
        } catch (NoSuchMethodException e) {
            throw new ApplicationException("Không hỗ trợ method này", "404", HttpStatus.NOT_FOUND);
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            throw new ApplicationException("Internal server error", "500", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // bean name | class name | method name | http method |
        return sendResponse(result);
    }
}
