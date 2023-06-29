<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="tag-header.jsp" %>

<%@page import="java.util.Map"%>
<%@page import="jp.co.aforce.bean.Item"%>
<%@page import="jp.co.aforce.bean.Product"%>
<%@page import="jp.co.aforce.bean.Manga"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="header.jsp" %>

<main>

<%-- カート作成後、購入に必要な情報の入力フォームとカート内の商品情報を表示 --%>
<p class="purchase">☑購入完了しました。</p>
<div class="div-purchase div-purchased">
	<div class="div-purchase-form">
		<p>配送先：${user.getLastName()} ${user.getFirstName()}, ${deliveryAddress}</p>
		<p>${deliveryDate}, ${deliveryTime}</p>
		<p>支払方法：${payment}</p>
	</div>
	<div class="div-purchase-price">
		<c:set var="subtotal" value="${0}"/>
		<c:forEach var="c" items="${purchaseList.entrySet()}">
			<c:set var="subtotal" value="${subtotal + (c.getValue().getKey().getPrice() * c.getKey().getAmount())}"></c:set>
		</c:forEach>
		<p class="subtotal">小計<span class="yen">￥</span>${subtotal}</span></p>
		<p class="subtotal">配送料<span class="yen">￥</span>500</p>
		<p class="total">合計<span class="price"><span class="yen">￥</span>${subtotal + 500}</span></p>
		<input type="button" value="ホームに戻る" class="large-btn" onclick="location.href='/ShoppingSite/views/user-home.jsp'">
	</div>
</div>

<hr>
<p> </p>

<c:forEach var="c" items="${purchaseList.entrySet()}">
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
	session.removeAttribute("deliveryAddress");
	session.removeAttribute("payment");
	session.removeAttribute("deliveryDate");
	session.removeAttribute("deliveryTime");
	session.removeAttribute("subtotal");
	session.removeAttribute("manga");
%>


<%@ include file="tag-footer.jsp" %>