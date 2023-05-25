package com.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class PublicAudit implements CommonInter{

	String status="";
	public String startAudit(ArrayList aList)
	{		
		boolean boolForList=true; 
		String failedContent="";
		try{
			
			for(int i=0;i<aList.size();i++)
			{
				String mapping=aList.get(i).toString();
				String arr[]=mapping.split("@");
				String blckNme="BLOCK"+arr[0].toString();
				String fNme=blckNme+"_"+arr[1].toString();
				String tagSign=arr[2].toString();
				String content=fetchFileCon(blckNme, fNme);
				String arrSplit[]=content.split("@");
				String packContent=arrSplit[0].toString();
				String packSign=arrSplit[1].toString();
				String curSig=genSignature(packContent);
				if(tagSign.equals(packSign) && tagSign.equals(curSig))
				{		
					System.out.println("Signature Verified For Packet........"+blckNme);
				}
				else
				{
					System.out.println("Signature Verification Failed ........"+blckNme);
					failedContent=failedContent+arr[0].toString()+"@";
					
					boolForList=false;
				}
				Thread.sleep(1000);
			}	
			if(boolForList==false)
			{
				status=failedContent;
				String stat=finalizecheck();
				status=status+"-"+stat;
			}
			else
			{
				status="success";
			}			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	return status;
	}
	
	public String fetchFileCon(String blckNme,String fNme)
    {   
		System.out.println("Fetching File Content for Audit Process in Block "+blckNme);
    	StringBuffer sb=new StringBuffer();
    	try{
	    	File f=new File("webapps/IdentityBasedStorage/BLOCKS/"+blckNme+"/"+fNme);
	    	FileReader fis=new FileReader(f);
	    	BufferedReader br=new BufferedReader(fis);  
	    	String s=""; 
	    	while ((s=br.readLine())!=null) {
	    		sb.append(s);
			}    	
//	    	System.out.println("fetchFileCon "+sb);
	    	br.close();
	    	fis.close();	    	
    	}    	
    	catch (Exception e) {
			e.printStackTrace();
		}
    	return sb.toString();
    }
	public String genSignature(String packet)
	{
		StringBuffer sb=null; 
		try
		{
			MessageDigest mdP = MessageDigest.getInstance("MD5");		
			mdP.update(packet.getBytes());
			byte mdbytes[] = mdP.digest();
			sb = new StringBuffer();
				for (int i = 0; i < mdbytes.length; i++) 
				{
					sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}			
		return sb.toString();
	}
	public String finalizecheck()
	{
		boolean recStatus=false;
		String stat="";
		try
		{
		Iterator iFinal=suspMap.keySet().iterator();
				while (iFinal.hasNext()) {
			String key=iFinal.next().toString();			
			String suspCon=suspMap.get(key).toString();
			String arr[]=key.split("@");
			String blckNme=arr[0].toString();
			String fNme=arr[1].toString();
			FileUpload fu=new FileUpload();
			fu.b=true;
			String cont=fu.fetchFile(blckNme, fNme);			
			String spl[]=cont.split("@");
			String filePacket=spl[0].toString();
			String fileSign=spl[1].toString();			
			String fileConSign=genSignature(filePacket);
			String suspSign=genSignature(suspCon.split("@")[0].toString());			
			System.out.println("fileNewsign "+fileConSign+" "+"fileSign"+fileSign+" "+"suspSign"+suspSign);
			
			if(!fileSign.equals(fileConSign)&&(suspSign.equals(fileSign)))
			{
				recStatus=true;
				String recContent=suspCon+"@"+suspSign;
				fu.saveFile(blckNme, fNme, recContent, suspSign);
				System.out.println("file recovered");
			}
			fu.b=false;			
		}		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		if(recStatus)
		{
			stat="success";
		}
		else
		{
			stat="fail";
			System.out.println("suspicious map empty");
		}
		return stat;
	}
	
	
}
