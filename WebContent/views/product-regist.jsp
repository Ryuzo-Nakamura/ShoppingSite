<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="tag-header.jsp" %>

<%@ page import="jp.co.aforce.tool.ProductInfo" %>

<%@ include file="admin-header.jsp" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-------------------- 確認画面ポップアップ -------------------%>
<div id="product-add-conf" class="conf-popup">
  <div class="conf">
    <p>商品情報を追加します。よろしいですか？</p>
    <div class="conf-btn">
    	<input type="button" value="キャンセル" class="cancel-btn small-btn reverse-btn" > 
    	<input type="button" value="追加" class="ok-btn small-btn">
    </div>
  </div>
</div>
<%-------------------------------------------------------------%>

<main>

<h1>商品追加</h1>

<a href="/ShoppingSite/views/product-add.jsp">&lt;&lt;商品一覧に戻る</a>

<c:set var="manga" value="${adminManga}"/>

<div class="manga-list product-manga-list">
	<div class="mangaList-img"><c:choose>
		<c:when test="${manga.getImgURL() != null}">
			<img src="${'/ShoppingSite/img/item/'.concat(manga.getImgURL())}" height="150px" width="auto">
		</c:when>
		<c:otherwise>
			<img src="/ShoppingSite/img/no-image.png" height="120px" width="auto">
		</c:otherwise>
		</c:choose></div>
	<p class="manga-title">${manga.getTitle()}</p>
	<p class="manga-info">
		作者：${manga.getAuthorString()}<br>
		出版社：${manga.getPublisher()}<br>
		ジャンル：${manga.getGenreString()}<br>
		総巻数：${manga.getTotalNumber()}巻
	</p>
</div>

<hr>
<p> </p>

<div>
<form action="/ShoppingSite/servlet/product-add" method="post" enctype="multipart/form-data" id="product-add-form">
	<input type="hidden" name="mangaId" value="<c:if test="${manga.getMangaId() != null}">${manga.getMangaId()}</c:if>">
	
	<p>巻数</p>
	<input type="number" name="number" class="form-text number-form" value="<c:if test="${product.getNumber() != null}">${product.getNumber()}</c:if>" min="0" max="999" required>
	
	<p>価格</p>
	<input type="number" name="price" class="form-text price-form" value="<c:if test="${product.getPrice() != null}">${product.getPrice()}</c:if>" min="0" required>
	
	<p>作品情報</p>
	<textarea name="description" rows="5" cols="75" class="form-textarea">${product.getDescription()}</textarea>
	
	<p>商品画像アップロード</p>
	<input type="file" name="img" accept="imaga/*" required>

	
	<p><c:if test="${productAddMessage != null}">${productAddMessage}</c:if></p>

	<div class="div-btn">
		<input type="button" value="追加" id="product-add-btn" class="medium-btn">
	</div>
</form>
</div>
</main>
<%
	session.removeAttribute("manga");
	session.removeAttribute("title");
	session.removeAttribute("titleError");
	session.removeAttribute("productAddMessage");
	ProductInfo.removeProductInfo(session);
%>



<%@ include file="tag-footer.jsp" %>