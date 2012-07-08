package com.best.system.account.editor;

import java.beans.PropertyEditorSupport;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springside.modules.utils.Collections3;

import com.best.system.account.entity.Role;
import com.best.system.account.service.IRoleService;
import com.google.common.collect.Sets;

@Component
public class RoleListEditor extends PropertyEditorSupport {

	@Autowired
	private IRoleService rolsService;

	/**
	 * Back From Page
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		String[] ids = StringUtils.split(text, ",");
		Set<Role> groups = Sets.newHashSet();
		for (String id : ids) {
			Role group = rolsService.findOne(id);
			groups.add(group);
		}
		setValue(groups);
	}

	/**
	 * Set to page
	 */
	@Override
	public String getAsText() {
		return Collections3.extractToString((Set<?>) getValue(), "id", ",");
	}
}
