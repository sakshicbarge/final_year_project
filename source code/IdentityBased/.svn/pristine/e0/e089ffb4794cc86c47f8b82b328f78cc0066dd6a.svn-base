package com.logic;

import java.io.File;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import jutils21.FrameWork;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.db.DbStatement;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class Login extends ActionSupport implements SessionAware,CommonInter{
	String name,passwd,status;
	DbStatement databaseStatment=new DbStatement(); 
	PreparedStatement preparedStatement=null;
	SessionMap<String,String> sessionmap;
	String servers=""; 
	public SessionMap<String, String> getSessionmap() {
		return sessionmap;
	}

	public void setSessionmap(SessionMap<String, String> sessionmap) {
		this.sessionmap = sessionmap;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String execute()
	{
		if(name.equals("") ||passwd.equals(""))
		{
			addActionError("Please fill all the details");
		}
		
		ResultSet rs;
		try {
//			HttpServletRequest req=(HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
//			FrameWork fr=new FrameWork();
//			if(fr.get(req))
//			{
			preparedStatement=(PreparedStatement)databaseStatment.getConnection().prepareStatement(databaseStatment.UserLogin());
			preparedStatement.setString(1, name); 
			preparedStatement.setString(2, passwd); 
			rs=preparedStatement.executeQuery();	
			boolean b=rs.next();
			if(b&&spl.size()>0)
			{
				
				String unm=name;
				sessionmap.put("usrname", unm);
				status="success";
			}
			else
			{
				status="error";
				addActionError("Invalid username or password or set admin configuration");
				
			}
//			}
		}
			catch(Exception e){
				e.printStackTrace();
			}
			
		return status;
	}

	public void setSession(Map<String, Object> arg0) {
		sessionmap=(SessionMap)arg0;
		
	}
	
}
