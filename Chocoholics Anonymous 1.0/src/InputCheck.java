import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;
import java.util.List;
public class InputCheck {
	private static Scanner in = new Scanner(System.in);
	private static boolean err=true;
	private static int y;
	private static Date dat;
	//Input control for integer value requirement
	public static int checkInput(String x){
		do 
		{
			try
			{
				y=Integer.parseInt(x.trim());
				err=false;
			}
			catch(NumberFormatException e)
			{
				System.err.println("Invalid input, require interger value...");
				x=in.nextLine();
			}
        } while(err);
		err=true;
		return y;
	}
	//Input control for integer value and value range requirement
	public static int checkInput(String x, List<Integer> z){
		do 
		{
			try
			{
				y=Integer.parseInt(x.trim());
				if(z.contains(y))
				{
					err=false;
					break;
				}
				else
				{
					System.err.println("Invalid input, unacceptable value...");
					x=in.nextLine();
				}
			}
			catch(NumberFormatException e)
			{
				System.err.println("Invalid input, require interger value...");
				x=in.nextLine();
			}
        } while(err);
		err=true;
		return y;
	}
	//Input control for integer value, maximum and minimum requirement
	public static int checkInput(String x, int min, int max){
		do 
		{
			try
			{
				y=Integer.parseInt(x.trim());
				err=false;
				if(y<min || y>max)
				{
					System.err.println("Invalid input, must choose between "+min+" to "+max+"...");
					err=true;
					x=in.nextLine();
				}
			}
			catch(NumberFormatException e)
			{
				System.err.println("Invalid input, require interger value...");
				x=in.nextLine();
			}
        } while(err);
		err=true;
		return y;
	}
	//Input control for date value requirement
	public static String checkInputDate(String x){
		SimpleDateFormat df=new SimpleDateFormat("MM-dd-yyyy");
		df.setLenient( false );
		do {
            try {
            	dat=df.parse(x);
            	err=false;
            } 
            catch (ParseException e) 
            {
            	System.err.println("Invalid input, require 'MM-dd-yyyy'...");
            	x=in.nextLine();
            }
        } while (err);
		err=true;
		return df.format(dat);
	}
}
