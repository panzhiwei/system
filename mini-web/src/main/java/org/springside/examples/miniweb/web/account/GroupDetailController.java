package org.springside.examples.miniweb.web.account;

import java.util.Set;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.examples.miniweb.entity.account.Role;
import org.springside.examples.miniweb.entity.account.Permission;
import org.springside.examples.miniweb.service.account.AccountManager;

@Controller
@RequestMapping(value = "/account/group/")
public class GroupDetailController {

	@Autowired
	private AccountManager accountManager;

	@Autowired
	private PermissionListEditor permissionListEditor;

	@InitBinder
	public void initBinder(WebDataBinder b) {
		b.registerCustomEditor(Set.class, "permissions", permissionListEditor);
	}
	
	@RequiresPermissions("group:edit")
	@RequestMapping(value = "update/{id}")
	public String updateForm(Model model) {
		model.addAttribute("allPermissions", accountManager.getAllPermissions());
		return "account/groupForm";
	}

	@RequiresPermissions("group:edit")
	@RequestMapping(value = "save/{id}")
	public String save(@ModelAttribute("group") Role group, RedirectAttributes redirectAttributes) {
		accountManager.saveGroup(group);
		redirectAttributes.addFlashAttribute("message", "修改权限组" + group.getName() + "成功");
		return "redirect:/account/group/";
	}

	@ModelAttribute("group")
	public Role getGroup(@PathVariable("id") String id) {
		return accountManager.getGroup(id);
	}
}
