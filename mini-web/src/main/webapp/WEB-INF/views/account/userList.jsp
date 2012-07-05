<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${model.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx }/account/user/list" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>登录名：</label>
				<input type="text" name="loginName" value=""/>
			</li>
			<li>
				<label>姓名：</label>
				<input type="text" name="realName" value=""/>
			</li>
			<li>
				<label>邮箱：</label>
				<input type="text" name="email" value=""/>
			</li>
			
		</ul>
				<div class="subBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">查询</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="demo_page4.html" target="navTab"><span>添加</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="demo/common/ajaxDone.html" class="delete"><span>批量删除默认方式</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" postType="string" href="demo/common/ajaxDone.html" class="delete"><span>批量删除逗号分隔</span></a></li>
			<li><a class="edit" href="${ctx }/account/user/update/{sid_user}" target="dialog" warn="请选择一个用户"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="1200" layoutH="138">
		<thead>
			<tr>
			<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
			<th width="100" orderField="loginName">登录名</th>
			<th width="100" orderField="realName">姓名</th>
			<th width="200" orderField="email">邮箱</th>
			<th>权限组</th>
			<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${users}" var="user" varStatus="index">
				<tr target="sid_user" rel="${user.id }">
					<td><input name="ids" value="xxx" type="checkbox"></td>
					<td>${user.loginName}</td>
					<td>${user.realName}</td>
					<td>${user.email}</td>
					<td>${user.roleNames}</td>
					<td>
						<shiro:hasPermission name="user:edit">
		    				<a href="update/${user.id}" title="修改" target="ajaxTodo" class="btnEdit">修改</a> 
		    				<a href="delete/${user.id}" title="删除" target="ajaxTodo" class="btnDel">删除</a>
						</shiro:hasPermission>
					</td>
				</tr>
			</c:forEach>
			
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
				<option value="200">200</option>
			</select>
			<span>条，共${totalCount}条</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="200" numPerPage="20" pageNumShown="10" currentPage="1"></div>

	</div>
</div>
