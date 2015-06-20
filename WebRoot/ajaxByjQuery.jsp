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

<title>Demo</title>
<!--
<link rel="stylesheet" type="text/css" href="styles.css">
-->
<script type="text/javascript" src="./res/script/jquery-2.1.4.min.js"></script>

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
	$.get(
		"CheckUserExistServlet",
		{
			account: me.value
		},
		function(data){
			var infoNodes = data.getElementsByTagName("info");
			if(infoNodes.length > 0){
				var info = infoNodes[0].childNodes[0].nodeValue;
				$("#checkAccountResultForGet").text(info);
			}
			
		}
	);
}


function checkAccountExistForPost(me){
	$.ajax({
		url: "CheckUserExistServlet",
		type: "post",
		cache: false,
		data: {
			account: me.value
		},
		dataType: "html",
		global: false,
		complete: function(){
			console.log("ajax request complete");
		},
		success: function(data){
			console.log("ajax request success");
			$("#checkAccountResultForPost").text(data);
		},
		error: function(){
			console.log("ajax request failure");
		},
	});
}
</script>
</html>
