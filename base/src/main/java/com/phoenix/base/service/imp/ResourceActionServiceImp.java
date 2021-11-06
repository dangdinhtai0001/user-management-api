package com.phoenix.base.service.imp;

import com.phoenix.base.constant.BeanIds;
import com.phoenix.base.model.ResourceActionModel;
import com.phoenix.base.repository.ResourceActionRepository;
import com.phoenix.base.service.BaseService;
import com.phoenix.base.service.ResourceActionService;
import com.phoenix.common.reflection.MethodUtils;
import com.phoenix.common.structure.DefaultTuple;
import com.phoenix.common.util.ClassUtils;
import com.phoenix.common.util.ReflectionUtil;
import com.phoenix.common.util.StringUtils;
import com.phoenix.core.annotation.ApplicationResource;
import com.phoenix.core.annotation.ApplicationResourceAction;
import com.phoenix.core.model.DefaultAuthenticationToken;
import com.phoenix.core.model.query.SearchCriteria;
import com.phoenix.core.model.query.SearchOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Service(BeanIds.RESOURCE_ACTION_SERVICES)
@Log4j2
public class ResourceActionServiceImp extends BaseService implements ResourceActionService {
    private final ResourceActionRepository resourceActionRepository;
    private final ApplicationContext applicationContext;

    protected ResourceActionServiceImp(
            ApplicationContext applicationContext,
            @Qualifier(BeanIds.RESOURCE_ACTION_REPOSITORY_IMP) ResourceActionRepository resourceActionRepository) {
        this.applicationContext = applicationContext;
        this.resourceActionRepository = resourceActionRepository;
    }

    @Override
    public DefaultAuthenticationToken getCurrentSecurityToken() {
        return null;
    }


    @Override
    public Long saveDataByListClassName(List<Object> listClassName) {
        //Tạo bộ điều kiện để query
        // Chưa rõ vì sao chạy vs list object còn list String thì không
        List<ResourceActionModel> resourceActionList = new LinkedList<>();
        List<Object> allMethodsNamesList = new LinkedList<>();

        loadResourceActionAndMethodName(listClassName, resourceActionList, allMethodsNamesList);

        List<SearchCriteria> criteriaList = new LinkedList<>();
        criteriaList.add(new SearchCriteria("resource", SearchOperation.IN, listClassName));
        criteriaList.add(new SearchCriteria("action", SearchOperation.IN, allMethodsNamesList));

        //Tìm các bản ghi đã tồn tại trong database
        String[] fields = {"id", "action", "resource", "beanName", "displayAction", "displayResource", "httpMethod", "enabled", "description"};
        List<ResourceActionModel> exitsResourceAction = resourceActionRepository.findAll(criteriaList, fields);

        resourceActionList.removeAll(exitsResourceAction);

        try {
            List<DefaultTuple> defaultTuples = convertListObjectToListTuple(resourceActionList, fields);
            long r = resourceActionRepository.insertAll(defaultTuples);

            //Set giá trị này vào bean
            setServiceMetadata(exitsResourceAction);
            setServiceMetadata(resourceActionList);

            return r;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return -1L;
        }
    }

    //region Private methods

    private void loadResourceActionAndMethodName(List<Object> listClassName,
                                                 List<ResourceActionModel> resourceActionList,
                                                 List<Object> allMethodsNamesList) {
        Class<?> instanceClass;
        List<String> methodNameList = new LinkedList<>();
        Annotation annotation;
        String beanName;
        String displayResource, httpMethod = null, description = null, displayPath = null;
        boolean isEnabled = false;

        for (Object className : listClassName) {
            try {
                methodNameList.clear();

                instanceClass = Class.forName(String.valueOf(className));
                beanName = getServiceBeanName(instanceClass);

                List<Method> list = MethodUtils.getMethodsListWithAnnotation(instanceClass, ApplicationResourceAction.class);

                if (list.isEmpty()) {
                    continue;
                }

                for (Method method : list) {
                    methodNameList.add(method.getName());

                    ApplicationResourceAction applicationResourceAction =
                            MethodUtils.getAnnotation(method, ApplicationResourceAction.class, false, true);
                    displayPath = String.valueOf(MethodUtils.invokeMethod(applicationResourceAction, "displayPath"));
                    description = String.valueOf(MethodUtils.invokeMethod(applicationResourceAction, "description"));
                    httpMethod = String.valueOf(MethodUtils.invokeMethod(applicationResourceAction, "httpMethod"));
                    isEnabled = (boolean) MethodUtils.invokeMethod(applicationResourceAction, "isEnabled");
                }

                annotation = instanceClass.getAnnotation(ApplicationResource.class);
                if (StringUtils.isEmpty(description)) {
                    description = String.valueOf(MethodUtils.invokeMethod(annotation, "description"));
                }
                if (StringUtils.isEmpty(httpMethod)) {
                    httpMethod = String.valueOf(MethodUtils.invokeMethod(annotation, "httpMethod"));
                }
                displayResource = String.valueOf(MethodUtils.invokeMethod(annotation, "displayResource"));

                for (String methodName : methodNameList) {
                    resourceActionList.add(buildResourceAction(String.valueOf(className), methodName, beanName,
                            displayResource, displayPath, httpMethod, isEnabled, description));
                }

                allMethodsNamesList.addAll(methodNameList);

            } catch (Exception e) {
                log.error(e);
            }
        }
    }

    private ResourceActionModel buildResourceAction(String resource, String action, String beanName,
                                                    String displayResource, String displayAction,
                                                    String httpMethod, boolean isEnabled,
                                                    String description) {
        ResourceActionModel resourceAction = new ResourceActionModel();

        resourceAction.setResource(resource);
        resourceAction.setAction(action);
        resourceAction.setBeanName(beanName);
        resourceAction.setDisplayResource(displayResource);
        resourceAction.setDisplayAction(displayAction);
        resourceAction.setHttpMethod(httpMethod);
        resourceAction.setEnabled(isEnabled);
        resourceAction.setDescription(description);

        return resourceAction;
    }

    /**
     * <h1> Lấy thuộc tính value của @service của class </h1>
     *
     * @param aClass : Class cần lấy value của @Service
     * @return thuộc tính value của @service
     */
    private String getServiceBeanName(Class<?> aClass) {
        //  Tìm kiếm @Service trong class
        Annotation annotation = aClass.getAnnotation(Service.class);

        //  Nếu không có @Service thì trả về null luôn
        if (annotation == null) {
            return null;
        }

        try {
            // Gọi hàm "value" trong anotation @Service (Tương đương vs việc lấy giá trị của thuộc tính đấy ra)
            Object o = MethodUtils.invokeMethod(annotation, "value");
            return String.valueOf(o);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void setServiceMetadata(List<ResourceActionModel> list) {
        String key;
        for (ResourceActionModel model : list) {
            key = model.getDisplayResource() + "|" + model.getDisplayAction();
            //noinspection unchecked
            applicationContext.getBean(BeanIds.APPLICATION_SERVICE_METADATA, HashMap.class).put(key, model);
        }

    }

    // endregion
}
