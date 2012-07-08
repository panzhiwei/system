<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/tags.jsp"%>
<div class="pageContent">
<form:form id="inputForm" modelAttribute="entity"
	action="${ctx}/account/role/save"
	onsubmit="return validateCallback(this,dialogAjaxDone)" method="post"	cssClass="pageForm required-validate">
	<form:hidden path="id"/>
	<input type="hidden" name="navTabId" value="${navTabId }"/>
	<div class="pageFormContent" layoutH="56">
		

		<p>
			<form:label path="name">名称:</form:label> 
			<form:input path="name" cssClass="required"/>
		</p>
		<div class="divider"></div>
		<P>
		<label>权限:<input type="checkbox" class="checkboxCtrl" group="permissions" />全选</label>
		</P>
		
			<form:checkboxes path="permissions" items="${allPermissions}" itemLabel="name" itemValue="id" element="p" />
		

	</div>
	<shiro:hasPermission name="role:edit">
		<jsp:include page="/include/formButton.jsp"></jsp:include>
	</shiro:hasPermission>
</form:form>
</div>