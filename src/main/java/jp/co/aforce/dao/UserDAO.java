package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jp.co.aforce.bean.User;

public class UserDAO extends DAO{
	
	public int search01(String userId) throws Exception {
		
		Connection con = getConnection();
		
		PreparedStatement st = con.prepareStatement("SELECT COUNT(*) FROM user_info WHERE USER_ID = ?");
		st.setString(1, userId);
		ResultSet rs = st.executeQuery();
		rs.next();
		int count = rs.getInt("COUNT(*)");
		
		st.close();
		con.close();
		
		return count;
	}
	
	public User search02(String userId) throws Exception {
		Connection con = getConnection();
		
		PreparedStatement st = con.prepareStatement("SELECT * FROM user_info WHERE USER_ID = ?");
		st.setString(1, userId);
		ResultSet rs = st.executeQuery();
		rs.next();
		
		User user = new User();
		user.setUserId(rs.getString("USER_ID"));
		user.setPassward(rs.getString("PASSWORD"));
		user.setLastName(rs.getString("LAST_NAME"));
		user.setFirstName(rs.getString("FIRST_NAME"));
		user.setBirthYear(Integer.parseInt(rs.getString("BIRTH_YEAR")));
		user.setBirthMonth(Integer.parseInt(rs.getString("BIRTH_MONTH")));
		user.setBirthDay(Integer.parseInt(rs.getString("BIRTH_DAY")));
		user.setPhoneNumber(rs.getString("PHONE_NUMBER"));
		user.setMailAddress(rs.getString("MAIL_ADDRESS"));
		user.setAddress(rs.getString("ADDRESS"));
		
		return user;
	}
	
	public int search03(String phoneNumber) throws Exception {
		
		Connection con = getConnection();
		
		PreparedStatement st = con.prepareStatement(
				"SELECT COUNT(*) FROM user_info "
				+ "WHERE PHONE_NUMBER = ?");
		st.setString(1, phoneNumber);
		ResultSet rs = st.executeQuery();
		rs.next();
		int count = rs.getInt("COUNT(*)");
		
		st.close();
		con.close();
			
		return count;
	}
	
	public int search04(String mailAddress) throws Exception {
		
		Connection con = getConnection();
		
		PreparedStatement st = con.prepareStatement(
				"SELECT COUNT(*) FROM user_info "
				+ "WHERE MAIL_ADDRESS = ?");
		st.setString(1, mailAddress);
		ResultSet rs = st.executeQuery();
		rs.next();
		int count = rs.getInt("COUNT(*)");
		
		st.close();
		con.close();
			
		return count;
	}
	
public int search05(String userId, String phoneNumber) throws Exception {
		
		Connection con = getConnection();
		
		PreparedStatement st = con.prepareStatement(
				"SELECT COUNT(*) FROM user_info "
				+ "WHERE PHONE_NUMBER = ?"
				+ "AND USER_ID != ?");
		st.setString(1, phoneNumber);
		st.setString(2, userId);
		ResultSet rs = st.executeQuery();
		rs.next();
		int count = rs.getInt("COUNT(*)");
		
		st.close();
		con.close();
			
		return count;
	}

	public int search06(String userId, String mailAddress) throws Exception {
		
		Connection con = getConnection();
		
		PreparedStatement st = con.prepareStatement(
				"SELECT COUNT(*) FROM user_info "
				+ "WHERE MAIL_ADDRESS = ?"
				+ "AND USER_ID != ?");
		st.setString(1, mailAddress);
		st.setString(2, userId);
		ResultSet rs = st.executeQuery();
		rs.next();
		int count = rs.getInt("COUNT(*)");
		
		st.close();
		con.close();
			
		return count;
	} 
	public int insert01(User user) throws Exception{
		
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("INSERT INTO user_info VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		st.setString(1, user.getUserId());
		st.setString(2, user.getPassword());
		st.setString(3, user.getLastName());
		st.setString(4, user.getFirstName());
		st.setInt(5, user.getBirthYear());
		st.setInt(6, user.getBirthMonth());
		st.setInt(7, user.getBirthDay());
		st.setString(8, user.getPhoneNumber());
		st.setString(9, user.getMailAddress());
		st.setString(10, user.getAddress());
		int line = st.executeUpdate();

		st.close();
		con.close();
		
		return line;
	}
	
	public int update01(User user) throws Exception{
		
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("UPDATE user_info SET "
				+ "LAST_NAME = ?,"
				+ "FIRST_NAME = ?,"
				+ "BIRTH_YEAR = ?,"
				+ "BIRTH_MONTH = ?,"
				+ "BIRTH_DAY = ?,"
				+ "PHONE_NUMBER = ?,"
				+ "MAIL_ADDRESS = ?,"
				+ "ADDRESS = ? "
				+ "WHERE USER_ID = ?");
		st.setString(1, user.getLastName());
		st.setString(2, user.getFirstName());
		st.setInt(3, user.getBirthYear());
		st.setInt(4, user.getBirthMonth());
		st.setInt(5, user.getBirthDay());
		st.setString(6, user.getPhoneNumber());
		st.setString(7, user.getMailAddress());
		st.setString(8, user.getAddress());
		st.setString(9, user.getUserId());
		int line = st.executeUpdate();

		st.close();
		con.close();
		
		return line;
	}
	
	public int delete01(String userId) throws Exception {
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("DELETE FROM user_info WHERE USER_ID = ?");
		st.setString(1, userId);
		int line = st.executeUpdate();

		st.close();
		con.close();
		
		return line;
	}
}
