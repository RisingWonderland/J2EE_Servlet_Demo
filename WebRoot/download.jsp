<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
	<base href="<%=basePath%>">

	<title>Download Demo</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	Download something. <br>
	<p>使用超链接实现文件下载：浏览器识别的文件将直接显示，例如图片；浏览器不识别的文件将启动下载。</p>
	<a href="./resources/img/img1.jpg">see a picture</a><br/>
	<a href="./resources/zip/zip1.zip">download a zip file</a><br/>
	<p>使用Servlet实现文件下载</p>
	<a href="./DownloadServlet?fileName=img/img1.jpg">download a picture</a><br/>
	<a href="./DownloadServlet?fileName=zip/zip1.zip">download a zip file</a><br/>
</body>
</html>
