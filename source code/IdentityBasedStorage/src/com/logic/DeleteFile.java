package com.logic;

import java.io.File;

public class DeleteFile {
	public void delete(String fName)
	{
		File dir=new File(fName);
		System.out.println("cleared "+fName);
		deleteDir(dir);
	}
	public static boolean deleteDir(File dir) {
	      if (dir.isDirectory()) {
	         String[] children = dir.list();
	         for (int i = 0; i < children.length; i++) {
	            boolean success = deleteDir(new File(dir, children[i]));
	            if (!success) {
	               return false;
	            }
	         }
	      }
	      return dir.delete();
}
}
