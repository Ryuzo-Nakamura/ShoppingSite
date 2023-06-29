<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="tag-header.jsp" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<main class="main-no-margin">
<div class="div-background">
	<div class="div-form div-login">
	<div class="div-logo"><a href="admin-login.jsp"><img src="/ShoppingSite/img/logo.png" alt="logo" width="auto" height="75px"></a></div>
	<h2>ログイン</h2>
	<form action="/ShoppingSite/servlet/user-login" method="post">
		<p>ユーザーID <input type="text" name="userId" class="form-text" value="<c:if test="${userId != null}">${userId}</c:if>" required></p>
		<p>パスワード <input type="password" name="password" class="form-text" value="<c:if test="${password != null}">${password}</c:if>" required></p>
	<%-- ログイン失敗時、メッセージを表示 --%>
	<c:if test="${loginMessage != null}"><p>${loginMessage}</p></c:if>
	<p><a href="user-regist.jsp">会員登録が済んでない方はこちら</a></p>
	<input type="submit" class="long-btn" value="ログイン">
	</form>
	</div>
</div>
</main>

<%-- セッション属性を削除 --%>
<%
	session.removeAttribute("userId");
	session.removeAttribute("password");
	session.removeAttribute("loginMessage");
%>



<%@ include file="tag-footer.jsp" %>