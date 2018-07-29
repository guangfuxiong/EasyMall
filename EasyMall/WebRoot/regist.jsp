<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>欢迎注册EasyMall</title>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="${app}/css/regist.css" />
<script type="text/javascript" src="${app}/js/jquery-1.4.2.js"></script>
<script>
			var formObj = {
				checkForm: function(){
					var flag = true;
					//注册表单校验
					//>>非空校验
					flag = this.checkNull("username", "用户名不能为空") && flag;
					flag = this.checkNull("password", "密码不能为空") && flag;
					flag = this.checkNull("password2", "确认密码不能为空") && flag;
					flag = this.checkNull("nickname", "昵称不能为空") && flag;
					flag = this.checkNull("email", "邮箱不能为空") && flag;
					flag = this.checkNull("valistr", "验证码不能为空") && flag;
					
					//>>两次密码是否一致
					flag = this.checkPassword("password", "两次密码不一致") && flag;
					
					//>>邮箱格式
					flag = this.checkEmail("email", "邮箱格式不正确") && flag;
					
					
 					return flag;
				},
				//检查邮箱格式是否正确
				checkEmail: function(name, msg){
					var email = $("input[name="+name+"]").val();
					//清空上次提示消息
					this.setMsg(name, "");
					
					this.checkNull(name, "邮箱不能为空");
					
					if($.trim(email) != ""){
						var reg = /^\w+@\w+(\.\w+)+$/;
						if(!reg.test(email)){
							this.setMsg(name, msg);
							return false;
						}
					}
					return true;
				}, 
				//检查两次密码是否一致
				checkPassword: function(name, msg){
					var psw1 = $("input[name="+name+"]").val();
					var psw2 = $("input[name="+name+"2]").val();
					
					//清空上次提示消息
					this.setMsg(name+"2", "");
					
					this.checkNull(name+"2", "确认密码不能为空");
					
					if($.trim(psw1) != "" && $.trim(psw2) != ""){
						if(psw1 != psw2){
							this.setMsg(name+"2", msg);
							return false;
						}
					}
					return true;
				},
				//检查输入项是否为空
				checkNull: function(name, msg){
					var value = $("input[name="+name+"]").val();
					
					//清空上次提示消息
					this.setMsg(name, "");
										
					if($.trim(value) == ""){
						this.setMsg(name, msg);
						return false;
					}
					return true;
				},
				setMsg: function(name, msg){
					$("#"+name+"_msg").html("<font style='color:red;'>"+msg+"</font>");
				}
			}
			
			//当整个页面加载完成之后立即触发
			$().ready(function(){
				//利用ajax实现用户名是否存在的校验

				$("input[name=username]").blur(function(){
					var username = $("input[name=username]").val();
					
					//检查用户名是否为空
					if(!formObj.checkNull("username", "用户名不能为空")){
						return;
					}
					
					//校验用户名是否存在
					//方式一: jquery--load()
					//$("#username_msg").load("${app}/AjaxCheckUsernameServlet", {username: username});
					
					//方式二: jquery--post()
					$.post("${app}/AjaxCheckUsernameServlet", {username: username}, function(result){
						$("#username_msg").html(result);
					});
				
				});
			});
			
		
		</script>
</head>
<body>
	<form action="${ pageContext.request.contextPath}/RegistServlet"
		method="POST" onsubmit="return formObj.checkForm()">
		<h1>欢迎注册EasyMall</h1>
		<table>
			<tr>
				<td class="tds" colspan="2" style="color:red; text-align: center;">
					${ msg }
				</td>
			</tr>
			<tr>
				<td class="tds">用户名：</td>
				<td><input type="text" name="username"
					value="${ param.username }" 
					/>
					<span id="username_msg"></span></td>
			</tr>
			<tr>
				<td class="tds">密码：</td>
				<td><input type="password" name="password"
					value="${ param.password }" 
					onblur="formObj.checkNull('password', '密码不能为空')"
					/>
					<span id="password_msg"></span></td>
			</tr>
			<tr>
				<td class="tds">确认密码：</td>
				<td><input type="password" name="password2"
					value="${ param.password2 }" 
					onblur="formObj.checkPassword('password', '两次密码不一致')"
					/>
					<span id="password2_msg"></span></td>
			</tr>
			<tr>
				<td class="tds">昵称：</td>
				<td><input type="text" name="nickname"
					value="${ param.nickname }" 
					onblur="formObj.checkNull('nickname', '昵称不能为空')"
					/>
					<span id="nickname_msg"></span></td>
			</tr>
			<tr>
				<td class="tds">邮箱：</td>
				<td><input type="text" name="email"
					value="${ param.email }" 
					onblur="formObj.checkEmail('email', '邮箱格式不正确')"
					/>
					<span id="email_msg"></span></td>
			</tr>
			<tr>
				<td class="tds">验证码：</td>
				<td><input type="text" name="valistr"  
					onblur="formObj.checkNull('valistr', '验证码不能为空')"
					/> 
					<img onclick="changeImage(this)" src="${app}/ValiImageServlet" alt="" /> 
					<span id="valistr_msg"></span></td>
			</tr>
			<script>
				function changeImage(thisObj){
					//alert(thisObj.src);
					thisObj.src = "${app}/ValiImageServlet?time="+new Date().getTime();
				}
			</script>
			<tr>
				<td class="sub_td" colspan="2" class="tds"><input type="submit"
					value="注册用户" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
