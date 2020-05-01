<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("root", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登陆</title>
<link href="${root}/xResources/css/reset.css" />
<link href="${root}/xResources/css/style.css" />
</head>
<body>
	<form action="../../userInfoTestServlet">
	<input type="hidden" value="register" name="method" />
	<table class="table-form">
		<tbody>
			<tr>
				<td>用户名：</td>
				<td class="white">
					<input type="text" name="username" />
				</td>
			</tr>
			<tr>
				<td>密  码：</td>
				<td class="white">
					<input type="password" name="userpassword" />
				</td>
			</tr>
			<tr>
				<td>个人爱好</td>
				<td class="white">
					<input type="checkbox" name="userhobby" /> 喝酒
					<input type="checkbox" name="userhobby" /> 抽烟
					<input type="checkbox" name="userhobby" /> 洗澡
					<input type="checkbox" name="userhobby" /> 按摩
					<input type="checkbox" name="userhobby" /> 打牌
				</td>
			</tr>
			<tr>
				<td></td>
				<td>
					<input type="submit" name="sub" value="提交" class="btn-default" />
				</td>
			</tr>
		</tbody>
	</table>
	</form>
</body>
</html>