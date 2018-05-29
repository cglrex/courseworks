import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProviderModifier {
	private static InputCheck ic=new InputCheck();
	private static int rowID=0;
	private static int numb=0;
	private static ResultSet rs;
	private static List<Integer> listID = new ArrayList<Integer>();
	private static List<Integer> listNumb = new ArrayList<Integer>();
		//function of adding new provider
		public static void addProvider() throws ClassNotFoundException, SQLException{
			Connection connection = null;
			Statement statement = null;
			Class.forName("org.sqlite.JDBC");
			
			try{
				//connect database and create sql statement func
				connection = DriverManager.getConnection("jdbc:sqlite:src//ChocoholicsAnonymous.db");
				statement = connection.createStatement();
				statement.setQueryTimeout(10);
				//create menu of Provider Modifier
				Scanner in = new Scanner(System.in);
				System.out.println("Input provider name: ");
				String pName = in.nextLine();
				System.out.println("Input provider address: ");
				String pAddress= in.nextLine();
				System.out.println("Input provider city: ");
				String pCity = in.nextLine();
				System.out.println("Input provider Zipcode ");
				String pZip = in.nextLine();
				System.out.println("Input provider state: ");
				String pState = in.nextLine();
				
				statement.executeUpdate("UPDATE sqlite_sequence SET seq = 100000000 WHERE name='Provider'");
				statement.executeUpdate("INSERT INTO Provider (pro_name, pro_address, pro_city, pro_state, pro_zip) VALUES ('"+pName+"', '"+pAddress+"', '"+pCity+"', '"+pState+"', '"+pZip+"')");				
			
			}catch(SQLException e){
				System.err.println(e);
			}
			//disconnect database and dispose sql statement func
			statement.close();
			connection.close();
		}
		
		//function of editing provider
		public static void editProvider() throws ClassNotFoundException, SQLException{
			Connection connection = null;
			Statement statement = null;
			Class.forName("org.sqlite.JDBC");
			
			try{
				//connect database and create sql statement func
				connection = DriverManager.getConnection("jdbc:sqlite:src//ChocoholicsAnonymous.db");
				statement = connection.createStatement();
				statement.setQueryTimeout(10);
				 
				Scanner in2 = new Scanner(System.in);
				System.out.println("Select provider to update provider info");
				//Show Provider List for selection
				rs = statement.executeQuery("SELECT pro_id, pro_name FROM provider");
				while (rs.next()) 
				{
					System.out.println(rs.getRow() + ". " + rs.getString("pro_name")+ " ("+rs.getInt("pro_id")+")");
					listID.add(rs.getRow()-1);
					listNumb.add(rs.getInt("pro_id"));
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
				rs = statement.executeQuery("SELECT * FROM provider WHERE pro_id = '"+numb+"'");
				
				System.out.println("1. Name: "+rs.getString("pro_name"));
				System.out.println("2. Address: "+rs.getString("pro_address"));
				System.out.println("3. City: "+rs.getString("pro_city"));
				System.out.println("4. Zipcode: "+rs.getString("pro_zip"));
				System.out.println("5. State: "+rs.getString("pro_state"));
				rowID = ic.checkInput(in2.nextLine(), 1, 5);
				//Input provider info (name, address, city, zipcode, state)
				switch(rowID){
					case 1:		
						System.out.println("Input new name: ");
						Scanner in3 = new Scanner(System.in);
						String newName = in3.nextLine();
						statement.executeUpdate("UPDATE provider SET pro_name =  '"+newName+"' WHERE pro_id = '"+numb+"'");
						break;
					case 2:
						System.out.println("Input new address: ");
						Scanner in3a = new Scanner(System.in);
						String newAddress = in3a.nextLine();
						statement.executeUpdate("UPDATE provider SET pro_address =  '"+newAddress+"' WHERE pro_id = '"+numb+"'");
						break;
					case 3:
						System.out.println("Input new city: ");
						Scanner in3b = new Scanner(System.in);
						String pCity = in3b.nextLine();
						statement.executeUpdate("UPDATE provider SET pro_city =  '"+pCity+"' WHERE pro_id = '"+numb+"'");
						break;
					case 4:
						System.out.println("Input new ZIP: ");
						Scanner in3c = new Scanner(System.in);
						String pZip = in3c.nextLine();
						statement.executeUpdate("UPDATE provider SET pro_zip =  '"+pZip+"' WHERE pro_id = '"+numb+"'");
						break;
					case 5:
						System.out.println("Input new state: ");
						Scanner in3d = new Scanner(System.in);
						String pState = in3d.nextLine();
						statement.executeUpdate("UPDATE provider SET pro_state =  '"+pState+"' WHERE pro_id = '"+numb+"'");
						break;
				}
			}catch(SQLException e){
				System.err.println(e);
			}
			//disconnect database and dispose sql statement func
			statement.close();
			connection.close();
		}
		
		
		//function of deleting provider
		public static void deleteProvider() throws ClassNotFoundException, SQLException{
			Connection connection = null;
			Statement statement = null;
			Class.forName("org.sqlite.JDBC");
			
			try{
				//connect database and create sql statement func
				connection = DriverManager.getConnection("jdbc:sqlite:src//ChocoholicsAnonymous.db");
				statement = connection.createStatement();
				statement.setQueryTimeout(10);
				
				Scanner in4 = new Scanner(System.in);
				//Show Provider List for selection
				System.out.println("Select provider to delete the record");
				rs = statement.executeQuery("SELECT pro_id, pro_name FROM provider");
				while (rs.next()) 
				{
					System.out.println(rs.getRow() + ". " + rs.getString("pro_name")+ " ("+rs.getInt("pro_id")+")");
					listID.add(rs.getRow()-1);
					listNumb.add(rs.getInt("pro_id"));
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
				
				statement.executeUpdate("DELETE FROM provider WHERE pro_id = '"+numb+"'");
				
				}catch(SQLException e){
					System.out.println(e);
				}
			//disconnect database and dispose sql statement func
			statement.close();
			connection.close();
		}
		
		
		//function of displaying provider according to provider number
		public static void displayProvider() throws ClassNotFoundException, SQLException{
			Connection connection = null;
			Statement statement = null;
			Class.forName("org.sqlite.JDBC");
			
			try{
				//connect database and create sql statement func
				connection = DriverManager.getConnection("jdbc:sqlite:src//ChocoholicsAnonymous.db");
				statement = connection.createStatement();
				statement.setQueryTimeout(10);
				
				Scanner in5 = new Scanner(System.in);
				System.out.println("Select provider to display the record");
				//Show Provider List for selection
				rs = statement.executeQuery("SELECT pro_id, pro_name FROM provider");
				while (rs.next()) 
				{
					System.out.println(rs.getRow() + ". " + rs.getString("pro_name")+ " ("+rs.getInt("pro_id")+")");
					listID.add(rs.getRow()-1);
					listNumb.add(rs.getInt("pro_id"));
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
				//Display selected Provider info
				rs = statement.executeQuery("SELECT * FROM provider WHERE pro_id = '"+numb+"'");
				  String pname = rs.getString("pro_name");
				  System.out.println("Provier name: "+ pname);
				 
				  String paddress = rs.getString("pro_address");
				  System.out.println("Provier address: "+ paddress);
				  
				  String pcity = rs.getString("pro_city");
				  System.out.println("Provier city: "+pcity);
				  
				  String pstate = rs.getString("pro_state");
				  System.out.println("Provier state: "+pstate);
				  
				  String pzip = rs.getString("pro_zip");
				  System.out.println("Provier ZIP: "+pzip + "\n");
				  
				}catch(SQLException e){
					System.out.println(e);
				}
			//disconnect database and dispose sql statement func
			statement.close();
			connection.close();
		}
		
		
		//function of showing all exiting providers
		public static void showAll() throws ClassNotFoundException, SQLException{
			Connection connection = null;
			Statement statement = null;
			Class.forName("org.sqlite.JDBC");
			
			try
			{
				//connect database and create sql statement func
				connection = DriverManager.getConnection("jdbc:sqlite:src//ChocoholicsAnonymous.db");
				statement = connection.createStatement();
				statement.setQueryTimeout(10);
				ResultSet rs = statement.executeQuery("SELECT * FROM provider");
				
				while (rs.next()) 
				{
					  String pname = rs.getString("pro_name");
					  System.out.println("Provider name: "+ pname);
					 
					  int pid = rs.getInt("pro_id");
					  System.out.println("Provider number: "+ pid);
					  
					  String paddress = rs.getString("pro_address");
					  System.out.println("Provider address: "+ paddress);
					  
					  String pcity = rs.getString("pro_city");
					  System.out.println("Provider city: "+pcity);
					  
					  String pstate = rs.getString("pro_state");
					  System.out.println("Provider state: "+pstate);
					  
					  String pzip = rs.getString("pro_zip");
					  System.out.println("Provider zip: "+pzip + "\n");
					  
	            }
			}
			catch(SQLException e)
			{
				System.out.println(e);
			}
			//disconnect database and dispose sql statement func
			statement.close();
			connection.close();
		}
		
		//function of displaying all service names and corresponding codes
		public static void showAllServices() throws ClassNotFoundException, SQLException{
			Connection connection = null;
			Statement statement = null;
			Class.forName("org.sqlite.JDBC");
			
			try
			{
				//connect database and create sql statement func
				connection = DriverManager.getConnection("jdbc:sqlite:src//ChocoholicsAnonymous.db");
				statement = connection.createStatement();
				statement.setQueryTimeout(10);
				ResultSet rs = statement.executeQuery("SELECT * FROM service order by ser_name asc");
				
				while (rs.next()) 
				{
					  String snum = rs.getString("ser_code");
					  System.out.println("Service code: "+ snum);
					  
					  String sname = rs.getString("ser_name");
					  System.out.println("Service name: "+ sname);
					  
					  String sfee = rs.getString("ser_fee");
					  System.out.println("Service fee: "+ sfee+"\n");
	            }
			}
			catch(SQLException e)
			{
				System.out.println(e);
			}
			//disconnect database and dispose sql statement func
			statement.close();
			connection.close();
		}
}
