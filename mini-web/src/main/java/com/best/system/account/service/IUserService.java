package com.best.system.account.service;


import com.best.system.account.entity.User;
import com.best.system.base.service.IBaseService;

public interface IUserService extends IBaseService<User> {

	User findUserByLoginName(String username);

}
