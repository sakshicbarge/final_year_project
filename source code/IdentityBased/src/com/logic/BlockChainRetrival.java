//package com.logic;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.LinkedHashSet;
//import java.util.Set;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//public class BlockChainRetrival extends HttpServlet   implements CommonInter {
//	private static final long serialVersionUID = 1L;
//	public static String USER_AGENT = "Mozilla/5.0";
//
//	@SuppressWarnings("unchecked")
//	public void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		response.setContentType("text/html");
//		JSONObject jsonObject = new JSONObject();
//
//		String url = "http://localhost:8050/chain";
//		URL obj = new URL(url);
//		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//
//		con.setRequestMethod("GET");
//		con.setRequestProperty("User-Agent", USER_AGENT);
//
//		int responseCode = con.getResponseCode();
//		System.out.println("\nSending 'GET' request to URL : " + url);
//		System.out.println("Response Code : " + responseCode);
//
//		BufferedReader in = new BufferedReader(new InputStreamReader(con
//				.getInputStream()));
//		String inputLine;
//		StringBuffer response1 = new StringBuffer();
//
//		while ((inputLine = in.readLine()) != null) {
//			response1.append(inputLine);
//		}
//		in.close();
//		String data = response1.toString();
//		System.out.println(data);
//		Object obj1 = "";
//		try {
//			obj1 = new JSONParser().parse(data);
//		} catch (ParseException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		JSONObject jo = (JSONObject) obj1;
//
//		Long blocksize = (Long) jo.get("length");
//		System.out.println("blockSize---------->" + blocksize);
//
//		System.out
//				.println("**********Retreiving chain attribute from Blockchain******"
//						+ jo.get("chain"));
//
//		JSONArray array = (JSONArray) jo.get("chain");
//		JSONObject jsonObject2 = null;
//		JSONArray array2 = null;
//		String dec = "";
//		String sender="";
//		for (int i = 1; i <= array.size(); i++) {
//			
//			try {
//				jsonObject = (JSONObject) array.get(i);
//				array2 = (JSONArray) jsonObject.get("transactions");
//				jsonObject = (JSONObject) array2.get(0);
//				System.out.print(jsonObject.get("sender"));
//				System.out.println("    " + jsonObject.get("fn"));
//				String sen = (String) jsonObject.get("sender");
//				String rec = (String) jsonObject.get("recipient");
//				String fc = (String) jsonObject.get("fn");
//				String userfile = (String) jsonObject.get("userfile");
//				String auditlist = (String) jsonObject.get("auditlist");
//				String msg1 = (String) jsonObject.get("msg");
//				String endtime = (String) jsonObject.get("endtime");
//				 sender = sen + "*" + rec + "*" + fc + "*" + userfile
//						+ "*" + auditlist + "*" + msg1 + "*" + endtime;
//				System.out
//						.println("************************sender**************************"
//								+ sender);
//			
//			
//				publicAuditList.add(sender);
//
//			} catch (IndexOutOfBoundsException e) {
//				
//				continue;
//			}
//
//
//			
//		}
//
//		RequestDispatcher rd = request.getRequestDispatcher("Auditing.jsp");
//	    rd.forward(request, response);
//
//	}
//
//}
