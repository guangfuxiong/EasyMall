<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title>全部商品</title>
    <link href="${app }/css/prodList.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@include file="_head.jsp" %>
	<div id="content">
		<div id="search_div">
			<form method="post" action="${app}/servlet/ProdListServlet">
				<span class="input_span">商品名：<input type="text" name="name" value="${name }"/></span>
				<span class="input_span">商品种类：<input type="text" name="category" value="${category}"/></span>
				<span class="input_span">商品价格区间：<input type="text" name="minprice" value="${minprice }"/>
				 - <input type="text" name="maxprice" value="${maxprice }"/></span>
				<input type="submit" value="查询">
			</form>
		</div>
		<div id="prod_content">
		<c:forEach items="${list }" var="prod">
			<div id="prod_div" style="margin-right: 0px;">
			  <a href="${app }/servlet/ProdInfoServlet?pid=${prod.id}">
				<img src="${app }/servlet/ProdImgServlet?imgurl=${prod.imgurl}"></img>
			  </a>
				<div id="prod_name_div">
				<a href="${app }/servlet/ProdInfoServlet?pid=${prod.id}">
					${prod.name }
				</a>
				</div>
				<div id="prod_price_div">
					￥${prod.price }元
				</div>
				<div>
					<div id="gotocart_div">
						<a href="${app }/servlet/CartAddServlet?pid=${prod.id}">加入购物车</a>
					</div>					
					<div id="say_div">
						库存:${prod.pnum }
					</div>					
				</div>
			</div>
		</c:forEach>
		</div>
		<div style="clear: both"></div>
	</div>
<%@include file="_foot.jsp" %>
</body>
</html>
