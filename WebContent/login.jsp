<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%	
	pageContext.setAttribute("root",request.getContextPath());          
%>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>javaEE</title>
	<link type="text/css" rel="stylesheet" href="${root}/xResources/css/reset.css">
	<link type="text/css" rel="stylesheet" href="${root}/xResources/css/style.css">
	<script type="text/javascript" src="${root}/xResources/js/jqueryv1.12.4/jquery.js"></script>
	<style type="text/css">
		html,body{
			width: 100%;height:100%; 
		}
		.login-frame{
			width: 400px;height: 225px;position:absolute;left: 50%;top: 50%;margin-left: -200px;margin-top: -160px;	border: 1px dotted gray; border-radius:4px;
		}
		.login-frame h3{
			text-indent: 10px;margin: 10px 0 10px 0;
		}
		.login-frame .error-clue{
			margin-top:15px;font-size: 12px;color:red;text-align: center;
		}
	</style>
</head>
<body>
	<div id="login-frame" class="login-frame">
		<h3>请先登陆</h3>
		<!-- 相对路径与绝对路径写法都可以只要自己别混淆即可 -->
		<!-- 
		<form action="loginServlet" name="login-form">	
		 -->
		<form action="${root}/loginServlet" name="login-form" enctype="application/x-www-form-urlencoded" method="post">	
		<input type="hidden" name="method" value="login" />
		<table class="table-4-form">
			<tbody>
				<tr>
					<td>账号</td>
					<td class="white">
						<input type="text" name="username" placeholder="请输入账号" />
					</td>			
				</tr>
				<tr>
					<td>密码</td>
					<td class="white">
						<input type="password" name="userpassword" placeholder="请输入密码" />
					</td>
					<td></td>
				</tr>
				<tr>
					<td colspan="2" class="white">
						<input type="submit" name="submit" value="登录" class="btn-default btn-64" />
						<input type="reset" name="reset" value="重置" class="btn-default btn-64"  />
					</td>
					<td></td>				
				</tr>
			</tbody>
		</table>
		</form>
		<p class="error-clue">${error}</p>		
	</div>
</body>
</html>