package org.springside.examples.miniweb.service.account;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.examples.miniweb.dao.account.BaseDao;
import org.springside.examples.miniweb.dao.account.GroupDao;
import org.springside.examples.miniweb.dao.account.UserDao;
import org.springside.examples.miniweb.entity.account.Group;
import org.springside.examples.miniweb.entity.account.QGroup;
import org.springside.examples.miniweb.entity.account.QUser;
import org.springside.examples.miniweb.entity.account.User;
import org.springside.examples.miniweb.service.ServiceException;

/**
 * 安全相关实体的管理类,包括用户和权限组.
 * 
 * @author calvin
 */
//Spring Bean的标识.
@Component
//默认将类中的所有public函数纳入事务管理.
@Transactional(readOnly = true)
public class AccountManager {

	private static Logger logger = LoggerFactory.getLogger(AccountManager.class);

	private BaseDao<User> userDao;
	private BaseDao<Group> groupDao;
	private ShiroDbRealm shiroRealm;

	//-- User Manager --//
	public User getUser(String id) {
		return userDao.findOne(id);
	}

	@Transactional(readOnly = false)
	public void saveUser(User entity) {
		userDao.save(entity);
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
		userDao.delete(id);
	}

	/**
	 * 判断是否超级管理员.
	 */
	private boolean isSupervisor(String id) {
		return StringUtils.equals(id,"1");
	}

	public List<User> getAllUser() {
		return (List<User>) userDao.findAll(new Sort(Direction.ASC, "id"));
	}

	public User findUserByLoginName(String loginName) {
		//return userDao.findByLoginName(loginName);
		QUser user=QUser.user;
		return userDao.findOne(user.loginName.eq(loginName));
	}

	//-- Group Manager --//
	public Group getGroup(String id) {
		return groupDao.findOne(id);
	}

	public List<Group> getAllGroup() {
		return (List<Group>) groupDao.findAll((new Sort(Direction.ASC, "id")));
	}

	@Transactional(readOnly = false)
	public void saveGroup(Group entity) {
		groupDao.save(entity);
		shiroRealm.clearAllCachedAuthorizationInfo();
	}

	@Transactional(readOnly = false)
	public void deleteGroup(String id) {
	
		QUser user=QUser.user;
		Group g=groupDao.findOne(id);
		Iterable<User> users=userDao.findAll(user.groupList.contains(g));
		for(User u:users){
			u.getGroupList().remove(g);
		}
		groupDao.delete(g);
		shiroRealm.clearAllCachedAuthorizationInfo();
	}

	@Autowired
	public void setUserDao(BaseDao<User> userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setGroupDao(BaseDao<Group> groupDao) {
		this.groupDao = groupDao;
	}

	@Autowired(required = false)
	public void setShiroRealm(ShiroDbRealm shiroRealm) {
		this.shiroRealm = shiroRealm;
	}
}
