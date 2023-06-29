package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jp.co.aforce.bean.Admin;

public class AdminDAO extends DAO{
	
	public int search01(String adminId) throws Exception {
		
		Connection con = getConnection();
		
		PreparedStatement st = con.prepareStatement("SELECT COUNT(*) FROM admin_info WHERE ADMIN_ID = ?");
		st.setString(1, adminId);
		ResultSet rs = st.executeQuery();
		rs.next();
		int count = rs.getInt("COUNT(*)");
		
		st.close();
		con.close();
		
		return count;
	}
	
	public Admin search02(String adminId) throws Exception {
		Connection con = getConnection();
		
		PreparedStatement st = con.prepareStatement("SELECT * FROM admin_info WHERE ADMIN_ID = ?");
		st.setString(1, adminId);
		ResultSet rs = st.executeQuery();
		rs.next();
		
		Admin admin = new Admin();
		admin.setAdminId(rs.getString("ADMIN_ID"));
		admin.setPassword(rs.getString("PASSWORD"));
		
		return admin;
	}
}
