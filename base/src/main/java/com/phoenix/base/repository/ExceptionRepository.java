package com.phoenix.base.repository;

import com.phoenix.core.model.DefaultException;

import java.io.Serializable;
import java.util.List;

public interface ExceptionRepository {
    <T extends DefaultException<E>, E extends Serializable> List<T> findAll();
}
