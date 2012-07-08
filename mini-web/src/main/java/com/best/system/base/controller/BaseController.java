package com.best.system.base.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefaults;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.best.system.base.entity.IdEntity;
import com.best.system.base.service.IBaseService;
import com.best.system.base.util.JsonResp;
import com.best.system.base.util.JsonResp.Callback;

public class BaseController<T extends IdEntity, S extends IBaseService<T>> {

	private S tempService;

	@ModelAttribute("navTabId")
	public String getNavTabId(@RequestParam("navTabId") String navTabId) {
		return navTabId;
	}

	@RequestMapping(value = { "list", "" })
	public String list(@PageableDefaults Pageable pageable, Model model) {
		Page<T> pages = tempService.findAll(pageable);
		model.addAttribute("pages", pages);
		return getView("list");
	}

	@RequestMapping(value = "create")
	public String createForm(Model model) {
		model.addAttribute("entity", getService().createEntity());
		return getView("form");
	}

	@RequestMapping(value = "save", produces = "application/json")
	public @ResponseBody
	JsonResp save(T entity, @ModelAttribute("navTabId") String navTabId, Model model) {
		boolean isUpdate = StringUtils.isNotBlank(entity.getId());
		entity = getService().save(entity);
		JsonResp resp = new JsonResp();
		resp.setCallbackType(Callback.CLOSECURRENT);
		resp.setNavTabId(navTabId);
		if (isUpdate)
			resp.setMessage(getUpdateMessage(entity));
		else
			resp.setMessage(getCreateMessage(entity));
		return resp;
	}

	@RequestMapping(value = "update/{id}")
	public String updateForm(@PathVariable("id") String id, Model model) {
		model.addAttribute("entity", getService().findOne(id));
		return getView("form");
	}

	@RequestMapping(value = "view/{id}")
	public String viewForm(@PathVariable("id") String id, Model model) {
		model.addAttribute("entity", getService().findOne(id));
		return getView("view");
	}

	@RequestMapping(value = "delete", produces = "application/json")
	public @ResponseBody
	JsonResp delete(@RequestParam("ids") String[] ids, Model model) {
		getService().delete(ids);
		JsonResp resp = new JsonResp();
		resp.setMessage(getDeleteMessage());
		return resp;
	}

	public S getService() {
		return tempService;
	}

	@Autowired
	public void setTempService(S tempService) {
		this.tempService = tempService;
	}

	protected String getView(String viewName) {
		return viewName;

	}

	protected String getCreateMessage(T entity) {
		return "创建成功";
	}

	protected String getUpdateMessage(T entity) {
		return "修改成功";
	}

	protected String getDeleteMessage() {
		return "删除成功";
	}

}
