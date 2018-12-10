<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${app}/css/addOrder.css" rel="stylesheet" type="text/css">
<title>创建订单</title>
</head>
<body>
	<div class="warp">
		<form name="form1" method="post" action="/orderAction_addOrder.action">
			<h3>增加订单</h3>
			<div id="forminfo">
				<span class="lf">收货地址：</span> <label for="textarea"></label>
				<textarea name="textarea" id="textarea" cols="45" rows="5"></textarea>
				<br> 支付方式：<input name="" type="radio" value="" checked="checked">&nbsp;在线支付
			</div>
			<table width="1200" height="80" border="1" cellpadding="0" cellspacing="0" bordercolor="#d8d8d8">
				<tr>
					<th width="276">商品图片</th>
					<th width="247">商品名称</th>
					<th width="231">商品单价</th>
					<th width="214">购买数量</th>
					<th width="232">总价</th>
				</tr>
				<c:set var="money" value="0"></c:set>
				<c:forEach items="${cart}" var="entry" >
				    <tr>
						<td><img src="${app}/servlet/ProdImgServlet?imgurl=${entry.key.imgurl}"/></td>
						<td>${entry.key.name}</td>
						<td>${entry.key.price }元</td>
						<td>${entry.value }件</td>
					    <td>${entry.key.price*entry.value }元</td>
					    <c:set var="money" value="${money+entry.key.price*entry.value }"></c:set>
				    </tr>
				</c:forEach>
				
			</table>

			<div class="Order_price">总价：${money}元</div>

			<div class="add_orderbox">
				<input name="" type="submit" value="增加订单" class="add_order_but">
			</div>
		</form>
	</div>
</body>
</html>