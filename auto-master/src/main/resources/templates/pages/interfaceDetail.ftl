<!DOCTYPE html>  

<html>  
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>执行用例</title>
<script type="text/javascript">
function downModule(idf_interface){
	window.location.href = "download?idf_interface=" + idf_interface;
}
</script>
</head>
<body>  
<h2>执行用例</h2>
<form action="runInterface" method="post" name="detailForm">
<input type="hidden" name="idf_interface" value="${bean.idf_interface!''}"/>
<input type="hidden" name="f_url" value="${bean.f_url!''}"/>
<p>接口名称：${bean.f_name!''}</p>
<p>接口描述：${bean.f_desc!''}</p>
<p>接口url：${bean.f_url!''}</p>
<p>参数列表</p>
<#list paramList as paramItem>
<input type="hidden" name="paramListKey" value="${paramItem!''}"/>
<p>${paramItem!''}:<#if paramItem!="没有参数"><input type="text" name="paramListValue" style="width:500px"/></#if></p>
</#list>
<p><input type="submit" value="提交"/></p>
<p><input type="button" value="下载模板" onclick="downModule(${bean.idf_interface!''})" /></p>
</form>
<form action="upload.html?idf_interface=${bean.idf_interface!''}"  enctype="multipart/form-data" method="post">
<input type="file" name="file" value="上传批量文件并执行"/></br>
<input type="submit" value="执行" name="submit"/>
</form>

</body>  
</html> 