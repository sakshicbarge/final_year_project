package com.logic;

import java.io.File;

@javax.jws.WebService(targetNamespace = "http://logic.com/", serviceName = "DeleteFileService", portName = "DeleteFilePort1", wsdlLocation = "WEB-INF/wsdl/DeleteFileService.wsdl")
public class DeleteFileDelegate {

	com.logic.DeleteFile deleteFile = new com.logic.DeleteFile();

	public void delete(String fName) {
		deleteFile.delete(fName);
	}

	public boolean deleteDir(File dir) {
		return deleteFile.deleteDir(dir);
	}

}