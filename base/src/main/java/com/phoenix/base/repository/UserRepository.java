package com.phoenix.base.repository;

import com.phoenix.base.model.UserPrincipal;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    /**
     * @param username Tài khoản cần tìm kiếm
     * @return Optional của UserPrincipal có username cần tìm, optional empty nếu không tìm thấy
     */
    Optional<UserPrincipal> findUserPrincipalByUsername(String username);

    @Transactional
    List<?> findGroupIdsByUsername(String username);

    int updateRefreshTokenByUsername(String refreshToken, String username);

    @Transactional
    Optional<?> findRefreshTokenByUsername(String username);
}
