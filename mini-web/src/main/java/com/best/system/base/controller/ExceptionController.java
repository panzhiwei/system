package com.best.system.base.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.best.system.base.util.JsonResp;
import com.best.system.base.util.JsonResp.Status;

public class ExceptionController {
	@ExceptionHandler
	public @ResponseBody JsonResp handle(Throwable e) {
		JsonResp resp=new JsonResp();
		resp.setMessage(e.getMessage());
		resp.setStatusCode(Status.FAIL);
		return resp;
	}
}
