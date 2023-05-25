package com.db;

import javax.servlet.ServletContext;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DbStatement {

	Connection connection = null;
	ServletContext context;
	String sql = "";

	DbListener dbc = new DbListener();

	public Connection getConnection() {
		// context=ServletActionContext.getServletContext();
		connection = dbc.getConnection();
		return connection;
	}

	public String UserRegister() {
		sql = "insert into register(userid,username,password,age,gender,address,contact,email) values(?,?,?,?,?,?,?,?)";
		return sql;
	}

	public String UserLogin() {
		sql = "select * from register where username=? and password=?";
		return sql;
	}

	public String UserCheck() {
		sql = "select * from register where username=?";
		return sql;
	}

}
