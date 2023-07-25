package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.bean.Product;

public class ProductDAO extends DAO{
	
	public int search01(String productId) throws Exception {
		
		Connection con = getConnection();
		
		PreparedStatement st = con.prepareStatement("SELECT COUNT(*) FROM product_info WHERE PRODUCT_ID = ?");
		st.setString(1, productId);
		ResultSet rs = st.executeQuery();
		rs.next();
		int count = rs.getInt("COUNT(*)");
		
		st.close();
		con.close();
		
		return count;
	}
	
public Product search02(String productId) throws Exception {
		
		Connection con = getConnection();
		
		PreparedStatement st = con.prepareStatement("SELECT * FROM product_info WHERE PRODUCT_ID = ?");
		st.setString(1, productId);
		ResultSet rs = st.executeQuery();
		rs.next();
		Product product = new Product();
		product.setProductId(rs.getString("PRODUCT_ID"));
		product.setMangaId(rs.getString("MANGA_ID"));
		product.setNumber(rs.getInt("NUMBER"));
		product.setPrice(rs.getInt("PRICE"));
		product.setDescription(rs.getString("DESCRIPTION"));
		product.setImgURL(rs.getString("IMG_URL"));
		
		st.close();
		con.close();
		
		return product;
	}
	
	public List<Product> search03(String mangaId) throws Exception {
		List<Product> list = new ArrayList<>();
		
		Connection con = getConnection();
		
		PreparedStatement st = con.prepareStatement("SELECT * FROM product_info WHERE MANGA_ID = ?");
		st.setString(1, mangaId);
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			Product product = new Product();
			product.setProductId(rs.getString("PRODUCT_ID"));
			product.setMangaId(rs.getString("MANGA_ID"));
			product.setNumber(rs.getInt("NUMBER"));
			product.setPrice(rs.getInt("PRICE"));
			product.setDescription(rs.getString("DESCRIPTION"));
			product.setImgURL(rs.getString("IMG_URL"));
			list.add(product);
		}
		
		st.close();
		con.close();
		
		return list;
	}
	
	public String search04(String mangaId) throws Exception {
		Connection con = getConnection();
		
		PreparedStatement st = con.prepareStatement("SELECT IMG_URL "
				+ "FROM product_info t1 "
				+ "LEFT OUTER JOIN (SELECT MANGA_ID, MAX(NUMBER) AS 'MAX' FROM product_info GROUP BY MANGA_ID) t2 "
				+ "ON t1.MANGA_ID = t2.MANGA_ID "
				+ "WHERE t1.NUMBER = t2.MAX "
				+ "AND t1.MANGA_ID = ?");
		st.setString(1, mangaId);
		ResultSet rs = st.executeQuery();
		
		rs.next();
		
		String imgURL = rs.getString("IMG_URL");
		
		st.close();
		con.close();
		
		return imgURL;
	}
	
	public int search05(String mangaId, int number) throws Exception {
		Connection con = getConnection();
		
		PreparedStatement st = con.prepareStatement("SELECT COUNT(*) "
				+ "FROM product_info "
				+ "WHERE MANGA_ID = ? "
				+ "AND NUMBER = ?");
		st.setString(1, mangaId);
		st.setInt(2, number);
		ResultSet rs = st.executeQuery();
		
		rs.next();
		
		int count = rs.getInt("COUNT(*)");
		
		st.close();
		con.close();
		
		return count;
	}
	
	public int insert01(Product product) throws Exception{
		
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("INSERT INTO product_info VALUES (?, ?, ?, ?, ?, ?)");
		st.setString(1, product.getProductId());
		st.setString(2, product.getMangaId());
		st.setInt(3, product.getNumber());
		st.setInt(4, product.getPrice());
		st.setString(5, product.getDescription());
		st.setString(6, product.getImgURL());
		int line = st.executeUpdate();

		st.close();
		con.close();
		
		return line;
	}
	
	public int update01(Product product) throws Exception {

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("UPDATE product_info SET "
				+ "NUMBER = ?, PRICE = ?, DESCRIPTION = ?"
				+ "WHERE PRODUCT_ID = ?");
		st.setInt(1, product.getNumber());
		st.setInt(2, product.getPrice());
		st.setString(3, product.getDescription());
		st.setString(4, product.getProductId());
		int line = st.executeUpdate();

		st.close();
		con.close();
		
		return line;
	}
	
	public int update02(Product product, String productId) throws Exception {

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("UPDATE product_info SET "
				+ "PRODUCT_ID = ?"
				+ "WHERE PRODUCT_ID = ?");
		st.setString(1, productId);
		st.setString(2, product.getProductId());
		int line = st.executeUpdate();

		st.close();
		con.close();
		
		return line;
	}
	
	public int update03(Product product) throws Exception {

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("UPDATE product_info SET "
				+ "NUMBER = ?, PRICE = ?, DESCRIPTION = ?, IMG_URL = ?"
				+ "WHERE PRODUCT_ID = ?");
		st.setInt(1, product.getNumber());
		st.setInt(2, product.getPrice());
		st.setString(3, product.getDescription());
		st.setString(4, product.getImgURL());
		st.setString(5, product.getProductId());
		int line = st.executeUpdate();

		st.close();
		con.close();
		
		return line;
	}
	
	public int delete01(String productId) throws Exception {
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("DELETE FROM product_info WHERE PRODUCT_ID = ?");
		st.setString(1, productId);
		int line = st.executeUpdate();

		st.close();
		con.close();
		
		return line;
	}
	
	public int delete02(String mangaId) throws Exception {
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("DELETE FROM product_info WHERE MANGA_ID = ?");
		st.setString(1, mangaId);
		int line = st.executeUpdate();

		st.close();
		con.close();
		
		return line;
	}
}
