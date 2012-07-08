package org.springside.examples.miniweb.service;

import org.springside.examples.miniweb.entity.account.User;

public interface IUserService extends IBaseService<User> {

	User findUserByLoginName(String username);

}
