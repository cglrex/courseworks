package indi.mfirstp.dbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	private static final String DBDRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/mfirstp?useSSL=false";
	private static final String DBUSER = "root";
	private static final String DBPASSWORD = "root";
	private Connection conn = null;

	public DatabaseConnection() throws Exception {
		try {
			Class.forName(DBDRIVER);
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
		} catch (Exception exception) {
			throw exception;
		} finally {

		}
	}

	public Connection getConnection() {
		return conn;
	}

	public void close() throws Exception {
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				throw e;
			}
		}
	}
}
