package com.best.system.base.util;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonValue;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_EMPTY)
public class JsonResp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Status statusCode = Status.OK;

	private String message;

	private String navTabId;

	private String rel;

	
	private Callback callbackType = Callback.DEFALUT;

	private String forwardUrl;

	public static enum Status {
		OK("200"), FAIL("300"), TIMEOUT("301");

		private String code;

		private Status(String code) {
			this.code = code;
		}

		@JsonValue
		public String getCode() {
			return this.code;
		}

	}

	public static enum Callback {
		FORWARD("forward"), CLOSECURRENT("closeCurrent"),DEFALUT("");

		private String value;

		private Callback(String value) {
			this.value = value;
		}
		
		@JsonValue
		public String getValue() {
			return this.value;
		}
	}

	public String getStatusCode() {
		return statusCode.getCode();
	}

	public void setStatusCode(Status statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getNavTabId() {
		return navTabId;
	}

	public void setNavTabId(String navTabId) {
		this.navTabId = navTabId;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public Callback getCallbackType() {
		return callbackType;
	}

	public void setCallbackType(Callback callbackType) {
		this.callbackType = callbackType;
	}

	public String getForwardUrl() {
		return forwardUrl;
	}

	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}

}
