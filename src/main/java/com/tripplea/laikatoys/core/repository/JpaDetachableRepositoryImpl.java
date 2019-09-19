package com.tripplea.laikatoys.core.repository;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class JpaDetachableRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
        implements JpaDetachableRepository<T, ID> {
    private EntityManager entityManager;

    public JpaDetachableRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public void detach(T t) {
        entityManager.detach(t);
    }
}
