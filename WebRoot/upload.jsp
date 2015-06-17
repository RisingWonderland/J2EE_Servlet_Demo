<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
	<base href="<%=basePath%>">

	<title>Upload Demo</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	<script type="text/javascript" src="./res/script/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="./res/script/jquery-2.1.4.min.js"></script>
	-->
	<script type="text/javascript" src="./res/script/jquery-2.1.4.js"></script>
	<script type="text/javascript" src="./res/script/jquery.ajaxfileupload.js"></script>

</head>

<body>
	<h1>Upload files</h1>
	<h2>普通上传 - 表单</h2>
	<form action="UploadServlet" name="form_upload1" method="post" enctype="multipart/form-data">
		<input type="file" name="form_upload1_file1" ><br/>
		<input type="submit" value="Upload one file" /><br/>
	</form>
	
	<h2>普通上传 - 表单 - 多文件</h2>
	<form action="UploadServlet" name="form_upload2" method="post" enctype="multipart/form-data">
		<input type="file" name="form_upload2_file1" ><br/>
		<input type="file" name="form_upload2_file2" ><br/>
		<input type="file" name="form_upload2_file3" ><br/>
		<input type="submit" value="Upload multiple file" /><br/>
	</form>
	
	<h2>无刷新上传 - 表单</h2>
	<p>原理：在页面中添加一个隐藏的iframe，上传文件所在的form表单指向该iframe，iframe中的页面刷新，宿主页面与其交互传递数据。</p>
	<form action="UploadServlet" name="form_upload3" id="form_upload3" method="post" enctype="multipart/form-data" target="iframe_hidden">
		<input type="file" name="form_upload3_file1" ><br/>
		<input type="button" value="Upload file without refresh" onclick="submitFormUpload3();" /><br/>
	</form>
	<iframe name="iframe_hidden" id="iframe_hidden" width="100%" height="50" ></iframe>
	
	<h2>无刷新上传 - 表单 - jQuery插件</h2>
	<p>选择文件后自动上传，请确保服务端返回JSON数据，并查看浏览器控制台输出</p>
	<form action="" name="form_upload4" id="form_upload4" method="post" enctype="multipart/form-data">
		<input type="file" name="form_upload4_file1" id="form_upload4_file1" ><br/>
	</form>
	
	<p></p>
	<p></p>
	<p></p>
</body>
<script type="text/javascript">
function submitFormUpload3(){
	var formUpload3 = document.getElementById("form_upload3");
	formUpload3.submit();
}

$(document).ready(function(){
	$("#form_upload4_file1").AjaxFileUpload({
		action: 'UploadServlet',
		onChange: function(filename){
			console.log('Upload onChange event fire.');
			console.log(filename);
		},
		onSubmit: function(filename){
			console.log('Upload onSubmit event fire.');
			console.log(filename);
		},
		onComplete: function(filename, response){
			console.log('Upload onComplete event fire.');
			console.log(filename);
			console.log(response);
		}
	});
});
</script>
</html>
