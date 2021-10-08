package com.phoenix.base.repository;

import com.google.common.collect.Multimap;

public interface ParameterRepository {
    Multimap<String, String> findAll();
}
