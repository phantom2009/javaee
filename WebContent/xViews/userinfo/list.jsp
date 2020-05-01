<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%	
	pageContext.setAttribute("root",request.getContextPath());          
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>javaEE</title>
	<link type="text/css" rel="stylesheet" href="${root}/xResources/css/reset.css">
	<link type="text/css" rel="stylesheet" href="${root}/xResources/css/style.css">	
	<script type="text/javascript" src="${root}/xResources/js/jqueryv1.12.4/jquery.js"></script>
	<script type="text/javascript" src="${root}/xResources/js/07javaEE.js"></script>	
	<style type="text/css">
		
	</style>
</head>
<body>
	<!-- north -->
	<%@include file="../commons/north.jsp" %>
	<div id="main-container" class="main-container">
		<!-- west -->
		<div id="west" class="west">		
			<%@include file="../commons/west.jsp" %>
		</div>
		<!-- start of center -->
		<div id="center" class="center">			
			<ul class="bread-crumb clearfix">
				<li>客户关系管理 -&gt;用户信息</li>
			</ul>		
			<div id="tool-bar" class="tool-bar">
				<button id="btn-add" type="button" class="btn-default btn-64 btn-import">新增</button>
			</div>					
			<p class="title4table">最新用户</p>					
			<table id="userInfoList" class="table-4-list">
				<tbody>						
					<tr class="th">
						<td>编号</td>
						<td>用户名</td>
						<td>密码</td>					
						<td align="center">操作</td>
					</tr>
					<c:forEach var="userInfo" items="${userinfos}">
					<tr>
						<td>${userInfo.id}</td>
						<td>${userInfo.userName}</td>
						<td>${userInfo.userPassword}</td>									
						<td align="center">
							<a class="detail" onclick="javascript:showDetail(${userInfo.id});">查看</a>
							<a href="${root}/userInfoServlet?method=edit&id=${userInfo.id}&currentPage=${page.currentPage}">修改</a>
							<a>删除</a>
						</td>
					</tr>
					</c:forEach>						
				</tbody>
			</table>
			<ul class="pageBar">
				<li><a href="${root}/userInfoServlet?method=list&page=1&size=5">首页</a></li>
				<li>
					<!-- 可以有多个c:when -->
					<c:choose>
						<c:when test="${page.currentPage==1}">
							上一页
						</c:when>					
						<c:otherwise>
							<a href="${root}/userInfoServlet?method=list&page=${page.currentPage-1}&size=5">上一页</a>
						</c:otherwise>
					</c:choose>	
				</li>
				<li>
					<c:choose>
						<c:when test="${page.currentPage==page.totalPage}">
							下一页
						</c:when>
						<c:otherwise>
							<a href="${root}/userInfoServlet?method=list&page=${page.currentPage+1}&size=5">下一页</a>
						</c:otherwise>					
					</c:choose>
				</li>
				<li><a href="${root}/userInfoServlet?method=list&page=${page.totalPage}&size=5">尾页</a></li>			
				<li>
					共${page.totalPage}页，${page.total}条记录
				</li>
				<li>
					跳转到：
					<input type="text" name="straightPage" value="${page.currentPage}" class="straightPage" /> 
					<input type="button" name="go" value="GO" class="btn-default" />
				</li>
			</ul>		
		</div><!-- end of center -->
		
	</div><!-- end of main-container -->
</body>
<script type="text/javascript">	

$.ajaxSetup({
    type: 'POST',
    complete: function(xhr,status) {
    	
    	console.log(xhr);
    	console.log(status);
    	
    	var txt=xhr.responseText;
    	console.log(txt);
    	
    	if(txt.indexOf && txt.indexOf("!DOCTYPE html")!= -1){
			var top = getTopWinow();
            var yes = confirm('由于您长时间没有操作, session已过期, 请重新登录.');
            if (yes) {
             	top.location.href = "${root}/login.jsp";         
			}
    	}
    }
});

function getTopWinow(){
    var p = window;
    while(p != p.parent){
        p = p.parent;
    }
    return p;
}

	window.onload=function(){
		
		//TODO:设置内容区域高度
		setContentAreaHeight();
	
		//TODO:设置菜单效果
		setMenuEffect();
	
		window.onresize=setContentAreaHeight;
	
	};
	
	function showDetail(id){
		$.post("${root}/userInfoServlet",{
			method:"get",
			id:id			
		}).done(function(da,sta,xhr){
			console.log(da);
			//if(da.indexOf && da.indexOf("html")!= -1){
	        //   window.location.href= "${root}/login.jsp";
	        //	return;
	        //}else{
	        //	console.log(da.id);
	        //}
			
			
		},"JSON");
	}
</script>
</html>