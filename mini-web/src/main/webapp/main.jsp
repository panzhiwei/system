<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="accountInfo">
	<div class="alertInfo">
		<!--
		<h2>最新消息：</h2>
		<a href="">深交所：中小散户是股票上市首日买入主体 占比56%</a>
		-->
		<h2><a href="doc/dwz-user-guide.pdf" target="_blank">DWZ框架使用手册下载</a></h2>
		<a href="doc/dwz-user-guide.swf" target="_blank">DWZ框架视频教材下载</a>
	</div>
	<div class="right">
		<p>待办工作32项，消息212条</p>
		<p>07月12日，星期二</p>
	</div>
	<p><span>DWZ富客户端框架 </span></p>
	<p><a href="demo_page2.html" target="dialog">DWZ小组</a></p>
</div>

<div class="pageCentent">
<textarea layoutH="80" style="width:98%">
DWZ富客户端框架设计目标是简单实用、扩展方便、快速开发、RIA思路、轻量级
DWZ支持用html扩展的方式来代替javascript代码, 基本可以保证程序员不董javascript, 也能使用各种页面组件和ajax技术. 如果有特定需求也可以扩展DWZ做定制化开化.
DWZ基于jQuery可以非常方便的定制特定需求的UI组件, 并以jQuery插件的形式发布.
DWZ富客户端框架完全开源，可以免费获取全部源码。团队中的每个人都是开源的拥护者，都希望为中国软件开源事业尽自己的一份力量。 

在线演示地址 http://demo.dwzjs.com
在线文档 http://demo.dwzjs.com/doc/dwz-user-guide.pdf
DWZ框架Ajax开发视频教材 http://demo.dwzjs.com/doc/dwz-ajax-develop.swf
DWZ框架演示视频 http://demo.dwzjs.com/doc/dwz-user-guide.swf
Google Code下载: http://code.google.com/p/dwz/

学习DWZ的建议:
    通读DWZ文档，很多新手提的问题文档中都写了
    看demo每个组件演示效果和代码（留意组件html结构）
    建议安装firebug，用firebug看html结构、CSS和调试JS都非常方便。见附录一 firebug介绍
    对于初学者不建议看DWZ全部源码，但还是非常有必要看看dwz.ui.js和dwz.ajax.js
    可以从google code下载dwz_thinkphp版本，结合php后台去理解DWZ和服务器端的交互方式

DWZ区别于其它JS框架，最大的优点:
    完全开源，源码没有做任何混淆处理，方便扩展
    CSS和js代码彻底分离，修改样式方便
    简单实用，扩展方便，轻量级框架，快速开发
    仍然保留了html的页面布局方式
    支持HTML扩展方式调用UI组件，开发人员不需写js
    只要懂html语法不需精通js，就可以使用ajax开发后台
    基于jQuery，UI组件以jQuery插件的形式发布，扩展方便


DWZ研发组联系方式:
	杜权(UI设计)		d@j-ui.com
	吴平(Ajax开发)	w@j-ui.com
	张慧华(Ajax开发)	z@j-ui.com
	
	官方微博(欢迎加入)	http://weibo.com/dwzui 

	jQuery.DWZ-UI-1群(满员) 107983317
	jQuery.DWZ-UI-2群(满员) 69611933
	jQuery.DWZ-UI-3群(满员) 20866231
	jQuery.DWZ-UI-4群(满员) 369203
	jQuery.DWZ-UI-5群 85031937

</textarea>
</div>