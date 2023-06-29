<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="tag-header.jsp" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="header.jsp" %>

<main>

<div class="manga-list">
	<div class="mangaList-img">
	<c:choose>
		<c:when test="${manga.getImgURL() != null}">
			<img src="${'/ShoppingSite/img/item/'.concat(manga.getImgURL())}" height="150px" width="auto">
		</c:when>
		<c:otherwise>
			<img src="/ShoppingSite/img/no-image.png" height="120px" width="auto">
		</c:otherwise>
	</c:choose>
	</div>
	<p class="manga-title">${manga.getTitle()}</p>
	<div>
		<p class="manga-info">
			作者：${manga.getAuthorString()}<br>
			出版社：${manga.getPublisher()}<br>
			ジャンル：${manga.getGenreString()}<br>
			総巻数：${manga.getTotalNumber()}巻
		</p>
		<div class="div-btn">
			<form action="/ShoppingSite/servlet/cart-add" method="post">
				<input type="hidden" name="mangaId" value="${manga.getMangaId()}">
				<input type="submit" value="まとめ買い"	class="medium-btn" >
			</form>
		</div>
	</div>
</div>
<hr>

<form action="/ShoppingSite/servlet/product-sort" method="post" id="product-sort-form" class="sort-form">
	<input type="hidden" name="mangaId" value="${manga.getMangaId()}">
	<label  class="sort-select">
	<select name="sort" id="user-product-sort">
		<option value="1" <c:if test="${sort==1}">selected</c:if>>古い順</option>
		<option value="2" <c:if test="${sort==2}">selected</c:if>>新しい順</option>
	</select>
	</label>
</form>

<div class="div-list">
<%-- 選択されたマンガタイトルの製品を一覧で表示 --%>
<c:forEach var="product" items="${productList}">
	<form action="/ShoppingSite/servlet/cart-add" method="post">
		<input type="hidden" name="productId" value="${product.getProductId()}">
		<div class="product-list search-product-list" id="${'product-list-'.concat(item.getProductId())}">
			<div class="productList-img">		
			<img src="${'/ShoppingSite/img/item/'.concat(product.getImgURL())}" height="120px" width="auto">
			</div>
			<p class="manga-title">${manga.getTitle()} (${product.getNumber()})</p>
			<p class="product-price"><span class="price"><span class="yen">￥</span>${product.getPrice()}</span></p>
			<p class="description">${product.getDescription()}</p>
			<div class="div-btn">
				<input type="number" name="amount" class="amount-form" value="1" min="1" max="999" required>
				<input type="submit" class="small-btn" value="カートに入れる">
			</div>
		</div>
	</form>
</c:forEach>
</div>

</main>

<%@ include file="tag-footer.jsp" %>