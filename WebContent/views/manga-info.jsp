<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="tag-header.jsp" %>

<%@ page import="jp.co.aforce.tool.MangaInfo" %>

<%@ include file="admin-header.jsp" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-------------------- 確認画面ポップアップ -------------------%>
<div id="manga-update-conf" class="conf-popup">
  <div class="conf">
    <p>マンガ情報を更新します。よろしいですか？</p>
    <div class="conf-btn">
    	<input type="button" value="キャンセル" class="cancel-btn small-btn reverse-btn" > 
    	<input type="button" value="更新" class="ok-btn small-btn">
    </div>
  </div>
</div>

<div id="manga-delete-conf" class="conf-popup">
  <div class="conf">
    <p>マンガ情報を削除します。このマンガタイトルの商品はすべて削除されます。本当によろしいですか？</p>
    <div class="conf-btn">
    	<input type="button" value="キャンセル" class="cancel-btn small-btn reverse-btn" > 
    	<input type="button" value="削除" class="ok-btn small-btn reverse-btn">
    </div>
  </div>
</div>
<%-------------------------------------------------------------%>


<c:set var="manga" value="${adminManga}"/>

<main>
<h2>商品情報管理</h2>

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

<form method="post" id="manga-info-form">
	<input type="hidden" name="adminMangaId" value="<c:if test="${manga.getMangaId() != null}">${manga.getMangaId()}</c:if>">
	
	<p>タイトル</p>
	<input type="text" name="adminTitle"  class="form-text" value="<c:if test="${manga.getTitle() != null}">${manga.getTitle()}</c:if>" required>
	
	<p>作者</p>
	<input type="text" name="adminAuthor" class="form-text" placeholder="複数人の場合は、空白もしくは「,」「、」で区切ってください" value="<c:if test="${manga.getAuthorString() != null}">${manga.getAuthorString()}</c:if>" maxlength="256" required>
	
	<p>出版社</p>
	<input type="text" name="adminPublisher" class="form-text" value="<c:if test="${manga.getPublisher() != null}">${manga.getPublisher()}</c:if>" min="0" required>
	
	<p>ジャンル</p>
	<input type="text" name="adminGenre" class="form-text" placeholder="複数記述するの場合は、空白もしくは「,」「、」で区切ってください" value="<c:if test="${manga.getGenreString() != null}">${manga.getGenreString()}</c:if>" maxlength="256" required>	

	
	<p><c:if test="${mangaManagementMessage != null}">${mangaManagementMessage}</c:if></p>

	<div class="div-btn">
		<input type="button" value="削除" id="manga-delete-btn" class="medium-btn reverse-btn">
		<input type="button" value="更新" id="manga-update-btn" class="medium-btn">
	</div>
</form>

</main>
<% 
	session.removeAttribute("manga");
	session.removeAttribute("mangaManagementMessage"); 
%>


<%@ include file="tag-footer.jsp" %>