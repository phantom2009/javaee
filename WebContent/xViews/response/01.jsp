<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("root", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Servlet Response</title>
<script type="text/javascript" src="${root}/xResources/js/jqueryv1.12.4/jquery.js"></script>
<style type="text/css">
	.demo{
		font-size: 24px;color:green;
	}
</style>
</head>
<body>

	<div id="returnHTML"></div>
	
	<img alt="" src="${root}/httpServletResponseTestServlet?method=returnStream&imageName=Chrysanthemum" width="200" height="200"  />
	
	<img alt="" src="${root}/httpServletResponseTestServlet?method=returnStream&imageName=Desert" width="200" height="200"  />
	
	<img alt="" src="${root}/httpServletResponseTestServlet?method=returnStream&imageName=Tulips" width="200" height="200"  />
	
<script type="text/javascript">

	$(function(){
		
		//alert($);
		
		//getJSON();
		
		getHTML();
		
		//getStream();
		
	});
	
	function getJSON(){
		$.ajax({
			url:"${root}/httpServletResponseTestServlet",
			data:{
				method:"returnJSON"
			},
			dataType:"json",
			success:function(da,sta,xhr){
				console.log(da.userName);
			}
		});
	}
	
	function getHTML(){
		$.ajax({
			url:"${root}/httpServletResponseTestServlet",
			data:{
				method:"returnHTML"
			},
			dataType:"html",
			success:function(da,sta,xhr){
				//console.log(da.userName);
				$("#returnHTML").html(da);
				
				console.log($(".demo"));
			}
		});
	}
	
	function getStream(){
		$.ajax({
			url:"${root}/httpServletResponseTestServlet",
			data:{
				method:"returnStream"
			},
			dataType:"text",
		}).done(function(da,sta,xhr){
			console.log(da);
		});
	}
</script>
</body>
</html>