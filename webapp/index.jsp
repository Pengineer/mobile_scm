<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'index.jsp' starting page</title>
  </head>
  
  <body>
    <form action="test.action" method="post">  <!-- index.jsp在webapp目录下，也就是根下面，而test.action的namespace就是根 -->
    	<input type="text" name="username"/>
    	<input type="submit" value="submit"/>
    </form>
  </body>
</html>
