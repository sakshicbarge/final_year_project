package com.logic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.db.DbStatement;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends ActionSupport implements SessionAware {
	String regid, regname, reggen, regpasswd, regrepasswd, regage, address,
			contact, mail, status;

	public String getRegid() {
		return regid;
	}

	public void setRegid(String regid) {
		this.regid = regid;
	}

	public String getRegname() {
		return regname;
	}

	public void setRegname(String regname) {
		this.regname = regname;
	}

	public String getReggen() {
		return reggen;
	}

	public void setReggen(String reggen) {
		this.reggen = reggen;
	}

	public String getRegpasswd() {
		return regpasswd;
	}

	public void setRegpasswd(String regpasswd) {
		this.regpasswd = regpasswd;
	}

	public String getRegrepasswd() {
		return regrepasswd;
	}

	public void setRegrepasswd(String regrepasswd) {
		this.regrepasswd = regrepasswd;
	}

	public String getRegage() {
		return regage;
	}

	public void setRegage(String regage) {
		this.regage = regage;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	DbStatement databaseStatment = new DbStatement();
	PreparedStatement preparedStatement = null;
	SessionMap<String, String> sessionmap;

	public void setSession(Map map) {
		sessionmap = (SessionMap<String, String>) map;
	}

	private String sessionId;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public static HashMap<String, SessionMap> getSession() {
		return session;
	}

	public static void setSession(HashMap<String, SessionMap> session) {
		Register.session = session;
	}

	public static HashMap<String, SessionMap> session = new HashMap<String, SessionMap>();

	public void validate() {

		Pattern pattern;
		Matcher matcher;

		final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@$*]).{6,20})";

		pattern = Pattern.compile(PASSWORD_PATTERN);
		matcher = pattern.matcher(regpasswd.toString());
		if (regname.equals("") || regpasswd.equals("")
				|| regrepasswd.equals("") || regage.equals("")
				|| reggen.equals("") || address.equals("")
				|| contact.equals("") || mail.equals("")) {
			addActionError("Please fill all the details...");
		} else if (!matcher.matches()) {
			addActionError("Password should contain one uppercase letter,one lowercase,one special character(@$*),one digit and should contain atleast of length 6");
		} else if (!regpasswd.equals(regrepasswd)) {
			addActionError("Password mismatch...");
		} else if (regpasswd.length() < 6) {
			addActionError("Password Should contain 6 Characters...");
		} else if (regname.length() < 5) {
			addActionError("UserName Should contain 5 Characters...");
		} else if (contact.length() < 10 || contact.length() > 12) {
			addActionError("Invalid Mobile Number...");
		} else if (!mail.contains("@") || !mail.contains(".")
				|| mail.length() < 10) {
			addActionError("Invalid Mail Id...");
		}
		if (!regage.equals("")) {
			try {
				int age = Integer.parseInt(regage);
			} catch (Exception e) {
				addActionError("Age should be an Integer...");
			}
		}
		if (!contact.equals("")) {
			try {
				Long age = Long.parseLong(contact);
			} catch (Exception e) {
				addActionError("Invalid Mobile Number...");
			}
		} else if (!regpasswd.equals(regrepasswd)) {
			addActionError("Password mismatch");
		}

	}

	public String execute() {
		status = "input";
		ResultSet rs;

		try {
			// HttpServletRequest req=(HttpServletRequest)
			// ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			// FrameWork fr=new FrameWork();
			// if(fr.get(req))
			// {
			preparedStatement = (PreparedStatement) databaseStatment
					.getConnection().prepareStatement(
							databaseStatment.UserCheck());
			preparedStatement.setString(1, regname);

			rs = preparedStatement.executeQuery();
			boolean b = rs.next();
			if (b) {

				addActionMessage("Username already exists");
			} else {
				preparedStatement = (PreparedStatement) databaseStatment
						.getConnection().prepareStatement(
								databaseStatment.UserRegister());
				preparedStatement.setString(1, regid);
				preparedStatement.setString(2, regname);

				preparedStatement.setString(3, regpasswd);
				preparedStatement.setString(4, regage);
				preparedStatement.setString(5, reggen);
				preparedStatement.setString(6, address);
				preparedStatement.setString(7, contact);
				preparedStatement.setString(8, mail);

				preparedStatement.executeUpdate();

				status = "success";

				addActionMessage("Registered successfully");
			}
			// }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return status;
	}

}
