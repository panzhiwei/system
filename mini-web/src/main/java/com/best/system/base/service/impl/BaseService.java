package com.best.system.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import com.best.system.base.dao.BaseDao;
import com.best.system.base.entity.IdEntity;
import com.best.system.base.service.IBaseService;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;

@Transactional(readOnly = true)
public abstract class BaseService<T extends IdEntity, D extends BaseDao<T>> implements IBaseService<T> {

	private D baseDao;

	public D getBaseDao() {
		return baseDao;
	}

	@Autowired
	public void setBaseDao(D temp) {
		this.baseDao = temp;
	}

	@Override
	@Transactional(readOnly = false)
	public T save(T entity) {
		return baseDao.save(entity);
	}

	@Override
	public T findOne(Predicate predicate) {
		return baseDao.findOne(predicate);
	}

	@Override
	public T findOne(Specification<T> spec) {
		return baseDao.findOne(spec);
	}

	@Override
	public List<T> findAll() {
		return baseDao.findAll();
	}

	@Override
	public Iterable<T> findAll(Predicate predicate) {
		return baseDao.findAll(predicate);
	}

	@Override
	public List<T> findAll(Specification<T> spec) {
		return baseDao.findAll(spec);
	}

	@Override
	public List<T> findAll(Sort sort) {
		return baseDao.findAll(sort);
	}

	@Override
	public T findOne(String id) {
		return baseDao.findOne(id);
	}

	@Override
	public Iterable<T> findAll(Predicate predicate, OrderSpecifier<?>... orders) {
		return baseDao.findAll(predicate, orders);
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		return baseDao.findAll(pageable);
	}

	@Override
	public Page<T> findAll(Specification<T> spec, Pageable pageable) {
		return baseDao.findAll(spec, pageable);
	}

	@Override
	@Transactional(readOnly = false)
	public List<T> save(Iterable<? extends T> entities) {
		return baseDao.save(entities);
	}

	@Override
	public boolean exists(String id) {
		return baseDao.exists(id);
	}

	@Override
	public List<T> findAll(Specification<T> spec, Sort sort) {
		return baseDao.findAll(spec, sort);
	}

	@Override
	@Transactional(readOnly = false)
	public void flush() {
		baseDao.flush();
	}

	@Override
	public Page<T> findAll(Predicate predicate, Pageable pageable) {
		return baseDao.findAll(predicate, pageable);
	}

	@Override
	@Transactional(readOnly = false)
	public T saveAndFlush(T entity) {
		return baseDao.saveAndFlush(entity);
	}

	@Override
	public long count(Specification<T> spec) {
		return baseDao.count(spec);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteInBatch(Iterable<T> entities) {
		baseDao.deleteInBatch(entities);
	}

	@Override
	public long count(Predicate predicate) {
		return baseDao.count(predicate);
	}

	@Override
	public long count() {
		return baseDao.count();
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(String id) {
		baseDao.delete(id);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(T entity) {
		baseDao.delete(entity);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(Iterable<? extends T> entities) {
		baseDao.delete(entities);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteAll() {
		baseDao.deleteAll();
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(String[] ids) {
		for (String id : ids)
			baseDao.delete(id);

	}

}
