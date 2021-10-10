package com.phoenix.base.service.imp;

import com.phoenix.base.constant.BeanIds;
import com.phoenix.base.model.ResourceActionModel;
import com.phoenix.base.repository.ResourceActionRepository;
import com.phoenix.base.service.BaseService;
import com.phoenix.base.service.ResourceActionService;
import com.phoenix.common.structure.Tuple;
import com.phoenix.common.util.ReflectionUtil;
import com.phoenix.core.annotation.ApplicationResource;
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
        List<ResourceActionModel> resourceActionList = new LinkedList<>();
        List<Object> allMethodsNamesList = new LinkedList<>();
        Class<?> instanceClass;
        List<String> methodsName;
        String beanName;
        String displayResource, displayAction, description;

        for (Object className : listClassName) {
            try {
                instanceClass = Class.forName(String.valueOf(className));
                methodsName = ReflectionUtil.getAllDeclaredMethodsMethodNames(instanceClass);

                beanName = getServiceBeanName(instanceClass);

                allMethodsNamesList.addAll(methodsName);

                description = (String) ReflectionUtil.getValueOfAnnotationAttribute(instanceClass, ApplicationResource.class, "description");
                displayResource = (String) ReflectionUtil.getValueOfAnnotationAttribute(instanceClass, ApplicationResource.class, "displayResource");
                displayAction = (String) ReflectionUtil.getValueOfAnnotationAttribute(instanceClass, ApplicationResource.class, "displayAction");

                for (String methodName : methodsName) {
                    resourceActionList.add(getResourceAction(String.valueOf(className), methodName, beanName, displayResource, displayAction, description));
                }

            } catch (Exception e) {
                log.error(e);
            }
        }

        List<ResourceActionModel> allResource = new LinkedList<>(resourceActionList);

        //Tạo bộ điều kiện để query
        // Chưa rõ vì sao chạy vs list object còn list String thì không
        List<SearchCriteria> criteriaList = new LinkedList<>();
        criteriaList.add(new SearchCriteria("resource", SearchOperation.IN, listClassName));
        criteriaList.add(new SearchCriteria("action", SearchOperation.IN, allMethodsNamesList));

        //Tìm các bản ghi đã tồn tại trong database
        String[] fields = {"id", "action", "resource", "beanName", "displayAction", "displayResource", "description"};
        List<ResourceActionModel> exitsResourceAction = resourceActionRepository.findAll(criteriaList, fields);

        resourceActionList.removeAll(exitsResourceAction);

        try {
            List<Tuple> tuples = convertListObjectToListTuple(resourceActionList, fields);
            long r = resourceActionRepository.insertAll(tuples);

            //Set giá trị này vào bean
            setServiceMetadata(exitsResourceAction);
            setServiceMetadata(resourceActionList);

            return r;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return -1L;
        }
//        return resourceActionRepository.saveAllAndFlush(resourceActionList);
    }

    //******************************************************************************************************
    //******************************** region private methods
    //******************************************************************************************************

    private ResourceActionModel getResourceAction(String resource, String action, String beanName,
                                                  String displayResource, String displayAction, String description) {
        ResourceActionModel resourceAction = new ResourceActionModel();

        resourceAction.setResource(resource);
        resourceAction.setAction(action);
        resourceAction.setBeanName(beanName);
        resourceAction.setDisplayResource(displayResource);
        resourceAction.setDisplayAction(displayAction);
        resourceAction.setDescription(description);

        return resourceAction;
    }

    /**
     * @param aClass : Class cần lấy value của @Service
     * @return thuộc tính value của @service
     */
    private String getServiceBeanName(Class<?> aClass) {
        Annotation annotation = ReflectionUtil.getAnnotationOfClass(aClass, Service.class);

        if (annotation == null) {
            return null;
        }

        for (Method method : Service.class.getDeclaredMethods()) {
            if ("value".equals(method.getName())) {
                try {
                    return String.valueOf(method.invoke(annotation));
                } catch (IllegalAccessException | InvocationTargetException e) {
                    return null;
                }
            }
        }

        return null;
    }

    private void setServiceMetadata(List<ResourceActionModel> list) {
        for (ResourceActionModel model : list) {
            applicationContext.getBean(BeanIds.APPLICATION_SERVICE_METADATA, HashMap.class).put(model.getDisplayResource(), model);
        }

    }
}
