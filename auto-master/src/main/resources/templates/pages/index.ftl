<!DOCTYPE html>  
  
<html>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试运维平台</title>
<link href="/js/bootstrap.min.css" rel="stylesheet">
<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<style type="text/css">
	.nav-top{background: url(/img/nav.jpg) center; width: 100%; height: 90px;}
	.nav-bottom{width: 100%; height: 40px; background: #002353;}
	.nav-frame{position: relative; height: 40px; width: 990px; margin: 0px auto; padding: 0px;}
	.nav-frame>li{float: left; margin: 0px; padding: 0px; list-style-type: none; min-width: 160px; text-align: center; height: 100%; font-size: 16px;  color: #FFFFFF;}
	.nav-frame>li:hover{background: #00a6f6;}
	.nav-frame>li>a{float: left; color: #FFFFFF;  width: 100%; text-decoration: none;height: 40px; line-height: 40px; margin-top: 0px; border-right: 1px solid #00a6f6; }
	.nav-frame>li>ul{width: 100%; background: #002353;margin: 0px; padding: 0px;  border-radius: 0px;}
	.nav-frame>li>ul>li{width: 100%;color: #00a6f6;font-size: 12px; margin: 0px; padding: 0px;}
	.nav-frame>li>ul>li>a{width: 100%;color: #00a6f6;font-size: 12px; height: 30px; line-height: 30px;margin: 0px; padding: 0px;}
	.nav-frame>li>ul>li>a:hover{background: #00a6f6; color:#FFFFFF;}
</style>

<script type="text/javascript">
$(document).ready(function(){
	$("#js_list1").click(function(){
		$("#downMain").attr({src:"showInterfaceList?f_source=O2O"});
	})
})
$(document).ready(function(){
	$("#js_list2").click(function(){
		$("#downMain").attr({src:"showAddInterface?f_source=O2O"});
	})
})
$(document).ready(function(){
	$("#js_list3").click(function(){
		$("#downMain").attr({src:"toInterfaceJsp.html?f_source=HR"});
	})
})
$(document).ready(function(){
	$("#js_list4").click(function(){
		$("#downMain").attr({src:"toSaveInterfaceJsp.html?f_source=HR"});
	})
})
$(document).ready(function(){
	$("#js_list5").click(function(){
		$("#downMain").attr({src:"toBatchJsp.html"});
	})
})
$(document).ready(function(){
		$(".nav-frame li").mousemove(function(){
			$(this).addClass("open")
		});
		$(".nav-frame li").mouseleave(function(){
			$(this).removeClass("open")
		});
	})
</script>
<body>  
<nav class="navbar navbar-default" role="navigation">
   <div class="nav-top">
   </div>
   <div class="nav-bottom">
      <ul class="nav-frame">
         <!-- <li class="dropdown" >
            <a href="http://172.95.65.12:8080/auto/index.jsp#" class="dropdown-toggle" data-toggle="dropdown">福加平台</a>
            <ul class="dropdown-menu">
               <li><a href="http://172.95.65.12:8080/auto/toInterfaceJsp.html">接口列表查询</a></li>
               <li><a href="http://172.95.65.12:8080/auto/toSaveInterfaceJsp.html">接口列表维护</a></li>
               <li><a href="http://172.95.65.12:8080/auto/index.jsp#">计息批次结果查询</a></li>
            </ul>
         </li> -->
         <li class="dropdown">
            <a href="http://172.95.65.12:8080/auto/index.jsp#" class="dropdown-toggle" data-toggle="dropdown">物加项目</a>
            <ul class="dropdown-menu">
               <li><a id="js_list1">接口列表查询</a></li>
               <li><a id="js_list2">添加接口</a></li>
            </ul>
         </li>
         <!--<li class="dropdown">
            <a href="http://172.95.65.12:8080/auto/index.jsp#" class="dropdown-toggle" data-toggle="dropdown">HR项目</a>
            <ul class="dropdown-menu">
               <li><a id="js_list3">接口列表查询</a></li>
               <li><a id="js_list4">添加接口</a></li>
            </ul>
         </li>-->
         <li class="dropdown">
            <a href="http://172.95.65.12:8080/auto/index.jsp#" class="dropdown-toggle" data-toggle="dropdown">运维管理</a>
            <ul class="dropdown-menu">
               <li><a href="http://172.95.65.16/nagios/" target="_blank">nagios</a></li>
               <li><a href="http://172.95.65.12:9090/" target="_blank">sonarqube</a></li>
            </ul>
         </li>
         <li class="dropdown">
            <a href="http://172.95.65.12:8080/auto/index.jsp#" class="dropdown-toggle" data-toggle="dropdown">友情链接</a>
            <ul class="dropdown-menu">
               <li><a href="http://172.95.65.10:81/zentao/user-login.html" target="_blank">禅道</a></li>
               <li><a href="http://172.95.65.11:8080/" target="_blank">部门wiki</a></li>
               <li><a href="http://172.95.65.37:8081/login" target="_blank">Jenkins</a></li>
               <li><a href="http://i.forte.com.cn/login/Login.jsp?logintype=1" target="_blank">办公OA</a></li>
               <li><a href="http://172.20.11.121:10080/" target="_blank">git_lab</a></li>
            </ul>
         </li>
         <!--<li class="dropdown">
            <a href="http://172.95.65.12:8080/auto/index.jsp#" class="dropdown-toggle" data-toggle="dropdown">HR系统批量任务</a>
            <ul class="dropdown-menu">
               <li><a id="js_list5">HR系统批量任务</a></li>
            </ul>
         </li>-->
      </ul>
   </div>
</nav>
<iframe name="down" id="downMain" src="" frameborder="false" scrolling="auto" style="border:none;" width="100%" height="570" allowtransparency="true"></iframe>

</body>  
</html> 