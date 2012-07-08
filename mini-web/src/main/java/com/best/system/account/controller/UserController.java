package com.best.system.account.controller;

import java.util.Set;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.best.system.account.editor.RoleListEditor;
import com.best.system.account.entity.User;
import com.best.system.account.service.IRoleService;
import com.best.system.account.service.IUserService;
import com.best.system.base.controller.BaseController;
import com.best.system.base.util.JsonResp;

@Controller
@RequestMapping(value = "/account/user")
public class UserController extends BaseController<User, IUserService> {

	@Override
	protected String getView(String viewName) {
		return "account/user/" + viewName;
	}
	
	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private RoleListEditor roleListEditor;

	@InitBinder
	public void initBinder(WebDataBinder b) {
		b.registerCustomEditor(Set.class, "roles", roleListEditor);
	}

	@RequiresPermissions("user:view")
	@RequestMapping(value = { "list", "" })
	public String list(@PageableDefaults Pageable pageable, Model model) {

		return super.list(pageable, model);
	}

	@RequiresPermissions("user:edit")
	@RequestMapping(value = "create")
	public String createForm(Model model) {
		model.addAttribute("allRoles", roleService.findAll());
		return super.createForm(model);
	}

	@RequiresPermissions("user:edit")
	@RequestMapping(value = "save", produces = "application/json")
	public @ResponseBody
	JsonResp save(User entity, @ModelAttribute("navTabId") String navTabId, Model model) {
		return super.save(entity, navTabId, model);
	}

	@RequiresPermissions("user:edit")
	@RequestMapping(value = "update/{id}")
	public String updateForm(@PathVariable("id") String id, Model model) {
		model.addAttribute("allRoles", roleService.findAll());
		return super.updateForm(id, model);
	}

	@RequiresPermissions("user:view")
	@RequestMapping(value = "view/{id}")
	public String viewForm(@PathVariable("id") String id, Model model) {
		model.addAttribute("allRoles", roleService.findAll());
		return super.viewForm(id, model);
	}

	@RequiresPermissions("user:edit")
	@RequestMapping(value = "delete", produces = "application/json")
	public @ResponseBody
	JsonResp delete(@RequestParam("ids") String[] ids, Model model) {
		return super.delete(ids, model);
	}
}
