<!DOCTYPE html>  

<html>  
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>批量执行接口用例结果</title>
</head>
<body>  
<h2>批量执行接口用例结果</h2>
<table border="1">
  <tr>
    <th width="100px">序号</th>
    <th width="200px">接口url</th>
    <th width="200px">接口请求</th>
    <th width="300px">接口响应</th>
    <th width="100px">执行结果</th>
  </tr>
  <#list resultPatch as resultPatchItem>
  <tr>
  	<td>${resultPatchItem.f_patch!''}</td>
  	<td>${forteInterface.f_url!''}</td>
  	<td>${resultPatchItem.f_parameters!''}</td>
  	<td><xmp>${resultPatchItem.f_response!''}</xmp></td>
  	<td><script>var v;v = ${resultPatchItem.f_status?js_string};document.write(v=1?"成功":"失败");</script></td>
  </tr>
  </#list>
</body>  
</html> 