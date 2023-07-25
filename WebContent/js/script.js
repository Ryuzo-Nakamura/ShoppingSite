'use strict';

/*=========================ホーム画面処理=================================== */

// eachTextAnimeにappeartextというクラス名をつける定義
function EachTextAnimeControl() {
	$('.eachTextAnime').each(function() {
		let elemPos = $(this).offset().top - 50;
		let scroll = $(window).scrollTop();
		let windowHeight = $(window).height();
		if(scroll >= elemPos - windowHeight) {
			$(this).addClass("appeartext");
		}else {S
			$(this).removeClass("appeartext");
		}
	});
}

//スクロールした際の動きを関数でまとめる
function PageTopAnime() {
	if($('#page-top')[0] != null){
		var scroll = $(window).scrollTop(); //スクロール値を取得
		if (scroll >= 200){//200pxスクロールしたら
			$('#page-top').removeClass('DownMove');		// DownMoveというクラス名を除去して
			$('#page-top').addClass('UpMove');			// UpMoveというクラス名を追加して出現
		}else{//それ以外は
			if($('#page-top').hasClass('UpMove')){//UpMoveというクラス名が既に付与されていたら
				$('#page-top').removeClass('UpMove');	//  UpMoveというクラス名を除去し
				$('#page-top').addClass('DownMove');	// DownMoveというクラス名を追加して非表示
			}
		}
		
		var wH = window.innerHeight; //画面の高さを取得
		var footerPos =  $('#footer').offset().top; //footerの位置を取得
		if(scroll+wH >= (footerPos+10)) {
			var pos = (scroll+wH) - footerPos+10 //スクロールの値＋画面の高さからfooterの位置＋10pxを引いた場所を取得し
			$('#page-top').css('bottom',pos);	//#page-topに上記の値をCSSのbottomに直接指定してフッター手前で止まるようにする
		}else{//それ以外は
			if($('#page-top').hasClass('UpMove')){//UpMoveというクラス名がついていたら
				$('#page-top').css('bottom','10px');// 下から10pxの位置にページリンクを指定
			}
		}
	}
}

$(window).scroll(function () {
	PageTopAnime();/* スクロールした際の動きの関数を呼ぶ*/
});

$(window).on('load', function(){
	// テキストが1文字ずつ出現
		let element = $(".eachTextAnime");
		element.each(function() {
			let text = $(this).text();
			let textbox = "";
			text.split('').forEach(function(t,i) {
				if(t !== " ") {
					if(i < 10) {
						textbox += '<span style="animation-delay:.' + i + 's;">' + t + '</span>';
					}else {
						let n = i/10;
						textbox += '<span style="animation-delay:.' + n + 's;">' + t + '</span>';
					}
				}else {
					textbox += t;
				}
			});
			$(this).html(textbox);
		});
		EachTextAnimeControl();
		PageTopAnime();/* スクロールした際の動きの関数を呼ぶ*/
});

// #page-topをクリックした際の設定
$('#page-top').click(function () {
    $('body,html').animate({
        scrollTop: 0//ページトップまでスクロール
    }, 500);//ページトップスクロールの速さ。数字が大きいほど遅くなる
    return false;//リンク自体の無効化
});

$('.top-slider').slick({
		autoplay: true,//自動的に動き出すか。初期値はfalse。
		infinite: true,//スライドをループさせるかどうか。初期値はtrue。
		speed: 500,//スライドのスピード。初期値は300。
		slidesToShow: 3,//スライドを画面に3枚見せる
		slidesToScroll: 1,//1回のスクロールで1枚の写真を移動して見せる
		prevArrow: '<div class="slick-prev"></div>',//矢印部分PreviewのHTMLを変更
		nextArrow: '<div class="slick-next"></div>',//矢印部分NextのHTMLを変更
		centerMode: true,//要素を中央ぞろえにする
		variableWidth: true,//幅の違う画像の高さを揃えて表示
		dots: true,//下部ドットナビゲーションの表示
});
	
