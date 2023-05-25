package com.logic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServlet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import service.FileUploadDelegate;
import service.FileUploadService1;
import auditservice.PublicAuditDelegate;
import auditservice.PublicAuditService;
import deleteservice.DeleteFileDelegate;
import deleteservice.DeleteFileService;

public class AccessServiceCall extends HttpServlet implements CommonInter {
	public static String USER_AGENT = "Mozilla/5.0";

	static int jobid = 0;
	ArrayList<String> arrayl = new ArrayList<String>();

	public void senDataSet(String loc, final String blockNo,
			final String blockCon, final String blocFileName) {
		try {
			FileUploadDelegate dele = CommonsFileUploadService(loc);
			dele.upload(blockNo, blockCon, blocFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void delFiles(String loc, String filName) {
		try {
			DeleteFileDelegate de = deleteService(loc);
			de.delete(filName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList retrieveDataSet(String loc, ArrayList al, String fNme) {
		ArrayList packList = null;
		try {
			FileUploadDelegate dele = CommonsFileUploadService(loc);
			packList = (ArrayList) dele.rwPackets(al, fNme);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return packList;
	}

	public ArrayList AttackerFileListFetch(String loc, String blckNo) {
		ArrayList fList = null;
		try {
			FileUploadDelegate dele = CommonsFileUploadService(loc);
			fList = (ArrayList) dele.fetchFiles(blckNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fList;
	}

	public String AttackerFileFetch(String loc, String blckNo, String fNme) {
		String content = "";
		try {
			FileUploadDelegate dele = CommonsFileUploadService(loc);
			content = dele.fetchFile(blckNo, fNme);

			// System.out.println("content  "+content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}

	public String AttackerFileSave(String loc, String blckNo, String fNme,
			String content, String sign) {
		String status = "";
		try {
			FileUploadDelegate dele = CommonsFileUploadService(loc);
			dele.saveFile(blckNo, fNme, content, sign);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public service.FileUploadDelegate CommonsFileUploadService(String loc) {
		FileUploadDelegate dele = null;
		try {
			URL baseUrl = service.FileUploadService1.class.getResource(".");
			URL url = new URL(baseUrl, "http://" + loc.trim()
					+ "/IdentityBasedStorage/FileUploadPort?wsdl");
			FileUploadService1 dataservice = new FileUploadService1(url);
			dele = dataservice.getFileUploadPort();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dele;
	}

	public deleteservice.DeleteFileDelegate deleteService(String loc) {
		DeleteFileDelegate dele = null;
		try {
			URL baseUrl = deleteservice.DeleteFileService.class
					.getResource(".");
			URL url = new URL(baseUrl, "http://" + loc.trim()
					+ "/IdentityBasedStorage/DeleteFilePort1?wsdl");
			DeleteFileService delservice = new DeleteFileService(url);
			dele = delservice.getDeleteFilePort1();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dele;
	}

	public auditservice.PublicAuditDelegate CommonsAuditService(String loc) {
		PublicAuditDelegate dele = null;
		try {
			URL baseUrl = service.FileUploadService1.class.getResource(".");
			URL url = new URL(baseUrl, "http://" + loc.trim()
					+ "/IdentityBasedStorage/PublicAuditPort?wsdl");
			PublicAuditService dataservice = new PublicAuditService(url);
			dele = dataservice.getPublicAuditPort();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dele;
	}

	public String responseFromServer(String loc) {
		String respVal = "";
		try {
			URL baseUrl = service.FileUploadService1.class.getResource(".");
			URL url = new URL(baseUrl, "http://" + loc.trim()
					+ "/IdentityBasedStorage");
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			int statusCode = http.getResponseCode();
			System.out.println("statuscode " + statusCode);
			if (statusCode == 200)
				respVal = "true";
			else
				respVal = "false";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return respVal;
	}

	public String delete(String loc, String block, String fName) {
		String status = "";
		try {
			FileUploadDelegate dele = CommonsFileUploadService(loc);
			status = dele.deleteUsrFile(block, fName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@SuppressWarnings("unchecked")
	public String auditCal(String loc, ArrayList auditList,
			HashMap blckPcMapIp, String usrFile, java.util.Date nextRunTi,
			long runTim, String ext) {
		JSONObject jsonObject = new JSONObject();
		String jstr="";
		String retStatus = "";
		try {
			System.out
					.println("============================Auditor checking starts============================");
			System.out.println(auditList);
			Thread.sleep(500);
			jobid++;
			String pckNo = "";
			String msg = "";
			String msg1 ="";
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String startTime = dateFormat.format(cal.getTime());
			// String aud="";
			PublicAuditDelegate pub = CommonsAuditService(loc);
			String stat = pub.startAudit(auditList);
			Calendar cal1 = Calendar.getInstance();
			String endTime = dateFormat.format(cal1.getTime());
			Calendar cal2 = Calendar.getInstance();
			String startTime1 = dateFormat.format(cal2.getTime());
			System.out.println("Auditing status===>" + stat);

			// ==========================PubLic Auditing Data For
			// Table=====================

			if (auditList.size() > 0) {
				String aud = "";

				HashMap blckPcMap = (HashMap) blckPcMapIp.get(loc);

				String auditing = "";
				if (!stat.equals("success")) {
					String arr[] = stat.split("-");
					String spp[] = arr[0].toString().trim().split("@");
					for (int j = 0; j < spp.length; j++) {
						if (j != spp.length) {
							Iterator itt = blckPcMap.keySet().iterator();
							while (itt.hasNext()) {
								String key = itt.next().toString();

								if (key.equals(spp[j].toString().trim())) {
									pckNo = pckNo + "&"
											+ blckPcMap.get(key).toString();
									 msg1 = "Packet" + pckNo + "Fails";
								}
							}
							aud = jobid + "*" + startTime + "*" + loc + "*"
									+ usrFile + "*" + auditList.toString()
									+ "*" + "<font color='red'>" + msg1
									+ "</font>" + "*" + endTime;
							// System.out.println("-------auditing------>"
							// +aud);
							System.out.println("-------*********************--------->" +aud);

							jsonObject.put("sender", jobid);
							jsonObject.put("recipient", startTime);
							jsonObject.put("amount", "5");
							jsonObject.put("fn", loc);
							jsonObject.put("userfile", usrFile);
							jsonObject.put("auditlist", auditList.toString());
							jsonObject.put("msg", msg1);
							jsonObject.put("endtime", endTime);

							 jstr = jsonObject.toJSONString();
							// System.out.println(jstr);
							BlockChainCall call = new BlockChainCall();
							call.addTransaction(jstr, BlockChainCall.SENT);

							try {
								Thread.sleep(1000);
							}

							catch (Exception e) {
								e.printStackTrace();
							}
							call.mineChain(BlockChainCall.MINE);
							call.mineChain(BlockChainCall.RECEIVE);
							Calendar cal3 = Calendar.getInstance();
							String endTime1 = dateFormat.format(cal3.getTime());
							if (arr[1].toString().equalsIgnoreCase("success")) {
								msg = "Recovered packet" + pckNo;
							} else {
								msg = "Recovery started for packet" + pckNo;
							}
							aud = jobid + "*" + startTime1 + "*" + loc + "*"
									+ usrFile + "*" + auditList.toString()
									+ "*" + "<font color='green'>" + msg
									+ "</font>" + "*" + endTime1;
							//publicAuditList.add(aud);

							jsonObject.put("sender", jobid);
							jsonObject.put("recipient", startTime);
							jsonObject.put("amount", "5");
							jsonObject.put("fn", loc);
							jsonObject.put("userfile", usrFile);
							jsonObject.put("auditlist", auditList.toString());
							jsonObject.put("msg", msg);
							jsonObject.put("endtime", endTime);

							jstr = jsonObject.toJSONString();
							// System.out.println(jstr);
							BlockChainCall call1 = new BlockChainCall();
							call1.addTransaction(jstr, BlockChainCall.SENT);

							try {
								Thread.sleep(1000);
							}

							catch (Exception e) {
								e.printStackTrace();
							}
							call.mineChain(BlockChainCall.MINE);
							call.mineChain(BlockChainCall.RECEIVE);

						}
					}
					if (arr[1].toString().trim().equalsIgnoreCase("fail")) {
						Download dl = new Download();
						dl.setUserName(usrFile.split("-")[0].toString());
						dl.setFileName((usrFile.split("-")[1].toString())
								.split("\\.")[0].toString().trim()
								+ "." + ext.trim());
						dl.setFlistName((usrFile.split("-")[1].toString())
								.split("\\.")[0].toString().trim()
								+ "." + ext.trim());
						dl.setType("Auditing");
						dl.deleteFile();
						dl.recoverUserComplaint();
					}
				}

				else {
					retStatus = "success";
					aud = jobid + "*" + startTime + "*" + loc + "*" + usrFile
							+ "*" + auditList.toString() + "*" + stat + "*"
							+ endTime + "@" + nextRunTi;

				}


				JSONObject json = new JSONObject();

				jsonObject.put("sender", jobid);
				jsonObject.put("recipient", startTime);
				jsonObject.put("amount", "5");
				jsonObject.put("fn", loc);
				jsonObject.put("userfile", usrFile);
				jsonObject.put("auditlist", auditList.toString());
				jsonObject.put("msg", stat);
				jsonObject.put("endtime", endTime);

				 jstr = jsonObject.toJSONString();
				// System.out.println(jstr);
				BlockChainCall call = new BlockChainCall();
				call.addTransaction(jstr, BlockChainCall.SENT);

				try {
					Thread.sleep(1000);
				}

				catch (Exception e) {
					e.printStackTrace();
				}
				call.mineChain(BlockChainCall.MINE);
				call.mineChain(BlockChainCall.RECEIVE);




//				 URL yahoo = new URL("http://localhost:9999/IdentityBased/BlockChainRetrival");
//				    URLConnection yc = yahoo.openConnection();
//				    BufferedReader in = new BufferedReader(
//				                            new InputStreamReader(
//				                            yc.getInputStream()));
			    

			}	
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retStatus;
	}

	}
