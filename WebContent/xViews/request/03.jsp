<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%
	pageContext.setAttribute("root", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Servlet 文件上传与读取</title>
	<link type="text/css" res="stylesheet" href="${root}/xResources/css/reset.css" />
	<link type="text/css" res="stylesheet" href="${root}/xResources/css/style.css" />
	<script type="text/javascript" src="${root}/xResources/js/jqueryv1.12.4/jquery.js"></script>
	<style type="text/css">
		.demo{
			font-size: 24px;color:green;
		}
	</style>
</head>
<body>
	
	<h3>html4时代的文件上传</h3>
	<form action="${root}/fileUploadServlet" method="post" enctype="multipart/form-data">
		
		File: <input type="file" name="file1"/>
		File: <input type="file" name="file2"/>
		<br />
		<br />
		Desc: <input type="text" name="desc"/>
		<br />
		<br />
		
		<input type="checkbox" name="interesting" value="Reading"/>Reading
		<input type="checkbox" name="interesting" value="Party"/>Party
		<input type="checkbox" name="interesting" value="Sports"/>Sports
		<input type="checkbox" name="interesting" value="Shopping"/>Shopping
		<br />
		<br />
		
		
		<input type="submit" name="sub" value="Submit"/>
		
	</form>

	<script type="text/javascript">
	
		$(function(){
			
			
			
		});
		
	</script>
</body>
</html>