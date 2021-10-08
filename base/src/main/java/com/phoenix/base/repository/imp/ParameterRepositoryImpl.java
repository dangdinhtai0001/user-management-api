package com.phoenix.base.repository.imp;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import com.phoenix.base.constant.BeanIds;
import com.phoenix.base.model.querydsl.QFwParameter;
import com.phoenix.base.repository.ParameterRepository;
import com.phoenix.core.repository.AbstractSingleQueryDslRepository;
import com.querydsl.core.Tuple;
import com.querydsl.sql.RelationalPathBase;
import com.querydsl.sql.SQLQueryFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(BeanIds.APPLICATION_PARAMETER_REPOSITORY_IMP)
public class ParameterRepositoryImpl extends AbstractSingleQueryDslRepository implements ParameterRepository {

    protected ParameterRepositoryImpl(
            @Qualifier(BeanIds.SQL_QUERY_FACTORY) SQLQueryFactory queryFactory
    ) {
        super(queryFactory);
    }

    // -------------------------------------------------------------------------------------
    // -------------------------------------------------------------------------------------
    // -------------------------------------------------------------------------------------

    @Override
    public Multimap<String, String> findAll() {
        List<Tuple> queryResult = this.defaultFindAll("key_", "value_");

        Multimap<String, String> multimap = LinkedListMultimap.create();

        for (Tuple tuple : queryResult) {
            multimap.put(tuple.get(0, String.class), tuple.get(1, String.class));
        }

        return multimap;
    }

    // -------------------------------------------------------------------------------------
    // -------------------------------------------------------------------------------------
    // -------------------------------------------------------------------------------------

    @SuppressWarnings("unchecked")
    @Override
    protected Class<QFwParameter> getRelationalPathBaseClass() {
        return QFwParameter.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected RelationalPathBase<QFwParameter> getRelationalPathBase() {
        return new QFwParameter("fw_parameter", getDefaultSchemaName());
    }


}
