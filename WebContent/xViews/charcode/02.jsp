<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ page import="java.io.PrintWriter"%>
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
	<h3>javaEE字符编码问题</h3>
	
	<%
		String name=request.getParameter("name");
		PrintWriter writer = response.getWriter();
		writer.write(name);
	%>
</body>
</html>