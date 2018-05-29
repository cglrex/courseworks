package testJDBCc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.Statement;

public class dbConnector {
	
	public static final String URL = "jdbc:mysql://localhost:3306/mfirstp";
    public static final String USER = "root";
    public static final String PASSWORD = "root";
    public static Connection conn = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
            try {
                conn = (Connection) DriverManager.getConnection(URL,USER,PASSWORD);
                System.out.print("数据库连接成功！");
                conn.close();
            } catch (SQLException e){
                System.out.println("数据库连接失败!");
                e.printStackTrace();
            }
            
	}

}
