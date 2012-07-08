package org.springside.examples.miniweb.service.account;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.examples.miniweb.dao.account.UserDao;
import org.springside.examples.miniweb.entity.account.QUser;
import org.springside.examples.miniweb.entity.account.User;
import org.springside.examples.miniweb.service.IUserService;
import org.springside.examples.miniweb.service.ServiceException;
import org.springside.examples.miniweb.service.impl.BaseService;

@Service
@Transactional(readOnly = true)
public class UserService extends BaseService<User, UserDao> implements IUserService {

	@Override
	public User createEntity() {
		return new User();
	}

	@Override
	public User findUserByLoginName(String username) {
		QUser user = QUser.user;
		return findOne(user.loginName.eq(username));
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	@Transactional(readOnly = false)
	@Override
	public void delete(String id) {
		if (isSupervisor(id)) {
			// logger.warn("操作员{}尝试删除超级管理员用户",
			// SecurityUtils.getSubject().getPrincipal());
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

}
