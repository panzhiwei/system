package com.best.system.account.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.best.system.base.entity.IdEntity;
import com.google.common.collect.Sets;

/**
 * Resource Base Access Control中的资源定义.
 * 
 * @author pzw
 */

@Entity
@Table(name = "T_B_PERMISSION")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Permission extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String value;
	private String name;
	//private transient Permission parent;
	//private transient Set<Permission> childrens;

	private transient Set<Role> roles = Sets.newLinkedHashSet();

	@Column(nullable = false, unique = true, length = 50, columnDefinition = "VARCHAR(50)")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(nullable = false, length = 100, columnDefinition = "VARCHAR(100)")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany(mappedBy = "permissions")
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

//	@ManyToOne(cascade = CascadeType.ALL, optional = true)
//	@JoinColumn(name = "PARENT_ID", columnDefinition = "VARCHAR(32)", nullable = true)
//	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//	public Permission getParent() {
//		return parent;
//	}
//
//	public void setParent(Permission parent) {
//		this.parent = parent;
//	}
//
//	@OneToMany(mappedBy = "parent")
//	public Set<Permission> getChildrens() {
//		return childrens;
//	}
//
//	public void setChildrens(Set<Permission> childrens) {
//		this.childrens = childrens;
//	}

}
