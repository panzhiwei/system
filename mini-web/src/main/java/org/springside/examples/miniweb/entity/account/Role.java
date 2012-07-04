package org.springside.examples.miniweb.entity.account;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springside.examples.miniweb.entity.IdEntity;
import org.springside.modules.utils.Collections3;

import com.google.common.collect.Sets;

/**
 * 权限组.
 * 
 * 注释见{@link User}.
 * 
 * @author pzw
 */
@Entity
@Table(name = "T_B_ROLE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Role extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	
	//private String value;

	private Set<Permission> permissions = Sets.newLinkedHashSet();
	
	private Set<User> users=Sets.newLinkedHashSet();

	public Role() {
	}

	public Role(String id, String name) {
		setId(id);
		this.name = name;
	}

	@Column(nullable = false, unique = true, columnDefinition = "VARCHAR(50)")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany
	@JoinTable(name = "T_B_ROLE_PERMISSION", joinColumns = { @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "PERMISSION_ID") })
	@OrderBy("id")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	@Transient
	public String getPermissionNames() {
		return Collections3.extractToString(permissions, "name", ",");
	}
	
	@Transient
	public String getPermissionValues() {
		return Collections3.extractToString(permissions, "value", ",");
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@ManyToMany(mappedBy="roles")
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

//	@Column(nullable = false, unique = true, columnDefinition = "VARCHAR(50)")
//	public String getValue() {
//		return value;
//	}
//
//	public void setValue(String value) {
//		this.value = value;
//	}
}
