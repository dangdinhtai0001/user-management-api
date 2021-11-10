package com.phoenix.base.repository2.querydsl;

import com.phoenix.base.constant.BeanIds;
import com.phoenix.base.model.querydsl.QFwException;
import com.phoenix.core.repository2.AbstractCrudQueryDslRepository;
import com.querydsl.sql.SQLQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository("AbstractCrudQueryDslRepositoryImp")
public class AbstractCrudQueryDslRepositoryImp extends AbstractCrudQueryDslRepository<QFwException> {
    @Value("${spring.datasource.username}")
    private String datasourceUsername;

    public AbstractCrudQueryDslRepositoryImp() {
        super(QFwException.class);
    }

    @Override
    public String getDefaultSchemaName() {
        return datasourceUsername;
    }

    @Autowired
    @Override
    protected void setDefaultSQLQueryFactory(@Qualifier(BeanIds.SQL_QUERY_FACTORY) SQLQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }
}
