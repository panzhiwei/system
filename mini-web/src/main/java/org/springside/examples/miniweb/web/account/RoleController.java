package org.springside.examples.miniweb.web.account;

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
import org.springside.examples.miniweb.entity.account.Role;
import org.springside.examples.miniweb.service.IPermissionService;
import org.springside.examples.miniweb.service.IRoleService;
import org.springside.examples.miniweb.web.BaseController;
import org.springside.examples.miniweb.web.Utils.JsonResp;

@Controller
@RequestMapping(value = "/account/role")
public class RoleController extends BaseController<Role, IRoleService> {

	@Override
	protected String getView(String viewName) {
		return "account/role/" + viewName;
	}
	
	@Autowired
	private PermissionListEditor permissionListEditor;
	
	@Autowired
	private IPermissionService permissionService;

	@InitBinder
	public void initBinder(WebDataBinder b) {
		b.registerCustomEditor(Set.class, "permissions", permissionListEditor);
	}

	@RequiresPermissions("role:view")
	@RequestMapping(value = { "list", "" })
	public String list(@PageableDefaults Pageable pageable, Model model) {

		return super.list(pageable, model);
	}

	@RequiresPermissions("role:edit")
	@RequestMapping(value = "create")
	public String createForm(Model model) {
		model.addAttribute("allPermissions", permissionService.findAll());
		return super.createForm(model);
	}

	@RequiresPermissions("role:edit")
	@RequestMapping(value = "save", produces = "application/json")
	public @ResponseBody
	JsonResp save(Role entity, @ModelAttribute("navTabId") String navTabId, Model model) {
		return super.save(entity, navTabId, model);
	}

	@RequiresPermissions("role:edit")
	@RequestMapping(value = "update/{id}")
	public String updateForm(@PathVariable("id") String id, Model model) {
		model.addAttribute("allPermissions", permissionService.findAll());
		return super.updateForm(id, model);
	}

	@RequiresPermissions("role:view")
	@RequestMapping(value = "view/{id}")
	public String viewForm(@PathVariable("id") String id, Model model) {
		model.addAttribute("allPermissions",permissionService.findAll());
		return super.viewForm(id, model);
	}

	@RequiresPermissions("role:edit")
	@RequestMapping(value = "delete", produces = "application/json")
	public @ResponseBody
	JsonResp delete(@RequestParam("ids") String[] ids, Model model) {
		return super.delete(ids, model);
	}
}
