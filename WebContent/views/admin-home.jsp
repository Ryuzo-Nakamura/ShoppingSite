<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="tag-header.jsp" %>

<%@ include file="admin-header.jsp" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<main>
<h1>管理者メニュー</h1>

<div class="menu_btn">
	<button class="exlarge-btn" onclick="location.href='/ShoppingSite/views/product-add.jsp'">商品追加</button>
	<button class="exlarge-btn" onclick="location.href='/ShoppingSite/views/manga-add.jsp'">マンガタイトル追加</button>
	<button class="exlarge-btn" onclick="location.href='/ShoppingSite/views/manga-management.jsp'">商品情報管理</button>
</div>
</main>

<%@ include file="tag-footer.jsp" %>