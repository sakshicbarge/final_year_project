/** To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.logic;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;

/**
 *
 */
public class BlockChainCall {
	public static String SENT = "http://localhost:8050/transactions";
	public static String RECEIVE = "http://localhost:8050/chain";
	public static String MINE = "http://localhost:8050/mine";

	// public sa

	public String addTransaction(String jsonInput, String urlLink) {
		try {
			URL url = new URL(urlLink);
			System.out.println(urlLink + "------------JSON INPUT--------->"
					+ jsonInput);
			HttpURLConnection httpCon = (HttpURLConnection) url
					.openConnection();
			httpCon.setRequestProperty("Content-Type", "application/json");
			httpCon.setRequestMethod("POST");
			httpCon.setDoInput(true);
			httpCon.setDoOutput(true);
			OutputStreamWriter out = new OutputStreamWriter(httpCon
					.getOutputStream());
			out.write(jsonInput);
			out.close();
			System.out.println(httpCon.getResponseCode());
			System.out.println(httpCon.getResponseMessage());
			InputStreamReader ir = new InputStreamReader(httpCon
					.getInputStream());
			char b[] = new char[1024];
			int l = 0;
			while (ir.ready()) {
				l = ir.read(b);
				System.out.println(new String(b, 0, l));
			}
		} catch (Exception e) {
			return "no";
		}
		return "ok";
	}

	public void mineChain(String urlLink) {
		try {
			URL url = new URL(urlLink);
			HttpURLConnection httpCon = (HttpURLConnection) url
					.openConnection();
			httpCon.setDoOutput(false);
			httpCon.setRequestMethod("GET");
			System.out.println(httpCon.getResponseCode());
			System.out.println(httpCon.getResponseMessage());
			InputStreamReader ir = new InputStreamReader(httpCon
					.getInputStream());
			char b[] = new char[1024];
			int l = 0;
			while (ir.ready()) {
				l = ir.read(b);
				System.out.println(new String(b, 0, l));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
