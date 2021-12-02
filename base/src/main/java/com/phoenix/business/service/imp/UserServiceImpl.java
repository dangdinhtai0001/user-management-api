package com.phoenix.business.service.imp;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import com.phoenix.base.constant.BeanIds;
import com.phoenix.base.model.querydsl.QFwUser;
import com.phoenix.base.model.querydsl.QFwUserStatus;
import com.phoenix.base.repository.UserRepository;
import com.phoenix.base.service.BaseService;
import com.phoenix.business.service.UserService;
import com.phoenix.common.util.MapUtils;
import com.phoenix.core.annotation.ApplicationResource;
import com.phoenix.core.annotation.ApplicationResourceAction;
import com.phoenix.core.config.DefaultExceptionCode;
import com.phoenix.core.exception.ApplicationException;
import com.phoenix.core.model.query.SearchCriteria;
import com.phoenix.core.model.query.SearchCriteriaRequest;
import com.querydsl.core.types.Path;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@Service(BeanIds.USER_SERVICES)
@ApplicationResource(displayResource = "user", description = "User service")
@Log4j2
public class UserServiceImpl extends BaseService implements UserService {

    private final String USERNAME_EXISTS_CODE = "002001";
    private final String USERNAME_KEY = "username";
    private final String PASSWORD_KEY = "password";
    private final String SEARCH_CRITERIA_KEY = "searchCriteria";

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @ApplicationResourceAction(displayPath = "create", httpMethod = "POST", description = "Create user")
    @Override
    @Transactional
    public Object create(Map<String, Object> object) throws ApplicationException {
        String username = MapUtils.getProperty(object, USERNAME_KEY);
        String password = MapUtils.getProperty(object, PASSWORD_KEY);

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

    @ApplicationResourceAction(displayPath = "find", httpMethod = "POST", description = "Find user")
    @Override
    @Transactional
    public List<Map<String, Object>> find(Map<String, Object> object) {
        List<Map<String, Object>> searchCriteria = MapUtils.getProperty(object, SEARCH_CRITERIA_KEY);

        Multimap<String, SearchCriteria> searchCriteriaMap = formatSearchCriteriaList(searchCriteria);

        Path<?>[] expressions = {QFwUser.fwUser.id, QFwUser.fwUser.username, QFwUserStatus.fwUserStatus.name};
        String[] names = {"id", "username", "status"};
        Class<?>[] types = {Long.class, String.class, String.class};

        return userRepository.findUserBy(searchCriteriaMap, expressions, names, types);
    }

    @Override
    protected Multimap<String, SearchCriteria> formatSearchCriteriaList(List<Map<String, Object>> searchCriteria) {
        Multimap<String, SearchCriteria> searchCriteriaMap = LinkedListMultimap.create(searchCriteria.size());

        for (Map<String, Object> searchCriteriaItem : searchCriteria) {
            if (MapUtils.getProperty(searchCriteriaItem, "key").equals("status")) {
                try {
                    searchCriteriaMap.put("fw_user_status", MapUtils.convertMap2Object(searchCriteriaItem, SearchCriteriaRequest.class).getSearchCriteria());
                } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException | InstantiationException e) {
                    log.warn("Unable to convert", e);
                }
            } else {
                try {
                    searchCriteriaMap.put("fw_user", MapUtils.convertMap2Object(searchCriteriaItem, SearchCriteriaRequest.class).getSearchCriteria());
                } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException | InstantiationException e) {
                    log.warn("Unable to convert", e);
                }
            }
        }

        return searchCriteriaMap;
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
