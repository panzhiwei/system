<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/tags.jsp"%>

<jsp:include page="/include/pageHeader.jsp"></jsp:include>

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
		
			<li><a class="icon" href="${ctx }/account/permission/view/{sid_entity}?navTabId=${navTabId}" target="dialog" warn="请选择一个用户"><span>查看详情</span></a></li>
			<li class="line">line</li>
			<shiro:hasPermission name="permission:edit">
				<li><a class="add" href="${ctx }/account/permission/create?navTabId=${navTabId}" target="dialog"><span>添加</span></a></li>
				
				<li><a class="edit" href="${ctx }/account/permission/update/{sid_entity}?navTabId=${navTabId}" target="dialog" warn="请选择一个用户"><span>修改</span></a></li>
				<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${ctx }/account/permission/delete?navTabId=${navTabId}" class="delete"><span>删除</span></a></li>
		
			</shiro:hasPermission>	
			<!-- <li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li> -->
		</ul>
	</div>
	<table class="table" layoutH="138" width="100%">
		<thead>
			<tr>
			<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
			<th width="100" orderField="name">权限名称</th>
			<th width="100" orderField="value">权限编码</th>
			
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pages.content}" var="entity">
				<tr target="sid_entity" rel="${entity.id }">
					<td><input name="ids" value="${entity.id }" type="checkbox"></td>
					<td>${entity.name}</td>
					<td>${entity.value}</td>
					
				</tr>
			</c:forEach>
			
		</tbody>
	</table>
	<jsp:include page="/include/pageFooter.jsp"></jsp:include>
	
</div>
