package com.phoenix.base.service;

import com.phoenix.base.model.business.UserProfile;

import java.util.List;

public interface UserService {
    void create(Object object);

    List<UserProfile> find(Object object);
}
