package org.springside.examples.miniweb.web.account;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.examples.miniweb.entity.account.Permission;
import org.springside.examples.miniweb.service.IPermissionService;
import org.springside.examples.miniweb.web.Utils.JsonResp;
import org.springside.examples.miniweb.web.Utils.JsonResp.Callback;

@Controller
@RequestMapping(value = "/account/permission")
public class PermissionController {

	@Autowired
	private IPermissionService permissionService;

	// @Autowired
	// private PermissionListEditor permissionListEditor;

	// @InitBinder
	// public void initBinder(WebDataBinder b) {
	// b.registerCustomEditor(Set.class, "permissions", permissionListEditor);
	// }

	@ModelAttribute("navTabId")
	public String getNavTabId(@RequestParam("navTabId") String navTabId) {
		return navTabId;
	}

	@RequiresPermissions("permission:view")
	@RequestMapping(value = { "list", "" })
	public String list(@PageableDefaults Pageable pageable, Model model) {
		Page<Permission> permissions = permissionService.findAll(pageable);
		model.addAttribute("permissons", permissions);
		return "account/permissionList";
	}

	@RequiresPermissions("permission:edit")
	@RequestMapping(value = "create")
	public String createForm(Model model) {
		model.addAttribute("permisson", new Permission());
		return "account/permissionForm";
	}

	@RequiresPermissions("permission:edit")
	@RequestMapping(value = "save", produces = "application/json")
	public @ResponseBody
	JsonResp save(Permission permission, @ModelAttribute("navTabId") String navTabId, Model model) {
		boolean isUpdate = StringUtils.isNotBlank(permission.getId());
		permissionService.save(permission);
		JsonResp resp = new JsonResp();
		resp.setCallbackType(Callback.CLOSECURRENT);
		resp.setNavTabId(navTabId);
		// resp.setCallback(CallbackType.CLOSECURRENT);
		if (isUpdate)
			resp.setMessage("修改权限组" + permission.getName() + "成功");
		else
			resp.setMessage("创建权限" + permission.getName() + "成功");
		return resp;
		// return "redirect:/account/permission/";
	}

	@RequiresPermissions("permission:edit")
	@RequestMapping(value = "update/{id}")
	public String updateForm(@PathVariable("id") String id, Model model) {
		model.addAttribute("permission", permissionService.findOne(id));
		return "account/permissionForm";
	}
	
	@RequiresPermissions("permission:view")
	@RequestMapping(value = "view/{id}")
	public String viewForm(@PathVariable("id") String id, Model model) {
		model.addAttribute("permission", permissionService.findOne(id));
		return "account/permissionForm";
	}

	// @RequiresPermissions("group:edit")
	// @RequestMapping(value = "save/{id}")
	// public String save(@ModelAttribute("group") Role group,
	// RedirectAttributes redirectAttributes) {
	// accountManager.saveGroup(group);
	// redirectAttributes.addFlashAttribute("message", "修改权限组" + group.getName()
	// + "成功");
	// return "redirect:/account/group/";
	// }

	@RequiresPermissions("permission:edit")
	@RequestMapping(value = "delete", produces = "application/json")
	public @ResponseBody
	JsonResp delete(@RequestParam("ids") String[] ids,  Model model) {
		permissionService.delete(ids);
		JsonResp resp = new JsonResp();
		resp.setMessage("删除权限成功");

		// model.addAttribute(resp);
		return resp;
	}
}
