package com.phoenix.base.service;

import com.google.gson.internal.LinkedTreeMap;
import com.phoenix.base.model.business.UserProfile;
import com.phoenix.core.exception.ApplicationException;

import java.util.List;

public interface UserService {
    Object create(LinkedTreeMap<?,?> object) throws ApplicationException;

    List<UserProfile> find(Object object);
}
