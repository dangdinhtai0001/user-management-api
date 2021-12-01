package com.phoenix.base.repository;

import com.phoenix.base.model.ResourceActionModel;
import com.phoenix.common.structure.DefaultTuple;
import com.phoenix.core.model.query.SearchCriteria;

import javax.transaction.Transactional;
import java.util.List;

public interface ServiceMetadataRepository {
    List<ResourceActionModel> findAll(String... fields);

    List<ResourceActionModel> findAll(List<SearchCriteria> criteriaList, String... fields);

    Long insertAll(String[] columnNames, List<ResourceActionModel> resourceActionModels);
}
