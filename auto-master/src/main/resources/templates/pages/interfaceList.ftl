<!DOCTYPE html>  
  
<html lang="en">  
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>接口列表</title>
<script type="text/javascript">
function deleteInterface(idf_interface){
	if(confirm("确认删除吗")){
		window.location.href = "deleteInterface?idf_interface="+idf_interface;
	}else{
		return;
	}
}
function updateInterface(idf_interface){
	window.location.href = "showUpdatePage?idf_interface="+idf_interface;
}
</script>
</head>
<body>  
<h2>接口列表</h2>
<form action="">
<table border="1">
  <tr>
    <th>序号</th>
    <th>接口名称</th>
    <th>接口描述</th>
    <th>接口来源</th>
    <th>操作</th>
  </tr>
  
  <#list interfaceList as interfaceItem>
	<tr>
		<td>${interfaceItem.idf_interface!''}</td>
		<td><a href="showInterfaceDetail?idf_interface=${interfaceItem.idf_interface!''}">${interfaceItem.f_name!''}</a></td>
		<td>${interfaceItem.f_desc!''}</td>
		<td>${interfaceItem.f_source!''}</td>
		<td><input type="button" value="修改" onclick="updateInterface(${interfaceItem.idf_interface!''})"/><input type="button" value="删除" onclick="deleteInterface(${interfaceItem.idf_interface!''})"/></td>
	</tr>
  </#list>
</table>
</form>


</body>  
  
</html> 