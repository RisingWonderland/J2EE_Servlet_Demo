<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>My JSP 'index.jsp' starting page</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	<button onclick="window.location.href='login.jsp'">Demo Login</button>
	<button>Demo Ajax</button>
	<button onclick="window.location.href='download.jsp'">Demo Download</button>
	<button onclick="window.location.href='upload.jsp'">Demo Upload</button>
	<button onclick="window.location.href='ajaxByJSNative.jsp'">Demo JavaScript native Ajax</button>
	<button onclick="window.location.href='ajaxByjQuery.jsp'">Demo jQuery Ajax</button>
	<button>Demo...</button>
</body>
</html>
