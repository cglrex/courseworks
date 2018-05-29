import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class memberModifier {
	private static InputCheck ic=new InputCheck();
	private static int rowID=0;
	private static int numb=0;
	private static ResultSet rs;
	private static List<Integer> listID = new ArrayList<Integer>();
	private static List<Integer> listNumb = new ArrayList<Integer>();
	//function of adding new member
	public static void addMember() throws ClassNotFoundException, SQLException{
		Connection connection = null;
		Statement statement = null;
		Class.forName("org.sqlite.JDBC");
		
		try{
			//connect database and create sql statement func
			connection = DriverManager.getConnection("jdbc:sqlite:src\\ChocoholicsAnonymous.db");
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			//create menu of Provider Modifier
			Scanner in = new Scanner(System.in);
			System.out.println("Input member name: ");
			String mName = in.nextLine();
			System.out.println("Input member address: ");
			String mAddress= in.nextLine();
			System.out.println("Input member city: ");
			String mCity = in.nextLine();
			System.out.println("Input member Zipcode ");
			String mZip = in.nextLine();
			System.out.println("Input member state: ");
			String mState = in.nextLine();
			System.out.println("Input member status: ");
			boolean mStatus = in.nextBoolean();
			
			statement.executeUpdate("UPDATE sqlite_sequence SET seq = 100000000 WHERE name='Member'");
			statement.executeUpdate("INSERT INTO Member (mem_name, mem_address, mem_city, mem_state, mem_zip, mem_status) VALUES ('"+mName+"', '"+mAddress+"', '"+mCity+"', '"+mState+"', '"+mZip+"', '"+mStatus+"')");
			
		}catch(SQLException e){
			System.err.println(e);
		}
		//disconnect database and dispose sql statement func
		statement.close();
		connection.close();
	}
	//function of editing member
	public static void editMember() throws ClassNotFoundException, SQLException{
		Connection connection = null;
		Statement statement = null;
		Class.forName("org.sqlite.JDBC");
		
		try{
			//connect database and create sql statement func
			connection = DriverManager.getConnection("jdbc:sqlite:src\\ChocoholicsAnonymous.db");
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			//Input provider info (name, address, city, zipcode, state)
			Scanner in2 = new Scanner(System.in);
			System.out.println("Select member to update member info");
			//Show Provider List for selection
			rs = statement.executeQuery("SELECT mem_id, mem_name FROM member");
			while (rs.next()) 
			{
				System.out.println(rs.getRow() + ". " + rs.getString("mem_name")+ " ("+rs.getInt("mem_id")+")");
				listID.add(rs.getRow()-1);
				listNumb.add(rs.getInt("mem_id"));
			} 
			rowID = ic.checkInput(in2.nextLine(), 1, listID.size());
			for(int i=0; i<listNumb.size(); i++)
			{
				if (rowID-1==listID.get(i))
				{
					numb = listNumb.get(i);
					break;
				}
			}
			System.out.println("Input the number to select the info to update:");
			//Display selected Provider info
			rs = statement.executeQuery("SELECT * FROM member WHERE mem_id = '"+numb+"'");
			
			System.out.println("1. Name: "+rs.getString("mem_name"));
			System.out.println("2. Address: "+rs.getString("mem_address"));
			System.out.println("3. City: "+rs.getString("mem_city"));
			System.out.println("4. Zipcode: "+rs.getString("mem_zip"));
			System.out.println("5. State: "+rs.getString("mem_state"));
			System.out.println("6. Membership: "+rs.getString("mem_status"));
			rowID = ic.checkInput(in2.nextLine(), 1, 6);
			switch(rowID){
				case 1:		
					System.out.println("Input new name: ");
					Scanner in3 = new Scanner(System.in);
					String newName = in3.nextLine();
					statement.executeUpdate("UPDATE Member SET mem_name = '"+newName+"' WHERE mem_id = '"+numb+"'");
					break;
				case 2:
					System.out.println("Input new address: ");
					Scanner in3a = new Scanner(System.in);
					String newAddress = in3a.nextLine();
					statement.executeUpdate("UPDATE Member SET mem_address = '"+newAddress+"' WHERE mem_id = '"+numb+"'");
					break;
				case 3:
					System.out.println("Input new city: ");
					Scanner in3b = new Scanner(System.in);
					String mCity = in3b.nextLine();
					statement.executeUpdate("UPDATE Member SET mem_city = '"+mCity+"' WHERE mem_id = '"+numb+"'");
					break;
				case 4:
					System.out.println("Input new ZIP: ");
					Scanner in3c = new Scanner(System.in);
					String mZip = in3c.nextLine();
					statement.executeUpdate("UPDATE Member SET mem_zip = '"+mZip+"' WHERE mem_id = '"+numb+"'");
					break;
				case 5:
					System.out.println("Input new state: ");
					Scanner in3d = new Scanner(System.in);
					String mState = in3d.nextLine();
					statement.executeUpdate("UPDATE Member SET mem_state = '"+mState+"' WHERE mem_id = '"+numb+"'");
					break;
				case 6:
					System.out.println("Input new state: ");
					Scanner in3e = new Scanner(System.in);
					boolean mStatus = in3e.nextBoolean();
					statement.executeUpdate("UPDATE Member SET mem_status = '"+mStatus+"' WHERE mem_id = '"+numb+"'");
					break;
			}
		}catch(SQLException e){
			System.err.println(e);
		}
		//disconnect database and dispose sql statement func
		statement.close();
		connection.close();
	}
	//function of deleting member
	public static void deleteMember() throws ClassNotFoundException, SQLException{
		Connection connection = null;
		Statement statement = null;
		Class.forName("org.sqlite.JDBC");
		
		try{
			//connect database and create sql statement func
			connection = DriverManager.getConnection("jdbc:sqlite:src\\ChocoholicsAnonymous.db");
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			
			Scanner in4 = new Scanner(System.in);
			System.out.println("Select member to delete the record");
			rs = statement.executeQuery("SELECT mem_id, mem_name FROM member");
			//Show Provider List for selection
			while (rs.next()) 
			{
				System.out.println(rs.getRow() + ". " + rs.getString("mem_name")+ " ("+rs.getInt("mem_id")+")");
				listID.add(rs.getRow()-1);
				listNumb.add(rs.getInt("mem_id"));
			} 
			rowID = ic.checkInput(in4.nextLine(), 1, listID.size());
			for(int i=0; i<listNumb.size(); i++)
			{
				if (rowID-1==listID.get(i))
				{
					numb = listNumb.get(i);
					break;
				}
			}
			statement.executeUpdate("DELETE FROM member WHERE mem_id = '"+numb+"'");			
			}catch(SQLException e){
				System.out.println(e);
			}
		//disconnect database and dispose sql statement func
		statement.close();
		connection.close();
	}	
	//function of displaying provider according to member number
	public static void displayMember() throws ClassNotFoundException, SQLException{
		Connection connection = null;
		Statement statement = null;
		Class.forName("org.sqlite.JDBC");
		
		try{
			//connect database and create sql statement func
			connection = DriverManager.getConnection("jdbc:sqlite:src\\ChocoholicsAnonymous.db");
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			
			Scanner in5 = new Scanner(System.in);
			System.out.println("Select member to display the record");
			//Show Provider List for selection
			rs = statement.executeQuery("SELECT mem_id, mem_name FROM member");
			while (rs.next()) 
			{
				System.out.println(rs.getRow() + ". " + rs.getString("mem_name")+ " ("+rs.getInt("mem_id")+")");
				listID.add(rs.getRow()-1);
				listNumb.add(rs.getInt("mem_id"));
			} 
			rowID = ic.checkInput(in5.nextLine(), 1, listID.size());
			for(int i=0; i<listNumb.size(); i++)
			{
				if (rowID-1==listID.get(i))
				{
					numb = listNumb.get(i);
					break;
				}
			}
			//Display selected Member info
			rs = statement.executeQuery("SELECT * FROM member WHERE mem_id = '"+numb+"'");
			  String mname = rs.getString("mem_name");
			  System.out.println("Member name: "+ mname);
			 
			  int mnumber = rs.getInt("mem_id");
			  System.out.println("Member number: "+ mnumber);
			  
			  String maddress = rs.getString("mem_address");
			  System.out.println("Member name: "+ maddress);
			  
			  String mcity = rs.getString("mem_city");
			  System.out.println("Member city: "+mcity);
			  
			  String mstate = rs.getString("mem_state");
			  System.out.println("Member state: "+mstate);
			  
			  String mzip = rs.getString("mem_zip");
			  System.out.println("Member state: "+mzip);
			  
			  boolean mstatus = rs.getBoolean("mem_status");
			  System.out.println("Member status: "+ mstatus + "\n");
			
			}catch(SQLException e){
				System.out.println(e);
			}
		//disconnect database and dispose sql statement func
		statement.close();
		connection.close();
	}
	//function of showing all exiting members
	public static void showAll() throws ClassNotFoundException, SQLException{
		Connection connection = null;
		Statement statement = null;
		Class.forName("org.sqlite.JDBC");
		
		try{
			//connect database and create sql statement func
			connection = DriverManager.getConnection("jdbc:sqlite:src\\ChocoholicsAnonymous.db");
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			rs = statement.executeQuery("SELECT * FROM member");
			
			while (rs.next()) {
				  String mname = rs.getString("mem_name");
				  System.out.println("Member name: "+ mname);
				 
				  int mnumber = rs.getInt("mem_id");
				  System.out.println("Member number: "+ mnumber);
				  
				  String maddress = rs.getString("mem_address");
				  System.out.println("Member address: "+ maddress);
				  
				  String mcity = rs.getString("mem_city");
				  System.out.println("Member city: "+mcity);
				  
				  String mstate = rs.getString("mem_state");
				  System.out.println("Member state: "+mstate);
				  
				  String mzip = rs.getString("mem_zip");
				  System.out.println("Member ZIP: "+mzip);
				  
				  String mstatus = rs.getString("mem_status");
				  System.out.println("Member status: "+ mstatus + "\n");
            }
			}catch(SQLException e){
				System.out.println(e);
			}
		//disconnect database and dispose sql statement func
		statement.close();
		connection.close();
	}
}

