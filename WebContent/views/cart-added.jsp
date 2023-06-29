<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="tag-header.jsp" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="header.jsp" %>

<main>
<p class="purchase">☑カートに追加しました</p>


	<div class="div-list">
	<%-- 選択されたマンガタイトルの製品を一覧で表示 --%>
	<c:forEach var="product" items="${addedProductList}">
		<form action="/ShoppingSite/servlet/cart-add" method="post">
			<input type="hidden" name="productId" value="${product.getProductId()}">
			<div class="product-list">
				<div class="productList-img">		
					<img src="${'/ShoppingSite/img/item/'.concat(product.getImgURL())}" height="120px" width="auto">
				</div>
				<p class="manga-title">${manga.getTitle()} (${product.getNumber()})</p>
				<p class="product-price"><span class="price"><span class="yen">￥</span>${product.getPrice()}</span></p>
				<p class="amount">数量：${amount}</p>
			</div>
		</form>
	</c:forEach>
	</div>


<div class="div-btn">
	<input type="button" value="商品ページに戻る" class="medium-btn reverse-btn" onclick="location.href='/ShoppingSite/views/product-list.jsp'">
	<input type="button" value="カートに移動" class="medium-btn" onclick="location.href='/ShoppingSite/views/cart-list.jsp'">
</div>

<hr>

<h2>オススメマンガ</h2>
<ul class="slider manga-slider">
	<li>
		<form action="/ShoppingSite/servlet/product-list" method="post">
			<input type="hidden" name="mangaId" value="00000016">
			<img src="/ShoppingSite/img/item/oshinoko005.jpg" class="home-img">
		</form>
	</li>
	<li>
		<form action="/ShoppingSite/servlet/product-list" method="post">
			<input type="hidden" name="mangaId" value="00000036">
			<img src="/ShoppingSite/img/item/bokuyaba003.jpg" class="home-img">
		</form>
	</li>
	<li>
		<form action="/ShoppingSite/servlet/product-list" method="post">
			<input type="hidden" name="mangaId" value="00000010">
			<img src="/ShoppingSite/img/item/bluelock005.jpg" class="home-img">
		</form>
	</li>
	<li>
		<form action="/ShoppingSite/servlet/product-list" method="post">
			<input type="hidden" name="mangaId" value="00000011">
			<img src="/ShoppingSite/img/item/bluelock-nagi-002.jpg" class="home-img">
		</form>
	</li>
	<li>
		<form action="/ShoppingSite/servlet/product-list" method="post">
			<input type="hidden" name="mangaId" value="00000035">
			<img src="/ShoppingSite/img/item/ao-no-hako003.jpg" class="home-img">
		</form>
	</li>
	<li>
		<form action="/ShoppingSite/servlet/product-list" method="post">
			<input type="hidden" name="mangaId" value="00000025">
			<img src="/ShoppingSite/img/item/pink-to-habanero003.jpg" class="home-img">
		</form>
	</li>
	<li>
		<form action="/ShoppingSite/servlet/product-list" method="post">
			<input type="hidden" name="mangaId" value="00000024">
			<img src="/ShoppingSite/img/item/uikon003.jpg" class="home-img">
		</form>
	</li>
	<li>
		<form action="/ShoppingSite/servlet/product-list" method="post">
			<input type="hidden" name="mangaId" value="00000007">
			<img src="/ShoppingSite/img/item/my_hero_academia005.jpg" class="home-img">
		</form>
	</li>
	<li>
		<form action="/ShoppingSite/servlet/product-list" method="post">
			<input type="hidden" name="mangaId" value="00000008">
			<img src="/ShoppingSite/img/item/jujutsu_kaisen004.jpg" class="home-img">
		</form>
	</li>
	<li>
		<form action="/ShoppingSite/servlet/product-list" method="post">
			<input type="hidden" name="mangaId" value="00000028">
			<img src="/ShoppingSite/img/item/the-fable003.jpg" class="home-img">
		</form>
	</li>
	<li>
		<form action="/ShoppingSite/servlet/product-list" method="post">
			<input type="hidden" name="mangaId" value="00000031">
			<img src="/ShoppingSite/img/item/kubo-san003.jpg" class="home-img">
		</form>
	</li>
	<li>
		<form action="/ShoppingSite/servlet/product-list" method="post">
			<input type="hidden" name="mangaId" value="00000012">
			<img src="/ShoppingSite/img/item/aoashi005.jpg" class="home-img">
		</form>
	</li>
	<li>
		<form action="/ShoppingSite/servlet/product-list" method="post">
			<input type="hidden" name="mangaId" value="00000003">
			<img src="/ShoppingSite/img/item/attack_of_titan05.jpg" class="home-img">
		</form>
	</li>
	<li>
		<form action="/ShoppingSite/servlet/product-list" method="post">
			<input type="hidden" name="mangaId" value="00000014">
			<img src="/ShoppingSite/img/item/major2nd005.jpg" class="home-img">
		</form>
	</li>
	<li>
		<form action="/ShoppingSite/servlet/product-list" method="post">
			<input type="hidden" name="mangaId" value="00000022">
			<img src="/ShoppingSite/img/item/honey-lemon-soda003.jpg" class="home-img">
		</form>
	</li>
	<li>
		<form action="/ShoppingSite/servlet/product-list" method="post">
			<input type="hidden" name="mangaId" value="00000026">
			<img src="/ShoppingSite/img/item/new-employee-who-can-read-the-air003.jpg" class="home-img">
		</form>
	</li>
	<li>
		<form action="/ShoppingSite/servlet/product-list" method="post">
			<input type="hidden" name="mangaId" value="00000037">
			<img src="/ShoppingSite/img/item/horimiya003.jpg" class="home-img">
		</form>
	</li>
</ul>

</main>

<%@ include file="tag-footer.jsp" %>