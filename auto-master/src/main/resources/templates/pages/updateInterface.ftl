<!DOCTYPE html>  

<html>  
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>接口详情</title>
</head>
<body>  
<h2>接口详情</h2>
<form action="updateInterface" name="updateForm">
<input type="hidden" name="idf_interface" value="${bean.idf_interface!''}"/>
<input type="hidden" name="f_source" value="${bean.f_source!''}" />
<p>序号：${bean.idf_interface!''}</p>
<p>接口名称：<input type="text" name="f_name" value="${bean.f_name!''}" style="width:500px"/></p>
<p>接口描述：<input type="text" name="f_desc" value="${bean.f_desc!''}" style="width:500px"/></p>
<p>接口地址：<input type="text" name="f_url" value="${bean.f_url!''}" style="width:500px"/></p>
<p>接口参数：<input type="text" name="f_parameters" value="${bean.f_parameters!''}" style="width:500px"/></p>
<p>请使用|分割参数</p>
<input type="submit" value="修改"/>
</form>
</body>
</body>  
</html> 