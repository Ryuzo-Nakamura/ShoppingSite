<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- ログインしていない場合、ログインぺージに強制遷移 --%>

<c:if test="${admin == null}">
	<meta http-equiv="refresh" content="0;URL=admin-login.jsp">
</c:if>

<%-- ヘッダーの表示 --%>
<header>
	<div class="header-logo">
		<a href="/ShoppingSite/views/admin-home.jsp">
			<img src="/ShoppingSite/img/logo-admin.png" alt="logo" width="auto" height="40px">
		</a>
	</div>

	<nav>
		<ul>
			<li><a href="/ShoppingSite/views/product-add.jsp">商品追加</a></li>
			<li><a href="/ShoppingSite/views/manga-add.jsp">マンガタイトル追加</a></li>
			<li><a href="/ShoppingSite/views/manga-management.jsp">商品情報管理</a></li>
		</ul>
	</nav>
	<a href="/ShoppingSite/views/admin-login.jsp">
		<img src="/ShoppingSite/img/logout.png" id="logout-btn" alt="logout" width="auto" height="40px">
	</a>
	
</header>
<hr>