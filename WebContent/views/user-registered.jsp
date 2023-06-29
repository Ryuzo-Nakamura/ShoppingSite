<%@ page language="java" contentType="text/html; charset=UTFhtml 8"
    pageEncoding="UTF-8"%>
<%@ include file="tag-header.jsp" %>

<%@ page import="jp.co.aforce.constant.Constant.ItemName" %>
<%@ page import="jp.co.aforce.tool.UserInfo" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<main class="main-no-margin">
<div class="div-background"><div class="div-form">
<p>以下の内容で登録しました。</p>

<%-- 登録情報を表示 --%>
<div> 
	<p><%=ItemName.USER_ID %>：${userId}</p>
	<p><%=ItemName.PASSWARD %>：
		<%-- パスワードを＊で出力 --%>
		<% for(int i = 0; i < session.getAttribute("password").toString().length(); i++){ %>
			＊
		<% } %>
	</p>
	<p>名前：${lastName} ${firstName}</p>
	<p>生年月日：${birthYear}年 ${birthMonth}月 ${birthDay}日</p>
	<p><%=ItemName.PHONE_NUMBER %>：${phoneNumber}</p>
	<p><%=ItemName.MAIL＿ADDRESS %>：${mailAddress}</p>
	<p><%=ItemName.ADDRESS %>：${address}</p>
</div>

<input type="button" value="ログイン画面に戻る" class="long-btn" onclick="location.href='/ShoppingSite/views/user-login.jsp'">


</div></div>
</main>

<%-- セッション属性のユーザー情報を削除 --%>
<% UserInfo.removeUserInfo(session); %>

<%@ include file="tag-footer.jsp" %>