<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>中岩科技静载信息管理系统</title>

<link href="${ctx}/static/dwz/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${ctx}/static/dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${ctx}/static/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="${ctx}/static/dwz/js/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
<!--[if IE]>
<link href="${ctx}/static/dwz/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

<script src="${ctx}/static/dwz/js/speedup.js" type="text/javascript"></script>
<script src="${ctx}/static/dwz/js/jquery-1.7.1.js" type="text/javascript"></script>
<script src="${ctx}/static/dwz/js/jquery.cookie.js" type="text/javascript"></script>
<script src="${ctx}/static/dwz/js/jquery.validate.js" type="text/javascript"></script>
<script src="${ctx}/static/dwz/js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="${ctx}/static/dwz/js/xheditor/xheditor-1.1.12-zh-cn.min.js" type="text/javascript"></script>
<script src="${ctx}/static/dwz/js/uploadify/scripts/swfobject.js" type="text/javascript"></script>
<script src="${ctx}/static/dwz/js/uploadify/scripts/jquery.uploadify.v2.1.0.js" type="text/javascript"></script>

<%-- <script src="${ctx}/static/dwz/js/dwz.core.js" type="text/javascript"></script>
<script src="${ctx}/static/dwz/js/dwz.util.date.js" type="text/javascript"></script>
<script src="${ctx}/static/dwz/js/dwz.validate.method.js" type="text/javascript"></script>
<script src="${ctx}/static/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
<script src="${ctx}/static/dwz/js/dwz.barDrag.js" type="text/javascript"></script>
<script src="${ctx}/static/dwz/js/dwz.drag.js" type="text/javascript"></script>
<script src="${ctx}/static/dwz/js/dwz.tree.js" type="text/javascript"></script>
<script src="${ctx}/static/dwz/js/dwz.accordion.js" type="text/javascript"></script>
<script src="${ctx}/static/dwz/js/dwz.ui.js" type="text/javascript"></script>
<script src="${ctx}/static/dwz/js/dwz.theme.js" type="text/javascript"></script>
<script src="${ctx}/static/dwz/js/dwz.switchEnv.js" type="text/javascript"></script>
<script src="${ctx}/static/dwz/js/dwz.alertMsg.js" type="text/javascript"></script>
<script src="${ctx}/static/dwz/js/dwz.contextmenu.js" type="text/javascript"></script>
<script src="${ctx}/static/dwz/js/dwz.navTab.js" type="text/javascript"></script>
<script src="${ctx}/static/dwz/js/dwz.tab.js" type="text/javascript"></script>
<script src="${ctx}/static/dwz/js/dwz.resize.js" type="text/javascript"></script>
<script src="${ctx}/static/dwz/js/dwz.dialog.js" type="text/javascript"></script>
<script src="${ctx}/static/dwz/js/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="${ctx}/static/dwz/js/dwz.sortDrag.js" type="text/javascript"></script>
<script src="${ctx}/static/dwz/js/dwz.cssTable.js" type="text/javascript"></script>
<script src="${ctx}/static/dwz/js/dwz.stable.js" type="text/javascript"></script>
<script src="${ctx}/static/dwz/js/dwz.taskBar.js" type="text/javascript"></script>
<script src="${ctx}/static/dwz/js/dwz.ajax.js" type="text/javascript"></script>
<script src="${ctx}/static/dwz/js/dwz.pagination.js" type="text/javascript"></script>
<script src="${ctx}/static/dwz/js/dwz.database.js" type="text/javascript"></script>
<script src="${ctx}/static/dwz/js/dwz.datepicker.js" type="text/javascript"></script>
<script src="${ctx}/static/dwz/js/dwz.effects.js" type="text/javascript"></script>
<script src="${ctx}/static/dwz/js/dwz.panel.js" type="text/javascript"></script>
<script src="${ctx}/static/dwz/js/dwz.checkbox.js" type="text/javascript"></script>
<script src="${ctx}/static/dwz/js/dwz.history.js" type="text/javascript"></script>
<script src="${ctx}/static/dwz/js/dwz.combox.js" type="text/javascript"></script>
<script src="${ctx}/static/dwz/js/dwz.print.js" type="text/javascript"></script>
 --%>
<script src="${ctx}/static/dwz/js/dwz.min.js" type="text/javascript"></script>

<script src="${ctx}/static/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
	DWZ.init("${ctx}/static/dwz/dwz.frag.xml", {
		//loginUrl:"login_dialog.html", loginTitle:"登录",	// 弹出登录对话框
		loginUrl:"${ctx }/logout",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"${ctx}/static/dwz/themes"}); // themeBase 相对于index页面的主题base路径
		}
	});
});
</script>
</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<a class="logo" href="http://www.whrsm.com/index.asp" target="_blank">标志</a>
				<ul class="nav">
					<shiro:user>
					<li style="color:white">您好, <shiro:principal property="realName" /></li>
					</shiro:user>
					<li><a href="changepwd.html" target="dialog" width="600">修改密码</a></li>
					<li><a href="changepwd.html" target="dialog" width="600">个人信息</a></li>
					<li><a href="${ctx }/logout">退出</a></li>
				</ul>
				<ul class="themeList" id="themeList">
					<li theme="default"><div class="selected">蓝色</div></li>
					<li theme="green"><div>绿色</div></li>
					<!--<li theme="red"><div>红色</div></li>-->
					<li theme="purple"><div>紫色</div></li>
					<li theme="silver"><div>银色</div></li>
					<li theme="azure"><div>天蓝</div></li>
				</ul>
			</div>

			<!-- navMenu -->
			
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>

				<div class="accordion" fillSpace="sidebar">
					<div class="accordionHeader">
						<h2><span>Folder</span>基础信息管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							
							<li><a href="${ctx }/account/user?navTabId=user" target="navTab" rel="user">用户管理</a></li>
							<li><a href="${ctx }/account/role?navTabId=role" target="navTab" rel="role">角色管理</a></li>
							<li><a href="${ctx }/account/permission?navTabId=permission" target="navTab" rel="permission">权限管理</a></li>
									
							
						</ul>
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>工程管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="blank.jsp" target="navTab" rel="demo_page1">工程信息管理</a></li>
							<li><a href="blank.jsp" target="navTab" rel="demo_page2">静载数据监控</a></li>
							<li><a href="blank.jsp" target="navTab" rel="demo_page4">静载历史数据</a></li>
							<li><a href="blank.jsp" target="navTab" rel="demo_page5">工程信息统计</a></li>
							<li><a href="blank.jsp" target="navTab" rel="demo_page5">未知数据管理</a></li>
							<li><a href="blank.jsp" target="navTab" rel="demo_page5">在线测试设备</a></li>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>人员管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="blank.jsp" target="navTab" rel="demo_page5">检测人员列表</a></li>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>设备管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="blank.jsp" target="navTab" rel="demo_page5">检测设备列表</a></li>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>监督管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="blank.jsp" target="navTab" rel="demo_page5">监督人员列表</a></li>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>单位管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="blank.jsp" target="navTab" rel="demo_page5">检测单位列表</a></li>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>公告管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="blank.jsp" target="navTab" rel="demo_page5">公告信息列表</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="accountInfo">
							
							<p><span>DWZ富客户端框架</span></p>
							<p>DWZ官方微博:<a href="http://weibo.com/dwzui" target="_blank">http://weibo.com/dwzui</a></p>
						</div>
						
			</div>
		</div>

	</div>

	<div id="footer">Copyright &copy; 2010 <a href="demo_page2.html" target="dialog">DWZ团队</a> Tel：010-52897073</div>


</body>
</html>