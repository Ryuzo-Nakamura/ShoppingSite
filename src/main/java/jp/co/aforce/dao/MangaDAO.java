package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.bean.Manga;

public class MangaDAO extends DAO{
	
	public int search01(String title) throws Exception {
		
		Connection con = getConnection();
		
		PreparedStatement st = con.prepareStatement("SELECT COUNT(*) FROM manga_info WHERE TITLE = ?");
		st.setString(1, title);
		ResultSet rs = st.executeQuery();
		rs.next();
		int count = rs.getInt("COUNT(*)");
		
		st.close();
		con.close();
		
		return count;
	}
	
	public Manga search02(String title) throws Exception {
		Connection con = getConnection();
		
		PreparedStatement st = con.prepareStatement("SELECT * FROM manga_info WHERE TITLE = ?");
		st.setString(1, title);
		ResultSet rs = st.executeQuery();
		rs.next();
		
		Manga manga = new Manga();
		manga.setMangaId(rs.getString("MANGA_ID"));
		manga.setTitle(rs.getString("TITLE"));
		manga.setAuthor(rs.getString("AUTHOR"));
		manga.setPublisher(rs.getString("PUBLISHER"));
		manga.setGenre(rs.getString("GENRE"));
		manga.setTotalNumber(Integer.parseInt(rs.getString("TOTAL_NUMBER")));
		manga.setImgURL(rs.getString("IMG_URL"));
		
		return manga;
	}
	
	public Manga search03(String mangaId) throws Exception {
		Connection con = getConnection();
		
		PreparedStatement st = con.prepareStatement("SELECT * FROM manga_info WHERE MANGA_ID = ?");
		st.setString(1, mangaId);
		ResultSet rs = st.executeQuery();
		rs.next();
		
		Manga manga = new Manga();
		manga.setMangaId(rs.getString("MANGA_ID"));
		manga.setTitle(rs.getString("TITLE"));
		manga.setAuthor(rs.getString("AUTHOR"));
		manga.setPublisher(rs.getString("PUBLISHER"));
		manga.setGenre(rs.getString("GENRE"));
		manga.setTotalNumber(Integer.parseInt(rs.getString("TOTAL_NUMBER")));
		manga.setImgURL(rs.getString("IMG_URL"));
		
		return manga;
	}
	
	public List<Manga> search04(String title) throws Exception {
		List<Manga> list = new ArrayList<>();
		
		Connection con = getConnection();
		
		PreparedStatement st = con.prepareStatement("SELECT * FROM manga_info WHERE TITLE LIKE ?");
		st.setString(1, "%" + title + "%");
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			Manga manga = new Manga();
			manga.setMangaId(rs.getString("MANGA_ID"));
			manga.setTitle(rs.getString("TITLE"));
			manga.setAuthor(rs.getString("AUTHOR"));
			manga.setPublisher(rs.getString("PUBLISHER"));
			manga.setGenre(rs.getString("GENRE"));
			manga.setTotalNumber(Integer.parseInt(rs.getString("TOTAL_NUMBER")));
			manga.setImgURL(rs.getString("IMG_URL"));
			list.add(manga);
		}
		
		st.close();
		con.close();
		
