import java.sql.*;
import java.text.ParseException;
import java.util.Scanner;
import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class serviceController {
	
	public static void addService() throws ClassNotFoundException, SQLException{
			
		Connection connection = null;
		Statement statement = null;
		ResultSet rs;
		int rowID=0;
		int memDis=0;
		int reProId=0;
		int confirmNum=0;
		String serDate="";
		InputCheck ic=new InputCheck();
		List<Integer> listID = new ArrayList<Integer>();
		List<Integer> listNumb = new ArrayList<Integer>();
		List<String> listName = new ArrayList<String>();
		List<Integer> listFee = new ArrayList<Integer>();
		List<Integer> val = Arrays.asList(1,2);
		
		try
		{
			//connect database and create sql statement func
			connection = DriverManager.getConnection("jdbc:sqlite:src//ChocoholicsAnonymous.db");
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			
			Scanner in = new Scanner(System.in);
			//asking user to select existed member from database
			System.out.println("Select the member number:");
			rs = statement.executeQuery("SELECT * FROM Member");
			//Show the Member list for user select
			while (rs.next()) 
			{
				System.out.println(rs.getRow() + ". " + rs.getString("mem_name")+ " ("+rs.getInt("mem_id")+")");
				listID.add(rs.getRow()-1);
				listNumb.add(rs.getInt("mem_id"));
				listName.add(rs.getString("mem_name"));
			} 
			//check validity of Member List input
			rowID=ic.checkInput(in.nextLine(),1,listID.size());
			//get the Member id from selected Member name
			for(int i=0; i<listNumb.size(); i++)
			{
				if (rowID-1==listID.get(i))
				{
					memDis = listNumb.get(i);
					break;
				}
			}
			//check the membership status (qualified/expired)
			rs = statement.executeQuery("SELECT * FROM Member WHERE mem_id = '"+memDis+"'");
			if(rs.next())
			{
				String memValid = rs.getString("mem_status");				
				if(memValid.equals(false))
				{
					System.out.println("The membership is suspended.");
				}
				else
				{	
					listID.clear();
					listNumb.clear();
					listName.clear();
					
					System.out.println("Select the provider number:");
					rs = statement.executeQuery("SELECT * FROM Provider");
					//Show the Provider list for user select
					while (rs.next()) 
					{
						System.out.println(rs.getRow() + ". " + rs.getString("pro_name")+ " ("+rs.getInt("pro_id")+")");
						listID.add(rs.getRow()-1);
						listNumb.add(rs.getInt("pro_id"));
						listName.add(rs.getString("pro_name"));
					} 
					
					//check validity of Provider List input
					rowID=ic.checkInput(in.nextLine(),1,listID.size());
					
					//get the Provider id from selected Provider name
					for(int i=0; i<listNumb.size(); i++)
					{
						if (rowID-1==listID.get(i))
						{
							reProId = listNumb.get(i);
							break;
						}
					}
					
					//check validity of date input
					System.out.println("Input date as MM-dd-yyyy");
					serDate=ic.checkInputDate(in.nextLine());
					String timestamp = new java.text.SimpleDateFormat("MM-dd-yyyy hh:mm:ss").format(new Date());			
							
					
					System.out.println("Select service type:");
					rs = statement.executeQuery("SELECT * FROM Service");
						
					listID.clear();
					listNumb.clear();
					listName.clear();
					//Show the Service list for user select	
					while (rs.next()) 
					{
						System.out.println(rs.getRow() + ". " + rs.getString("ser_name")+" ("+rs.getString("ser_code")+")");
						listID.add(rs.getRow()-1);
						listNumb.add(rs.getInt("ser_code"));
						listName.add(rs.getString("ser_name"));
						listFee.add(rs.getInt("ser_fee"));
					} 
						//check validity of Service List input            
						rowID=ic.checkInput(in.nextLine(),1,listID.size());

						//Confirm the Service selection
						for(int i=0; i<listNumb.size(); i++)
						{
							if (rowID-1==listID.get(i))
							{
								System.out.println("Is this the right service?");
								System.out.println("Selected service: " + listName.get(i)+" ("+listNumb.get(i)+")");
								System.out.println("Service fee: $"+listFee.get(i));
								System.out.println("1 for yes, 2 for no: ");
								//check validity of input
								confirmNum=ic.checkInput(in.nextLine(),val);
								//Input the comment or not
								if(confirmNum == 1)
								{					
									System.out.println("Do you have any comment? 1 for yes, 2 for no: ");
									//check validity of input
									confirmNum=ic.checkInput(in.nextLine(),val);
									
									String mComment = "No comment.";
									if(confirmNum == 1)
									{
										Scanner in2 = new Scanner(System.in);
										System.out.println("Enter your comment: ");
										mComment = in2.nextLine();
									}
									//Insert new service record into database
									statement.executeUpdate("INSERT INTO Record (ser_comment, ser_code, ser_sys_date, ser_date, mem_id, pro_id) VALUES "
									+ "('"+mComment+"', '"+listNumb.get(i)+"', '"+timestamp+"', "+ "'"+serDate+"', '"+memDis+"', '"+reProId+"')");
									break;
								}
								
							}	
						
					}
				}
			}
		}
		catch(SQLException e)
		{
			System.err.println(e);
		}
		//disconnect database and dispose sql statement func
		statement.close();
		connection.close();
	}
}

