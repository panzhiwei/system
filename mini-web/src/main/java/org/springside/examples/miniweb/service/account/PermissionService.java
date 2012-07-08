package org.springside.examples.miniweb.service.account;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.examples.miniweb.dao.account.PermissionDao;
import org.springside.examples.miniweb.entity.account.Permission;
import org.springside.examples.miniweb.service.IPermissionService;
import org.springside.examples.miniweb.service.impl.BaseService;

@Service
@Transactional(readOnly = true)
public class PermissionService extends BaseService<Permission, PermissionDao> implements IPermissionService{

	@Override
	public Permission createEntity() {
		return new Permission();
	}
	
	
	
}
