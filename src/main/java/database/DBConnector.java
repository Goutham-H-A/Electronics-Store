package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class DBConnector 
{
	public static final String DRIVER_NAME="com.mysql.jdbc.Driver";
	public static final String PASSWORD="root";
	public static final String USER_NAME="root";
	public static final String URL="jdbc:mysql://localhost:3306/electronisstore";

	public static Connection getConnection()
	{
		Connection con=null;
		try
		{
			Class.forName(DRIVER_NAME);
			con=DriverManager.getConnection(URL,USER_NAME,PASSWORD);
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return con;
	}


	public static void closeConnection(Connection con)
	{
		try
		{
			if(con!=null)
			{
				con.close();
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}

	}

	public static void closeStatement(PreparedStatement prt){
		try{
			if(prt!=null)
			{
				prt.close();
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}





}
