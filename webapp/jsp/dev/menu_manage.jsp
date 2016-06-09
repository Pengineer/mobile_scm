<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="hust.model.Menu"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>菜单管理</title>
    <link href="${ctx}/skin/default/css/page.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/skin/default/css/table.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${ctx}/js/common/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/js/common/tree.js"></script>
	<script type="text/javascript" src="${ctx}/js/dev/menu_manage.js"></script>
  </head>
  
  <body style="margin:8px">
  	<table class="table" width="90%" align="center">
  		<tr>
			<td width="200" id="treeContainer" style="text-align: left; vertical-align: top; padding-left: 8px"></td>
			<td id="menuDetail" style="padding: 0px">
				<iframe id="ifrm" name="menuDetail" frameborder="0" width="100%" height="100%" src="dev/menuManage_menuDetail.action?menu.id=root&menu.type=<%=Menu.MENUTYPE_PARENT%>"></iframe>
			</td>
		</tr>
  	</table>
  </body>
</html>
