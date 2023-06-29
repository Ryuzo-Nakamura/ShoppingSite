package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.bean.Item;

public class ItemDAO extends DAO{
	
	public int search01(String itemId) throws Exception {
		
		Connection con = getConnection();
		
		PreparedStatement st = con.prepareStatement("SELECT COUNT(*) FROM item_info WHERE ITEM_ID = ?");
		st.setString(1, itemId);
		ResultSet rs = st.executeQuery();
		rs.next();
		int count = rs.getInt("COUNT(*)");
		
		st.close();
		con.close();
		
		return count;
	}
	
public Item search02(String itemId) throws Exception {
		
		Connection con = getConnection();
		
		PreparedStatement st = con.prepareStatement("SELECT * FROM item_info WHERE ITEM_ID = ?");
		st.setString(1, itemId);
		ResultSet rs = st.executeQuery();
		rs.next();
		Item item = new Item();
		item.setItemId(rs.getString("ITEM_ID"));
		item.setUserId(rs.getString("USER_ID"));
		item.setProductId(rs.getString("PRODUCT_ID"));
		item.setAmount(rs.getInt("AMOUNT"));
		
		st.close();
		con.close();
		
		return item;
	}
	
	public List<Item> search03(String userId) throws Exception {
		List<Item> list = new ArrayList<>();
		
		Connection con = getConnection();
		
		PreparedStatement st = con.prepareStatement("SELECT * FROM item_info WHERE USER_ID = ?");
		st.setString(1, userId);
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			Item item = new Item();
			item.setItemId(rs.getString("ITEM_ID"));
			item.setUserId(rs.getString("USER_ID"));
			item.setProductId(rs.getString("PRODUCT_ID"));
			item.setAmount(rs.getInt("AMOUNT"));
			list.add(item);
		}
		
		st.close();
		con.close();
		
		return list;
	}
	
	public int insert01(Item item) throws Exception {
		
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("INSERT INTO item_info VALUES (?, ?, ?, ?)");
		st.setString(1, item.getItemId());
		st.setString(2, item.getUserId());
		st.setString(3, item.getProductId());
		st.setInt(4, item.getAmount());
		int line = st.executeUpdate();
	
		st.close();
		con.close();
		
		return line;
	}
	
	public int update01(Item item) throws Exception {
		
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("UPDATE item_info SET AMOUNT = ? WHERE ITEM_ID = ?");
		st.setInt(1, item.getAmount());
		st.setString(2, item.getItemId());
		int line = st.executeUpdate();
	
		st.close();
		con.close();
		
		return line;
	}
	
	public int delete01(String itemId) throws Exception{
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("DELETE FROM item_info WHERE ITEM_ID = ?");
		st.setString(1, itemId);
		int line = st.executeUpdate();
		
		st.close();
		con.close();
		
		return line;
	}
	
	public int delete02(String userId) throws Exception{
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("DELETE FROM item_info WHERE USER_ID = ?");
		st.setString(1, userId);
		int line = st.executeUpdate();
		
		st.close();
		con.close();
		
		return line;
	}
}
