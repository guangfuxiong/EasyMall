<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>我的购物车</title>
    <meta http-equiv="Content-type" content="text/html;charset=utf-8"/>
    <link rel="stylesheet" href="${app }/css/cart.css">
    <script type="text/javascript" src="${app }/js/jquery-1.4.2.js"></script>
    <script type="text/javascript">
        function checkAll(obj){
        	var checked=$(obj).attr("checked");
        	$("#prods input").attr("checked",checked);
        }
        function check(obj){
        	var checked=$(obj).attr("checked");
        	var checkedA=true;
        	$(".warp input[name='prodC']").each(function(){
        		var flag=$(this).attr("checked");
        		if(!flag){
        		    checkedA=flag;
        		}
        	});
        	if((!checked)||checkedA){
        		$("#title input[name='allC']").attr("checked",checked);
        	}
        }
        function delNum(id){
        	var newNum=$("#"+id).val()-1;
        	editNum(id,newNum);
        }
        function addNum(id){
        	var newNum=$("#"+id).val()*1+1;
        	editNum(id,newNum);
        }
        function changePnum(id,val){
        	var newNum=$("#"+id).val();
        	editNum(id,newNum);
        }
        function editNum(id,newNum){
        	$.post("${app}/servlet/CartEditServlet",{"id":id,"newNum":newNum},function(result){
        		if("true"==result){
        			$("#"+id).val(newNum);
        			var onePrice=$("#"+id).parent().prev().text()*1.0;
        			var preTotalPrice=$("#"+id).parent().next().text()*1.0;
        			var nowTotalPrice=newNum*parseFloat(onePrice)*1.0;
        			$("#"+id).parent().next().text(nowTotalPrice);
        			//总金额
        		    var oldTol=$("#span_2").text().replace("￥","")*1.0;
        			var nowTol=oldTol-preTotalPrice+nowTotalPrice;
        			$("#span_2").text("￥"+nowTol);
        		}
        	});
        }
        function deleteConfirm(id){
        	if(confirm("确定要删除？！")){
        		$.post("${app}/servlet/CartDelServlet",{"id":id},function(result){
    				if("true"==result){
    					$("#"+id).parent().parent().remove();
    				}
    			});
        		//location.href="${app}/servlet/CartDelServlet?id="+id;
        	}
        }
        function deleteChecked(){
        	var delNum=0;
        	$("input[name='prodC']").each(function(){
        		if($(this).attr("checked")){
        			delNum++;
        		}
        	});
        	if(confirm("确定要删除这"+delNum+"件商品？！")){
        		$("input[name='prodC']").each(function(){
            		if($(this).attr("checked")){
            			var id=$(this).parent().find(".buyNumInp1").attr("id");
            			var obj=$(this);
            			$.post("${app}/servlet/CartDelServlet",{"id":id},function(result){
            				if("true"==result){
            					obj.parent().remove();
            				}
            			});
            			//location.href="${app}/servlet/CartDelServlet?id="+id;
            		}
            	});
        	}
        }
    </script>
</head>
<body>
<%@include file="_head.jsp" %>
    <div class="warp">
	${msg }
		<!-- 标题信息 -->
	<div id="title">
		<input name="allC" type="checkbox" value="" onclick="checkAll(this)"/>
		<span id="title_checkall_text">全选</span>
		<span id="title_name">商品</span>
		<span id="title_price">单价（元）</span>
		<span id="title_buynum">数量</span>
		<span id="title_money">小计（元）</span>
		<span id="title_del">操作</span>
	</div>
	<c:set var="money" value="0"/>
<c:forEach items="${cart}" var="entry">
	<div id="prods">
		<input name="prodC" type="checkbox" value="" onclick="check(this)"/>
		<img src="${app }/servlet/ProdImgServlet?imgurl=${entry.key.imgurl}" width="90" height="90" />
		<span id="prods_name">${entry.key.name}</span>
		<span id="prods_price">${entry.key.price }</span>
		<span id="prods_buynum"> 
			<a href="javascript:void(0)" id="delNum" onclick="delNum('${entry.key.id}')">-</a>
			<input id="${entry.key.id }" class="buyNumInp1" type="text" value="${entry.value }" onblur="changePnum('${entry.key.id}',${entry.value})">
			<a href="javascript:void(0)" id="addNum" onclick="addNum('${entry.key.id}')" >+</a>
		</span>
		<span id="prods_money">${entry.key.price*entry.value}</span>
		<c:set var="money" value="${money+entry.key.price*entry.value}"/>
		<span id="prods_del"><a href="javascript:void(0)" onclick="deleteConfirm('${entry.key.id}')">删除</a></span>
	</div>
</c:forEach>
	<!-- 总计条 -->
		<div id="total">
			<div id="total_1">
				<!-- <input name="allC" type="checkbox" value=""/> 
				<span>全选</span> -->
				<a id="del_a" href="javascript:void(0)" onclick="deleteChecked()">删除选中的商品</a>
				<span id="span_1">总价：</span>
				<span id="span_2">￥${money}</span>
			</div>
			<div id="total_2">	
				<a id="goto_order" href="${app }/servlet/OrderAddServlet">去结算</a>
			</div>
		</div>
	</div>
<%@include file="_foot.jsp" %>
</body>
</html>