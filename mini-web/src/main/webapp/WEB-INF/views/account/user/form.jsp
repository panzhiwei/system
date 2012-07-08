<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/tags.jsp"%>
<div class="pageContent">
<form:form id="inputForm" modelAttribute="entity"
	action="${ctx}/account/user/save"
	onsubmit="return validateCallback(this,dialogAjaxDone)" method="post"	cssClass="pageForm required-validate">
	<form:hidden path="id"/>
	<input type="hidden" name="navTabId" value="${navTabId }"/>
	<div class="pageFormContent" layoutH="56">
		<p>
			<form:label path="loginName" >登录名:</form:label>
			<form:input path="loginName" cssClass="required"/>
		</p>
		<p>
			<form:label path="realName" >姓名:</form:label>
			<form:input path="realName" cssClass="required"/>
		</p>
		<p>
			<form:label path="password" >密码:</form:label>
			<form:input path="password" cssClass="required"/>
		</p>
		<p>
			<label for="passwordConfirm">确认密码:</label>
			<input type="text" id="passwordConfirm" name="passwordConfirm" cssClass="required" equalTo="#password">
		</p>
		<p>
			<form:label path="email" >邮箱:</form:label>
			<form:input path="email" cssClass="email"/>
		</p>
		<div class="divider"></div>
		<P>
		<label>分配角色:<input type="checkbox" class="checkboxCtrl" group="roles" />全选</label>
		</P>
		
			<form:checkboxes path="roles" items="${allRoles}" itemLabel="name" itemValue="id" element="p" />

	</div>
	<shiro:hasPermission name="user:edit">
		<jsp:include page="/include/formButton.jsp"></jsp:include>
	</shiro:hasPermission>
</form:form>
</div>