		return list;
	}
	
	public List<Manga> search05(String publisher) throws Exception {
		List<Manga> list = new ArrayList<>();
		
		Connection con = getConnection();
		
		PreparedStatement st = con.prepareStatement("SELECT * FROM manga_info WHERE PUBLISHER LIKE ?");
		st.setString(1, "%" + publisher + "%");
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			Manga manga = new Manga();
			manga.setMangaId(rs.getString("MANGA_ID"));
			manga.setTitle(rs.getString("TITLE"));
			manga.setAuthor(rs.getString("AUTHOR"));
			manga.setPublisher(rs.getString("PUBLISHER"));
			manga.setGenre(rs.getString("GENRE"));
			manga.setTotalNumber(Integer.parseInt(rs.getString("TOTAL_NUMBER")));
			manga.setImgURL(rs.getString("IMG_URL"));
			list.add(manga);
		}
		
		st.close();
		con.close();
		
		return list;
	}
	
	public List<Manga> search06(String author) throws Exception {
		List<Manga> list = new ArrayList<>();
		
		Connection con = getConnection();
		
		PreparedStatement st = con.prepareStatement("SELECT * FROM manga_info WHERE AUTHOR LIKE ?");
		st.setString(1, "%" + author + "%");
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			Manga manga = new Manga();
			manga.setMangaId(rs.getString("MANGA_ID"));
			manga.setTitle(rs.getString("TITLE"));
			manga.setAuthor(rs.getString("AUTHOR"));
			manga.setPublisher(rs.getString("PUBLISHER"));
			manga.setGenre(rs.getString("GENRE"));
			manga.setTotalNumber(Integer.parseInt(rs.getString("TOTAL_NUMBER")));
			manga.setImgURL(rs.getString("IMG_URL"));
			list.add(manga);
		}
		
		st.close();
		con.close();
		
		return list;
	}
	
	public List<Manga> search07(String genre) throws Exception {
		List<Manga> list = new ArrayList<>();
		
		Connection con = getConnection();
		
		PreparedStatement st = con.prepareStatement("SELECT * FROM manga_info WHERE GENRE LIKE ?");
		st.setString(1, "%" + genre + "%");
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			Manga manga = new Manga();
			manga.setMangaId(rs.getString("MANGA_ID"));
			manga.setTitle(rs.getString("TITLE"));
			manga.setAuthor(rs.getString("AUTHOR"));
			manga.setPublisher(rs.getString("PUBLISHER"));
			manga.setGenre(rs.getString("GENRE"));
			manga.setTotalNumber(Integer.parseInt(rs.getString("TOTAL_NUMBER")));
			manga.setImgURL(rs.getString("IMG_URL"));
			list.add(manga);
		}
		
		st.close();
		con.close();
		
		return list;
	}
	
	public int insert01(Manga manga) throws Exception{
		
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("INSERT INTO manga_info VALUES (?, ?, ?, ?, ?, ?, null)");
		st.setString(1, manga.getMangaId());
		st.setString(2, manga.getTitle());
		st.setString(3, manga.getAuthorString());
		st.setString(4, manga.getPublisher());
		st.setString(5, manga.getGenreString());
		st.setInt(6, manga.getTotalNumber());
		int line = st.executeUpdate();

		st.close();
		con.close();
		
		return line;
	}
	
	public int update01(Manga manga) throws Exception {

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("UPDATE manga_info SET "
				+ "TITLE = ?, AUTHOR = ?, PUBLISHER = ?, GENRE = ? "
				+ "WHERE MANGA_ID = ?");
		st.setString(1, manga.getTitle());
		st.setString(2, manga.getAuthorString());
		st.setString(3, manga.getPublisher());
		st.setString(4, manga.getGenreString());
		st.setString(5, manga.getMangaId());
		int line = st.executeUpdate();

		st.close();
		con.close();
		
		return line;
	}
	
	public int update02(Manga manga) throws Exception {
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("UPDATE manga_info SET "
				+ "TOTAL_NUMBER = ? WHERE MANGA_ID = ?");
		st.setInt(1, manga.getTotalNumber());
		st.setString(2, manga.getMangaId());
		int line = st.executeUpdate();

		st.close();
		con.close();
		
		return line;
	}
	
	public int update03(Manga manga) throws Exception {
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("UPDATE manga_info SET "
				+ "IMG_URL = ? WHERE MANGA_ID = ?");
		st.setString(1, manga.getImgURL());
		st.setString(2, manga.getMangaId());
		int line = st.executeUpdate();

		st.close();
		con.close();
		
		return line;
	}
	
	public int update04(String mangaId, String imgURL) throws Exception {
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("UPDATE manga_info SET "
				+ "IMG_URL = ? WHERE MANGA_ID = ?");
		st.setString(1, imgURL);
		st.setString(2, mangaId);
		int line = st.executeUpdate();

		st.close();
		con.close();
		
		return line;
	}
	
	public int delete01(String mangaId) throws Exception {
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("DELETE FROM manga_info WHERE MANGA_ID = ?");
		st.setString(1, mangaId);
		int line = st.executeUpdate();
		
		st.close();
		con.close();
		
		return line;
	}
	
	public int lastID01() throws Exception {
		
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("SELECT * FROM manga_info");
		ResultSet rs = st.executeQuery();
		
		int lastMangaID = 0;
		while(rs.next()) {
			lastMangaID = Integer.parseInt(rs.getString("MANGA_ID"));
		}
		
		st.close();
		con.close();
		
		return lastMangaID;
	}
}
