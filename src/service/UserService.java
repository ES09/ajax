package service;

import java.util.Map;

public interface UserService {
	public int insertUser(Map<String,String> user);
	public boolean loginByIdPwd(Map<String,String> login);

}
