package com.logic;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Collections;

public class PublicAudit extends ActionSupport implements CommonInter {
	String page, total, records, q, status = "";
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
			for (int i = 0; i < publicAuditList.size(); i++) {
				// System.out.println("PublicAuditList"+publicAuditList.toString());
				String audVal = publicAuditList.get(i).toString();
				String spp[] = audVal.split("\\*");
				// System.out.println("spp size"+spp.length);
				JqGridModel gridModel1 = new JqGridModel();
				gridModel1.setJobid(spp[0].toString());
				gridModel1.setStartTime(spp[1].toString());
				gridModel1.setLoc(spp[2].toString());
				gridModel1.setUsrFile(spp[3].toString());
				gridModel1.setAudList(spp[4].toString());
				if (spp[5].toString().contains("Recover")) {
					gridModel1.setStat("<font color='green'>"
							+ spp[5].toString() + "</font>");
				} else {
					gridModel1.setStat(spp[5].toString());
				}
				gridModel1.setEndTime(spp[6].toString());
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
