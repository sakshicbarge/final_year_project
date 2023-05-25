package com.logic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.sun.net.ssl.HttpsURLConnection;

public class Urlconnection {
	 public void serverCall() {
		 
		 
		 
		 System.out.println("*********************************************************************************************");
		 
		 
		 
		 
	        try {
	            StringBuilder postData = new StringBuilder();

	            postData.append(URLEncoder.encode("values", "UTF-8"));
	            postData.append("=");
	        
	            byte[] postDataBytes = postData.toString().getBytes("UTF-8");
	            URL url = null;

	            url = new URL("http://localhost:9999/IdentityBased/BlockChainRetrival"); // here is your URL path for Post
	           
	            System.out.println("***************"+url+"*************************");
	            
	            
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            conn.setReadTimeout(2000 /* milliseconds */);
	            conn.setConnectTimeout(2000 /* milliseconds */);
	            conn.setRequestMethod("POST");
	            conn.setDoInput(true);

	            conn.setDoOutput(true);
	            conn.getOutputStream().write(postDataBytes);

	            int responseCode = conn.getResponseCode();

	            if (responseCode == HttpsURLConnection.HTTP_OK) {

	                BufferedReader in = new BufferedReader(new InputStreamReader(
	                        conn.getInputStream()));

	                StringBuffer sb = new StringBuffer("");
	                String line = "";

	                while ((line = in.readLine()) != null) {

	                    sb.append(line);
	                    break;
	                }

	                in.close();
	                //Thread.sleep(2000);

	            } else {
	            }
	        } catch (Exception e) {
	        }
	 }

}
