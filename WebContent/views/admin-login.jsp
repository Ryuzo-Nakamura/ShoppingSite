<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="tag-header.jsp" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${admin != null}">
	<% session.removeAttribute("admin"); %>
</c:if>

<main class="main-no-margin">
<div class="div-background">
	<div class="div-form div-login">
	<div class="div-logo"><a href="user-login.jsp"><img src="/ShoppingSite/img/logo-admin.png" alt="logo" width="auto" height="75px"></a></div>
	<h2>ログイン(管理者)</h2>
	<form action="/ShoppingSite/servlet/admin-login" method="post">
		<p>管理者ID <input type="text" name="adminId" class="form-text" value="<c:if test="${adminId != null}">${adminId}</c:if>" required></p>
		<p>パスワード <input type="password" name="adminPassword" class="form-text" value="<c:if test="${adminPassword != null}">${adminPassword}</c:if>" required>
		<%-- ログイン失敗時、メッセージを表示 --%>
	<c:if test="${adminLoginMessage != null}"><p>${adminLoginMessage}</p></c:if>
		<input type="submit" value="ログイン" class="long-btn">
		</p>
	</form>
	</div>
</div>
</main>
<c:if test="${adminLoginMessage != null}"><p>${adminLoginMessage}</p></c:if>

<%
	session.removeAttribute("adminId");
	session.removeAttribute("adminPassword");
	session.removeAttribute("adminLoginMessage");
%>



<%@ include file="tag-footer.jsp" %>