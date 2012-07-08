<%@ page contentType="text/html;charset=UTF-8" %>
<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})" >
				<option value="20" ${pages.size==20 ?"selected":""}>20</option>
				<option value="50" ${pages.size==50 ?"selected":""}>50</option>
				<option value="100" ${pages.size==100 ?"selected":""}>100</option>
				<option value="200" ${pages.size==200 ?"selected":""}>200</option>
			</select>
			<span>条，共${pages.totalElements}条</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="${pages.totalElements}" numPerPage="${pages.size}" pageNumShown="10" currentPage="${pages.number+1 }"></div>

	</div>