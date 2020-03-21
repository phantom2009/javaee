<%@page import="javaee.entities.UserInfo"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>javaEE</title>
</head>
<body>
	<h3>hello java!</h3>
	<%
		UserInfo userInfo=new UserInfo(Integer.valueOf(1001),"admin","123456");
	
		out.write(userInfo.toString());
	%>
</body>
</html>