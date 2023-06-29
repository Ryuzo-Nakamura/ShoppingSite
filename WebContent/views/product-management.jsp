<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="tag-header.jsp" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="admin-header.jsp" %>

<main>
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
	<form action="/ShoppingSite/servlet/manga-management" method="post">
		<input type="hidden" name="mangaId" value="${manga.getMangaId()}">
		<div class="div-btn"><input type="submit" class="medium-btn" value="編集"></div>
	</form>
</div>

<hr>
<p> </p>
<%-- 選択されたマンガタイトルの製品を一覧で表示 --%>
<c:forEach var="product" items="${adminProductList}">
	<form action="/ShoppingSite/servlet/product-management" method="post">
		<input type="hidden" name="productId" value="${product.getProductId()}">
		<div class="product-list search-product-list" id="${'product-list-'.concat(item.getProductId())}">	
			<div class="productList-img">		
			<img src="${'/ShoppingSite/img/item/'.concat(product.getImgURL())}" height="120px" width="auto">
			</div>
			<p class="manga-title">${manga.getTitle()} (${product.getNumber()})</p>
			<p class="product-price"><span class="price"><span class="yen">￥</span>${product.getPrice()}</span></p>
			<p class="description">${product.getDescription()}</p>
			<div class="div-btn">
				<input type="submit" class="small-btn" value="編集">
			</div>
		</div>
	</form>
</c:forEach>

</main>

<%@ include file="tag-footer.jsp" %>