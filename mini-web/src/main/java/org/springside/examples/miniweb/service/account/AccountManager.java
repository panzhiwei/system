package org.springside.examples.miniweb.service.account;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.examples.miniweb.dao.account.PermissionDao;
import org.springside.examples.miniweb.dao.account.RoleDao;
import org.springside.examples.miniweb.dao.account.UserDao;
import org.springside.examples.miniweb.entity.account.Permission;
import org.springside.examples.miniweb.entity.account.QUser;
import org.springside.examples.miniweb.entity.account.Role;
import org.springside.examples.miniweb.entity.account.User;
import org.springside.examples.miniweb.service.ServiceException;
import org.springside.examples.miniweb.service.impl.BaseService;

/**
 * 安全相关实体的管理类,包括用户和权限组.
 * 
 * @author calvin
 */
// Spring Bean的标识.
@Service
// 默认将类中的所有public函数纳入事务管理.
@Transactional(readOnly = true)
public class AccountManager extends BaseService<User, UserDao>{

	private static Logger logger = LoggerFactory.getLogger(AccountManager.class);

	//private UserDao userDao;
	private RoleDao roleDao;
	private PermissionDao permissionDao;
	private ShiroDbRealm shiroRealm;

	// -- User Manager --//
	public User getUser(String id) {
		return findOne(id);
	}

	@Transactional(readOnly = false)
	public void saveUser(User entity) {
		save(entity);
		shiroRealm.clearCachedAuthorizationInfo(entity.getLoginName());
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	@Transactional(readOnly = false)
	public void deleteUser(String id) {
		if (isSupervisor(id)) {
			logger.warn("操作员{}尝试删除超级管理员用户", SecurityUtils.getSubject().getPrincipal());
			throw new ServiceException("不能删除超级管理员用户");
		}
		delete(id);
	}

	/**
	 * 判断是否超级管理员.
	 */
	private boolean isSupervisor(String id) {
		return StringUtils.equals(id, "1");
	}

	public List<User> getAllUser() {
		return (List<User>) findAll(new Sort(Direction.ASC, "id"));
	}

	public User findUserByLoginName(String loginName) {
		// return findByLoginName(loginName);
		QUser user = QUser.user;
		return findOne(user.loginName.eq(loginName));
	}

	// -- Group Manager --//
	public Role getGroup(String id) {
		return roleDao.findOne(id);
	}

	public List<Role> getAllGroup() {
		return (List<Role>) roleDao.findAll((new Sort(Direction.ASC, "id")));
	}

	@Transactional(readOnly = false)
	public void saveGroup(Role entity) {
		roleDao.save(entity);
		shiroRealm.clearAllCachedAuthorizationInfo();
	}

	@Transactional(readOnly = false)
	public void deleteGroup(String id) {

		QUser user = QUser.user;
		Role g = roleDao.findOne(id);
		Iterable<User> users = findAll(user.roles.contains(g));
		for (User u : users) {
			u.getRoles().remove(g);
		}
		roleDao.delete(g);
		shiroRealm.clearAllCachedAuthorizationInfo();
	}

//	@Autowired
//	public void setIUserDao(UserDao userDao) {
//		this.userDao = userDao;
//	}

	@Autowired
	public void setIRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Autowired(required = false)
	public void setShiroRealm(ShiroDbRealm shiroRealm) {
		this.shiroRealm = shiroRealm;
	}

	public List<Permission> getAllPermissions() {
		return permissionDao.findAll();
	}

	@Autowired
	public void setIPermissionDao(PermissionDao permissionDao) {
		this.permissionDao = permissionDao;
	}

	public Permission getPermisson(String id) {
		return permissionDao.findOne(id);
	}
}
