package jp.co.aforce.constant;

public class Constant {
	
	private Constant() {}
	
	// 項目名
	public static class ItemName{
		public static final String USER_ID = "ユーザID";
		public static final String PASSWARD = "パスワード";
		public static final String LAST_NAME = "名前_姓";
		public static final String FIRST_NAME = "名前_名";
		public static final String BIRTH_YEAR = "生年月日_年";
		public static final String BIRTH_MONTH = "生年月日_月";
		public static final String BIRTH_DAY = "生年月日_日";
		public static final String PHONE_NUMBER = "電話番号";
		public static final String MAIL＿ADDRESS = "メールアドレス";
		public static final String ADDRESS = "住所";
		
		public static final String MANGA_ID = "マンガID";
		public static final String TILTE = "タイトル";
		public static final String AUTHOR = "作者";
		public static final String PUBLISHER = "出版社";
		public static final String GENRE = "ジャンル";
		public static final String TOTAL_NUMBER = "総巻数";
		
		public static final String PRODUCT_ID = "商品ID";
		public static final String NUMBER = "巻数";
		public static final String PRICE = "値段";
		public static final String DESCRIPTION = "作品情報";
	}
	
	// メッセージ
	public static class Message{
		public static final String W_C0001 = "IDもしくは、パスワードが違います。";
		public static final String W_C0002 = "この{0}は既に使用されています。";
		public static final String W_C0003 = "パスワードが一致していません";
		public static final String W_C0004 = "'{0}'は既に追加されています。";
		public static final String W_C0005 = "この商品は既に追加されています。";
		public static final String E_W0001 = "会員情報登録に失敗しました。";
		public static final String E_W0002 = "会員情報更新に失敗しました。";
		public static final String E_W0003 = "会員情報削除に失敗しました。";
		public static final String E_W0004 = "マンガタイトル情報追加に失敗しました。";
		public static final String E_W0005 = "マンガタイトル情報更新に失敗しました。";
		public static final String E_W0006 = "マンガタイトル情報削除に失敗しました。";
		public static final String E_W0007 = "商品情報追加に失敗しました。";
		public static final String E_W0008 = "商品情報更新に失敗しました。";
		public static final String E_W0009 = "商品情報削除に失敗しました。";
		public static final String E_C0001 = "{0}は存在しません";
		public static final String I_W0001 = "マンガタイトル情報追加が完了しました。";
		public static final String I_W0002 = "ID'{0}' のマンガタイトル情報更新が完了しました。";
		public static final String I_W0003 = "ID'{0}' のマンガタイトル情報削除が完了しました。";
		public static final String I_W0004 = "商品情報追加が完了しました。";
		public static final String I_W0005 = "ID'{0}' の商品情報更新が完了しました。";
		public static final String I_W0006 = "ID'{0}' の商品情報削除が完了しました。";
		public static final String I_W0007 = "会員情報更新が完了しました。";
		public static final String I_W0008 = "会員情報削除が完了しました。";
	}
	
	// 支払方法
	public static class Payment{
		public static final String COD = "代引き";
		public static final String CARD = "クレジットカード";
		public static final String CVS = "コンビニ決済";
		public static final String BANK = "銀行振込";
	}
	
	// 配送時間
	public static class DeliveryTime{
		public static final String T0 = "指定なし";
		public static final String T9_12 = "午前中(9時～12時)";
		public static final String T13_15 = "13時～15時";
		public static final String T15_17 = "15時～17時";
		public static final String T17_19 = "17時～19時";
		public static final String T19_21 = "19時～21時";
	}
}
