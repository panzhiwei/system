<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="pageContent">
<form:form id="inputForm" modelAttribute="permission"
	action="${ctx}/account/permission/save"
	onsubmit="return validateCallback(this,dialogAjaxDone)" method="post"	cssClass="pageForm required-validate">
	<input type="hidden" name="id" value="${permission.id}" />
	<input type="hidden" name="navTabId" value="${navTabId }"/>
	<div class="pageFormContent" layoutH="56">
		

		<p>
			<label>权限名称:</label> 
			<input type="text"	id="name" name="name"  value="${permission.name}" class="required" />
		</p>
		<p>
			<label>权限编码:</label> 
			<input type="text" id="value" name="value" value="${permission.value}" class="required" />
		</p>

	</div>
<shiro:hasPermission name="permission:edit">
	<div class="formBar">
		<ul>
			<li><div class="buttonActive">
					<div class="buttonContent">
						<button type="submit">保存</button>
					</div>
				</div></li>
			<li>
				<div class="button">
					<div class="buttonContent">
						<button type="button" class="close">取消</button>
					</div>
				</div>
			</li>
		</ul>
	</div>
	</shiro:hasPermission>
</form:form>
</div>