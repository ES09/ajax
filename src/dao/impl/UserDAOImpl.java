package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import dao.UserDAO;
import db.DBCon;

public class UserDAOImpl implements UserDAO {

	private static final String INSERT_USER = "insert into user_info(ui_num, ui_name, ui_id, ui_pwd, ui_email)"
							+ " values(seq_ui_num.nextval,?,?,?,?) ";
	private static final String LOGIN_BY_IDPWD = "select ui_id, ui_pwd from user_info where ui_id=?";
	
	
	@Override
	public int insertUser(Map<String, String> user) {
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(INSERT_USER);
			ps.setString(1, user.get("uiName"));
			ps.setString(2, user.get("uiId"));
			ps.setString(3, user.get("uiPwd"));
			ps.setString(4, user.get("uiEmail"));
			return ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCon.close();
		}
		return 0;
	}

	@Override
	public int updateUser(Map<String, String> user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean loginByIdPwd(Map<String, String> login) {
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(LOGIN_BY_IDPWD);
			ps.setString(1, login.get("uiId"));
			ResultSet rs = ps.executeQuery();
			Map<String, String> user = new HashMap<>();
			while(rs.next()) {
				user.put("uiId", rs.getString("ui_id"));
				user.put("uiPwd", rs.getString("ui_pwd"));
			} 
			String loginId = login.get("uiId");
			String loginPwd = login.get("uiPwd");
			String DBId = user.get("uiId");
			String DBPwd = user.get("uiPwd");
			if(loginId.equals(DBId) && loginPwd.equals(DBPwd)) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void main(String[] args) {
		UserDAO udao = new UserDAOImpl();
		Map<String,String> login = new HashMap<>();
		login.put("uiId","ABC");
		login.put("uiPwd", "abc");
		System.out.println(udao.loginByIdPwd(login)); }

}
