<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/tags.jsp"%>
<div class="pageContent">
<form:form id="inputForm" modelAttribute="entity"
	action="${ctx}/account/role/save"
	onsubmit="return validateCallback(this,dialogAjaxDone)" method="post"	cssClass="pageForm required-validate">
	<input type="hidden" name="id" value="${entity.id}" />
	<input type="hidden" name="navTabId" value="${navTabId }"/>
	<div class="pageFormContent" layoutH="56">
		

		<p>
			<label>名称:</label> 
			<input type="text"	id="name" name="name"  value="${entity.name}" class="required" />
		</p>
		<div class="divider"></div>
		<P>
		<label>权限:<input type="checkbox" class="checkboxCtrl" group="permissions" />全选</label>
		</P>
		
			<form:checkboxes path="permissions" items="${allPermissions}" itemLabel="name" itemValue="id" element="p" />
		

	</div>

</form:form>
</div>