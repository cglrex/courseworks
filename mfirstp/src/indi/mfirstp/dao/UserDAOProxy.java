package indi.mfirstp.dao;

import indi.mfirstp.dbc.DatabaseConnection;
import indi.mfirstp.vo.User;

public class UserDAOProxy implements IUserDAO {
	private DatabaseConnection dbc = null;
	private IUserDAO dao = null;

	public UserDAOProxy() {
		try {
			dbc = new DatabaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		dao = new UserDAOImpI(dbc.getConnection());
	}

	@Override
	public boolean findLogin(User user) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			flag = dao.findLogin(user);
		} catch (Exception e) {
			throw e;
		} finally {
			dbc.close();
		}
		return flag;
	}

	@Override
	public boolean checkUsername(User user) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			flag = dao.checkUsername(user);
		} catch (Exception e) {
			throw e;
		} finally {
			dbc.close();
		}
		return flag;
	}

	@Override
	public void sighupUser(User user) throws Exception {
		// TODO Auto-generated method stub
		try {
			dao.sighupUser(user);
		} catch (Exception e) {
			throw e;
		} finally {
			dbc.close();
		}
	}
	
}
