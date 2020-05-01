<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%
	pageContext.setAttribute("root", request.getContextPath());
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Servlet 文件下载</title>
	<link type="text/css" res="stylesheet" href="${root}/xResources/css/reset.css" />
	<link type="text/css" res="stylesheet" href="${root}/xResources/css/style.css" />
	<script type="text/javascript" src="${root}/xResources/js/jqueryv1.12.4/jquery.js"></script>
	<style type="text/css">
		
	</style>
</head>
<body>
	
	<h3>文件下载--静态文件下载方式</h3>
	<p>固定路径的文件下载</p>
	<ul>
		<li><a href="${root}/xResources/zips/Chrysanthemum.zip">Chrysanthemum</a></li>		
		<li><a href="${root}/xResources/zips/Desert.zip">Desert</a></li>		
		<li><a href="${root}/xResources/zips/Hydrangeas.zip">Hydrangeas</a></li>		
		<li><a href="${root}/xResources/imgs/SamplePictures/Chrysanthemum.jpg">Chrysanthemum.jpg</a></li>		
	</ul>
	
	<p>静态文件下载无法传递参数，也就是写死的，如果要权限控制这种方式很不合适</p>	
	<h3>文件下载--动态方式</h3>
	<% 
		//1. 通知客户端浏览器: 这是一个需要下载的文件, 不能再按普通的 html 的方式打开.
		//即设置一个响应的类型: application/x-msdownload
		//response.setContentType("application/x-msdownload"); 
	
		//2. 通知客户端浏览器: 不再有浏览器来处理该文件, 而是交由用户自行处理
		//设置用户处理的方式: 响应头: Content-Disposition
		//response.setHeader("Content-Disposition", "attachment;filename=abc.txt");
	%>	
	<ul>
		<li><a href="${root}/fileDownloadServlet?filename=Chrysanthemum&type=zip">Chrysanthemum文件下载</a></li>
		<li><a href="${root}/fileDownloadServlet?filename=Desert&type=zip">Desert文件下载</a></li>
		<li><a href="${root}/fileDownloadServlet?filename=Hydrangeas&type=zip">Hydrangeas文件下载</a></li>
	</ul>
	
	<h3>下载csv</h3>
	<ul>
		<li><a href="${root}/fileDownloadServlet?filename=01&type=csv">01文件下载</a></li>
		<li><a href="${root}/fileDownloadServlet?filename=02&type=csv">02文件下载</a></li>	
	</ul>	
	
	<h3>前端使用二进制流下载</h3>
	<button id="downloadByJavaScript">下载</button>
	
		
	<script type="text/javascript">
	
		//前台下载文件
		function downFile(content, filename) {
		    // 创建隐藏的可下载链接
		    var eleLink = document.createElement('a');
		    eleLink.download = filename;
		    eleLink.style.display = 'none';
		    // 字符内容转变成blob地址
		    var blob = new Blob([content]);
		    eleLink.href = URL.createObjectURL(blob);
		    // 触发点击
		    document.body.appendChild(eleLink);
		    eleLink.click();
		    // 然后移除
		    document.body.removeChild(eleLink);
		};	
	
		
		$(function(){
			
			
			$("#downloadByJavaScript").click(function(evt){
				
				 let formData = new FormData();
			        formData.append("type","csv");
			        formData.append("filename","02");
			 
	            
	            
	            
	            fetch("${root}/fileDownloadServlet", {
	            	method:"POST",   
	            	headers: {
	            		'Content-Type': 'application/x-www-form-urlencoded'
	            	},
					body: "filename=02&type=csv"
	            }).then(function(res){
	            	//console.log(res.blob);   
	            	//console.log(res);   
	            	return res.blob();
	            }).then(function(data){
	            	//console.log(data);
	            	console.log(data instanceof Blob);
	            	
	            	downFile(data, "temp.csv");
	            });
	          
	           
				
				
			});
			
		});
		
		
	
	</script>
</body>
</html>