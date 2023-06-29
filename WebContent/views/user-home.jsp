<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="tag-header.jsp" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="header.jsp" %>

<div id="top-img">
	<img src="/ShoppingSite/img/top-img.jpg" width="100%">
	<div id="top-title">
		<p class="eachTextAnime">マンガ買うなら、</p>
		<h4 class="eachTextAnime">Manga Bookstore</h4>
	</div>
</div>

<main class="main-no-margin">
<ul class="slider top-slider">
	<li><img src="/ShoppingSite/img/news01.png" alt=""></li>
	<li><img src="/ShoppingSite/img/news02.png" alt=""></li>
	<li><img src="/ShoppingSite/img/news03.png" alt=""></li>
	<li><img src="/ShoppingSite/img/news04.png" alt=""></li>
	<li><img src="/ShoppingSite/img/news05.png" alt=""></li>
</ul>

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

<h2>少年マンガ</h2>
<ul class="slider manga-slider">
	<li>
		<form action="/ShoppingSite/servlet/product-list" method="post">
			<input type="hidden" name="mangaId" value="00000001">
			<img src="/ShoppingSite/img/item/onepiece010.jpg" class="home-img">
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
			<input type="hidden" name="mangaId" value="00000003">
			<img src="/ShoppingSite/img/item/attack_of_titan05.jpg" class="home-img">
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
			<input type="hidden" name="mangaId" value="00000009">
			<img src="/ShoppingSite/img/item/fairytail005.jpg" class="home-img">
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
			<input type="hidden" name="mangaId" value="00000034">
			<img src="/ShoppingSite/img/item/nisekoi003.jpg" class="home-img">
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
			<input type="hidden" name="mangaId" value="00000005">
			<img src="/ShoppingSite/img/item/yowamushi-pedal005.jpg" class="home-img">
		</form>
	</li>
	<li>
		<form action="/ShoppingSite/servlet/product-list" method="post">
			<input type="hidden" name="mangaId" value="00000006">
			<img src="/ShoppingSite/img/item/bleach005.jpg" class="home-img">
		</form>
	</li>
</ul>

<h2>青年マンガ</h2>
<ul class="slider manga-slider">
	<li>
		<form action="/ShoppingSite/servlet/product-list" method="post">
			<input type="hidden" name="mangaId" value="00000012">
			<img src="/ShoppingSite/img/item/aoashi005.jpg" class="home-img">
		</form>
	</li>
	<li>
		<form action="/ShoppingSite/servlet/product-list" method="post">
			<input type="hidden" name="mangaId" value="00000016">
			<img src="/ShoppingSite/img/item/oshinoko005.jpg" class="home-img">
		</form>
	</li>
	<li>
		<form action="/ShoppingSite/servlet/product-list" method="post">
			<input type="hidden" name="mangaId" value="00000015">
			<img src="/ShoppingSite/img/item/kaguya-sama-love-is-war005.jpg" class="home-img">
		</form>
	</li>
	<li>
		<form action="/ShoppingSite/servlet/product-list" method="post">
			<input type="hidden" name="mangaId" value="00000027">
			<img src="/ShoppingSite/img/item/kingdom003.jpg" class="home-img">
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
			<input type="hidden" name="mangaId" value="00000036">
			<img src="/ShoppingSite/img/item/bokuyaba003.jpg" class="home-img">
		</form>
	</li>
	<li>
		<form action="/ShoppingSite/servlet/product-list" method="post">
			<input type="hidden" name="mangaId" value="00000032">
			<img src="/ShoppingSite/img/item/my-dress-up-darling003.jpg" class="home-img">
		</form>
	</li>
	<li>
		<form action="/ShoppingSite/servlet/product-list" method="post">
			<input type="hidden" name="mangaId" value="00000030">
			<img src="/ShoppingSite/img/item/space-brothers003.jpg" class="home-img">
		</form>
	</li>
	<li>
		<form action="/ShoppingSite/servlet/product-list" method="post">
			<input type="hidden" name="mangaId" value="00000029">
			<img src="/ShoppingSite/img/item/ajin003.jpg" class="home-img">
		</form>
	</li>
	<li>
		<form action="/ShoppingSite/servlet/product-list" method="post">
			<input type="hidden" name="mangaId" value="00000033">
			<img src="/ShoppingSite/img/item/my-home-hero003.jpg" class="home-img">
		</form>
	</li>
</ul>

<h2>少女マンガ</h2>
<ul class="slider manga-slider">
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
			<input type="hidden" name="mangaId" value="00000022">
			<img src="/ShoppingSite/img/item/honey-lemon-soda003.jpg" class="home-img">
		</form>
	</li>
	<li>
		<form action="/ShoppingSite/servlet/product-list" method="post">
			<input type="hidden" name="mangaId" value="00000021">
			<img src="/ShoppingSite/img/item/ao-haru-ride003.jpg" class="home-img">
		</form>
	</li>
	<li>
		<form action="/ShoppingSite/servlet/product-list" method="post">
			<input type="hidden" name="mangaId" value="00000037">
			<img src="/ShoppingSite/img/item/horimiya003.jpg" class="home-img">
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
			<input type="hidden" name="mangaId" value="00000018">
			<img src="/ShoppingSite/img/item/fruitbasket005.jpg" class="home-img">
		</form>
	</li>
	<li>
		<form action="/ShoppingSite/servlet/product-list" method="post">
			<input type="hidden" name="mangaId" value="00000020">
			<img src="/ShoppingSite/img/item/natsume's-book-of-friends003.jpg" class="home-img">
		</form>
	</li>
	<li>
		<form action="/ShoppingSite/servlet/product-list" method="post">
			<input type="hidden" name="mangaId" value="00000019">
			<img src="/ShoppingSite/img/item/chihayahuru003.jpg" class="home-img">
		</form>
	</li>
	<li>
		<form action="/ShoppingSite/servlet/product-list" method="post">
			<input type="hidden" name="mangaId" value="00000017">
			<img src="/ShoppingSite/img/item/kimi-ni-todoke005.jpg" class="home-img">
		</form>
	</li>
	<li>
		<form action="/ShoppingSite/servlet/product-list" method="post">
			<input type="hidden" name="mangaId" value="00000023">
			<img src="/ShoppingSite/img/item/wotakoi003.jpg" class="home-img">
		</form>
	</li>
</ul>

</main>

<%@ include file="footer.jsp" %>

<%@ include file="tag-footer.jsp" %>