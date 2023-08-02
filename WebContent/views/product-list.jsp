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
				<input type="hidden" name="add-type" id="add-all" value="0">
				<input type="submit" value="まとめ買い" class="medium-btn" id="add-all-btn"
						<c:if test="${manga == null || productList.size() == 0}">
								style="background-color:#d38693; border:#d38693" disabled</c:if>>
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

<form action="/ShoppingSite/servlet/cart-add" method="post">
	<div class="div-btn">
		<p id="check-text">商品が選択されていません</p>
		<input type="submit" class="medium-btn" id="add-multi-btn" value="まとめて追加" disabled>
		<input type="hidden" name="add-type" id="add-multi" value="0">
	</div>
	<div class="div-list">
		<input type="hidden" name="add-productId" id="add-single-id" value="">
		<c:forEach var="product" items="${productList}">
			<input type="hidden" name="productId" value="${product.getProductId()}">
			<div class="product-list search-product-list user-product-list" id="${'product-list-'.concat(item.getProductId())}">
			<input type="checkbox" name="check" class="checkbox" id="${'check-'.concat(product.getProductId())}" value="${product.getProductId()}">
			<label for="${'check-'.concat(product.getProductId())}" class="checkbox"></label>
			<div class="productList-img">		
			<img src="${'/ShoppingSite/img/item/'.concat(product.getImgURL())}" height="120px" width="auto">
			</div>
			<p class="manga-title">${manga.getTitle()} (${product.getNumber()})</p>
			<p class="product-price"><span class="price"><span class="yen">￥</span>${product.getPrice()}</span></p>
			<p class="description">${product.getDescription()}</p>
			<div class="div-btn">
				<input type="number" name="amount" class="amount-form" value="1" min="1" max="999" required>
				<input type="submit" class="small-btn add-single-btn" id="${product.getProductId()}" value="カートに入れる">
			</div>
		</div>
		</c:forEach>
	</div>
</form>

</main>

<%@ include file="tag-footer.jsp" %>