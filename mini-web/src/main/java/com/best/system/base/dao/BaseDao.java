package com.best.system.base.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.best.system.base.entity.IdEntity;


public interface BaseDao<T extends IdEntity> extends JpaRepository<T, String>,JpaSpecificationExecutor<T>,QueryDslPredicateExecutor<T>{

}
