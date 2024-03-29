package com.best.system.account.editor;

import java.beans.PropertyEditorSupport;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springside.modules.utils.Collections3;

import com.best.system.account.entity.Permission;
import com.best.system.account.service.IPermissionService;
import com.google.common.collect.Sets;


@Component
public class PermissionListEditor extends PropertyEditorSupport {

	@Autowired
	private IPermissionService permissionService;

	/**
	 * Back From Page
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		String[] ids = StringUtils.split(text, ",");
		Set<Permission> permissions = Sets.newHashSet();
		for (String id : ids) {
			Permission permission = permissionService.findOne(id);
			permissions.add(permission);
		}
		setValue(permissions);
	}

	/**
	 * Set to page
	 */
	@Override
	public String getAsText() {
		return Collections3.extractToString((Set<?>) getValue(), "id", ",");
	}
}
