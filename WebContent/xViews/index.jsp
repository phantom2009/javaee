<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%	
	pageContext.setAttribute("root",request.getContextPath());          
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>javaEE</title>
	<link type="text/css" rel="stylesheet" href="${root}/xResources/css/reset.css">
	<link type="text/css" rel="stylesheet" href="${root}/xResources/css/style.css">	
	<script type="text/javascript" src="${root}/xResources/js/jqueryv1.12.4/jquery.js"></script>
	<script type="text/javascript" src="${root}/xResources/js/07javaEE.js"></script>	
</head>
<body>
	<!-- north -->
	<%@include file="commons/north.jsp" %>	
	<div id="main-container" class="main-container">
		<!-- west -->
		<div id="west" class="west">		
			<%@include file="commons/west.jsp"%>
		</div>		
		<!-- center -->
		<div id="center" class="center">		
			
			<ul class="bread-crumb"><li>客户关系管理 -&gt;首页</li></ul>
		
			<p class="title4table">最新用户</p>
								
			<table class="table-4-list">
				<tbody>						
					<tr class="th">
						<td>编号</td>
						<td>用户名</td>
						<td>密码</td>					
						<td align="center">操作</td>
					</tr>
					<c:forEach items="${userinfos}" var="userinfo">
					<tr>
						<td>${userinfo.id}</td>
						<td>${userinfo.userName}</td>
						<td>${userinfo.userPassword}</td>				
						<td align="center">
							<a href="javascript:void(0)" onclick="getUserInfo('${userinfo.id}')">查看</a>						
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<p class="title4table">最新客户信息</p>	
							
			<table class="table-4-list">
				<tbody>						
					<tr class="th">
						<td>编号</td>
						<td>客户名</td>
						<td>性别</td>					
						<td align="center">操作</td>
					</tr>
					<c:forEach items="${cusinfos}" var="cusinfo">
					<tr>
						<td>${cusinfo.id}</td>
						<td>${cusinfo.cusName}</td>
						<td>${cusinfo.cusSex}</td>				
						<td align="center">
							<a href="javascript:void(0);" onclick="getCusInfo('${cusinfo.id}')">查看</a>						
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
												
		</div><!-- end of center -->
	</div><!-- end of main-container -->	
</body>
<script type="text/javascript">		
	window.onload=function(){
		
		//TODO:设置内容区域高度
		setContentAreaHeight();
	
		//TODO:设置菜单效果
		setMenuEffect();
	
		window.onresize=setContentAreaHeight;
	
	};
</script>
</html>