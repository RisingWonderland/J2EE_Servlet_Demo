<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
	<base href="<%=basePath%>">

	<title>User login</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<form name="form_login" method="post" action="UserLoginServlet">
		<label>Account: </label><input name="userAccount" type="text" value="" /><br/>
		<label>Password: </label><input name="userPassword" type="password" value="" /><br/>
		<input type="submit" value="Submit" />
		<input type="reset" value="Reset" />
	</form>
</body>
</html>
