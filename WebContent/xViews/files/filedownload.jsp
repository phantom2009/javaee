<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%
	pageContext.setAttribute("root", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Servlet 文件下载</title>
	<script type="text/javascript" src="${root}/xResources/js/jquery-3.5.0.js"></script>
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
	
	<p>静态文件下载本质上是页面的静态资源，这就和img标记的src属性是一样的，这点不要怀疑，img的src也是类似a标记将文件下载到浏览器</p>	
	<h3>文件下载--动态方式</h3>
	<ul>
		<li><a href="${root}/standardDownloadServlet?filename=Chrysanthemum&type=zip">Chrysanthemum文件下载</a></li>
		<li><a href="${root}/standardDownloadServlet?filename=Desert&type=zip">Desert文件下载</a></li>
		<li><a href="${root}/standardDownloadServlet?filename=Hydrangeas&type=zip">Hydrangeas文件下载</a></li>
	</ul>
	或者
	<ul>
		<li><a href="${root}/standardDownloadServlet?filename=01&type=csv">01文件下载</a></li>
		<li><a href="${root}/standardDownloadServlet?filename=02&type=csv">02文件下载</a></li>	
	</ul>	
	

	<h3>前端使用二进制流下载：所谓的前台下载</h3>
	<button id="downloadByJavaScript">下载</button>
	
	
	<h3>iframe下载方式</h3>
	<button id="downloadByIframe">下载</button>
	
	<iframe id="iframe4download" style="display:none;"></iframe>
	
		
	<script type="text/javascript">
	
		//TODO:前台下载文件
		function downloadFile(content, filename) {
		    // 创建隐藏的可下载链接
		    var eleLink = document.createElement('a');
		    //只有Firefox和chrome支持a标记的download属性，这就是说ie safari opera都不支持，现在h5时代也不能完全相信
		    eleLink.download = filename;
		    eleLink.style.display = 'none';
		    eleLink.innerHTML="temp";
		    // 字符内容转变成blob地址
		    var blob = new Blob([content]);
		    eleLink.href = URL.createObjectURL(blob);
		    // 触发点击
		    document.body.appendChild(eleLink);
		    eleLink.click();
		    // 然后移除
		    document.body.removeChild(eleLink);
		};	
		//在不移除时候a标记是这个样式：<a download="temp.csv" href="blob:http://localhost:8080/c3b0d9d6-8378-4e76-a055-3bd6fb70eb10">temp</a>
		
		
		
		$(function(){
			
			
			$("#downloadByJavaScript").click(function(evt){
				 let formData = new FormData();
			        formData.append("type","csv");
			        formData.append("filename","02");
			     	//post方式下载的好处就是这里表单传参数不受get方式请求参数限制，比如过长或者文件流等原因      
	            fetch("${root}/standardDownloadServlet", {
	            	method:"POST",   
	            	headers: {
	            		'Content-Type': 'application/x-www-form-urlencoded'
	            	},
					body: "filename=02&type=csv"
	            }).then(function(res){ 
	            	return res.blob();      //服务端不以带类型的返回文件内容 如json/xml之类，返回的是二进制流
	            }).then(function(data){
	            	console.log(data instanceof Blob);
	            	downloadFile(data, "temp.csv");
	            });
			});
			
			
			//var prm = Sys.WebForms.PageRequestManager.getInstance();
	        //prm.add_initializeRequest(downloadfile);
	        //触发函数
	        //function downloadfile(filepath) {
	        //   var iframe = document.createElement("iframe");
	        //   iframe.src = "GenerateFile.aspx?filepath=" + filepath;
	        //  iframe.style.display = "none";
	        //   document.body.appendChild(iframe);
	        //}
	        $("#downloadByIframe").click(function(){
	        	
	        	//也不是什么高科技，还是src。
	        	document.getElementById("iframe4download").src="${root}/standardDownloadServlet?filename=01&type=csv";
	        	
	        });
			
		});
		
		
	
	</script>
	
</body>
</html>