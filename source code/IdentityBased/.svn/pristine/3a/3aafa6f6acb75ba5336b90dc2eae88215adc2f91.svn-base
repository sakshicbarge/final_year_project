package com.logic;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

import com.opensymphony.xwork2.ActionSupport;

public class Attacker extends ActionSupport{
	
	String status;
	String serverUI,blocks,files,server,content,editContent;
	public static String sign="";
	public String getEditContent() {
		return editContent;
	}
	public void setEditContent(String editContent) {
		this.editContent = editContent;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getServerUI() {
		return serverUI;
	}
	public void setServerUI(String serverUI) {
		this.serverUI = serverUI;
	}
	ArrayList servList=new ArrayList();
	ArrayList blockList=new ArrayList();
	ArrayList fileList=new ArrayList();

	public ArrayList getFileList() {
		return fileList;
	}
	public void setFileList(ArrayList fileList) {
		this.fileList = fileList;
	}
	String serverName,blockName;
	
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	public ArrayList getServList() {
		return servList;
	}
	public void setServList(ArrayList servList) {
		this.servList = servList;
	}
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public String getBlocks() {
		return blocks;
	}
	public void setBlocks(String blocks) {
		this.blocks = blocks;
	}
	public String getFiles() {
		return files;
	}
	public void setFiles(String files) {
		this.files = files;
	}
	public ArrayList getBlockList() {
		return blockList;
	}
	public void setBlockList(ArrayList blockList) {
		this.blockList = blockList;
	}
	public String execute()
	{
		String st=uiListData();
		if(st.equalsIgnoreCase("success"))
		{
			status="success";
		}
		else
		{
			status="error";
		}
		return status;
	}
	public String saveFile()
	{
		if(!serverUI.equalsIgnoreCase("----Select----") && !blocks.equalsIgnoreCase("----Select----") && !files.equalsIgnoreCase("----Select----") && !editContent.equals(""))
		{
			AccessServiceCall asc=new AccessServiceCall();
			asc.AttackerFileSave(serverUI, blocks, files, editContent, sign);
			uiListData();
		}		
		return "success";
	}
	public String getFilesFromServer()
	{
		try{
			
			AccessServiceCall asc=new AccessServiceCall();
			fileList.add("----Select----");
			uiListData();			
			if(!serverUI.equalsIgnoreCase("----Select----") && !blocks.equalsIgnoreCase("----Select----")&& files==null)
			{
				fileList.addAll(asc.AttackerFileListFetch(serverUI, blocks));
			}
			else if(!serverUI.equalsIgnoreCase("----Select----") && !blocks.equalsIgnoreCase("----Select----") && !files.equalsIgnoreCase("----Select----"))
			{
				String encContent=asc.AttackerFileFetch(serverUI, blocks,files);	
				fileList.addAll(asc.AttackerFileListFetch(serverUI, blocks));
//				Base64 bs=new Base64();
//				content=new String(bs.decode(encContent.split("\\@")[0].toString().trim().getBytes()));
				//System.out.println("encCon "+encContent);
				String arr[]=null;
				if(encContent.contains("@"))
				{
				arr=encContent.split("@");
				}
				content=arr[0].toString().trim();
				sign=arr[1].toString().trim();				
			}
			//System.out.println("servername"+serverUI+"blockname"+blocks);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return "success";
	}
	public String uiListData()
	{
		String stat="";
		boolean b=commonPropert();
		if(b){
		if(server!="")
		{
			String splTemp[]=server.split("\\,");
			int oldCountServ=splTemp.length;
			servList.add("----Select----");
			blockList.add("----Select----");
			for(int i=0;i<splTemp.length;i++)
			{
				servList.add(splTemp[i].toString());		
			}
			
			File f=new File("webapps/IdentityBasedStorage/BLOCKS");
			if(f.exists())
			{
				File[] flength=f.listFiles();				
				for(int j=0;j<flength.length;j++)
				{
					String filName=flength[j].getName().toString();
					blockList.add(filName);
				}
			}
			stat="success";
		}
		
		}
		else
		{
			stat="error";
		}
		return stat;
	}
	public boolean commonPropert()
	{
		boolean b=false;
		try
		{
			String pat="webapps/IdentityBased/System.properties";
			File f=new File(pat);
			
			if(f.isFile())
			{
				Properties prop=new Properties();
		        FileInputStream fis=new FileInputStream(pat);
		        prop.load(fis);
		        server=prop.getProperty("IP").trim();
		        //System.out.println("servers===="+server);
		        b=true;
			}
			else
			{
				System.out.println("No property file!");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return b;
	}
}
