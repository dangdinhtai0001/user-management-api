package com.phoenix.core.service;

import com.phoenix.common.structure.DefaultTuple;
import com.phoenix.core.exception.ApplicationException;
import com.phoenix.core.repository.SingleQueryDslRepository;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public abstract class AbstractDefaultCrudService extends AbstractCoreService implements DefaultCrudService {
    protected final SingleQueryDslRepository repository;

    protected AbstractDefaultCrudService(SingleQueryDslRepository repository) {
        this.repository = repository;
    }

    @Override
    public <T> Long create(T object, String... fields) throws ApplicationException {
        try {
            DefaultTuple defaultTuples = convertObjectToTuple(object, fields);
            return repository.defaultInsert(defaultTuples);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            log.error("Unable to create object.", e);
        }
        return -1L;
    }

    @Override
    public <T> Long createAll(List<T> list, String... fields) throws ApplicationException {
        try {
            List<DefaultTuple> defaultTuples = convertListObjectToListTuple(list, fields);
            return repository.defaultInsert(defaultTuples);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            log.error("Unable to create list object.", e);
        }
        return -1L;
    }

    protected abstract <T> Class<T> getDefaultModelClass();

}
