<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="tag-header.jsp"%>

<%@ page import="jp.co.aforce.tool.UserInfo"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="header.jsp"%>

<%-------------------- 確認画面ポップアップ -------------------%>
<div id="user-update-conf" class="conf-popup">
	<div class="conf">
		<p>この内容で会員情報を更新します。よろしいですか？</p>
		<div class="conf-btn">
			<input type="button" value="キャンセル"
				class="cancel-btn small-btn reverse-btn"> <input
				type="button" value="更新" class="ok-btn small-btn">
		</div>
	</div>
</div>

<div id="user-delete-conf" class="conf-popup">
	<div class="conf">
		<p>会員情報を削除します。本当によろしいですか？</p>
		<div class="conf-btn">
			<input type="button" value="キャンセル"
				class="cancel-btn small-btn reverse-btn"> <input
				type="button" value="削除" class="ok-btn small-btn">
		</div>
	</div>
</div>

<div id="userId-update-conf" class="conf-popup">
	<div class="conf">
		<p>ユーザIDを更新します。よろしいですか？</p>
		<div class="conf-btn">
			<input type="button" value="キャンセル"
				class="cancel-btn small-btn reverse-btn"> <input
				type="button" value="更新" class="ok-btn small-btn">
		</div>
	</div>
</div>

<div id="password-update-conf" class="conf-popup">
	<div class="conf">
		<p>パスワードを更新します。よろしいですか？</p>
		<div class="conf-btn">
			<input type="button" value="キャンセル"
				class="cancel-btn small-btn reverse-btn"> <input
				type="button" value="更新" class="ok-btn small-btn">
		</div>
	</div>
</div>

<div id="user-logout-conf" class="conf-popup">
	<div class="conf">
		<p>ログアウトします。本当によろしいですか？</p>
		<div class="conf-btn">
			<input type="button" value="キャンセル"
				class="cancel-btn small-btn reverse-btn"> <input
				type="button" value="ログアウト" class="ok-btn small-btn">
		</div>
	</div>
</div>
<%-------------------------------------------------------------%>

<%
	String update = "";
	if(session.getAttribute("update") != null){
		update = (String)session.getAttribute("update");
	}
	if(update.equals("") || update.equals("info")){
%>
		<style>
		#update-user-info p{
		text-decoration: underline;
		color: #ef2346;
		}
		.user-id {
			display: none;
		}
		.user-password {
			display: none;
		}
		</style>
<%		
	} else if(update.equals("id")){
%>
		<style>
		#update-user-id p{
		text-decoration: underline;
		color: #ef2346;
		}
		.user-info {
			display: none;
		}
		.user-password {
			display: none;
		}
		</style>
<%
	} else{
%>
		<style>
		#update-user-password p{
		text-decoration: underline;
		color: #ef2346;
		}
		.user-info {
			display: none;
		}
		.user-id {
			display: none;
		}
		</style>
<%
	}
%>

<div class="user-info-menu">
	<div id="update-user-info">
		<p>会員情報変更/削除</p>
	</div>
	<div id="update-user-id">
		<p>ユーザID変更</p>
	</div>
	<div id="update-user-password">
		<p>パスワード変更</p>
	</div>
	<form id="user-logout-form" method="post">
		<div class="div-btn">
			<input type="button" value="ログアウト" class="large-btn"
				id="user-logout-btn">
		</div>
	</form>
</div>

