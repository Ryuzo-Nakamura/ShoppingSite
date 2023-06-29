package jp.co.aforce.bean;

public class Product implements java.io.Serializable {
	
	private String productId;
	private String mangaId;
	private int number;
	private int price;
	private String description;
	private String imgURL;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String itemId) {
		this.productId = itemId;
	}
	public String getMangaId() {
		return mangaId;
	}
	public void setMangaId(String mangaId) {
		this.mangaId = mangaId;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImgURL() {
		return imgURL;
	}
	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
}
