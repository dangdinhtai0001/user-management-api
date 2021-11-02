package com.phoenix.business.service.imp;

import com.google.gson.internal.LinkedTreeMap;
import com.phoenix.base.constant.BeanIds;
import com.phoenix.business.model.UserProfile;
import com.phoenix.base.repository.UserRepository;
import com.phoenix.base.service.BaseService;
import com.phoenix.business.service.UserService;
import com.phoenix.core.annotation.ApplicationResource;
import com.phoenix.core.config.DefaultExceptionCode;
import com.phoenix.core.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(BeanIds.USER_SERVICES)
@ApplicationResource(displayResource = "user", description = "User service")
public class UserServiceImpl extends BaseService implements UserService {

    private final String USERNAME_EXISTS_CODE = "002001";

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public Object create(LinkedTreeMap<?, ?> object) throws ApplicationException {
        String username = getPropertyOfRequestBodyByKey(object, "username", String.class);
        String password = getPropertyOfRequestBodyByKey(object, "password", String.class);

        validateCreatePayload(username, password);

        String encodedPassword = passwordEncoder.encode(password);

        return userRepository.createUser(username, encodedPassword);
    }

    private void validateCreatePayload(String username, String password) throws ApplicationException {
        if (username == null || password == null) {
            throw getApplicationException(DefaultExceptionCode.BAD_REQUEST);
        }
        if (userRepository.isExistsUsername(username)) {
            throw getApplicationException(USERNAME_EXISTS_CODE);
        }
    }

    // ============================================================================================================

    @Override
    public List<UserProfile> find(Object object) {
        return null;
    }

    @Autowired
    public void setPasswordEncoder(@Qualifier(BeanIds.PASSWORD_ENCODER) PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserRepository(@Qualifier(BeanIds.BASE_USER_REPOSITORY_IMP) UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
