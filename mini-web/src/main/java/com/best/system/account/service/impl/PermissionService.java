package com.best.system.account.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.best.system.account.dao.PermissionDao;
import com.best.system.account.entity.Permission;
import com.best.system.account.service.IPermissionService;
import com.best.system.base.service.impl.BaseService;

@Service
@Transactional(readOnly = true)
public class PermissionService extends BaseService<Permission, PermissionDao> implements IPermissionService{

	@Override
	public Permission createEntity() {
		return new Permission();
	}
	
	
	
}
