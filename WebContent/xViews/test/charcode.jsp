<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("root", request.getContextPath());
%>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>javaEE字符编码问题</title>
</head>
<body>
	<h3>字符编码问题的产生</h3>
	<p>表单get</p>
	<form action="${root}/CharcodeServlet" method="get" enctype="application/x-www-form-urlencoded">
		<input type="text" name="name" />
		<button type="submit">提交</button>
	</form>          
	<p>a标记get</p>
	<a href="${root}/CharcodeServlet?name=中文">传递参数</a>
	<p>表单post</p>
	<form action="${root}/CharcodeServlet" method="post" enctype="application/x-www-form-urlencoded">
		<input type="text" name="name" />
		<button type="submit">提交</button>
	</form> 
</body>
</html>