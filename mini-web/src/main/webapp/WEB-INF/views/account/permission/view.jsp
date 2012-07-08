<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="pageContent">
<form:form id="inputForm" modelAttribute="entity"
	action="${ctx}/account/permission/save"
	onsubmit="return validateCallback(this,dialogAjaxDone)" method="post"	cssClass="pageForm required-validate">
	<input type="hidden" name="id" value="${entity.id}" />
	<input type="hidden" name="navTabId" value="${navTabId }"/>
	<div class="pageFormContent" layoutH="56">
		

		<p>
			<label>权限名称:</label> 
			<input type="text"	id="name" name="name"  value="${entity.name}" class="required" />
		</p>
		<p>
			<label>权限编码:</label> 
			<input type="text" id="value" name="value" value="${entity.value}" class="required" />
		</p>

	</div>

</form:form>
</div>