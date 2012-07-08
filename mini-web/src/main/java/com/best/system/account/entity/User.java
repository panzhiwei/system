package com.best.system.account.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springside.modules.utils.Collections3;

import com.best.system.base.entity.IdEntity;
import com.google.common.collect.Sets;

/**
 * 用户.
 * 
 * @author pzw
 */
@Entity
@Table(name = "T_B_USER")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String loginName;
	private String password;
	private String realName;
	private String email;
	private boolean locked;

	private boolean passwordExpired;

	private transient Set<Role> roles = Sets.newLinkedHashSet();// 有序的关联对象集合

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "T_B_USER_ROLE", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	@OrderBy("id")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public boolean isPasswordExpired() {
		return passwordExpired;
	}

	public void setPasswordExpired(boolean passwordExpired) {
		this.passwordExpired = passwordExpired;
	}

	/**
	 * 用户拥有的权限组名称字符串, 多个权限组名称用','分隔.
	 */
	// 非持久化属性.
	@Transient
	public String getRoleNames() {
		return Collections3.extractToString(roles, "name", ", ");
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("loginName",this.loginName).append("realName",this.realName).append("email",this.email)
				.append("locked",this.locked).toString();
	}
	
}