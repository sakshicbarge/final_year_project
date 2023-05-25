package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DbListener implements ServletContextListener 
{
	static Connection connection = null;

	public void contextDestroyed(ServletContextEvent arg0)
	{
		try
    	{
    		System.out.println("DataBase Connection Closed");
			connection.close();			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void contextInitialized(ServletContextEvent arg0)
	{
		ServletContext context = arg0.getServletContext();
		try 
		{
			

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection("jdbc:mysql://localhost/identitybased","root","root");
			context.setAttribute("conncetion", connection);
			System.out.println("....................connection created succesfully for Identity based...............................");
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	 public Connection getConnection()
	    {
	    	return connection;
	    }

}
