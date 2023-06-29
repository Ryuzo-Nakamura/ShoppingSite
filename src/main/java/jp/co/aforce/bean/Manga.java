package jp.co.aforce.bean;

public class Manga implements java.io.Serializable {
	
	private String mangaId;
	private String title;
	private String[] author;
	private String publisher;
	private String[] genre;
	private int totalNumber;
	private String imgURL;
	
	public String getMangaId() {
		return mangaId;
	}
	public void setMangaId(String mangaId) {
		this.mangaId = mangaId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String[] getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author.split("[ ,　、]");
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String[] getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre.split("[ ,　、]");
	}
	public int getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}
	public void raiseTotalNumber() {
		this.totalNumber += 1;
	}
	public void reduceTotalNumber() {
		this.totalNumber -= 1;
	}
	
	public String getAuthorString() {
		String authors = "";
		for(String author : this.author) {
			authors += author + ",";
		}
		
		return authors.substring(0, authors.length()-1);
	}
	
	public String getGenreString() {
		String genres = "";
		for(String genre : this.genre) {
			genres += genre + ",";
		}
		
		return genres.substring(0, genres.length()-1);
	}
	public String getImgURL() {
		return imgURL;
	}
	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
}