$('.manga-slider').slick({
		autoplay: false,//自動的に動き出すか。初期値はfalse。
		infinite: true,//スライドをループさせるかどうか。初期値はtrue。
		slidesToShow: 8,//スライドを画面に3枚見せる
		slidesToScroll: 8,//1回のスクロールで3枚の写真を移動して見せる
		prevArrow: '<div class="slick-prev"></div>',//矢印部分PreviewのHTMLを変更
		nextArrow: '<div class="slick-next"></div>',//矢印部分NextのHTMLを変更
		dots: true,//下部ドットナビゲーションの表示
		responsive: [
			{
			breakpoint: 769,//モニターの横幅が769px以下の見せ方
			settings: {
				slidesToShow: 2,//スライドを画面に2枚見せる
				slidesToScroll: 2,//1回のスクロールで2枚の写真を移動して見せる
			}
		},
		{
			breakpoint: 426,//モニターの横幅が426px以下の見せ方
			settings: {
				slidesToShow: 1,//スライドを画面に1枚見せる
				slidesToScroll: 1,//1回のスクロールで1枚の写真を移動して見せる
			}
		}
	]
	});
	
Array.from(document.getElementsByClassName('home-img'))
	.forEach(function(target){
		target.addEventListener('click',e=>{
    		e.target.closest('form').submit();
    	});
});

/*================会員情報画面クリックイベント=================*/

// 会員情報変更/削除
$('#update-user-info').on('click', function(){
	// 表示画面変更
	$('.user-info').show();
	$('.user-id').hide();
	$('.user-password').hide();
	
	// 選択中のメニューを強調
	$('#update-user-info p').css({
		'text-decoration': 'underline',
		color: '#ef2346'
	});
	$('#update-user-id p').css({
		'text-decoration': 'none',
		color: 'black'
	});
	$('#update-user-password p').css({
		'text-decoration': 'none',
		color: 'black'
	});
});

// ユーザID変更
$('#update-user-id').on('click', function(){
	// 表示画面変更
	$('.user-info').hide();
	$('.user-id').show();
	$('.user-password').hide();
	
	// 選択中のメニューを強調
	$('#update-user-info p').css({
		'text-decoration': 'none',
		color: 'black'
	});
	$('#update-user-id p').css({
		'text-decoration': 'underline',
		color: '#ef2346'
	});
	$('#update-user-password p').css({
		'text-decoration': 'none',
		color: 'black'
	});
});

// パスワード変更
$('#update-user-password').on('click', function(){
	// 表示画面変更
	$('.user-info').hide();
	$('.user-id').hide();
	$('.user-password').show();
	
	// 選択中のメニューを強調
	$('#update-user-info p').css({
		'text-decoration': 'none',
		color: 'black'
	});
	$('#update-user-id p').css({
		'text-decoration': 'none',
		color: 'black'
	});
	$('#update-user-password p').css({
		'text-decoration': 'underline',
		color: '#ef2346'
	});
});

/*=================マンガ一覧クリックイベント==================*/

Array.from(document.getElementsByClassName('search-manga-list'))
	.forEach(function(target){
		target.addEventListener('click',e=>{
    		e.target.closest('form').submit();
    	});
});


Array.from(document.getElementsByClassName('management-manga-list'))
	.forEach(function(target){
		target.addEventListener('click',e=>{
    		e.target.closest('form').submit();
    	});
});



/*========================配達先住所自動入力=======================*/

const inputAddress = document.getElementById('inputAddress');
if($('#inputAddress') != null){
	$('#inputAddress').on('click', function(){
		$('#deliveryAddress').val($('#userAddress').val());
	
	});
}

/*========================ソートイベント===========================*/
$('#user-manga-sort').change(function(){
	$('#manga-sort-form').submit();
});

$('#user-product-sort').change(function(){
	$('#product-sort-form').submit();
});

/*========商品追加/編集における画像選択時のイベント==============*/

$('#addImg').on('change',function(e){
	let fileReader = new FileReader();
	fileReader.onload = function(e){
		$('.imgPreview').show();
		$('#addImgPreview').attr('src', e.target.result);
	}
	fileReader.readAsDataURL(e.target.files[0]);
});

