<%@ page language="java" contentType="text/html; charset=UTFhtml 8"
    pageEncoding="UTF-8"%>
<%@ include file="tag-header.jsp" %>

<%@ page import="jp.co.aforce.tool.UserInfo" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-------------------- 確認画面ポップアップ -------------------%>
<div id="user-regist-conf" class="conf-popup">
  <div class="conf">
    <p>この内容で登録します。よろしいですか？</p>
    <div class="conf-btn">
    	<input type="button" value="キャンセル" class="cancel-btn small-btn reverse-btn" > 
    	<input type="button" value="登録" class="ok-btn small-btn">
    </div>
  </div>
</div>
<%-------------------------------------------------------------%>

<main class="main-no-margin">
<div class="div-background"><div class="div-form">
<div class="div-logo"><img src="/ShoppingSite/img/logo.png" alt="logo" width="auto" height="50px"></div>
<h3>会員情報登録</h3>

<form action="/ShoppingSite/servlet/user-regist" id="user-regist-form"  method="post">
	<p>ユーザID</p>
	<input type="text" name="userId" class="form-text" placeholder="半角英数字6文字以上" value="<c:if test="${userId != null}">${userId}</c:if>" pattern="^[0-9A-Za-z]+$" minlength="6" maxlength="32" required>
	<p class="error"><c:if test="${userIdError != null}">${userIdError}</c:if></p>
	<p>パスワード</p>
	<p><input type="password" name="password" class="form-text" placeholder="半角英数字8文字以上" value="<c:if test="${password != null}">${password}</c:if>" minlength="8" maxlength="64" required></p>
	<p>パスワード再入力</p>
	<p><input type="password" name="password2" class="form-text" placeholder="半角英数字8文字以上" value="<c:if test="${password2 != null}">${password2}</c:if>" minlength="8" maxlength="64" required></p>
	<p class="error"><c:if test="${passwordError != null}">${passwordError}</c:if></p>
	<p>名前</p>
	<div class="name-form"> 姓 <input type="text" name="lastName" class="form-text" value="<c:if test="${lastName != null}">${lastName}</c:if>" maxlength="32" required> </div>
	<div class="name-form"> 名 <input type="text" name="firstName" class="form-text" value="<c:if test="${firstName != null}">${firstName}</c:if>" maxlength="32" required></div>
	<p>生年月日</p>
	<div class="birth-form">
		<div class="form-select">
			<select name="birthYear" required>
				<option value=""></option>
				<% for(int y = 2023; y >= 1900; y--) { %>
					<c:set var="y" value="<%=y %>"></c:set>
					<option value="<%=y %>" <c:if test="${y==birthYear}">selected</c:if>><%=y %></option>
				<% } %>
			</select> 年
		</div>
		<div class="form-select">
			<select name="birthMonth" id="birth_month" required>
				<option value=""></option>
				<% for(int m = 1; m <= 12; m++) { %>
					<c:set var="m" value="<%=m %>"></c:set>
					<option value="<%=m %>" <c:if test="${m==birthMonth}">selected</c:if>><%=m %></option>
				<% } %>
			</select> 月
		</div>
		<div class="form-select">
			<select name="birthDay" required>
				<option value=""></option>
				<% for(int d = 1; d <= 31; d++) { %>
					<c:set var="d" value="<%=d %>"></c:set>
					<option value="<%=d %>" <c:if test="${d==birthDay}">selected</c:if>><%=d %></option>
				<% } %>
			</select> 日
		</div>
	</div>
	<p>電話番号</p>
	<input type="tel" name="phoneNumber" class="form-text" pattern="\d{2,4}-?\d{2,4}-?\d{3,4}" placeholder="ハイフン省略可" value="<c:if test="${phoneNumber != null}">${phoneNumber}</c:if>" maxlength="21" required>
	<p class="error"><c:if test="${phoneNumberError != null}">${phoneNumberError}</c:if></p>
	<p>メールアドレス</p>
	<input type="email" name="mailAddress" class="form-text" value="<c:if test="${mailAddress != null}">${mailAddress}</c:if>" maxlength="256" required>
	<p class="error"><c:if test="${mailAddressError != null}">${mailAddressError}</c:if></p>
	<p>住所</p>
	<input type="text" name="address" class="form-text" value="<c:if test="${address != null}">${address}</c:if>" maxlength="256" required>
	
	<p class="btn_p">
		<input type="button" value="登録" id="user-regist-btn" class="long-btn">
	</p>
</form>
</div></div>
</main>


<%-- セッション属性を削除 --%>
<%
	session.removeAttribute("userIdError");
	session.removeAttribute("passwordError");
	session.removeAttribute("phoneNumberError");
	session.removeAttribute("mailAddressError");
	UserInfo.removeUserInfo(session);
	session.removeAttribute("password2");
%>


<%@ include file="tag-footer.jsp" %>