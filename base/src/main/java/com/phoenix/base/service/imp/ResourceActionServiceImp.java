package com.phoenix.base.service.imp;

import com.phoenix.base.constant.BeanIds;
import com.phoenix.base.model.ResourceActionModel;
import com.phoenix.base.repository.ResourceActionRepository;
import com.phoenix.base.service.BaseService;
import com.phoenix.base.service.ResourceActionService;
import com.phoenix.common.structure.Tuple;
import com.phoenix.common.util.ReflectionUtil;
import com.phoenix.core.model.DefaultAuthenticationToken;
import com.phoenix.core.model.query.SearchCriteria;
import com.phoenix.core.model.query.SearchOperation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

@Service(BeanIds.RESOURCE_ACTION_SERVICES)
public class ResourceActionServiceImp extends BaseService implements ResourceActionService {
    private final ResourceActionRepository resourceActionRepository;

    protected ResourceActionServiceImp(
            ApplicationContext applicationContext,
            @Qualifier(BeanIds.RESOURCE_ACTION_REPOSITORY_IMP) ResourceActionRepository resourceActionRepository) {
        super(applicationContext);
        this.resourceActionRepository = resourceActionRepository;
    }

    @Override
    public DefaultAuthenticationToken getCurrentSecurityToken() {
        return null;
    }


    @Override
    public Long saveDataByListClassName(List<String> listClassName) {
        List<ResourceActionModel> resourceActionList = new LinkedList<>();
        List<String> allMethodsNamesList = new LinkedList<>();
        Class<?> instanceClass;
        List<String> methodsName;

        for (String className : listClassName) {
            try {
                instanceClass = Class.forName(className);
                methodsName = ReflectionUtil.getAllDeclaredMethodsMethodNames(instanceClass);

                getServiceBeanName(instanceClass, Service.class, "value");

                allMethodsNamesList.addAll(methodsName);

                for (String methodName : methodsName) {
                    resourceActionList.add(getResourceAction(className, methodName, null));
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        List<SearchCriteria> criteriaList = new LinkedList<>();
        criteriaList.add(new SearchCriteria("resource", SearchOperation.IN, listClassName));
        criteriaList.add(new SearchCriteria("action", SearchOperation.IN, allMethodsNamesList));

        String[] fields = {"id", "action", "resource", "description"};
        List<ResourceActionModel> exitsResourceAction = resourceActionRepository.findAll(criteriaList, fields);

        resourceActionList.removeAll(exitsResourceAction);

        try {
            List<Tuple> tuples = convertListObjectToListTuple(resourceActionList, fields);
            return resourceActionRepository.insertAll(tuples);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return -1L;
        }
//        return resourceActionRepository.saveAllAndFlush(resourceActionList);
    }

    //******************************************************************************************************
    //******************************** region private methods
    //******************************************************************************************************

    private ResourceActionModel getResourceAction(String resource, String action, String description) {
        ResourceActionModel resourceAction = new ResourceActionModel();

        resourceAction.setResource(resource);
        resourceAction.setAction(action);
        resourceAction.setDescription(description);

        return resourceAction;
    }

    private String getServiceBeanName(Class<?> aClass, Class<?> annotationClass, String attribute) {
        Annotation annotation = ReflectionUtil.getAnnotationOfClass(aClass, annotationClass);

        if(annotation == null){
            return null;
        }

        for (Method method : annotationClass.getDeclaredMethods()) {
            if (attribute.equals(method.getName())) {
                try {
                    return String.valueOf(method.invoke(annotation));
                } catch (IllegalAccessException | InvocationTargetException e) {
                    return null;
                }
            }
        }

        return null;
    }
}
