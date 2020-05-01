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
		/*  */
	</style>
</head>
<body>
	<h3>Servlet文件上传</h3>
	<pre>
		表单需要设置为post,multipart/form-data
		D:\workspace4study\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\07javaEE\xResources\upload
	</pre>
	<form action="${root}/fileUploadServlet" method="post" enctype="multipart/form-data">
		File: <input type="file" name="file1"/>
		<br />
		File: <input type="file" name="file2"/>
		<br />
		<br />
		Desc: <input type="text" name="description"/>
		<br />
		<br />
		<input type="checkbox" name="interesting" value="Reading"/>Reading
		<input type="checkbox" name="interesting" value="Party"/>Party
		<input type="checkbox" name="interesting" value="Sports"/>Sports
		<input type="checkbox" name="interesting" value="Shopping"/>Shopping
		<br />
		<br />
		<input type="submit" value="Submit"/>
	</form>
	
	<!-- 
		在本部分我们主要学习native servlet	如何实现文件上传，至于同步或异步是对于前端来说的
			
			前端异步上传的大概思路有
			
			ajaxFileUpload插件；
			自己写iframe隐藏域；			
			百度 多文件上传：这个百度作者是通过actionScript往页面中插入flash实现的	
			XMLHttpRequest2 的异步表单：这个是目前的主流
	 
	 -->
	
</body>
</html>