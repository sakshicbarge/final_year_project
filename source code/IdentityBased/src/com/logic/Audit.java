package com.logic;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Collections;

public class Audit extends ActionSupport implements CommonInter {
	String page, total, records, q, status = "";
	public static String USER_AGENT = "Mozilla/5.0";
String sen,rec,fc,userfile,auditlist,msg1,endtime="";
	java.util.Date nexRunTim;
	long runTim;

	public long getRunTim() {
		return runTim;
	}

	public void setRunTim(long runTim) {
		this.runTim = runTim;
	}

	public java.util.Date getNexRunTim() {
		return nexRunTim;
	}

	public void setNexRunTim(java.util.Date nexRunTim) {
		this.nexRunTim = nexRunTim;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getRecords() {
		return records;
	}

	public void setRecords(String records) {
		this.records = records;
	}

	public ArrayList<JqGridModel> getRows() {
		return rows;
	}

	public void setRows(ArrayList<JqGridModel> rows) {
		this.rows = rows;
	}

	ArrayList<JqGridModel> rows = new ArrayList<JqGridModel>();

	public String execute() {
		
		try {
			ArrayList<JqGridModel> jqGridModels = new ArrayList<JqGridModel>();
				// System.out.println("PublicAuditList"+publicAuditList.toString());
			JSONObject jsonObject = new JSONObject();

			String url = "http://localhost:8050/chain";
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", USER_AGENT);

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(con
					.getInputStream()));
			String inputLine;
			StringBuffer response1 = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response1.append(inputLine);
			}
			in.close();
			String data = response1.toString();
			System.out.println(data);
			Object obj1 = "";
			try {
				obj1 = new JSONParser().parse(data);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JSONObject jo = (JSONObject) obj1;

			Long blocksize = (Long) jo.get("length");
			System.out.println("blockSize---------->" + blocksize);

			System.out
					.println("**********Retreiving chain attribute from Blockchain******"
							+ jo.get("chain"));

			JSONArray array = (JSONArray) jo.get("chain");
			JSONObject jsonObject2 = null;
			JSONArray array2 = null;
			String dec = "";
			String sender="";
			for (int i = 1; i <= array.size(); i++) {
				
				try {
					jsonObject = (JSONObject) array.get(i);
					array2 = (JSONArray) jsonObject.get("transactions");
					jsonObject = (JSONObject) array2.get(0);
					System.out.print(jsonObject.get("sender"));
					System.out.println("    " + jsonObject.get("fn"));
					 sen = (String) jsonObject.get("sender");
					 rec = (String) jsonObject.get("recipient");
					 fc = (String) jsonObject.get("fn");
					 userfile = (String) jsonObject.get("userfile");
					 auditlist = (String) jsonObject.get("auditlist");
					 msg1 = (String) jsonObject.get("msg");
					 endtime = (String) jsonObject.get("endtime");
					 sender = sen + "*" + rec + "*" + fc + "*" + userfile
							+ "*" + auditlist + "*" + msg1 + "*" + endtime;
					System.out
							.println("************************sender**************************"
									+ sender);
				
				

				} catch (IndexOutOfBoundsException e) {
					
					continue;
				}

								// System.out.println("spp size"+spp.length);
				JqGridModel gridModel1 = new JqGridModel();
				gridModel1.setJobid(sen.toString());
				gridModel1.setStartTime(rec.toString());
				gridModel1.setLoc(fc.toString());
				gridModel1.setUsrFile(userfile.toString());
				gridModel1.setAudList(auditlist.toString());
				if (msg1.toString().contains("Recover")) {
					gridModel1.setStat("<font color='green'>"
							+ msg1.toString() + "</font>");
				} else {
					gridModel1.setStat(msg1.toString());
				}
				gridModel1.setEndTime(endtime.toString());
				jqGridModels.add(gridModel1);
			}
			Collections.reverse(jqGridModels);
			setPage("1");
			int tot = jqGridModels.size() / 10;
			setTotal(String.valueOf(tot + 1));
			setRecords(String.valueOf(jqGridModels.size()));
			setRows(jqGridModels);
			status = "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	public String clear() {
		publicAuditList.clear();
		return "success";
	}

}
