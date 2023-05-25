package com.logic;

import com.opensymphony.xwork2.ActionSupport;

public class AdminLogin extends ActionSupport{
	String status,admusr,admpass="";
	public String getAdmusr() {
		return admusr;
	}
	public void setAdmusr(String admusr) {
		this.admusr = admusr;
	}
	public String getAdmpass() {
		return admpass;
	}
	public void setAdmpass(String admpass) {
		this.admpass = admpass;
	}
	public String execute()
	{
		if(admusr.equals("")||(admpass.equals("")))
		{
			status="error";
			addActionMessage("Fields should not be empty");
		}
		else if(admusr.equals("Admin")&&(admpass.equals("Admin")))
		{
			status="success";
		}
		else
		{
			status="error";
			addActionMessage("Invalid username or password");
		}
		return status;
	}
}
