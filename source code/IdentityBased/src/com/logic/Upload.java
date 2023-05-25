package com.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;


import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import com.mysql.jdbc.ResultSetMetaData;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class Upload extends ActionSupport implements SessionAware, CommonInter {

	SessionMap<String, String> sessionmap;
	ArrayList<FileUploadBean> filelist = new ArrayList<FileUploadBean>();
	ArrayList<UserFatListBean> usrFatList = new ArrayList<UserFatListBean>();
	String concatedstring = "";
	String appendstring = "";
	String uploadMsg, mappingcontent = "", encodedcontent = "";

	public String getUploadMsg() {
		return uploadMsg;
	}

	public void setUploadMsg(String uploadMsg) {
		this.uploadMsg = uploadMsg;
	}

	String dum = "";
	String ownerpublickey = "";
	String ownerprivatekey = "";
	StringBuffer strContent = new StringBuffer("");
	Vector tagAndBlockVec = new Vector();
	LinkedHashMap chunk = new LinkedHashMap();
	LinkedHashMap tag = new LinkedHashMap();
	LinkedHashMap readMap = new LinkedHashMap();
	String ar[];

	private String uploadDocFileName;
	private String uploadDocContentType;
	private String destPath;
	private String group;
	private String gl;
	private static final long serialVersionUID = 1L;
	private String status = "";
	private String username;
	private File uploadDoc;
	private String pki1;

	public ArrayList<FileUploadBean> getFilelist() {
		return filelist;
	}

	public void setFilelist(ArrayList<FileUploadBean> filelist) {
		this.filelist = filelist;
	}

	public SessionMap<String, String> getSessionmap() {
		return sessionmap;
	}

	public void setSessionmap(SessionMap<String, String> sessionmap) {
		this.sessionmap = sessionmap;
	}

	public void setSession(Map<String, Object> arg0) {
		sessionmap = (SessionMap) arg0;
	}

	public void setUploadDoc(File uploadDoc) {
		this.uploadDoc = uploadDoc;
	}

	public String display() {

		return NONE;
	}

	public File getUploadDoc() {
		return uploadDoc;
	}

	public String getUploadDocFileName() {
		return uploadDocFileName;
	}

	public void setUploadDocFileName(String uploadDocFileName) {
		this.uploadDocFileName = uploadDocFileName;
	}

	public String getUploadDocContentType() {
		return uploadDocContentType;
	}

	public void setUploadDocContentType(String uploadDocContentType) {
		this.uploadDocContentType = uploadDocContentType;
	}

	public String execute() {
		try {
			
			String uname = sessionmap.get("usrname").toString();
			System.out.println(uploadDocFileName
					+ "==============uploadDocFileName");
			String fNmeWithOutExt = uploadDocFileName.split("\\.")[0];
			String ext = uploadDocFileName.split("\\.")[1];
			sessionmap.put("fNmeWithOutExt", fNmeWithOutExt);
			sessionmap.put("ext", ext);
			File fd = new File("webapps/IdentityBased/UploadStorage/");
			destPath = "webapps/IdentityBased/UploadStorage/";
			if (!fd.exists()) {
				fd.mkdir();
				System.out
						.println("FOlder for Upload files created suucessfully...");
			}
			FATFS fs = new FATFS();
			LinkedHashMap lkm = fs.readSerFile();
			Iterator it = lkm.keySet().iterator();
			System.out.println(" No Of  Files in FAT " + lkm.size());
			System.out.println(lkm.keySet());
			boolean b = true;
			while (it.hasNext()) {
				String userFile = it.next().toString();
				if (userFile.contains(fNmeWithOutExt)) {
					b = false;
					break;
				}
			}
			if (b) {
				File destFile = new File(destPath, uploadDocFileName);
				FileUtils.copyFile(uploadDoc, destFile);

				Base64 bs64 = new Base64();
				FileInputStream imageInFile = new FileInputStream(destFile);
				byte imageData[] = new byte[(int) destFile.length()];
				imageInFile.read(imageData);
				String Base64String = bs64.encodeBytes(imageData);
				String destSaveBlock = "webapps/IdentityBased/UploadStorage/"
						+ fNmeWithOutExt + "_enc.txt";
				File ff = new File(destSaveBlock);
				if (!ff.exists()) {
					ff.createNewFile();
				}
				FileOutputStream fos = new FileOutputStream(ff);
				fos.write(Base64String.getBytes());
				fos.close();

				// ==================="File Upload and Base 64 Conversion fininshed================"

				File fff = new File("webapps/IdentityBased/UploadStorage/"
						+ fNmeWithOutExt + "_enc.txt");
				BlockSplit block = new BlockSplit();
				tagAndBlockVec = block.splitFile(fff, ext);
				chunk = (LinkedHashMap) tagAndBlockVec.get(0);
				tag = (LinkedHashMap) tagAndBlockVec.get(1);
				// System.out.println("tag Map Size===(Should be greater than chunk size by 1)==="+tag.size());
				System.out.println("chunk Map Size======" + chunk.size());
				imageInFile.close();

				// ================"Block Splitting process Finished==================="

				File f5 = new File("webapps/IdentityBased/BlocksMapping");
				if (!f5.exists()) {
					f5.mkdir();
					System.out.println("New BlockMapping Directory Created");
				}
				FATFS fat = new FATFS(block);
				fat.createFS(uname);

				// ===================="Fat File System Updated with new File "================

				readMap = (LinkedHashMap) fat.retrieveFat(fNmeWithOutExt
						+ ".txt", uname);
				int len = readMap.size();
				DistributePackets(readMap, chunk, fNmeWithOutExt,
						uploadDocFileName);

				uploadMsg = "File Uploaded successfully";
				status = "success";
			} else {
				status = "error";
				filelist = null;
				uploadMsg = "Filename already exists.Try with another name or upload some other file....";
			}
			// }
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return status;
	}

	public ArrayList<FileUploadBean> DistributePackets(LinkedHashMap map,
			LinkedHashMap chunkContent, String fNmeNoExt, String fName) {
		int servlen = spl.size();
		System.out.println("**********Packet Distribution***********");
		for (int i = 0; i < servlen; i++) {
			if (!spl.get(i).toString().equals("")
					&& spl.get(i).toString().contains(":")) {
				final int cc = i;
				try {
					Iterator it2 = map.keySet().iterator();
					while (it2.hasNext()) {
						String key = it2.next().toString();
						String split[] = key.split("@");
						String blckMapString = split[0].toString();
						String contentMapString = split[1].toString();
						String value = map.get(key).toString();
						if (spl.get(i).toString().equalsIgnoreCase(value)) {
							String mapSpl[] = blckMapString.split("_");
							String dataBlckNo = mapSpl[1].toString();
							String dataPackNo = mapSpl[2].toString();
							String tagsign = contentMapString.split("_")[0]
									.toString();

							System.out.println("IP  " + value + " FileName "
									+ fName + " BLOCK " + dataBlckNo
									+ " Signature " + tagsign);
							FileUploadBean upbean = new FileUploadBean();
							upbean.setServerLoc(spl.get(i).toString());
							upbean.setBlockNo("BLOCK" + dataBlckNo);
							upbean.setBlockSign(tagsign);
							upbean.setFileName(fName);
							filelist.add(upbean);
							
							
							
							
							
							
							AccessServiceCall sercall = new AccessServiceCall();
							sercall.senDataSet(spl.get(i).toString(), "BLOCK"
									+ dataBlckNo, chunkContent.get(
									"s" + dataPackNo).toString()
									+ "@" + tagsign, fNmeNoExt + ".txt");
							Thread.sleep(100);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return filelist;
	}

	public ArrayList<UserFatListBean> getUsrFatList() {
		return usrFatList;
	}

	public void setUsrFatList(ArrayList<UserFatListBean> usrFatList) {
		this.usrFatList = usrFatList;
	}

}
