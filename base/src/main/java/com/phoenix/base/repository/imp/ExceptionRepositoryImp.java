package com.phoenix.base.repository.imp;

import com.phoenix.base.constant.BeanIds;
import com.phoenix.base.model.ExceptionModel;
import com.phoenix.base.model.querydsl.QFwException;
import com.phoenix.base.repository.ExceptionRepository;
import com.phoenix.core.repository.AbstractSingleQueryDslRepository;
import com.querydsl.core.Tuple;
import com.querydsl.sql.RelationalPathBase;
import com.querydsl.sql.SQLQueryFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(BeanIds.EXCEPTION_REPOSITORY_IMP)
public class ExceptionRepositoryImp extends AbstractSingleQueryDslRepository implements ExceptionRepository {

    protected ExceptionRepositoryImp(
            @Qualifier(BeanIds.SQL_QUERY_FACTORY) SQLQueryFactory queryFactory) {
        super(queryFactory);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ExceptionModel> findAll() {
        String[] columns = {"id", "code_", "message_", "httpCode"};
        String[] fields = {"id", "code", "message", "httpCode"};
        List<Tuple> queryResult = defaultFindAll(columns);

        return parseResult(queryResult, ExceptionModel.class, fields);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Class<QFwException> getRelationalPathBaseClass() {
        return QFwException.class;
    }


    @SuppressWarnings("unchecked")
    @Override
    protected RelationalPathBase<QFwException> getRelationalPathBase() {
        return new QFwException("fw_exception", getDefaultSchemaName());
    }


}
