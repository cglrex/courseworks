package indi.mfirstp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import indi.mfirstp.vo.User;

public class UserDAOImpI implements IUserDAO {
	private Connection conn = null;
	private PreparedStatement pst = null;

	public UserDAOImpI(Connection conn) {
		this.conn = conn;
	}

	@Override

	public boolean findLogin(User user) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			String sql = "select name from user where name = ? and password = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, user.getEmail());
			pst.setString(2, user.getPassword());
			ResultSet rSet = pst.executeQuery();
			if (rSet.next()) {
				user.setEmail(rSet.getString(1));
				flag = true;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (Exception e) {
					throw e;
				}
			}
		}
		return flag;
	}

	@Override
	public boolean checkUsername(User user) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;

		try {
			String sql = "select name from user where name= ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, user.getEmail());
			ResultSet rSet = pst.executeQuery();
			if (rSet.next()) {
				user.setEmail(rSet.getString(1));
				flag = true;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (Exception e) {
					throw e;
				}
			}
		}
		return flag;
	}

	@Override
	public void sighupUser(User user) throws Exception {
		// TODO Auto-generated method stub
		try {
			String sql = "insert into user(userid,name,password) values(null,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, user.getEmail());
			pst.setString(2, user.getPassword());
			pst.executeUpdate();
		}catch (Exception e) {
			throw e;
		}finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (Exception e) {
					throw e;
				}
			}
		}
	}
	
}
