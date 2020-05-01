<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ page import="javaEE.entities.UserInfo" %>
<%
	pageContext.setAttribute("root", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Servlet Request</title>	
	<style type="text/css">
		
	</style>
</head>
<body>
	
	<pre>
		request是内置对象，其类型为HttpServletRequest,UML结构图就不做多理解啦
		
		1.从servlet请求转发过来，request中本身带数据
	</pre>
	
	<%
		UserInfo user=(UserInfo)request.getAttribute("user");
		out.write(user.getId()+","+user.getUserName()+","+user.getUserPassword());
	%>
	
	<P>
		用el表达式直接获取:
		${requestScope.user.id},${requestScope.user.userName},${requestScope.user.userPassword}
	</p>
	<P>
		省略scope写法:
		${user.id},${user.userName},${user.userPassword}
	</p>
	
	<p>
		Session域：${userinfo.id}
	</p>
	
</body>
<script type="text/javascript">
	
</script>
</html>