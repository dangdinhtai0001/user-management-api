package com.phoenix.core.service;

import com.google.common.collect.Multimap;
import com.phoenix.core.model.query.SearchCriteria;

import java.util.Map;

@FunctionalInterface
public interface SearchCriteriaConvertor {
    Multimap<String, SearchCriteria> convert(Map<String, Object> searchCriteriaList);
}

