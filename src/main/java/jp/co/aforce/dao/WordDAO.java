package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jp.co.aforce.bean.Word;

public class WordDAO extends DAO{

	public int search01(String word, int type) throws Exception {

		Connection con = getConnection();

		PreparedStatement st =
				con.prepareStatement("SELECT COUNT(*) FROM word_info "
						+ "WHERE WORD = ? AND WORD_TYPE = ?");
		st.setString(1, word);
		st.setInt(2, type);
		ResultSet rs = st.executeQuery();
		rs.next();
		int count = rs.getInt("COUNT(*)");

		st.close();
		con.close();

		return count;
	}
	
	public int search02(int type) throws Exception {

		Connection con = getConnection();

		PreparedStatement st =
				con.prepareStatement("SELECT COUNT(*) FROM word_info "
						+ "WHERE WORD_TYPE = ?");
		st.setInt(1, type);
		ResultSet rs = st.executeQuery();
		rs.next();
		int count = rs.getInt("COUNT(*)");

		st.close();
		con.close();

		return count;
	}
	
	
	public int insert01(Word word) throws Exception {

		Connection con = getConnection();

		PreparedStatement st =
				con.prepareStatement("INSERT INTO word_info VALUES(?,?,?)");
		st.setString(1, word.getWordId());
		st.setString(2, word.getWord());
		st.setInt(3, word.getWordType());
		int line = st.executeUpdate();

		st.close();
		con.close();

		return line;
	}
}
