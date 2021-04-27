import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class databaseAccess 
{
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	public databaseAccess()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinedatabase?user=root&password=password");
		}
		catch(SQLException sqle)
		{
			System.out.println("sqle: " + sqle.getMessage());
		}
		catch(ClassNotFoundException cnfe)
		{
			System.out.println("cnfe: " + cnfe.getMessage());
		}
	}
	
	//validate
	public boolean validate(String username, String userpassword)
	{
		try {
			//after ? comes the value user input
			ps = conn.prepareStatement("SELECT COUNT(*) FROM Users WHERE username=? AND userpassword=?");
			//replace first question mark with the firstName variable. question mark means a variable will go there
			ps.setString(1, username);
			ps.setString(2, userpassword);
			rs = ps.executeQuery();			
				
			//as long as there are more rows, as select will return a table
			//the table is a count of how many successful username password combos there are, should be 1 or 0
			boolean matchExists = false;
			while(rs.next())
			{
				matchExists = (rs.getInt(1) == 1);
			}
			
			return matchExists;
		}
			
		catch(SQLException sqle)
		{
			System.out.println("sqle: " + sqle.getMessage());
			return false;
		}
		finally //no matter what happens, this will occur
		{
			try {
				if(rs != null)
					rs.close(); 
				if(ps != null)
					ps.close();
				if(conn != null)
					conn.close();
			}
			catch(SQLException sqle)
			{
				System.out.println("sqle closing stuff: " + sqle.getMessage());
				return false;
			}
		}
	}
	
	public boolean userExists(String username)
	{
		try {
			//after ? comes the value user input
			ps = conn.prepareStatement("SELECT COUNT(*) FROM Users WHERE username=?");
			//replace first question mark with the firstName variable. question mark means a variable will go there
			ps.setString(1, username);
			rs = ps.executeQuery();			
				
			//as long as there are more rows, as select will return a table
			//the table is a count of how many successful username password combos there are, should be 1 or 0
			boolean matchExists = false;
			while(rs.next())
			{
				matchExists = (rs.getInt(1) == 1);
			}
			
			return matchExists;
		}
			
		catch(SQLException sqle)
		{
			System.out.println("sqle: " + sqle.getMessage());
			return false;
		}
	}
	
	//create account
	public boolean createAccount(String username, String password)
	{
		try {
			boolean check = false;
			ps = conn.prepareStatement("SELECT COUNT(*) FROM Users WHERE username=?");
			ps.setString(1, username);
			rs = ps.executeQuery();		
			while(rs.next())
			{
				check = (rs.getInt(1) == 0);
			}
			
			//if there is already a user with that username that exists, return false
			if(check == false)
			{
				return false;
			}
			
			else
			{
				//after ? comes the value user input 
				//where not exists, only insert row
				ps = conn.prepareStatement("INSERT INTO Users(username, userpassword) VALUES(?,?)");
				//replace first question mark with the firstName variable. question mark means a variable will go there
				ps.setString(1, username);
				ps.setString(2, password);
				ps.executeUpdate();
				
				return true;
			}
		}
		
		catch(SQLException sqle)
		{
			System.out.println("sqle: " + sqle.getMessage());
			return false;
		}
		finally //no matter what happens, this will occur
		{
			try {
				if(rs != null)
					rs.close();
				if(ps != null)
					ps.close();
				if(conn != null)
					conn.close();
			}
			catch(SQLException sqle)
			{
				System.out.println("sqle closing stuff: " + sqle.getMessage());
				return false;
			}
		}
	}
	
	
	public void addToQueue(String username, String park, String location, String timestamp)
	{
		try {
			//after ? comes the value user input 
			//where not exists, only insert row
			ps = conn.prepareStatement("INSERT INTO Queue(username, park, location, time_stamp) VALUES(?,?,?,?)");
			//replace first question mark with the firstName variable. question mark means a variable will go there
			ps.setString(1, username);
			ps.setString(2, park);
			ps.setString(3, location);
			ps.setString(4, timestamp);
			ps.executeUpdate();
		}
		
		catch(SQLException sqle)
		{
			System.out.println("sqle: " + sqle.getMessage());
		}
		finally //no matter what happens, this will occur
		{
			try {
				if(rs != null)
					rs.close();
				if(ps != null)
					ps.close();
				if(conn != null)
					conn.close();
			}
			catch(SQLException sqle)
			{
				System.out.println("sqle closing stuff: " + sqle.getMessage());
			}
		}
	}
	
	public ArrayList<String> getQueue(String park, String location)
	{
		ArrayList<String> linequeue = new ArrayList<String>();
		
		try {
			ps = conn.prepareStatement("SELECT * FROM searchqueries WHERE park=? AND location=? ORDER BY time_stamp DESC;");
			ps.setString(1, park);
			ps.setString(2, location);
			rs = ps.executeQuery();	
			
			while(rs.next())
			{
				linequeue.add(rs.getString("search"));
			}
			return linequeue;
		}
		
		catch(SQLException sqle)
		{
			System.out.println("sqle: " + sqle.getMessage());
		}
		return history;
	}
}
