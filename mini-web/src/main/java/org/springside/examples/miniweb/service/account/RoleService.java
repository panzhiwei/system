package org.springside.examples.miniweb.service.account;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.examples.miniweb.dao.account.RoleDao;
import org.springside.examples.miniweb.entity.account.Role;
import org.springside.examples.miniweb.service.IRoleService;
import org.springside.examples.miniweb.service.impl.BaseService;

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
