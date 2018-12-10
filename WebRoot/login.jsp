<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" href="${app}/css/login.css"/>
		<title>EasyMall欢迎您登陆</title>
		<script>
			window.onload = function(){
				//用js对用户名进行url解码
				var username = "${ cookie.remname.value }";
				//encodeURI();
				username = decodeURI(username);
				document.getElementsByName("username")[0].value = username;
			}
		</script>
	</head>
	<body>
		<h1>欢迎登陆EasyMall</h1>
		<form action="${app}/LoginServlet" method="POST">
			<table>
				<!-- 获取提示消息 -->
				<tr>
					<td class="tdx" colspan="2" style="color:red;text-align: center;">
						${ msg }
					</td>
				</tr>
				
				<tr>
					<td class="tdx">用户名:</td>
					<td><input type="text" name="username" value="${ cookie.remname.value }"/></td>
				</tr>
				<tr>
					<td class="tdx">密码:</td>
					<td><input type="password" name="password"/></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="checkbox" name="remname" value="true"
							${ (empty cookie.remname) ? "" : "checked='checked'" }
						/>记住用户名
						<input type="checkbox" name="autologin" value="true"/>30天内自动登陆
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="登陆"/>
					</td>
				</tr>
			</table>
		</form>		
	</body>
</html>
