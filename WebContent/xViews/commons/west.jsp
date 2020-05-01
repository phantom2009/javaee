<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<p>管理中心</p>
<!-- 
<ul id="menu">
	<li><a href="${root}/homeServlet">首页</a></li>
	<li><a href="${root}/xViews/userinfo/list.jsp">用户账号管理</a></li>
	<li><a href="${root}/xViews/cusinfo/list.jsp">客户信息管理</a></li>
	<li><a href="${root}/xViews/custype/list.jsp">客户类别</a></li>
	<li><a href="${root}/xViews/custype/list.jsp">积分信息管理</a></li>
</ul>
 -->
<!-- 咱学习servlet jsp，全部使用Servlet机制 -->
<ul id="menu">
	<li><a href="${root}/homeServlet">首页</a></li>
	<li><a href="${root}/userInfoServlet?method=list">用户账号管理</a></li>
	<li><a href="${root}/xViews/cusinfo/list.jsp">客户信息管理</a></li>
	<li><a href="${root}/xViews/custype/list.jsp">客户类别</a></li>
	<li><a href="${root}/xViews/custype/list.jsp">积分信息管理</a></li>
</ul>
