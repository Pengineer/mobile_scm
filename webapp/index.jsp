<%@ page language="java" pageEncoding="UTF-8"%>

<!-- 本页面是输入http://IP/projectName后根据web.xml配置跳转的页面，由于首页的显示有时需要请求数据（比如验证码等），因此一般这里仅仅做一个简单的跳转 -->
<%response.sendRedirect("toIndex.action");%>