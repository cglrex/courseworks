import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class reportCreater {
	//create the report for members according to their own id
	private static Connection connection = null;
	private static Statement statement = null;
	private static ResultSet rs;
	private static InputCheck ic = new InputCheck();
	private static int rowID=0;
	private static Date dat=new Date();
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private static List<Integer> listID = new ArrayList<Integer>();
	private static List<Integer> listNumb = new ArrayList<Integer>();
	private static List<Integer> listNumb2 = new ArrayList<Integer>();
	private static List<String> listName = new ArrayList<String>();
	private static List<String> listName2 = new ArrayList<String>();
	private static List<String> listService = new ArrayList<String>();
	private static List<String> listDate = new ArrayList<String>();
	private static List<String> listDate2 = new ArrayList<String>();
	private static List<Integer> listFee = new ArrayList<Integer>();
	

	private static String name = new String();
	private static String numb = new String();
	private static String address = new String();
	private static String city = new String();
	private static String state = new String();
	private static String zipcode = new String();
	private static String memStatus="Expired";
	private static boolean status=true;
	private static FileWriter writer;
	
	public static void memREC() throws ClassNotFoundException, SQLException, IOException{
		Class.forName("org.sqlite.JDBC");
		listID.clear();
		listNumb.clear();
		listName.clear();
		listDate.clear();
		listName2.clear();
		listService.clear();
		
		try{
			//connect database and create sql statement func
			connection = DriverManager.getConnection("jdbc:sqlite:src//ChocoholicsAnonymous.db");
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			
			//get member info (name, number, address, city, state, zipcode, status)			
			Scanner in = new Scanner(System.in);
			
			System.out.println("Select member number to create report");
			rs = statement.executeQuery("SELECT * FROM Member WHERE mem_id");
			while (rs.next()) 
			{
				System.out.println(rs.getRow() + ". " + rs.getString("mem_name")+ " ("+rs.getInt("mem_id")+")");
				listID.add(rs.getRow()-1);
				listNumb.add(rs.getInt("mem_id"));
				listName.add(rs.getString("mem_name"));
			} 
			rowID=ic.checkInput(in.nextLine(),1,listID.size());
			
			for(int i=0; i<listID.size(); i++)
			{
				if (rowID-1==listID.get(i))
				{
					rs = statement.executeQuery("SELECT * FROM Mem_report WHERE mem_id = '"+listNumb.get(i)+"'");
					break;
				}
			}
			
			
			while (rs.next())
			{
				numb=rs.getString("mem_id");
				name=rs.getString("mem_name");
				address=rs.getString("mem_address");
				city=rs.getString("mem_city");
				state=rs.getString("mem_state");
				zipcode=rs.getString("mem_zip");
				status=rs.getBoolean("mem_status");
				listDate.add(rs.getString("ser_date"));
				listName2.add(rs.getString("pro_name"));
				listService.add(rs.getString("pro_name"));
			}
						
			
			if(status==true)memStatus="Qualified";
			System.out.println("Member name: " + name );
			System.out.println("Member number: " + numb);
			System.out.println("Member street address: " + address);
			System.out.println("Member city: " + city);
			System.out.println("Member state: " + state);
			System.out.println("Member ZIP code: " + zipcode);
			System.out.println("Member Status: " + memStatus);
			
			
			String pathname = name +"("+ df.format(dat)+").txt";
			System.out.println(df.format(dat));
			File filename = new File(pathname); 
			writer = new FileWriter(filename);
			
			writer.write("Member name: " + name + "\r\n");
			writer.write("Member number: " + numb + "\r\n");
			writer.write("Member street address: " + address + "\r\n");
			writer.write("Member city: " + city + "\r\n");
			writer.write("Member state: " + state + "\r\n");
			writer.write("Member ZIP code: " + zipcode + "\r\n");
			writer.write("Member Status: " + memStatus + "\r\n");
			
			for(int i=0; i<listDate.size(); i++)
			{
				System.out.println("Service Date: "+listDate.get(i)+"| Provider Name: "+listName2.get(i)+"| Service Name: "+listService.get(i));	
				writer.write("Service Date: "+listDate.get(i)+"| Provider Name: "+listName2.get(i)+"| Service Name: "+listService.get(i)+"\r\n");
			}
			System.out.println();
		}
		catch(SQLException e)
		{
				System.out.println(e);
		}
		//disconnect database and dispose sql statement func
		writer.close();
		statement.close();
		connection.close();
	}

	public static void proREC() throws ClassNotFoundException, SQLException, IOException{
		Class.forName("org.sqlite.JDBC");
		listID.clear();
		listNumb.clear();
		listNumb2.clear();
		listName.clear();
		listName2.clear();
		listDate.clear();
		listDate2.clear();
		int totalFee=0;
		
		try{
			//connect database and create sql statement func
			connection = DriverManager.getConnection("jdbc:sqlite:src//ChocoholicsAnonymous.db");
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			
			
			Scanner in = new Scanner(System.in);
			System.out.println("Select provider number to create report:");
			rs = statement.executeQuery("SELECT * FROM Provider");
			while (rs.next()) 
			{
				System.out.println(rs.getRow() + ". " + rs.getString("pro_name")+ " ("+rs.getInt("pro_id")+")");
				listID.add(rs.getRow()-1);
				listNumb.add(rs.getInt("pro_id"));
				listName.add(rs.getString("pro_name"));
			} 
			rowID=ic.checkInput(in.nextLine(),1,listID.size());
			
			for(int i=0; i<listID.size(); i++)
			{
				if (rowID-1==listID.get(i))
				{
					rs = statement.executeQuery("SELECT * FROM Pro_report WHERE pro_id = '"+listNumb.get(i)+"'");
					break;
				}
			}
			
			listNumb.clear();
			while (rs.next())
			{
				numb=rs.getString("pro_id");
				name=rs.getString("pro_name");
				address=rs.getString("pro_address");
				city=rs.getString("pro_city");
				state=rs.getString("pro_state");
				zipcode=rs.getString("pro_zip");
				listDate.add(rs.getString("ser_date"));
				listDate2.add(rs.getString("ser_sys_date"));
				listName2.add(rs.getString("mem_name"));
				listNumb2.add(rs.getInt("mem_id"));
				listNumb.add(rs.getInt("ser_code"));
				listFee.add(rs.getInt("ser_fee"));
			}
			
			System.out.println("Provider name: " + name );
			System.out.println("Provider number: " + numb);
			System.out.println("Provider street address: " + address);
			System.out.println("Provider city: " + city);
			System.out.println("Provider state: " + state);
			System.out.println("Provider ZIP code: " + zipcode);
			
			String pathname = name +"("+ df.format(dat)+").txt";
			writer = new FileWriter(pathname);
			
			writer.write("Provider name: " + name + "\r\n");
			writer.write("Provider number: " + numb + "\r\n");
			writer.write("Provider street address: " + address + "\r\n");
			writer.write("Provider city: " + city + "\r\n");
			writer.write("Provider state: " + state + "\r\n");
			writer.write("Provider ZIP code: " + zipcode + "\r\n");
			
			for(int i=0; i<listDate.size(); i++)
			{
				System.out.println("Service Date: "+listDate.get(i)+"| System received Date: "+listDate2.get(i)+"| Member Name: "+listName2.get(i)+"| Member Number: "+listNumb2.get(i)+"| Service Code: "+listNumb.get(i)+"| Service Fee: $"+listFee.get(i));
				writer.write("Service Date: "+listDate.get(i)+"| System received Date: "+listDate2.get(i)+"| Member Name: "+listName2.get(i)+"| Member Number: "+listNumb2.get(i)+"| Service Code: "+listNumb.get(i)+"| Service Fee: $"+listFee.get(i)+"\r\n");

				totalFee=totalFee+listFee.get(i);
			}
			
			//System.out.println("Total number of consultations members: ");
			System.out.println("Total Fee: $"+totalFee+"\r\n");
			writer.write("Total fee is: $"+totalFee+"\r\n");
			
			}catch(SQLException e){
				System.out.println(e);
			}
		//disconnect database and dispose sql statement func
		writer.close();
		statement.close();
		connection.close();
	}
}
