package com.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import java.util.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class Admin extends ActionSupport implements CommonInter {
	String servers = "";
	String status;
	String proPat = "", fatPath = "", setTim = "";
	File propFile, fatFile;
	int newCountServ = 0;
	String servVal = "";
	String serverValue;
	String ipVal, ipValhidden;
	Vector ipVector = new Vector();
	Vector ipPropVector = new Vector();
	Vector timeVec = new Vector();

	public String getSetTim() {
		return setTim;
	}

	public void setSetTim(String setTim) {
		this.setTim = setTim;
	}

	public String getIpValhidden() {
		return ipValhidden;
	}

	public void setIpValhidden(String ipValhidden) {
		this.ipValhidden = ipValhidden;
	}

	public Vector getIpPropVector() {
		return ipPropVector;
	}

	public void setIpPropVector(Vector ipPropVector) {
		this.ipPropVector = ipPropVector;
	}

	public String getIpVal() {
		return ipVal;
	}

	public void setIpVal(String ipVal) {
		this.ipVal = ipVal;
	}

	public String getServerValue() {
		return serverValue;
	}

	public void setServerValue(String serverValue) {
		this.serverValue = serverValue;
	}

	public String execute() {
		// System.out.println("IPval==="+ipVal);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		boolean bPro = false, bFat = false;

		StringTokenizer stIPVal = new StringTokenizer(ipVal, ",");
		while (stIPVal.hasMoreTokens()) {
			ipVector.add(stIPVal.nextToken().toString());
		}
		newCountServ = ipVector.size();

		proPat = "webapps/IdentityBased/System.properties";
		propFile = new File(proPat);

		if (!propFile.exists()) {
			createPropert(propFile, ipVal);
			updateSpl();
			addActionMessage("Server Architecture created");

		} else {
			fatPath = "webapps/IdentityBased/BlocksMapping/mapping.ser";
			fatFile = new File(fatPath);
			bPro = true;

			if (fatFile.exists()) {
				bFat = true;
				System.out.println("FAT file exists");
				addActionMessage("FAT File exists");
			} else {
				System.out.println("No FAT file");
				addActionMessage("No FAT File exists");
				updateSpl();
				bFat = false;
			}

		}
		if (bPro == true && bFat == false) {

			createPropert(propFile, ipVal);

		} else if (bPro && bFat) {

			// Vector
			commonPropert();
			String splTemp[] = servers.split("\\,");
			int oldCountServ = splTemp.length;
			for (int i = 0; i < splTemp.length; i++) {
				ipPropVector.add(splTemp[i].toString());
			}
			if (newCountServ == oldCountServ) {

				boolean chk = ipVector.containsAll(ipPropVector);
				if (chk == true) {
					// update spl
					spl.clear();
					for (int i = 0; i < ipVector.size(); i++) {
						spl.add(ipVector.get(i).toString());
					}
				} else {
					addActionMessage("Ip Conflict.File Download will encounter problems.Do you want to delete FAT and Continue?");
					request.setAttribute("Conflict", "Y");
					request.setAttribute("serverNo", ipPropVector);

				}

			} else {

				request.setAttribute("serverNo", ipVector);
				request.setAttribute("Architecture", "Y");
				addActionMessage("Server Architecture is different.Do you want to delete FAT and Continue?");
			}

		}

		status = "success";
		return status;
	}

	public String setQuartz(String time) {
		auditTime.add(time);
		status = "success";
		return status;
	}

	public void commonPropert() {
		try {
			String pat = "webapps/IdentityBased/System.properties";
			File f = new File(pat);
			if (f.isFile()) {
				Properties prop = new Properties();
				FileInputStream fis = new FileInputStream(pat);
				prop.load(fis);
				servers = prop.getProperty("IP").trim();
				System.out.println("servers==" + servers);
			} else {
				System.out.println("No property file!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createPropert(File createF, String ipaddrs) {
		try {
			createF.createNewFile();
			if (createF.isFile()) {
				Properties prop = new Properties();
				prop.setProperty("IP", ipaddrs.trim());
				FileOutputStream fileOut = new FileOutputStream(createF);
				prop.store(fileOut, "Server");
				fileOut.close();
			} else {
				System.out.println("No property file!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String serverCount() {
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		Vector serv = new Vector();
		int ser = Integer.parseInt(serverValue);
		for (int i = 0; i < ser; i++) {
			serv.add(serverValue);
		}
		request.setAttribute("serverNo", serv);

		System.out.println("server===>" + serverValue);
		status = "success";
		return status;
	}

	public String conflictIP() {
		try {
			System.out.println("conflict IP " + ipValhidden);
			File f = new File("webapps/IdentityBased/BlocksMapping/mapping.ser");
			f.delete();
			proPat = "webapps/IdentityBased/System.properties";
			propFile = new File(proPat);
			createPropert(propFile, ipValhidden.trim());
			addActionMessage("Welcome to new server architecture");
			status = "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public String retainIP() {

		commonPropert();
		int cnt = 0;
		String splTemp[] = servers.split("\\,");
		int oldCountServ = splTemp.length;
		for (int i = 0; i < splTemp.length; i++) {
			AccessServiceCall serCall = new AccessServiceCall();
			String resVal = serCall.responseFromServer(splTemp[i].toString()
					.trim());

			if (resVal.equals("false")) {
				System.out.println("server not connected");
			} else {
				cnt++;
				System.out.println("server connected");
			}
		}
		if (oldCountServ == cnt)
			addActionMessage("Old Server Architecture");
		else
			addActionMessage("Server connection problem");
		status = "success";
		return status;
	}

	public String setTime() {
		try {
			auditTime.clear();
			auditTime.add(setTim);

			System.out.println("auditTime " + auditTime);
			addActionMessage("Audit Time set");
			Scheduling sch = new Scheduling();
			sch.cal1(setTim);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		status = "success";

		return status;
	}

	public void updateSpl() {
		spl.clear();
		commonPropert();
		String splTemp[] = servers.split("\\,");
		for (int i = 0; i < splTemp.length; i++) {
			spl.add(splTemp[i].toString());
		}
	}

	public String deleteUploadFiles() {
		try {
			String fNm = new String("webapps/IdentityBasedStorage/BLOCKS");
			File file = new File("webapps/IdentityBased/UploadStorage");
			if (file.exists()) {
				String files[] = file.list();
				for (String temp : files) {
					File fileDelete = new File(file, temp);
					fileDelete.delete();
				}
			}

			File file1 = new File(
					"webapps/IdentityBased/BlocksMapping/mapping.ser");
			if (file1.exists()) {
				file1.delete();
			}
			if (!spl.isEmpty()) {
				int servlen = spl.size();
				for (int i = 0; i < servlen; i++) {
					if (!spl.get(i).toString().equals("")
							&& spl.get(i).toString().contains(":")) {
						System.out.println("spl " + spl.get(i).toString());
						AccessServiceCall acs = new AccessServiceCall();
						acs.delFiles(spl.get(i).toString(), fNm);
						Thread.sleep(100);
					}
				}
				status = "success";
				addActionMessage("Cleared...");
			} else {
				status = "success";
				addActionMessage("First set server architecture...");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
}
