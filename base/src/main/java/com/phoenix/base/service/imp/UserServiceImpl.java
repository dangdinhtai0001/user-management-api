package com.phoenix.base.service.imp;

import com.google.gson.internal.LinkedTreeMap;
import com.phoenix.base.constant.ApplicationConstant;
import com.phoenix.base.constant.BeanIds;
import com.phoenix.base.model.business.UserProfile;
import com.phoenix.base.service.BaseService;
import com.phoenix.base.service.UserService;
import com.phoenix.core.annotation.ApplicationResource;
import com.phoenix.core.config.DefaultExceptionCode;
import com.phoenix.core.exception.ApplicationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(BeanIds.USER_SERVICES)
@ApplicationResource(displayResource = "user", description = "User service")
public class UserServiceImpl extends BaseService implements UserService {

    @Override
    public Object create(LinkedTreeMap<?, ?> object) throws ApplicationException {
        String username = getPropertyOfRequestBodyByKey(object, "username", String.class);
        String password = getPropertyOfRequestBodyByKey(object, "password", String.class);

        validateCreatePayload(username, password);


        return null;
    }

    private void validateCreatePayload(String username, String password) throws ApplicationException {
        if (username == null || password == null) {
            throw getApplicationException(DefaultExceptionCode.BAD_REQUEST);
        }
    }

    @Override
    public List<UserProfile> find(Object object) {
        return null;
    }
}
