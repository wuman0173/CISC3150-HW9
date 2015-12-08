/*Benjamin Wu
CISC 3150
HW9 SQL Databases - Creating a connection with a database with java

comments: Followed the tutorial.. http://stackoverflow.com/questions/2839321/connect-java-to-a-mysql-database
This isnt working. I can't connect to the database, I keep getting the SQLEXCEPTION: No suitable driver found for "URL HERE" no matter which url I give. 
UPDATE: OMG IT WORKS. Manually loaded driver using step 6... Followed directions as well, moved jar file before manually uploading driver.
so then used...
	$ javac -cp mysql-connector-java-5.0.8-bin.jar SQLBase.java
	$ java -cp mysql-connector-java-5.0.8-bin.jar; SQLBase

it comes out, but not neatly, could be used with printf function? maybe try later.
*/

import java.util.*;
import java.sql.*;

public class SQLBase{

public static void main(String[] args)
	{
	Enumeration <Driver> ed = DriverManager.getDrivers();
	while(ed.hasMoreElements())
		{
		System.out.println((Driver)ed.nextElement());
		}
/********USED STEP 6 OF TUTORIAL TO MANUALLY UPLOAD DRIVER*********/
		System.out.println("Loading driver...");
	try 
		{
  		Class.forName("com.mysql.jdbc.Driver");
    		System.out.println("Driver loaded!");
		}
	catch (ClassNotFoundException e) 
		{
    		throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		}
/****END *** USED STEP 6 OF TUTORIAL TO MANUALLY UPLOAD DRIVER*****/
	try
		{
		//Class.forName("com.mysql.jdbc.Driver").newInstance(); //try this with those exceptions on the bottom. nope.
		String url = "jdbc:mysql://localhost:3306/EMP";
		String username = "wuman";
		String password = "password";
		Connection my_connection = DriverManager.getConnection(url, username, password);
		Statement my_state = my_connection.createStatement();
		ResultSet my_results = my_state.executeQuery("Select * from employees");
		while (my_results.next())
			{
			System.out.println(my_results.getString(1) + "\t" + my_results.getString(2) + "\t" + my_results.getString(3) + "\t" + my_results.getString(4));
//Need to find a way to getString(x) where x is the number of its respective column, this is hardcoded at the moment.
			}
		}
	catch (SQLException ex)
		{
			throw new IllegalStateException("Cannot connect the database!", ex);
		}/*
	catch (ClassNotFoundException e)
		{
			throw new IllegalStateException("Cannot find the CLASS in the classpath!", e);
		}
	catch (InstantiationException e)
		{
			throw new IllegalStateException("Cannot find the INSTANTIATION in the classpath!", e);
		}
	catch (IllegalAccessException e)
		{
			throw new IllegalStateException("Cannot find the ACCESS in the classpath!", e);
		}*/
	}
}
