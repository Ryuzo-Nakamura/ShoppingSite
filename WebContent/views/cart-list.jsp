<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="tag-header.jsp" %>

<%@page import="java.util.Map"%>
<%@page import="jp.co.aforce.bean.Item"%>
<%@page import="jp.co.aforce.bean.Product"%>
<%@page import="jp.co.aforce.bean.Manga"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="header.jsp" %>

<%-- カート作成後、カート内の商品情報を表示 --%>
<main>
<h2>ショッピングカート</h2>
<p class="price-column">価格</p>
<hr>
<p> </p>
<c:choose>
	<c:when test="${cart.size() == 0}"><p>カートは空です。</p></c:when>
	<c:otherwise>
		<c:forEach var="c" items="${cart.entrySet()}">
			<form action="/ShoppingSite/servlet/cart-delete" method="post"><div class="cart-list">
				<input type="hidden" name="itemId" value="${c.getKey().getItemId()}">
				<input type="hidden" name="uri" value="<%=request.getRequestURI() %>">
				<div id="cartList-img">
					<img src="${'/ShoppingSite/img/item/'.concat(c.getValue().getKey().getImgURL())}" height="120px" width="auto">
				</div>
				<p class="manga-title">${c.getValue().getValue().getTitle()} (${c.getValue().getKey().getNumber()})</p>
				<p class="unit-price"><span class="yen">￥</span>${c.getValue().getKey().getPrice()}</p>
				<p class="amount">数量：${c.getKey().getAmount()}</p>
				<div class="div-btn"><input type="submit" class="medium-btn reverse-btn" value="削除"></div>
				<p class="subtotal"><span class="price-title">小計：</span><span class="price"><span class="yen">￥</span>${c.getValue().getKey().getPrice() * c.getKey().getAmount()}</span></p>
			</div></form>
		</c:forEach>
		<hr>
		<c:set var="subtotal" value="${0}"/>
		<c:forEach var="c" items="${cart.entrySet()}">
			<c:set var="subtotal" value="${subtotal + (c.getValue().getKey().getPrice() * c.getKey().getAmount())}"></c:set>
		</c:forEach>
		<form action="/ShoppingSite/servlet/cart-delete" method="post" id="all-delete">
			<input type="submit" value="まとめて削除" class="medium-btn reverse-btn">
		</form>
		<div id="cart-list-total">
		<p class="total"><span class="price-title">合計：</span><span class="price"><span class="yen">￥</span>${subtotal}</span></p>
		</div>
		<div class="div-btn"><input type="button" class="large-btn" value="購入に進む" onclick="location.href='/ShoppingSite/views/cart-purchase.jsp'"></div>
	</c:otherwise>
</c:choose>
</main>
<%
	session.removeAttribute("subtotal");
%>


<%@ include file="tag-footer.jsp" %>