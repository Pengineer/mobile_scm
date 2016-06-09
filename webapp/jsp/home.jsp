<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<title>${sysname }</title>
	<link rel="stylesheet" href="${ctx}/skin/${skin}/css/main.css" type="text/css">
	<script type="text/javascript" src="${ctx}/js/common/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/js/home.js"></script>
  </head>

  <!-- onselectstart表示鼠标无法右键；oncontextmenu设置鼠标无法右键。由于这两个参数不支持火狐浏览器，因此在css样式中为火狐浏览器做单独设置 -->
  <body onselectstart="return false;" oncontextmenu="return false;">
	<input type="hidden" name="ctx" value="${ctx}" />
	<input type="hidden" name="skinpath" value="${ctx}/skin/${skin}" />
	<div class="mask"></div>
	<div class="menu">
		<div class="handle"></div>
	</div>
	<div class="main">
		<div class="conDiv">
			<!-- 消息框的显示需要权限的控制，这里使用action代替普通jsp -->
			<iframe name="content" width="100%" height="100%" frameborder="0" src="login/welcome.action"></iframe>
		</div>
	</div>
	<div class="foot">登录用户:『${loginUser }』</div>
  </body>
</html>



 
