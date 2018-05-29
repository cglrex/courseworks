package indi.mfirstp.dao;

import indi.mfirstp.vo.User;

public interface IUserDAO {
	public boolean findLogin(User user) throws Exception;
	
	public boolean checkUsername(User user) throws Exception;
	
	public void sighupUser(User user) throws Exception;
	
}