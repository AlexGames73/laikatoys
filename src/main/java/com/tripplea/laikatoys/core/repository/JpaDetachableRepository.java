package com.tripplea.laikatoys.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface JpaDetachableRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
    void detach(T t);
}
