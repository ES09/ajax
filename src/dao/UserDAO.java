package dao;

import java.util.Map;

public interface UserDAO {
	public int insertUser(Map<String,String> user);
	public int updateUser(Map<String,String> user);
	public boolean loginByIdPwd(Map<String,String> login);
	
	public Map<String,String> selectUserByUiId(String uiId,String uiPwd);
	
}
