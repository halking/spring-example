package com.hal.labs.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseDao<E, P> extends JpaRepository<E, P>, JpaSpecificationExecutor<E> {

}
