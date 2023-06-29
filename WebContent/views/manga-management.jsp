<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="tag-header.jsp" %>

<%@ page import="jp.co.aforce.tool.MangaInfo" %>

<%@ include file="admin-header.jsp" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<main>

<h1>商品情報管理</h1>

<div class="div-admin-search">
<form action="/ShoppingSite/servlet/manga-search" method="post">
	<p>タイトル</p>
	<input type="hidden" name="uri" value="<%=request.getRequestURI() %>">
	<input type="text" name="adminSearchTitle" class="form-text" value="<c:if test="${adminSearchTitle != null}">${adminSearchTitle}</c:if>" maxlength="256" placeholder="入力なしの場合、一覧が表示されます。">
	<p class="btn_p">
		<input type="submit" value="検索" class="medium-btn">
	</p>
</form></div>


<div class="div-list">
<c:forEach var="manga" items="${adminMangaList}">
	<form action="/ShoppingSite/servlet/product-list" method="post">
		<input type="hidden" name="uri" value="<%=request.getRequestURI() %>">
		<input type="hidden" name="mangaId" value="${manga.getMangaId()}">
		<div class="manga-list management-manga-list" id="${'management-manga-list-'.concat(manga.getMangaId())}">
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
		</div>
	</form>
</c:forEach>
</div>
</main>

<%@ include file="tag-footer.jsp" %>