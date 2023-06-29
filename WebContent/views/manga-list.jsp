<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="tag-header.jsp" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="header.jsp" %>

<main>
<form action="/ShoppingSite/servlet/manga-search" method="post" id="manga-sort-form" class="sort-form">
	<label  class="sort-select">
	<select name="sort" id="user-manga-sort">
		<option value="1" <c:if test="${sort==1}">selected</c:if>>商品番号(昇順)</option>
		<option value="2" <c:if test="${sort==2}">selected</c:if>>商品番号(降順)</option>
		<option value="3" <c:if test="${sort==3}">selected</c:if>>タイトル(昇順)</option>
		<option value="4" <c:if test="${sort==4}">selected</c:if>>タイトル(降順)</option>
	</select>
	</label>
</form>

<div class="div-list">
<%-- 検索ワードを含むマンガタイトル情報を一覧で表示 --%>
<c:forEach var="manga" items="${mangaList}">
	<form action="/ShoppingSite/servlet/product-list" method="post">
		<input type="hidden" name="mangaId" value="${manga.getMangaId()}">
		<div class="manga-list search-manga-list">
			<div class="mangaList-img"><c:choose>
				<c:when test="${manga.getImgURL() != null}">
					<img src="${'/ShoppingSite/img/item/'.concat(manga.getImgURL())}" height="150px" width="auto">
				</c:when>
				<c:otherwise>
					<img src="/ShoppingSite/img/no-image.png" height="120px" width="auto">
				</c:otherwise>
			</c:choose></div>
			<p class="manga-title">${manga.getTitle()}</p>
			<form action="/ShoppingSite/servlet/manga-search" method="post"></form>
			<div class="manga-info">
			<div class="div-tag">作者：
				<c:forEach var="author" items="${manga.getAuthor()}">
					<form action="/ShoppingSite/servlet/manga-search" method="post">
						<input type="hidden" name="searchWord" value="${author}">
						<input type="button" class="tag-btn" value="${author}">
					</form>
				</c:forEach>
			</div>
			<div class="div-tag">出版社：
				<form action="/ShoppingSite/servlet/manga-search" method="post">
					<input type="hidden" name="searchWord" value="${manga.getPublisher()}">
					<input type="button" class="tag-btn" value="${manga.getPublisher()}">
				</form>
			</div>
			<div class="div-tag">
				<c:forEach var="g" items="${manga.getGenre()}">
					<form action="/ShoppingSite/servlet/manga-search" method="post">
						<input type="hidden" name="searchWord" value="${g}">
						<input type="button" class="tag-btn" value="${g}">
					</form>
				</c:forEach>
			</div>
			<p>総巻数：${manga.getTotalNumber()}巻</p>
			</div>
		</div>
	</form>
</c:forEach>
</div>

</main>
<%@ include file="tag-footer.jsp" %>