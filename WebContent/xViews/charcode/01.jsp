<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%
	pageContext.setAttribute("root", request.getContextPath());
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>javaEE char code</title>
</head>
<body>
	<p>表单get</p>
	<form action="${root}/charcodeServlet" method="get" enctype="application/x-www-form-urlencoded">
		<input type="text" name="name" />
		<button type="submit">提交</button>
	</form>          
	<p>a标记get</p>
	<a href="${root}/charcodeServlet?name=中文">传递参数</a>
	<p>表单post</p>
	<form action="${root}/charcodeServlet" method="post" enctype="application/x-www-form-urlencoded">
		<input type="text" name="name" />
		<button type="submit">提交</button>
	</form> 

	
</body>
</html>