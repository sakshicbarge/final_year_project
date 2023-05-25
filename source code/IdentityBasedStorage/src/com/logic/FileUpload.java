package com.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class FileUpload implements CommonInter {
		HashMap<String,String> filemap=new HashMap<String,String>();
		boolean b=false;
    public void upload(String block,String cont,String fname)
    {
    	try 
		{
			String blo="webapps/IdentityBasedStorage/BLOCKS";			
			File blocksfile=new File(blo);
			if(!blocksfile.exists())
			{
				blocksfile.mkdir();
				for(int i=0;i<=30;i++)
				{
		    		 new File("webapps/IdentityBasedStorage/BLOCKS/BLOCK"+i).mkdir();
				}
				System.out.println("Cloud Space Created ");
			}
			if(blocksfile.listFiles().length==0)
			{
				for(int i=0;i<=30;i++)
				{
		    		 new File("webapps/IdentityBasedStorage/BLOCKS/BLOCK"+i).mkdir();
				}
			}
			
		//-------------------------------------block splitting------------------------------------//
			System.out.println("******i************************blocks***********" +block);
			
			File f=new File("webapps/IdentityBasedStorage/BLOCKS");			
			File fFodr=new File("webapps/IdentityBasedStorage/BLOCKS/"+block);
			if(fFodr.exists())
			{
				File blckF=new File("webapps/IdentityBasedStorage/BLOCKS/"+block+"/"+block+"_"+fname);		
				if(blckF.exists())
				{
					blckF.delete();
				}
				FileWriter fw=new FileWriter(blckF);
				fw.write(cont);
				fw.close();
			}
			else
			{
				System.out.println("File Upload Encountered Pblm.....Check Storage Blocks(30 cnt)");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
    }
    public ArrayList RwPackets(ArrayList blckName,String fName)
    {
    	ArrayList conlst=new ArrayList();
    	try{
    		b=true;
    		for(int i=0;i<blckName.size();i++)   		
		    	{
		    		String bNo=blckName.get(i).toString();		    		
		    		String blckNme="BLOCK"+bNo;
		    		String fNme="BLOCK"+bNo+"_"+fName;
		    		String pckCon=fetchFile(blckNme, fNme);
		    		String pckCont=pckCon.split("@")[0].toString();
		    		conlst.add(pckCont);
		    		System.out.println("**********packet*********" +pckCont+ "**********packet**********" +pckCon);
		    	}
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}
    	b=false;
    	return conlst;
    }
    public ArrayList fetchFiles(String fdrNme)
    {   
    	ArrayList al = new ArrayList();
    	try
    	{
    	File f=new File("webapps/IdentityBasedStorage/BLOCKS/"+fdrNme);
    	File[] fArr=f.listFiles();
    	
    	for(int i=0;i<fArr.length;i++)
    	{
    		al.add(fArr[i].getName().toString().trim());
    	}
    	if(al!=null)
    	{
    	System.out.println(al.toString());
    	}
//    	al=new ArrayList(Arrays.asList(fArr));
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}
    	return al; 
    }
    public String fetchFile(String blckNme,String fNme)
    {
    	System.out.println("Fetching File Content in block "+blckNme+" & file "+fNme);
    	Timestamp curtm=new Timestamp(new Date().getTime());
    	long timDiff=0;
    	timVector.add(curtm);
    	if(timVector.size()==2)
    	{    			
    		timDiff=(timVector.get(1).getTime())-(timVector.get(0).getTime());
    		timVector.remove(timVector.firstElement()); 
    	}	
    	StringBuffer sb=new StringBuffer();
    	try
    	{
	    	File f=new File("webapps/IdentityBasedStorage/BLOCKS/"+blckNme+"/"+fNme);
	    	FileReader fis=new FileReader(f);
	    	BufferedReader br=new BufferedReader(fis);  
	    	String s=""; 
	    	while ((s=br.readLine())!=null) {
	    		sb.append(s);
			}    	
	//    	System.out.println("fetchFile "+sb);
	    	br.close();
	    	fis.close();
	    	//System.out.println("Time Difference id "+timDiff+" boolean "+b);
	    	if(timDiff>2000 && b==false)
	    	{
	    		suspMap.put(blckNme+"@"+fNme, sb.toString());
	    	}
	    	if(b)
	    	{
	    		System.out.println("File Deleted..............");
	    		f.delete();
	    	}
    	}  	
    	catch (Exception e) 
    	{
			e.printStackTrace();
		}
    	return sb.toString();
    }
    public String saveFile(String blckNme,String fNme,String encData,String sign)
    {
    	String status="";
    	try
    	{
    		String contentToSave=encData+"@"+sign;
    		File f=new File("webapps/IdentityBasedStorage/BLOCKS/"+blckNme+"/"+fNme);
    		FileOutputStream fos=new FileOutputStream(f);
    		fos.write(contentToSave.getBytes());
    		fos.close();    		
    	}
    	catch (Exception e)
    	{
			e.printStackTrace();
		}
    	return status;
    }
    public String deleteUsrFile(String block,String filName)
    {
    	String status="";
    	String blo="webapps/IdentityBasedStorage/BLOCKS";			
		File blocksfile=new File(blo);
		if(blocksfile.exists())
		{
			File fFodr=new File("webapps/IdentityBasedStorage/BLOCKS/"+block);
			if(fFodr.exists())
			{
				File blckF=new File("webapps/IdentityBasedStorage/BLOCKS/"+block+"/"+block+"_"+filName);		
				if(blckF.exists())
				{
					blckF.delete();
					status="success";
				}				
			}			
		}
		else
		{
			status="error";
		}
		//System.out.println("status in webservice file delete"+status);
    	return status;
    }  
}
