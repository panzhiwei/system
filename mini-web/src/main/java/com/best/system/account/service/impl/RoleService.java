package com.best.system.account.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.best.system.account.dao.RoleDao;
import com.best.system.account.entity.Role;
import com.best.system.account.service.IRoleService;
import com.best.system.base.service.impl.BaseService;

@Service
@Transactional(readOnly = true)
public class RoleService extends BaseService<Role, RoleDao> implements IRoleService {

//	@Autowired
//	private PermissionDao permissionDao;
//	
	@Override
	public Role createEntity() {
		return new Role();
	}
	
//	public List<Permission> getAllPermissions() {
//		return permissionDao.findAll();
//	}

}
