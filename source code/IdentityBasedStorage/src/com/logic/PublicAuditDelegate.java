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

@javax.jws.WebService(targetNamespace = "http://logic.com/", serviceName = "PublicAuditService", portName = "PublicAuditPort", wsdlLocation = "WEB-INF/wsdl/PublicAuditService.wsdl")
public class PublicAuditDelegate {

	com.logic.PublicAudit publicAudit = new com.logic.PublicAudit();

	public String startAudit(ArrayList aList) {
		return publicAudit.startAudit(aList);
	}

	public String fetchFileCon(String blckNme, String fNme) {
		return publicAudit.fetchFileCon(blckNme, fNme);
	}

	public String genSignature(String packet) {
		return publicAudit.genSignature(packet);
	}

	public void finalizecheck() {
		publicAudit.finalizecheck();
	}

}