<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${permissons.size}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx }/account/permission?navTabId=${navTabId }" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>权限名：</label>
				<input type="text" name="name" value=""/>
			</li>
			<li>
				<label>权限编码：</label>
				<input type="text" name="value" value=""/>
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
		
			<li><a class="icon" href="${ctx }/account/permission/view/{sid_permission}?navTabId=${navTabId}" target="dialog" warn="请选择一个用户"><span>查看详情</span></a></li>
			<li class="line">line</li>
			<shiro:hasPermission name="permission:edit">
				<li><a class="add" href="${ctx }/account/permission/create?navTabId=${navTabId}" target="dialog"><span>添加</span></a></li>
				<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${ctx }/account/permission/delete?navTabId=${navTabId}" class="delete"><span>删除</span></a></li>
		
				<li><a class="edit" href="${ctx }/account/permission/update/{sid_permission}?navTabId=${navTabId}" target="dialog" warn="请选择一个用户"><span>修改</span></a></li>
			</shiro:hasPermission>	
			<!-- <li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li> -->
		</ul>
	</div>
	<table class="table" width="1200" layoutH="138">
		<thead>
			<tr>
			<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
			<th width="100" orderField="name">权限名称</th>
			<th width="100" orderField="value">权限编码</th>
			
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${permissons.content}" var="permission" varStatus="index">
				<tr target="sid_permission" rel="${permission.id }">
					<td><input name="ids" value="${permission.id }" type="checkbox"></td>
					<td>${permission.name}</td>
					<td>${permission.value}</td>
					
				</tr>
			</c:forEach>
			
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})" >
				<option value="20" <c:if test="${permissons.size==20 }">selected</c:if> >20</option>
				<option value="50" <c:if test="${permissons.size==50 }">selected</c:if>>50</option>
				<option value="100" <c:if test="${permissons.size==100 }">selected</c:if>>100</option>
				<option value="200"<c:if test="${permissons.size==200 }">selected</c:if> >200</option>
			</select>
			<span>条，共${permissons.totalElements}条</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="${permissons.totalElements}" numPerPage="${permissons.size}" pageNumShown="10" currentPage="${permissons.number+1 }"></div>

	</div>
</div>