$('#updateImg').on('change',function(e){
	let fileReader = new FileReader();
	fileReader.onload = function(e){
		$('.imgPreview').show();
		$('#updateImgPreview').attr('src', e.target.result);
		$('#updateImgCaption').text($('#updateImg').prop('files')[0].name);
	}
	fileReader.readAsDataURL(e.target.files[0]);
});

/*=================確認画面ポップアップ======================*/

// 会員情報登録における確認画面ポップアップ表示
$('#user-regist-btn').on('click', function(){
	if(!$('#user-regist-form')[0].reportValidity()) {
    		 return false;
   	}
   	$('#user-regist-conf').show();
   	$('body').css('overflow-y', 'hidden');
});

// 会員情報更新における確認画面ポップアップ表示
$('#user-update-btn').on('click', function(){
	if(!$('#user-info-form')[0].reportValidity()) {
    		 return false;
   	}
   	$('#user-update-conf').show();
   	$('body').css('overflow-y', 'hidden');
});

// 会員情報削除における確認画面ポップアップ表示
$('#user-delete-btn').on('click', function(){
	$('#user-delete-conf').show();
   	$('body').css('overflow-y', 'hidden');
});

// ユーザID更新における確認画面ポップアップ表示
$('#userId-update-btn').on('click', function(){
	if(!$('#user-id-form')[0].reportValidity()) {
    		 return false;
   	}
   	$('#userId-update-conf').show();
   	$('body').css('overflow-y', 'hidden');
});

// パスワード更新における確認画面ポップアップ表示
$('#password-update-btn').on('click', function(){
	if(!$('#user-password-form')[0].reportValidity()) {
    		 return false;
   	}
   	$('#password-update-conf').show();
   	$('body').css('overflow-y', 'hidden');
});

// ログアウトにおける確認画面ポップアップ表示
$('#user-logout-btn').on('click', function(){
	$('#user-logout-conf').show();
   	$('body').css('overflow-y', 'hidden');
});

// 商品購入における確認画面ポップアップ表示
$('#user-purchase-btn').on('click', function(){
	if(!$('#user-purchase-form')[0].reportValidity()) {
    		 return false;
   	}
   	$('#user-purchase-conf').show();
   	$('body').css('overflow-y', 'hidden');
});

// マンガ情報追加における確認画面ポップアップ表示
$('#manga-add-btn').on('click', function(){
	if(!$('#manga-add-form')[0].reportValidity()) {
    		 return false;
   	}
   	$('#manga-add-conf').show();
   	$('body').css('overflow-y', 'hidden');
});

// マンガ情報更新における確認画面ポップアップ表示
$('#manga-update-btn').on('click', function(){
	if(!$('#manga-info-form')[0].reportValidity()) {
    		 return false;
   	}
   	$('#manga-update-conf').show();
   	$('body').css('overflow-y', 'hidden');
});

// マンガ情報削除における確認画面ポップアップ表示
$('#manga-delete-btn').on('click', function(){
	$('#manga-delete-conf').show();
   	$('body').css('overflow-y', 'hidden');
});

// 商品情報追加における確認画面ポップアップ表示
$('#product-add-btn').on('click', function(){
	if(!$('#product-add-form')[0].reportValidity()) {
    		 return false;
   	}
   	$('#product-add-conf').show();
   	$('body').css('overflow-y', 'hidden');
});

// 商品情報更新における確認画面ポップアップ表示
$('#product-update-btn').on('click', function(){
	if(!$('#product-info-form')[0].reportValidity()) {
    		 return false;
   	}
   	$('#product-update-conf').show();
   	$('body').css('overflow-y', 'hidden');
});

// 商品情報削除における確認画面ポップアップ表示
$('#product-delete-btn').on('click', function(){
	$('#product-delete-conf').show();
   	$('body').css('overflow-y', 'hidden');
});

/* =================確認画面ボタン押下時処理===================*/

// 確認画面「キャンセル」押下時
$('.cancel-btn').on('click', function(){
	$(".conf-popup").hide(); // 確認ボックスを消す
  	$('body').css('overflow-y', 'auto');
});

// 確認画面(会員情報登録)「OK(登録)」押下時
$('#user-regist-conf .ok-btn').on('click', function(){
	$(".conf-popup").hide(); // 確認ボックスを消す
  	$('body').css('overflow-y', 'auto');
  	$('#user-regist-form').submit();
});

