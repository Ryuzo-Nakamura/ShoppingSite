<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="tag-header.jsp" %>

<%@ page import="jp.co.aforce.tool.MangaInfo" %>

<%@ include file="admin-header.jsp" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-------------------- 確認画面ポップアップ -------------------%>
<div id="product-update-conf" class="conf-popup">
  <div class="conf">
    <p>商品情報を更新します。よろしいですか？</p>
    <div class="conf-btn">
    	<input type="button" value="キャンセル" class="cancel-btn small-btn reverse-btn" > 
    	<input type="button" value="更新" class="ok-btn small-btn">
    </div>
  </div>
</div>

<div id="product-delete-conf" class="conf-popup">
  <div class="conf">
    <p>商品情報を削除します。本当によろしいですか？</p>
    <div class="conf-btn">
    	<input type="button" value="キャンセル" class="cancel-btn small-btn reverse-btn" > 
    	<input type="button" value="削除" class="ok-btn small-btn">
    </div>
  </div>
</div>
<%-------------------------------------------------------------%>


<c:set var="manga" value="${adminManga}"/>
<c:set var="product" value="${adminProduct}"/>

<main>

<h2>商品情報管理</h2>

<div class="product-list">	
	<div class="productList-img">		
		<img src="${'/ShoppingSite/img/item/'.concat(product.getImgURL())}" height="120px" width="auto">
	</div>
	<p class="manga-title">${manga.getTitle()} (${product.getNumber()})</p>
	<p class="product-price"><span class="price"><span class="yen">￥</span>${product.getPrice()}</span></p>
	<p class="description">${product.getDescription()}</p>
</div>

<form method="post" id="product-info-form">
	<input type="hidden" name="productId" value="<c:if test="${product.getProductId() != null}">${product.getProductId()}</c:if>">
	
	<p>巻数</p>
	<input type="number" name="number" class="form-text number-form" value="<c:if test="${product.getNumber() != null}">${product.getNumber()}</c:if>" min="0" max="999" required>
	
	<p>価格</p>
	<input type="number" name="price" class="form-text price-form" value="<c:if test="${product.getPrice() != null}">${product.getPrice()}</c:if>" min="0" required>
	
	<p>作品情報</p>
	<textarea name="description" rows="5" cols="75" class="form-textarea">${product.getDescription()}</textarea>
	
	<p><c:if test="${productManagementMessage != null}">${productManagementMessage}</c:if></p>
	
	<div class="div-btn">
		<input type="button" value="削除" id="product-delete-btn" class="medium-btn reverse-btn">
		<input type="button" value="更新" id="product-update-btn" class="medium-btn">
	</div>
</form>
</main>
<% 
	session.removeAttribute("manga");
	session.removeAttribute("product");
	session.removeAttribute("productManagementMessage"); 
%>



<%@ include file="tag-footer.jsp" %>