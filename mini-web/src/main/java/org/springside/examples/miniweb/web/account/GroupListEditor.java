package org.springside.examples.miniweb.web.account;

import java.beans.PropertyEditorSupport;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springside.examples.miniweb.entity.account.Role;
import org.springside.examples.miniweb.service.account.AccountManager;
import org.springside.modules.utils.Collections3;

import com.google.common.collect.Sets;

/**
 * 用于转换用户表单中复杂对象Group的checkbox的关联。
 * 
 * @author calvin
 */
@Component
public class GroupListEditor extends PropertyEditorSupport {

	@Autowired
	private AccountManager accountManager;

	/**
	 * Back From Page
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		String[] ids = StringUtils.split(text, ",");
		Set<Role> groups = Sets.newHashSet();
		for (String id : ids) {
			Role group = accountManager.getGroup(String.valueOf(id));
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
