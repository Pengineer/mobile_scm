<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/include/taglib.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>菜单管理</title>   
    <%@ include file="/jsp/include/css.jsp" %>
    <%@ include file="/jsp/include/js.jsp" %>
  </head>

  <body>
    <table width="98%" align="center" border="0">
      <tr><td>当前菜单：<span style="font-weight:bold;color:#000099">${menu.name}</span></td></tr>    
    </table>
    <table width="98%" align="center" class="table">     
      <tr>       
        <th>菜单类型</th>
        <th>菜单动作</th>       
        <th>说明</th>
        <th>父菜单</th>
        <th>操作</th>
      </tr>
      <tr>
        <td>${menu.typeName}</td>  <!-- 从值栈里面取对象值 -->
        <td>${menu.action.name}</td>       
        <td>${menu.remark}</td>
        <td><a href="menuManage_menuDetail.action?menu.id=${parentMenu.id}&menu.type=${parentMenu.type}">${parentMenu.name}</a></td>
        <td>        
           <c:if test="${not (menu.id eq 'root')}">
              <a href="menuManage_editMenu.action?menu.id=${menu.id}">编辑</a>
              <a href="menuManage_delMenu.action?menu.id=${menu.id}">删除</a>
           </c:if> 
           <c:if test="${menu.type eq 1}">
              <a href="menuManage_toAddSubMenu.action?menu.id=${menu.id}">添加子菜单</a>
           </c:if> 
           <c:if test="${menu.type eq 2}">
              <a href="menuManage_addAction.action?menu.id=${menu.id}">添加动作</a>
           </c:if>    
        </td>
      </tr>
    </table>
    <s:if test="subMenuList != null">
      <table width="98%" align="center" border="0" style="margin-top:15px">
        <tr><td>子菜单列表：</td></tr>
      </table>
      <table width="98%" align="center" class="table">
        <tr>
          <th>菜单名称</th>
          <th>菜单类型</th>
          <th>菜单动作</th>       
          <th>说明</th>        
          <th>操作</th>       
        </tr>     
        <c:forEach items="${subMenuList}" var="subMenu">
          <tr>
            <td><a href="#">${subMenu.name}</a></td>
            <td>${subMenu.typeName}</td>
            <td>${subMenu.action.name}</td>
            <td>${subMenu.remark}</td>
            <td>
			编辑
			删除
            </td>
          </tr>
        </c:forEach>
      </table>
    </s:if>    
    <s:elseif test="normalActionList!=null || authorActionList!=null">
       <table width="98%" align="center" border="0" style="margin-top:15px">
        <tr><td>普通动作列表：</td></tr>
      </table>
      <table width="98%" align="center" class="table">
        <tr>
          <th>动作名称</th>
          <th>动作类型</th>
          <th>说明</th>        
          <th>操作</th>       
        </tr>     
        <c:forEach items="${normalActionList}" var="normalAction">
          <tr>
            <td><a href="#">${normalAction.name}</a></td>
            <td>普通动作</td>
            <td>${normalAction.remark}</td>
            <td>
			编辑
			删除
            </td>
          </tr>
        </c:forEach>
      </table>
       <table width="98%" align="center" border="0" style="margin-top:15px">
        <tr><td>授权动作列表：</td></tr>
      </table>
      <table width="98%" align="center" class="table">
        <tr>
          <th>动作名称</th>
          <th>动作类型</th>
          <th>说明</th>        
          <th>操作</th>       
        </tr>     
        <c:forEach items="${authorActionList}" var="authorAction">
          <tr>
            <td><a href="#">${authorAction.name}</a></td>
            <td>授权动作</td>
            <td>${authorAction.remark}</td>
            <td>
			编辑
			删除
            </td>
          </tr>
        </c:forEach>
      </table>
    </s:elseif>
  </body>
</html>
