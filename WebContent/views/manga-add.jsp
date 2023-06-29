<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="tag-header.jsp" %>

<%@ page import="jp.co.aforce.tool.MangaInfo" %>

<%@ include file="admin-header.jsp" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%-------------------- 確認画面ポップアップ -------------------%>
<div id="manga-add-conf" class="conf-popup">
  <div class="conf">
    <p>マンガ情報を追加します。よろしいですか？</p>
    <div class="conf-btn">
    	<input type="button" value="キャンセル" class="cancel-btn small-btn reverse-btn" > 
    	<input type="button" value="追加" class="ok-btn small-btn">
    </div>
  </div>
</div>
<%-------------------------------------------------------------%>

<main>

<h2>マンガタイトル追加</h2>

<a href="/ShoppingSite/views/product-add.jsp">&lt;&lt;商品追加に戻る</a>

<form action="/ShoppingSite/servlet/manga-add" method="post" id="manga-add-form">
	<p>タイトル</p>
	<input type="text" name="title" class="form-text" value="<c:if test="${title != null}">${title}</c:if>" maxlength="256" required>
	<p class="error"><c:if test="${titleError != null}">${titleError}</c:if></p>
	
	<p>作者</p>
	<input type="text" name="author" class="form-text" placeholder="複数人の場合は、空白もしくは「,」「、」で区切ってください" value="<c:if test="${author != null}">${author}</c:if>" maxlength="256" required>
	
	<p>出版社</p>
	<input type="text" name="publisher" class="form-text" value="<c:if test="${publisher != null}">${publisher}</c:if>" maxlength="32" required>
	
	<p>ジャンル</p>
	<input type="text" name="genre" class="form-text" placeholder="複数記述するの場合は、空白もしくは「,」「、」で区切ってください" value="<c:if test="${genre != null}">${genre}</c:if>" maxlength="256" required>
	
	<p><c:if test="${mangaAddMessage != null}">${mangaAddMessage}</c:if></p>
	<div class="div-btn">
		<input type="button" value="追加" class="medium-btn" id="manga-add-btn">
	</div>
</form>

</main>

<%
	MangaInfo.removeMangaInfo(session);
	session.removeAttribute("titleError");
	session.removeAttribute("itemAddMessage");
%>



<%@ include file="tag-footer.jsp" %>