<main>
	<div class="div-user-info">
		<%-- 会員情報 --%>
		<div class="div-info user-info">
			<h2>会員情報変更/削除</h2>
			<form id="user-info-form" method="post">
				<input type="hidden" name="update" value="info">
				<input type="hidden" name="userId" value="${user.getUserId()}">
				<input type="hidden" name="password" value="${user.getPassword()}">
				<p>名前</p>
				<div class="name-form">
					姓 <input type="text" name="lastName" class="form-text"
						value="${user.getLastName()}" maxlength="32" required>
				</div>
				<div class="name-form">
					名 <input type="text" name="firstName" class="form-text"
						value="${user.getFirstName()}" maxlength="32" required>
				</div>
				<p>生年月日</p>
				<div class="birth-form">
					<div class="form-select">
						<select name="birthYear" required>
							<option value=""></option>
							<%
							for (int y = 2023; y >= 1900; y--) {
							%>
							<c:set var="y" value="<%=y%>"></c:set>
							<option value="<%=y %>"
								<c:if test="${y==user.getBirthYear()}">selected</c:if>><%=y%></option>
							<%
							}
							%>
						</select> 年
					</div>
					<div class="form-select">
						<select name="birthMonth" id="birth_month" required>
							<option value=""></option>
							<%
							for (int m = 1; m <= 12; m++) {
							%>
							<c:set var="m" value="<%=m%>"></c:set>
							<option value="<%=m %>"
								<c:if test="${m==user.getBirthMonth()}">selected</c:if>><%=m%></option>
							<%
							}
							%>
						</select> 月
					</div>
					<div class="form-select">
						<select name="birthDay" required>
							<option value=""></option>
							<%
							for (int d = 1; d <= 31; d++) {
							%>
							<c:set var="d" value="<%=d%>"></c:set>
							<option value="<%=d %>"
								<c:if test="${d==user.getBirthDay()}">selected</c:if>><%=d%></option>
							<%
							}
							%>
						</select> 日
					</div>
				</div>
				<p>電話番号</p>
				<input type="tel" name="phoneNumber" class="form-text"
					pattern="\d{2,4}-?\d{2,4}-?\d{3,4}" placeholder="ハイフン省略可"
					value="${user.getPhoneNumber()}" maxlength="21" required>
				<p class="error">
					<c:if test="${phoneNumberError != null}">${phoneNumberError}</c:if>
				</p>
				<p>メールアドレス</p>
				<input type="email" name="mailAddress" class="form-text"
					value="${user.getMailAddress()}" maxlength="256" required>
				<p class="error">
					<c:if test="${mailAddressError != null}">${mailAddressError}</c:if>
				</p>
				<p>住所</p>
				<input type="text" name="address" class="form-text"
					value="${user.getAddress()}" maxlength="256" required>
				<p>
					<c:if test="${userManagementMessage != null}">${userManagementMessage}</c:if>
				</p>
				<div class="div-btn">
					<input type="button" value="削除" id="user-delete-btn" class="medium-btn reverse-btn">
					<input type="button" value="更新" id="user-update-btn" class="medium-btn">
				</div>
			</form>
		</div>
		<%-- ユーザID --%>
		<div class="div-info user-id">
			<h2>ユーザID変更</h2>
			<form id="user-id-form" action="/ShoppingSite/servlet/user-update" method="post">
				<input type="hidden" name="update" value="id">
				<input type="hidden" name="userId" value="${user.getUserId()}">
				<input type="hidden" name="mailAddress" value="${user.getMailAddress()}">
				<p>変更後のユーザID</p>
				<input type="text" name="newUserId" class="form-text" value="${newUserId}" maxlength="32" required>
				<p>
					<c:if test="${userIdManagementMessage != null}">${userIdManagementMessage}</c:if>
				</p>
				<div class="div-btn">
					<input type="button" value="更新" id="userId-update-btn" class="medium-btn">
				</div>
			</form>
		</div>
		<%-- パスワード --%>
		<div class="div-info user-password">
			<h2>パスワード変更</h2>
			<form id="user-password-form" action="/ShoppingSite/servlet/user-update" method="post">
				<input type="hidden" name="update" value="password">
				<input type="hidden" name="userId" value="${user.getUserId()}">
				<input type="hidden" name="password" value="${user.getPassword()}">
				<p>現在のパスワード</p>
				<input type="password" name="currentPassword" class="form-text" value="${currentPassword}" maxlength="64" required>
				<p>変更後のパスワード</p>
				<input type="password" name="newPassword" class="form-text" value="${newPassword}" maxlength="64" required>
				<p>変更後のパスワード(確認用)</p>
				<input type="password" name="newPassword2" class="form-text" value="${newPassword2}" maxlength="64" required>
				<p>
					<c:if test="${passwordManagementMessage != null}">${passwordManagementMessage}</c:if>
				</p>
				<div class="div-btn">
					<input type="button" value="更新" id="password-update-btn" class="medium-btn">
				</div>
			</form>
		</div>
	</div>
</main>
<%
session.removeAttribute("phoneNumberError");
session.removeAttribute("mailAddressError");
session.removeAttribute("userManagementMessage");
session.removeAttribute("newUserId");
session.removeAttribute("currentPassword");
session.removeAttribute("newPassword");
session.removeAttribute("newPassword2");
UserInfo.removeUserInfo(session);
%>

<%@ include file="tag-footer.jsp"%>