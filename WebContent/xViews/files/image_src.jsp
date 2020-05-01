<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%
	pageContext.setAttribute("root", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Servlet 图片获取</title>
	<script type="text/javascript" src="${root}/xResources/js/jquery-3.5.0.js"></script>
</head>
<body>
	<img alt="" src="${root}/imageOutputServlet">	
</body>
</html>