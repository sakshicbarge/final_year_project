package com.logic;

public class FileUploadBean {
	String serverLoc, blockNo, blockSign, fileName = "";

	public String getBlockSign() {
		return blockSign;
	}

	public void setBlockSign(String blockSign) {
		this.blockSign = blockSign;
	}

	public String getServerLoc() {
		return serverLoc;
	}

	public void setServerLoc(String serverLoc) {
		this.serverLoc = serverLoc;
	}

	public String getBlockNo() {
		return blockNo;
	}

	public void setBlockNo(String blockNo) {
		this.blockNo = blockNo;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
