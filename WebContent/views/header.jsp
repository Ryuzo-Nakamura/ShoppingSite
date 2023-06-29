<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- ログインしていない場合、ログインぺージに強制遷移 --%>

<c:if test="${user == null}">
	<meta http-equiv="refresh" content="0;URL=user-login.jsp">
</c:if>

<%-- ヘッダーの表示 --%>
<header>
	<div class="header-logo">
		<a href="/ShoppingSite/views/user-home.jsp">
			<img src="/ShoppingSite/img/logo.png" alt="logo" width="auto" height="40px">
		</a>
	</div>
	<%-- 検索フォーム --%>
	<div id="header-search-form">
		<form action="/ShoppingSite/servlet/manga-search" class="search-form" method="post">
    		<label>
        		<input type="text" name="searchWord" placeholder="マンガタイトル, 作者, ジャンル" value="${searchWord}">
       	 	</label>
    		<button type="submit" aria-label="検索"></button>
		</form>
	</div>
	<div class="header-icon">
		<%-- ユーザーアイコン、クリック時にユーザー情報画面に遷移 --%>
		<a href="/ShoppingSite/views/user-info.jsp">
			<img alt="User" src="/ShoppingSite/img/user.jpeg" width="auto" height="40px">
			<p>${user.getLastName()} ${user.getFirstName()}</p>
		</a>
		<%-- カートアイコン、クリック時にカート画面に遷移 --%>
		<a href="/ShoppingSite/views/cart-list.jsp">
			<c:choose>
				<c:when test="${cart.size() == 0}">
					<img src="/ShoppingSite/img/cart.jpeg" width="auto" height="40px">
				</c:when>
				<c:otherwise>
					<img src="/ShoppingSite/img/cart-in.jpeg" width="auto" height="40px">
				</c:otherwise>
			</c:choose>
		</a>
	</div>
</header>
