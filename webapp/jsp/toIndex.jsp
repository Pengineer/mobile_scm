<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html>
  <head>
    <!-- EL表达式查找顺序：page->request->session->context -->
    <title>${sysname}</title>
    
    <!-- 样式的引入使用link，这里就直接写了
    	(1)对body标签设置样式：背景、字体、页面里的内容和页面的边距
    	(2)因为table标签不会继承body标签，因此需要单独设置字体
    	(3).container定义class名为container的所有标签样式（前面加一个div，表示指定是div标签）：宽度，高度，背景图片，如果层里的内容超过层的大小则隐藏，左边距，上边距
    	(4).leftLogo：相对顶级div的样式
    	(5).loginFormDiv：相对顶级div的样式，z-index表示同级div的z抽索引（值大的在上面），padding表示层与内容之间间隙
    	(6).loginTable：表格文字加粗
    	(7).validateCodeDiv：整个页面与container同级的div
     -->
    <style type="text/css">
    	body {
    		background: #2469ac;
    		font-size: 14px;
    		margin:0;
    	}
    	
    	table {
    		font-size:14px;
    	}
    	
    	div.container {
    		width:100%;
    		height:250px;
    		background-image:url("images/login_back.jpg");
    		overflow:hidden;
    		position:absolute;
    		left:0px;
    		top:210px;
    	}
    	
    	.leftLogo {
    		position:absolute;
    		top:0;
    		left:0;
    		width:571px;
    		height:250px;
    		background-image: url("images/login_logo.png");
    	}
    	
    	.loginFormDiv {
    		position:absolute;
    		top:0;
    		left:730px;
    		width:298px;
    		height:250px;
    		z-index:10;
    		padding:0;
    	}
    	.loginTable {
    		font-weight: bold;
    	}
    	.txt {
    		border:1px solid #999999;
    		width:130px;
    		height:22px;
    		margin-left:8px;
    		font-size:12px;
    	}
    	.rightPic {
    		position:absolute;
    		top:0;
    		right:100px;
    		width:158px;
    		height:250px;
    		z-index:5px;
    		background-image: url("images/login_pic.png");
    	}
    	.validateCodeDiv {
    		position:absolute;
    		width:160px;
    		height:80px;
    		background-color: #f5f5f5;
    		border: 1px solid black;
    		z-index: 15;
    	}
    </style>
    
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript" src="js/user_login.js"></script>
  </head>
  
  <body>
    <div class="container">
    	<div class="leftLogo"></div>
    	<div class="loginFormDiv">
    		<!-- 由于这里的提交采用了ajax方式，因此拦截了form表单本身的提交 -->
    		<form name="frm1" onsubmit="return false;">
    			<!-- 相对本div：表格宽度、表格边框宽度、表格中单元格之间间距、单元格内容与单元格间距 -->
    			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="loginTable">
    				<!-- 表格第一行，用于显示提示信息：定义高度、列数、水平居中、垂直居中 -->
    				<tr>
    					<td height="72" colspan="3" align="center" valign="middle">
    						<!-- span是一个行内元素，可以将一行的内容分成多个片段，然后对每个片段应用各自的样式-->
    						<span style="color:red;font-weight:bold;" id="messBox"></span>
    					</td>
    				</tr>
    				<!-- 表格第二行:style="cursor:pointer"表示鼠标在图片上形状是手型 ;rowspan="3"表示第二行的第三列占三行，也就是把第一行和第三行兼并-->
    				<tr>
    					<td width="100" height="35" align="right" valign="middle">用户名ID：</td>
    					<td width="140" align="left" valign="middle"><input type="text" name="account" class="txt"/></td>
    					<td rowspan="3" align="left" valign="middle"><img src="images/login_submitBtn1.gif" id="submitBtn" style="cursor:pointer; margin-left: 8px"></td>
    				</tr>
    				<tr>
    					<td width="100" height="35" align="right" valign="middle">密码：</td>
    					<td width="140" align="left" valign="middle"><input type="password" name="passwd" class="txt"></td>
    				</tr>
    				<tr>
    					<td width="100" height="35" align="right" valign="middle">验证码：</td>
    					<td width="140" align="left" valign="middle"><input type="text" name="vcode" class="txt" maxLength="4" /></td>
    				</tr>
    			</table>
    		</form>
    	</div>
    	<div class="rightPic"></div>
    </div>
    <div class="validateCodeDiv" style="cursor:pointer;display:none" onclick="changeCode();">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
    		<tr>
    			<td height="60"><img src="servlet/getVcode" id="imgVcode" /></td>
    		</tr>
    		<tr>
    			<td align="center" valign="middle" height="20" style="color:blue">若看不清，点图片换一张</td>
    		</tr>
    	</table>
    </div>
  </body>
</html>
