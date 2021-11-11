package com.phoenix.base.repository.imp;

import com.phoenix.base.constant.BeanIds;
import com.phoenix.base.model.ResourceActionModel;
import com.phoenix.base.model.querydsl.QFwServiceMetadata;
import com.phoenix.base.repository.ResourceActionRepository;
import com.phoenix.common.structure.DefaultTuple;
import com.phoenix.core.model.query.SearchCriteria;
import com.phoenix.core.repository.AbstractSingleQueryDslRepository;
import com.querydsl.core.Tuple;
import com.querydsl.sql.RelationalPathBase;
import com.querydsl.sql.SQLQueryFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository(BeanIds.RESOURCE_ACTION_REPOSITORY_IMP)
@Repository("ResourceActionRepositoryImp")
public class ResourceActionRepositoryImp extends AbstractSingleQueryDslRepository implements ResourceActionRepository {
    protected ResourceActionRepositoryImp(
            @Qualifier(BeanIds.SQL_QUERY_FACTORY) SQLQueryFactory queryFactory
    ) {
        super(queryFactory);
    }

    @Override
    public List<ResourceActionModel> findAll(String... fields) {
        List<Tuple> queryResult = this.defaultFindAll(fields);
        return parseResult(queryResult, ResourceActionModel.class, fields);
    }

    @Override
    public List<ResourceActionModel> findAll(List<SearchCriteria> criteriaList, String... fields) {
        List<Tuple> queryResult = this.defaultFindAll(criteriaList, fields);

        return parseResult(queryResult, ResourceActionModel.class, fields);
    }

    @Override
    public Long insertAll(List<DefaultTuple> defaultTuples) {
        return defaultInsert(defaultTuples);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Class<QFwServiceMetadata> getRelationalPathBaseClass() {
        return QFwServiceMetadata.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected RelationalPathBase<QFwServiceMetadata> getRelationalPathBase() {
        return new QFwServiceMetadata("fw_resource_action", getDefaultSchemaName());
    }

}
