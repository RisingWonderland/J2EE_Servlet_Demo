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

<title>jQuery Demo 1</title>
<!--
<link rel="stylesheet" type="text/css" href="styles.css">
-->
<script type="text/javascript" src="./res/script/jquery-2.1.4.min.js"></script>

</head>

<body>
	province:<select id="province"></select><br/>
    city:<select id="city"></select><br/>
    Table Show：
    <table id="table" border="1"></table>
</body>
<script type="text/javascript">
$(document).ready(function(){
	$("<option></option>").html("--请选择--").appendTo($("#province"));
	$("#province").change(setCity);
	console.log("OK");
	$.getJSON("GetProvinceServlet", function(data){
		console.log(data);
		$.each(data, function(i){
			$("<option></option>").val(data[i].id).html(data[i].name).appendTo($("#province"));
		});
		for(var i = 0;i < data.length;i++)
		{
			var tr = $("<tr></tr>");
			tr.append($("<td></td>")).html(data[i].id);
			tr.append($("<td></td>")).html(data[i].name);
			tr.appendTo($("#table"));
		}
	});
});
function setCity()
{
	$("#city").empty();
	$.getJSON("GetCityServlet?cityId=" + $("#province").val(), function(data){
		$.each(data, function(i){
			$("<option></option>").val(data[i].id).html(data[i].name).appendTo($("#city"));
		});
	});
}
</script>
</html>
