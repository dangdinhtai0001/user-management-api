package com.phoenix.base.repository.imp;


import com.phoenix.base.constant.BeanIds;
import com.phoenix.base.model.UserPrincipal;
import com.phoenix.base.model.querydsl.QFwUser;
import com.phoenix.base.model.querydsl.QFwUserStatus;
import com.phoenix.base.repository.UserRepository;
import com.phoenix.core.repository2.AbstractCoreQueryDslRepository;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Path;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository(BeanIds.BASE_USER_REPOSITORY_IMP)
@Log4j2
public class DefaultUserDetailsRepository extends AbstractCoreQueryDslRepository implements UserRepository {
    @Value("${spring.datasource.username}")
    private String datasourceUsername;

    @Override
    public Optional<UserPrincipal> findUserPrincipalByUsername(String username) throws IllegalAccessException {
        Path<?>[] expressions = {QFwUser.fwUser.id, QFwUser.fwUser.username, QFwUser.fwUser.password,
                QFwUser.fwUser.hashAlgorithm, QFwUser.fwUser.passwordSalt, QFwUserStatus.fwUserStatus.name};

        SQLQuery<Tuple> query = this.queryFactory
                .select(expressions)
                .from(this.getQuerySource(QFwUser.class, "fw_user"))
                .rightJoin(this.getQuerySource(QFwUserStatus.class, "fw_user_status"))
                .on(QFwUser.fwUser.statusId.eq(QFwUserStatus.fwUserStatus.id))
                .where(QFwUser.fwUser.username.eq(username));
        ;

        log.debug(query.getSQL().getSQL());

        List<Tuple> queryResult = query.fetch();
        if (queryResult.isEmpty()) {
            return Optional.empty();
        }

        Tuple record = queryResult.get(0);
        UserPrincipal userPrincipal = new UserPrincipal();

        userPrincipal.setId(record.get(0, Integer.class));
        userPrincipal.setUsername(record.get(1, String.class));
        userPrincipal.setPassword(record.get(2, String.class));
        userPrincipal.setHashAlgorithm(record.get(3, String.class));
        userPrincipal.setPasswordSalt(record.get(4, String.class));
        userPrincipal.setStatus(record.get(5, String.class));

        log.debug(query.getSQL().getSQL());

        return Optional.of(userPrincipal);
    }

    @Override
    public List<?> findGroupIdsByUsername(String username) {
        return null;
    }

    @Override
    public int updateRefreshTokenByUsername(String refreshToken, String username) {
        return 0;
    }

    @Override
    public Optional<?> findRefreshTokenByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public long createUser(String username, String encodedPassword) {
        return 0;
    }

    @Override
    public boolean isExistsUsername(String username) {
        return false;
    }

    // region abstract methods

    @Override
    public String getDefaultSchemaName() {
        return this.datasourceUsername;
    }

    @Autowired
    @Override
    protected void setDefaultSQLQueryFactory(@Qualifier(BeanIds.SQL_QUERY_FACTORY) SQLQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    // region
}
