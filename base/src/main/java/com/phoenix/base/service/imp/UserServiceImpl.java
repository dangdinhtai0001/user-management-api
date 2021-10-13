package com.phoenix.base.service.imp;

import com.google.gson.internal.LinkedTreeMap;
import com.phoenix.base.constant.BeanIds;
import com.phoenix.base.model.business.UserProfile;
import com.phoenix.base.service.BaseService;
import com.phoenix.base.service.UserService;
import com.phoenix.core.annotation.ApplicationResource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(BeanIds.USER_SERVICES)
@ApplicationResource(displayResource = "user", description = "User service")
public class UserServiceImpl extends BaseService implements UserService {

    @Override
    public Object create(LinkedTreeMap object) {
        return null;
    }

    @Override
    public List<UserProfile> find(Object object) {
        return null;
    }
}