// 確認画面(会員情報管理)「OK(更新)」押下時
$('#user-update-conf .ok-btn').on('click', function(){
	$(".conf-popup").hide(); // 確認ボックスを消す
  	$('body').css('overflow-y', 'auto');
  	$('#user-info-form').attr('action', '/ShoppingSite/servlet/user-update')
  	$('#user-info-form').submit();
});

// 確認画面(会員情報管理)「OK(削除)」押下時
$('#user-delete-conf .ok-btn').on('click', function(){
	$(".conf-popup").hide(); // 確認ボックスを消す
  	$('body').css('overflow-y', 'auto');
  	$('#user-info-form').attr('action', '/ShoppingSite/servlet/user-delete')
  	$('#user-info-form').submit();
});

// 確認画面(ユーザID変更)「OK(更新)」押下時
$('#userId-update-conf .ok-btn').on('click', function(){
	$(".conf-popup").hide(); // 確認ボックスを消す
  	$('body').css('overflow-y', 'auto');
  	$('#user-id-form').submit();
});

// 確認画面(パスワード変更)「OK(更新)」押下時
$('#password-update-conf .ok-btn').on('click', function(){
	$(".conf-popup").hide(); // 確認ボックスを消す
  	$('body').css('overflow-y', 'auto');
  	$('#user-password-form').submit();
});

// 確認画面(ログアウト)「OK(ログアウト)」押下時
$('#user-logout-conf .ok-btn').on('click', function(){
	$(".conf-popup").hide(); // 確認ボックスを消す
  	$('body').css('overflow-y', 'auto');
  	$('#user-info-form').attr('action', '/ShoppingSite/servlet/user-logout')
  	$('#user-info-form').submit();
});

// 確認画面(商品購入)「OK(購入)」押下時
$('#user-purchase-conf .ok-btn').on('click', function(){
	$(".conf-popup").hide(); // 確認ボックスを消す
  	$('body').css('overflow-y', 'auto');
  	$('#user-purchase-form').submit();
});

// 確認画面(マンガ情報追加)「OK(追加)」押下時
$('#manga-add-conf .ok-btn').on('click', function(){
	$(".conf-popup").hide(); // 確認ボックスを消す
  	$('body').css('overflow-y', 'auto');
  	$('#manga-add-form').submit();
});

// 確認画面(マンガ情報管理)「OK(更新)」押下時
$('#manga-update-conf .ok-btn').on('click', function(){
	$(".conf-popup").hide(); // 確認ボックスを消す
  	$('body').css('overflow-y', 'auto');
  	$('#manga-info-form').attr('action', '/ShoppingSite/servlet/manga-update')
  	$('#manga-info-form').submit();
});

// 確認画面(マンガ情報管理)「OK(削除)」押下時
$('#manga-delete-conf .ok-btn').on('click', function(){
	$(".conf-popup").hide(); // 確認ボックスを消す
  	$('body').css('overflow-y', 'auto');
  	$('#manga-info-form').attr('action', '/ShoppingSite/servlet/manga-delete')
  	$('#manga-info-form').submit();
});

// 確認画面(商品情報追加)「OK(追加)」押下時
$('#product-add-conf .ok-btn').on('click', function(){
	$(".conf-popup").hide(); // 確認ボックスを消す
  	$('body').css('overflow-y', 'auto');
  	$('#product-add-form').submit();
});

// 確認画面(商品情報管理)「OK(更新)」押下時
$('#product-update-conf .ok-btn').on('click', function(){
	$(".conf-popup").hide(); // 確認ボックスを消す
  	$('body').css('overflow-y', 'auto');
  	$('#product-info-form').attr('action', '/ShoppingSite/servlet/product-update')
  	$('#product-info-form').submit();
});

// 確認画面(商品情報管理)「OK(削除)」押下時
$('#product-delete-conf .ok-btn').on('click', function(){
	$(".conf-popup").hide(); // 確認ボックスを消す
  	$('body').css('overflow-y', 'auto');
  	$('#product-info-form').attr('action', '/ShoppingSite/servlet/product-delete')
  	$('#product-info-form').submit();
});