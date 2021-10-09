package com.phoenix.base.controller.imp;

import com.phoenix.base.constant.BeanIds;
import com.phoenix.base.controller.BaseController;
import com.phoenix.base.model.ResourceActionModel;
import com.phoenix.common.util.ReflectionUtil;
import com.phoenix.core.controller.AbstractCoreController;
import com.phoenix.core.exception.ApplicationException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@RestController
public class BaseControllerImpl extends AbstractCoreController implements BaseController {

    private final ApplicationContext applicationContext;
    private final Map<String, ResourceActionModel> serviceMetadata;

    public BaseControllerImpl(ApplicationContext applicationContext,
                              @Qualifier(BeanIds.APPLICATION_SERVICE_METADATA) Map<String, ResourceActionModel> serviceMetadata) {
        this.applicationContext = applicationContext;
        this.serviceMetadata = serviceMetadata;
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
        Object result;

        try {
            ResourceActionModel metadata = serviceMetadata.getOrDefault(service, null);

            if (metadata == null ) {
                throw new ApplicationException("Can't not found this path.", "404", HttpStatus.NOT_FOUND);
            }

            Object beanObject = applicationContext.getBean(metadata.getBeanName());

            result = ReflectionUtil.invokeMethod(beanObject, action);
        } catch (NoSuchMethodException | NoSuchBeanDefinitionException e) {
            throw new ApplicationException("Can't not found this path.", "404", HttpStatus.NOT_FOUND);
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            throw new ApplicationException("Internal server error", "500", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return sendResponse(result);
    }
}
