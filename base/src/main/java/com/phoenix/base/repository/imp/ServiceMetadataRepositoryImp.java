package com.phoenix.base.repository.imp;

import com.phoenix.base.constant.BeanIds;
import com.phoenix.base.model.ResourceActionModel;
import com.phoenix.base.model.querydsl.QFwServiceMetadata;
import com.phoenix.base.repository.ServiceMetadataRepository;
import com.phoenix.common.exceptions.SupportException;
import com.phoenix.common.reflection.FieldUtils;
import com.phoenix.common.structure.DefaultTuple;
import com.phoenix.common.util.MapUtils;
import com.phoenix.core.model.query.SearchCriteria;
import com.phoenix.core.repository2.AbstractCrudQueryDslRepository;
import com.querydsl.sql.SQLQueryFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log4j2
@Repository(BeanIds.RESOURCE_ACTION_REPOSITORY_IMP)
public class ServiceMetadataRepositoryImp
        extends AbstractCrudQueryDslRepository<QFwServiceMetadata> implements ServiceMetadataRepository {

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
            //e.printStackTrace();
            log.warn(e.getMessage(), e);
            return null;
        }
    }

    @Override
    @Transactional
    public Long insertAll(String[] columnNames, List<ResourceActionModel> resourceActionModels) {
        if (resourceActionModels == null || resourceActionModels.size() == 0) {
            return 0L;
        }
        List<Object[]> values = new ArrayList<>(resourceActionModels.size());
        Field[] allFields = FieldUtils.getAllFields(ResourceActionModel.class);


        for (ResourceActionModel resourceActionModel : resourceActionModels) {
            Object[] value = new Object[columnNames.length];

            for (int j = 0; j < allFields.length; j++) {
                try {
                    value[j] = FieldUtils.readField(allFields[j], resourceActionModel, true);
                } catch (IllegalAccessException e) {
                    log.warn("{}", e.getMessage());
                    value[j] = null;
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
