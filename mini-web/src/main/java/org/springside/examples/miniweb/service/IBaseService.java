package org.springside.examples.miniweb.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springside.examples.miniweb.entity.IdEntity;

import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;

public interface IBaseService<T extends IdEntity> {
	public T save(T entity);

	public T findOne(Predicate predicate);

	public T findOne(Specification<T> spec);

	public List<T> findAll();

	public Iterable<T> findAll(Predicate predicate);

	public List<T> findAll(Specification<T> spec);

	public List<T> findAll(Sort sort);

	public T findOne(String id);

	public Iterable<T> findAll(Predicate predicate, OrderSpecifier<?>... orders);

	public Page<T> findAll(Pageable pageable);

	public Page<T> findAll(Specification<T> spec, Pageable pageable);

	public List<T> save(Iterable<? extends T> entities);

	public boolean exists(String id);

	public List<T> findAll(Specification<T> spec, Sort sort);

	public void flush();

	public Page<T> findAll(Predicate predicate, Pageable pageable);

	public T saveAndFlush(T entity);

	public long count(Specification<T> spec);

	public void deleteInBatch(Iterable<T> entities);

	public long count(Predicate predicate);

	public long count();

	public void delete(String id);

	public void delete(T entity);

	public void delete(Iterable<? extends T> entities);

	public void deleteAll();
	
	public void delete(String[] ids);
}
