package com.best.system.account.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.best.system.account.entity.Permission;
import com.best.system.account.service.IPermissionService;
import com.best.system.base.controller.BaseController;
import com.best.system.base.util.JsonResp;

@Controller
@RequestMapping(value = "/account/permission")
public class PermissionController extends BaseController<Permission, IPermissionService> {

	@Override
	protected String getView(String viewName) {
		return "account/permission/" + viewName;
	}

	@RequiresPermissions("permission:view")
	@RequestMapping(value = { "list", "" })
	public String list(@PageableDefaults Pageable pageable, Model model) {

		return super.list(pageable, model);
	}

	@RequiresPermissions("permission:edit")
	@RequestMapping(value = "create")
	public String createForm(Model model) {
		return super.createForm(model);
	}

	@RequiresPermissions("permission:edit")
	@RequestMapping(value = "save", produces = "application/json")
	public @ResponseBody
	JsonResp save(Permission entity, @ModelAttribute("navTabId") String navTabId, Model model) {
		return super.save(entity, navTabId, model);
	}

	@RequiresPermissions("permission:edit")
	@RequestMapping(value = "update/{id}")
	public String updateForm(@PathVariable("id") String id, Model model) {
		return super.updateForm(id, model);
	}

	@RequiresPermissions("permission:view")
	@RequestMapping(value = "view/{id}")
	public String viewForm(@PathVariable("id") String id, Model model) {
		return super.viewForm(id, model);
	}

	@RequiresPermissions("permission:edit")
	@RequestMapping(value = "delete", produces = "application/json")
	public @ResponseBody
	JsonResp delete(@RequestParam("ids") String[] ids, Model model) {
		return super.delete(ids, model);
	}
}
