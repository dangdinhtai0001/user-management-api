package com.phoenix.base.repository2;

import com.phoenix.core.repository2.AbstractCoreNativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("AbstractCoreNativeQueryImp")
public class AbstractCoreNativeQueryImp extends AbstractCoreNativeQuery {
    @Override
    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
