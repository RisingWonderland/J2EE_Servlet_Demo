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

<title>JavaScript native Ajax demo</title>
<!--
<link rel="stylesheet" type="text/css" href="styles.css">
-->

</head>

<body>
	Check if the user exists, <br/>
	please enter account (get):<br/>
	<input type="text" name="account" onblur="checkAccountExistForGet(this);" /><span id="checkAccountResultForGet"></span><br/>
	please enter password (post):<br/>
	<input type="text" name="account" onblur="checkAccountExistForPost(this);" /><span id="checkAccountResultForPost"></span><br/>
</body>
<script type="text/javascript">
function checkAccountExistForGet(me){
	var url = "CheckUserExistServlet?account=" + me.value;
	var xhr = getXHR();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var result = xhr.responseXML;
			if(result){
				var infoNode = result.getElementsByTagName("info");
				if(infoNode.length > 0){
					var info = infoNode[0].childNodes[0].nodeValue;
					document.getElementById("checkAccountResultForGet").innerHTML = info;
				}
			}
		}
	}
	xhr.open("get", url, true);
	xhr.send();
}

function checkAccountExistForPost(me){
	var url = "CheckUserExistServlet";
	var xhr = getXHR();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var result = xhr.responseText;
			document.getElementById("checkAccountResultForPost").innerHTML = result;
		}
	}
	xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	xhr.open("POST", url, true);
	xhr.send("account=" + me.value);
}

function getXHR(){
	if(window.XMLHttpRequest){
		return new XMLHttpRequest();
	}else{
		return new ActiveXObject("Microsoft.XMLHTTP");
	}
}
</script>
</html>
