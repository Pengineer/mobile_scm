<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="hust.model.Menu"%>
<%@ include file="/jsp/include/taglib.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>菜单管理</title>   
    <%@ include file="/jsp/include/css.jsp" %>
    <%@ include file="/jsp/include/js.jsp" %>  
    <script type="text/javascript">
      $().ready(function() {
        if ($("input[name=refreshTree]").val() == "true") {
          top.loadTree();
        }
      });
    </script> 
  </head>

  <body>
    <input type="hidden" name="refreshTree" value="${refreshTree}" />
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
        <td>${menu.typeName}</td>
        <td>${menu.action.name}</td>       
        <td>${menu.remark}</td>
        <td><a href="menuManage_menuDetail.action?menu.id=${parentMenu.id}&menu.type=${parentMenu.type}">${parentMenu.name}</a></td>
        <td>        
           <c:if test="${not (menu.id eq 'root')}">
              <a href="menuManage_toEditMenu.action?menu.id=${menu.id}">编辑</a>
           </c:if> 
           <c:if test="${menu.type eq 1}">
              <a href="menuManage_toAddSubMenu.action?menu.id=${menu.id}">添加子菜单</a>
           </c:if> 
           <c:if test="${menu.type eq 2}">
              <a href="menuManage_toAddAction.action?menu.id=${menu.id}">添加动作</a>
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
            <td>
              <c:if test="${subMenu.type eq 3}">
                ${subMenu.name}
              </c:if>
              <c:if test="${subMenu.type != 3}">
                <a href="menuManage_menuDetail.action?menu.id=${subMenu.id}">${subMenu.name}</a></td>
              </c:if>
            <td>${subMenu.typeName}</td>
            <td>${subMenu.action.name}</td>
            <td>${subMenu.remark}</td>
            <td>
              <a href="menuManage_delMenu.action?menu.id=${subMenu.id}&menu.pid=${subMenu.pid}&menu.type=${subMenu.type}" onclick="return confirm('此操作将删除所有相关子菜单或动作，确定要删除吗?');">删除</a>
              <a href="menuManage_upMenu.action?menu.id=${subMenu.id}">上移</a>
              <a href="menuManage_downMenu.action?menu.id=${subMenu.id}">下移</a>
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
            <td>${normalAction.name}</td>
            <td>普通动作</td>
            <td <c:if test="${normalAction.id eq menu.action.id}">style="color:red"</c:if>>${normalAction.remark}</td>
            <td>
              <c:if test="${normalAction.actionId ne menu.action.actionId}">
              <a href="menuManage_toEditAction.action?action.id=${normalAction.id}">编辑</a>
              <a href="menuManage_delAction.action?action.id=${normalAction.id}" onclick="return confirm('确定要删除吗?');">删除</a>
              </c:if>
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
            <td>${authorAction.name}</td>
            <td>授权动作</td>
            <td>${authorAction.remark}</td>
            <td>
              <a href="menuManage_toEditAction.action?action.id=${authorAction.id}">编辑</a>
              <a href="menuManage_delAction.action?action.id=${authorAction.id}" onclick="return confirm('确定要删除吗?');">删除</a>
            </td>
          </tr>
        </c:forEach>
      </table>
    </s:elseif>
  </body>
</html>
