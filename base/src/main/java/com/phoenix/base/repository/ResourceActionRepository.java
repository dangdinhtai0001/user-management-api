package com.phoenix.base.repository;

import com.phoenix.base.model.ResourceActionModel;
import com.phoenix.common.structure.DefaultTuple;
import com.phoenix.core.model.query.SearchCriteria;

import java.util.List;

public interface ResourceActionRepository {
    List<ResourceActionModel> findAll(String... fields);

    List<ResourceActionModel> findAll(List<SearchCriteria> criteriaList, String... fields);

    Long insertAll(List<DefaultTuple> defaultTuples);
}
