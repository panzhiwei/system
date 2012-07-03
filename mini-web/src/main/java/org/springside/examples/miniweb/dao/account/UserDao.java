package org.springside.examples.miniweb.dao.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springside.examples.miniweb.entity.account.User;

/**
 * 用户对象的Dao interface.
 * 
 * @author calvin
 */
public interface UserDao extends JpaRepository<User, String>,JpaSpecificationExecutor<User>,QueryDslPredicateExecutor<User> {

	User findByLoginName(String loginName);
}
