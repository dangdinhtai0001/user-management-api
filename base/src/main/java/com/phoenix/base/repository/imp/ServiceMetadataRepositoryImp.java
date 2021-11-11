package com.phoenix.base.repository.imp;

import com.phoenix.base.constant.BeanIds;
import com.phoenix.base.model.ResourceActionModel;
import com.phoenix.base.model.querydsl.QFwServiceMetadata;
import com.phoenix.base.repository.ResourceActionRepository;
import com.phoenix.base.repository.ServiceMetadataRepository;
import com.phoenix.common.exceptions.SupportException;
import com.phoenix.common.structure.DefaultTuple;
import com.phoenix.common.util.MapUtils;
import com.phoenix.core.model.query.SearchCriteria;
import com.phoenix.core.repository2.AbstractCrudQueryDslRepository;
import com.querydsl.sql.SQLQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository(BeanIds.RESOURCE_ACTION_REPOSITORY_IMP)
public class ServiceMetadataRepositoryImp
        extends AbstractCrudQueryDslRepository<QFwServiceMetadata> implements ResourceActionRepository {

    @Value("${spring.datasource.username}")
    private String datasourceUsername;

    protected ServiceMetadataRepositoryImp() {
        super(QFwServiceMetadata.class);
    }

    @Override
    public String getDefaultSchemaName() {
        return this.datasourceUsername;
    }

    @Autowired
    @Override
    protected void setDefaultSQLQueryFactory(@Qualifier(BeanIds.SQL_QUERY_FACTORY) SQLQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    @Transactional
    public List<ResourceActionModel> findAll(String... fields) {
        try {
            List<Map<String, Object>> list = this.findByCondition(fields, null);
            return MapUtils.convertMap2Object(list, ResourceActionModel.class);
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException | InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public List<ResourceActionModel> findAll(List<SearchCriteria> criteriaList, String... fields) {
        try {
            List<Map<String, Object>> list = this.findByCondition(fields, null, criteriaList);
            return MapUtils.convertMap2Object(list, ResourceActionModel.class);
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException | InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public Long insertAll(List<DefaultTuple> defaultTuples) {
        if (defaultTuples == null || defaultTuples.size() == 0) {
            return 0L;
        }
        String[] columnNames = defaultTuples.get(0).getArrayExpressions();
        List<Object[]> values = new ArrayList<>(defaultTuples.size());

        for (int i = 0; i < defaultTuples.size(); i++) {
            Object[] value = new Object[columnNames.length];
            for (int j = 0; j < columnNames.length; j++) {
                try {
                    value[j] = defaultTuples.get(i).get(j);
                } catch (SupportException e) {
                    value[i] = null;
                }
            }
            values.add(value);
        }
        try {
            return this.create(QFwServiceMetadata.class, columnNames, values);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            return 0L;
        }
    }


}
