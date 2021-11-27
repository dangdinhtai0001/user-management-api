package com.phoenix.business.service;

import com.phoenix.business.model.UserProfile;
import com.phoenix.core.exception.ApplicationException;

import java.util.List;
import java.util.Map;

public interface UserService {
    Object create(Map<String,Object> object) throws ApplicationException;

    List<UserProfile> find(Object object);
}
