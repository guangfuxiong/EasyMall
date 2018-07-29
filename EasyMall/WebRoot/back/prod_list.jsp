<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<style type="text/css">
body {
	background: #F5F5F5;
	text-align: center;
}

table {
	text-align: center;
	margin: 0px auto;
}

th {
	background-color: silver;
}
</style>
<script type="text/javascript" src="${app }/js/jquery-1.4.2.js"></script>
<script type="text/javascript">
$().ready(function(){
   //选择对应的输入框，并绑定失去焦点的事件
   $("input[name=pnum]").blur(function(){
   		//获取输入框中的内容
   		var pnum = $(this).val();
   		//获取当前商品“旧的值”对应的输入框对象
   		var $oldInp = $(this).next();
   		//编写正则表达式
   		var reg = /^[1-9][0-9]*$/;
   		if(!reg.test(pnum)){
   		    $(this).val($oldInp.val());
   		    alert("请输入正整数");
   		}else if(pnum!=$oldInp.val()){
	   		//获取商品的id
	   		var pid = $(this).attr("id");
   			$.post("${app}/servlet/AjaxProdEditPnumServlet",
	   			{"pid":pid,"pnum":pnum},function(result){
	   			    if("true"==result){
	   			        $oldInp.val(pnum);
	   			    	alert("商品数量修改成功！");
	   			    }else{
	   			    	alert("商品数量修改失败");
	   			    }
	   			});
   		}
   });
   $(".del").click(function(){
   		if(confirm("您确认删除该商品吗？")){
   			var id = $(this).parent().prev().prev().children(
   				"[type=text]").attr("id");
   			location.href="${app}/servlet/BackProdDelServlet?pid="+id;
   		}
   });
});	
</script>
</head>
<body>
	<h1>商品管理</h1>
	<a href="${app}/back/prod_add.jsp">添加商品</a>
	<hr>
	<table bordercolor="black" border="1" width="95%" cellspacing="0px" cellpadding="5px">
		<tr>
			<th>商品图片</th>
			<th>商品id</th>
			<th>商品名称</th>
			<th>商品种类</th>
			<th>商品单价</th>
			<th>库存数量</th>
			<th>描述信息</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${list }" var="prod">
			<tr>
				<td><img width="120px" height="120px" src="${app }/servlet/ProdImgServlet?imgurl=${prod.imgurl}"/>
				</td>
				<td>${prod.id }</td>
				<td>${prod.name }</td>
				<td>${prod.category }</td>
				<td>${prod.price }</td>
				<td><input type="text" id="${prod.id }" name="pnum" value="${prod.pnum }" style="width: 50px"/>
					<input type="hidden" name="oldPnum" value="${prod.pnum }"/>
				</td>
				<td>${prod.description }</td>
				<td><a href="javascript:void(0)" class="del">删除</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
