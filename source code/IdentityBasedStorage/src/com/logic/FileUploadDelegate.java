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

@javax.jws.WebService(targetNamespace = "http://logic.com/", serviceName = "FileUploadService1", portName = "FileUploadPort", wsdlLocation = "WEB-INF/wsdl/FileUploadService1.wsdl")
public class FileUploadDelegate {

	com.logic.FileUpload fileUpload = new com.logic.FileUpload();

	public void upload(String block, String cont, String fname) {
		fileUpload.upload(block, cont, fname);
	}

	public ArrayList RwPackets(ArrayList blckName, String fName) {
		return fileUpload.RwPackets(blckName, fName);
	}

	public ArrayList fetchFiles(String fdrNme) {
		return fileUpload.fetchFiles(fdrNme);
	}

	public String fetchFile(String blckNme, String fNme) {
		return fileUpload.fetchFile(blckNme, fNme);
	}

	public String saveFile(String blckNme, String fNme, String encData,
			String sign) {
		return fileUpload.saveFile(blckNme, fNme, encData, sign);
	}

	public String deleteUsrFile(String block, String filName) {
		return fileUpload.deleteUsrFile(block, filName);
	}

}