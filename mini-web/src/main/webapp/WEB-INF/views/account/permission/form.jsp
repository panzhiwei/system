<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="pageContent">
<form:form id="inputForm" modelAttribute="entity"
	action="${ctx}/account/permission/save"
	onsubmit="return validateCallback(this,dialogAjaxDone)" method="post"	cssClass="pageForm required-validate">
	<form:hidden path="id"/>
	<input type="hidden" name="navTabId" value="${navTabId }"/>
	<div class="pageFormContent" layoutH="56">	

		<p>
			
			<form:label path="name">权限名称:</form:label>
			<form:input path="name" cssClass="required"/>
		</p>
		<p>
			<form:label path="value">权限编码:</form:label> 
			<form:input path="value" cssClass="required"/>
		</p>

	</div>
	<shiro:hasPermission name="permission:edit">
		<jsp:include page="/include/formButton.jsp"></jsp:include>	
	</shiro:hasPermission>
</form:form>
</div>