package com.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

public class UserFATTable implements SessionAware{
	String page,total,records,status="";
	SessionMap<String,String> sessionmap;
	ArrayList<UserFatListBean> rows=new ArrayList<UserFatListBean>();
	
	public ArrayList<UserFatListBean> getRows() {
		return rows;
	}
	public void setRows(ArrayList<UserFatListBean> rows) {
		this.rows = rows;
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
	public SessionMap<String, String> getSessionmap() {
		return sessionmap;
	}
	public void setSessionmap(SessionMap<String, String> sessionmap) {
		this.sessionmap = sessionmap;
	}
	public void setSession(Map<String, Object> arg0) {
		sessionmap=(SessionMap)arg0;		
	}
	public String execute()
	{
		try
		{
//			System.out.println("get fat");
			LinkedHashMap fatMap=new LinkedHashMap();
			String usName=sessionmap.get("usrname").toString();
			FATFS f=new FATFS();
			fatMap=f.readSerFile();
			ArrayList<UserFatListBean> jqGridModels1 = new ArrayList<UserFatListBean>();

			Iterator it2=fatMap.keySet().iterator();
			while(it2.hasNext())
			{			
				String key=it2.next().toString();
				if(key.contains(usName))
				{					
					LinkedHashMap usrFileMap=(LinkedHashMap)fatMap.get(key);			
					LinkedHashMap<String, String> blckPacketMap=null;
					LinkedHashSet<String> blckTagSet=null;
					Iterator itFlMap=usrFileMap.keySet().iterator();
					String fNme="",ipp="",blockNo="",packno="",Sign="";
					String spkey[]=key.split("-");
					fNme=spkey[1].toString();
					LinkedHashMap<String,LinkedHashMap> blckPacketMapIp=new LinkedHashMap<String,LinkedHashMap>();
					LinkedHashMap<String,LinkedHashSet> blckTagMapIp=new LinkedHashMap<String,LinkedHashSet>();
					while(itFlMap.hasNext())
					{	
						String mapping=itFlMap.next().toString();
						String ip=usrFileMap.get(mapping).toString().trim();
						String blckNo=mapping.split("_")[1].toString();		
						String tagwithPacket=mapping.split("_")[2].toString().trim();
						int packNo=Integer.parseInt(tagwithPacket.split("@")[0].toString().trim());
						String tag=tagwithPacket.split("@")[1].toString();						
						if(blckPacketMapIp.containsKey(ip))
						{
							blckPacketMap=blckPacketMapIp.get(ip);
							blckTagSet=blckTagMapIp.get(ip);
							blckTagSet.add(tag);
							blckPacketMap.put(blckNo,String.valueOf(packNo));
							blckPacketMapIp.put(ip,blckPacketMap);
							blckTagMapIp.put(ip, blckTagSet);
						}
						else 
						{
							blckPacketMap=new LinkedHashMap<String, String>();
							blckTagSet=new LinkedHashSet<String>();
							blckTagSet.add(tag);
							blckPacketMap.put(blckNo,String.valueOf(packNo));
							blckPacketMapIp.put(ip,blckPacketMap);
							blckTagMapIp.put(ip, blckTagSet);
						}
					}
				

					Iterator ii=blckTagMapIp.keySet().iterator();
					while(ii.hasNext())
					{
						String ip=ii.next().toString().trim();
						//System.out.println(ip);
						//System.out.println(blckPacketMapIp);
						blockNo=blockNo+blckPacketMapIp.get(ip).keySet().toString().replace("[","").replace("]","").replace(",","\n").replace(" ","")+"\n";
						packno=packno+blckPacketMapIp.get(ip).values().toString().replace("[","").replace("]","").replace(",","\n").replace(" ","")+"\n";
						Sign=Sign+blckTagMapIp.get(ip).toString().replace("[","").replace("]","").replace(",","\n").replace(" ","")+"\n";
						ipp=ipp+ip;
						for(int i=0;i<blckPacketMapIp.get(ip).size();i++)
						{
							ipp=ipp+"\n";
						}
					}
					//System.out.println(ipp);
					//System.out.println(blockNo);
					//System.out.println(packno);
					//System.out.println(Sign);
					UserFatListBean usrFat=new UserFatListBean();	
					usrFat.setSerLoc(ipp);										
					usrFat.setUsrFile(fNme);
					usrFat.setBlckNo(blockNo);
					usrFat.setPackNo(packno);
					usrFat.setBlckSign(Sign);					
					jqGridModels1.add(usrFat);
				}
									
			}
			setPage("1");
			setTotal(String.valueOf(jqGridModels1.size()));
			setRecords(String.valueOf(jqGridModels1.size()));
			setRows(jqGridModels1);
			status="success";
		}
		catch(Exception e)
		{ 
			e.printStackTrace();
		}
		return status;
	}

}
