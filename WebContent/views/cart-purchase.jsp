<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="tag-header.jsp" %>

<%@page import="java.util.Map"%>
<%@page import="jp.co.aforce.bean.Item"%>
<%@page import="jp.co.aforce.bean.Product"%>
<%@page import="jp.co.aforce.bean.Manga"%>

<%@page import="java.lang.reflect.Field"%>
<%@page import="jp.co.aforce.constant.Constant" %>

<%@page import="java.util.Calendar" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="header.jsp" %>

<%-------------------- 確認画面ポップアップ -------------------%>
<div id="user-purchase-conf" class="conf-popup">
  <div class="conf">
    <p>商品を購入します。よろしいですか？</p>
    <div class="conf-btn">
    	<input type="button" value="キャンセル" class="cancel-btn small-btn reverse-btn" > 
    	<input type="button" value="購入" class="ok-btn small-btn">
    </div>
  </div>
</div>
<%-------------------------------------------------------------%>
<main>

<%-- カート作成後、購入に必要な情報の入力フォームとカート内の商品情報を表示 --%>
<h1>購入</h1>
<form action="/ShoppingSite/servlet/cart-purchase" method="post" id="user-purchase-form">
	<div class="div-purchase">
	<div class="div-purchase-form">
		<p>配送先</p>
		<input type="text" name="deliveryAddress" class="form-text" id="deliveryAddress" required>
		<input type="hidden" id="userAddress" value="${user.getAddress()}">
		<input type="button" id="inputAddress" class="small-btn" value="自宅の住所を自動入力">
		<p>支払方法</p>
		<div class="form-select">
			<select name="payment" required>
				<option value=""></option>
				<% for(Field field : Constant.Payment.class.getDeclaredFields()){ %>
					<option value="<%=(String)field.get(Constant.Payment.class) %>"><%=(String)field.get(Constant.Payment.class) %></option>
				<%	} %>
			</select>
		</div>
		<p>配送日</p>
		<div class="form-select">
			<select name="deliveryDate" required>
				<option value=""></option>
				<option value="指定なし">指定なし</option>
				<% 		
					for(int i = 2; i <= 10; i++){
						Calendar cal = Calendar.getInstance();
						cal.getTime(); 
						cal.add(Calendar.DATE, i);
				%>
					<option value="<%=cal.get(Calendar.MONTH)+1%>/<%=cal.get(Calendar.DATE) %>"><%=cal.get(Calendar.MONTH)+1%>/<%=cal.get(Calendar.DATE) %></option>
				<%	} %>
			</select>
		</div>
		<p>配送時間</p>
		<div class="form-select">
			<select name="deliveryTime" required>
				<option value=""></option>
				<% for(Field field : Constant.DeliveryTime.class.getDeclaredFields()){ %>
					<option value="<%=(String)field.get(Constant.DeliveryTime.class) %>"><%=(String)field.get(Constant.DeliveryTime.class) %></option>
				<%	} %>
			</select>
		</div>
		<c:set var="subtotal" value="${0}"/>
			<c:forEach var="c" items="${cart.entrySet()}">
			<c:set var="subtotal" value="${subtotal + (c.getValue().getKey().getPrice() * c.getKey().getAmount())}"></c:set>
		</c:forEach>
	</div>
	<div class="div-purchase-price">
		<p class="subtotal"><span class="price-title">小計：</span><span class="yen">￥</span>${subtotal}</span></p>
		<p class="subtotal"><span class="price-title">配送料：</span><span class="yen">￥</span>500</p>
		<p class="total"><span class="price-title">合計：</span><span class="price"><span class="yen">￥</span>${subtotal + 500}</span></p>
		<div class="div-btn"><input type="button" class="large-btn" value="購入に進む" id="user-purchase-btn"></div>
	</div>
	</div>
</form>
</div>
<hr>
<p> </p>
<h3>購入商品</h3>
<c:forEach var="c" items="${cart.entrySet()}">
	<div class="product-list">
		<input type="hidden" name="itemId" value="${c.getKey().getItemId()}">
		<div class="productList-img">
		<img src="${'/ShoppingSite/img/item/'.concat(c.getValue().getKey().getImgURL())}" height="120px" width="auto">
		</div>
		<p class="manga-title">${c.getValue().getValue().getTitle()} (${c.getValue().getKey().getNumber()})</p>
		<p class="product-price"><span class="price"><span class="yen">￥</span>${c.getValue().getKey().getPrice()}</span></p>
		<p class="amount">数量：${c.getKey().getAmount()}</p>
	</div>
</c:forEach>


</main>

<%
	session.removeAttribute("subtotal");
	session.removeAttribute("manga");
%>


<%@ include file="tag-footer.jsp" %>