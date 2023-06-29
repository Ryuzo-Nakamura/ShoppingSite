<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="tag-header.jsp" %>

<%@ page import="jp.co.aforce.tool.UserInfo" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="header.jsp" %>

<%-------------------- 確認画面ポップアップ -------------------%>
<div id="user-update-conf" class="conf-popup">
  <div class="conf">
    <p>この内容で会員情報を更新します。よろしいですか？</p>
    <div class="conf-btn">
    	<input type="button" value="キャンセル" class="cancel-btn small-btn reverse-btn" > 
    	<input type="button" value="更新" class="ok-btn small-btn">
    </div>
  </div>
</div>

<div id="user-delete-conf" class="conf-popup">
  <div class="conf">
    <p>会員情報を削除します。本当によろしいですか？</p>
    <div class="conf-btn">
    	<input type="button" value="キャンセル" class="cancel-btn small-btn reverse-btn"> 
    	<input type="button" value="削除" class="ok-btn small-btn">
    </div>
  </div>
</div>

<div id="user-logout-conf" class="conf-popup">
  <div class="conf">
    <p>ログアウトします。本当によろしいですか？</p>
    <div class="conf-btn">
    	<input type="button" value="キャンセル" class="cancel-btn small-btn reverse-btn"> 
    	<input type="button" value="ログアウト" class="ok-btn small-btn">
    </div>
  </div>
</div>
<%-------------------------------------------------------------%>

<main>
<div class="div-user-info">
<div class="div-info user-info">
	<h2>会員情報</h2>
	<form id="user-info-form" method="post">
		<input type="hidden" name="URI" value="<%=request.getRequestURI() %>">

		<input type="hidden" name="userId" value="${user.getUserId()}">
		<input type="hidden" name="password" value="${user.getPassword()}">
		<p>名前</p>
		<div class="name-form"> 姓 <input type="text" name="lastName" class="form-text" value="${user.getLastName()}" maxlength="32" required> </div>
		<div class="name-form"> 名 <input type="text" name="firstName" class="form-text" value="${user.getFirstName()}" maxlength="32" required></div>
		
		<p>生年月日</p>
		<div class="birth-form">
			<div class="form-select">
				<select name="birthYear" required>
					<option value=""></option>
					<% for(int y = 2023; y >= 1900; y--) { %>
						<c:set var="y" value="<%=y %>"></c:set>
						<option value="<%=y %>" <c:if test="${y==user.getBirthYear()}">selected</c:if>><%=y %></option>
					<% } %>
				</select> 年
			</div>
			<div class="form-select">
				<select name="birthMonth" id="birth_month" required>
					<option value=""></option>
					<% for(int m = 1; m <= 12; m++) { %>
						<c:set var="m" value="<%=m %>"></c:set>
						<option value="<%=m %>" <c:if test="${m==user.getBirthMonth()}">selected</c:if>><%=m %></option>
					<% } %>
				</select> 月   
			</div>
			<div class="form-select">
				<select name="birthDay" required>
					<option value=""></option>
					<% for(int d = 1; d <= 31; d++) { %>
						<c:set var="d" value="<%=d %>"></c:set>
						<option value="<%=d %>" <c:if test="${d==user.getBirthDay()}">selected</c:if>><%=d %></option>
					<% } %>
				</select> 日   
			</div>
		</div>
	
		<p>電話番号</p>
		<input type="tel" name="phoneNumber" class="form-text" pattern="\d{2,4}-?\d{2,4}-?\d{3,4}" placeholder="ハイフン省略可" value="${user.getPhoneNumber()}" maxlength="21" required>
		<p class="error"><c:if test="${phoneNumberError != null}">${phoneNumberError}</c:if></p>
		
		<p>メールアドレス</p>
		<input type="email" name="mailAddress" class="form-text" value="${user.getMailAddress()}" maxlength="256" required>
		<p class="error"><c:if test="${mailAddressError != null}">${mailAddressError}</c:if></p>
	
		<p>住所</p>
		<input type="text" name="address" class="form-text" value="${user.getAddress()}" maxlength="256" required>
		
		<p><c:if test="${userManagementMessage != null}">${userManagementMessage}</c:if></p>
	
		<div class="div-btn">
			<input type="button" value="削除" id="user-delete-btn" class="medium-btn reverse-btn">
			<input type="button" value="更新" id="user-update-btn" class="medium-btn">
		</div>
		<hr>
		<p> </p>
		<div class="div-btn">
			<input type="button" value="ログアウト" class="large-btn" id="user-logout-btn"></p>
		</div>
	</form>
</div>
</div>
</main>
<%
	session.removeAttribute("phoneNumberError");
	session.removeAttribute("mailAddressError");
	session.removeAttribute("userManagementMessage");
	UserInfo.removeUserInfo(session);
%>

<%@ include file="tag-footer.jsp" %>