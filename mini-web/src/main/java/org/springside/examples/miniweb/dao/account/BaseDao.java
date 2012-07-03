package org.springside.examples.miniweb.dao.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springside.examples.miniweb.entity.IdEntity;


public interface BaseDao<T extends IdEntity> extends JpaRepository<T, String>,JpaSpecificationExecutor<T>,QueryDslPredicateExecutor<T>{

}
