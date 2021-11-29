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
    Optional<UserPrincipal> findUserPrincipalByUsername(String username) throws IllegalAccessException;

    List<?> findGroupIdsByUsername(String username);

    int updateRefreshTokenByUsername(String refreshToken, String username);

    Optional<?> findRefreshTokenByUsername(String username);

    long createUser(String username, String encodedPassword);

    /**
     * @param username Tên tài khoản
     * @return trả về true nếu đã tồn tại tên tài khoản, false nếu ngược lại
     */
    boolean isExistsUsername(String username);
}
