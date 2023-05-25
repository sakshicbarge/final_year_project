package com.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Random;

public class FATFS implements CommonInter {

	LinkedHashMap lkmFAT = new LinkedHashMap();
	LinkedHashMap lkMaping = new LinkedHashMap();
	String uNme = "";
	BlockSplit block;

	public FATFS() {
	}

	public FATFS(BlockSplit block) {
		this.block = block;
		lkmFAT = readSerFile();
		// System.out.println("Block split lkm size "+lkmFAT.size());
		// System.out.println("Block split================= lkm size "+lkmFAT.keySet());
	}

	public void createFS(String uname) {
		this.uNme = uname;
		PrepareMapping();
		lkmFAT
				.put(uname + "-" + block.fileNme.split("_")[0] + ".txt",
						lkMaping);

		writeSerFile(lkmFAT);
	}

	public void writeSerFile(LinkedHashMap<String, String> blmap) {
		try {

			FileOutputStream fileOut = new FileOutputStream(new File(
					"webapps/IdentityBased/BlocksMapping/mapping.ser"));
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			// System.out.println("HashMap writeser====>"+blmap);
			out.writeObject(blmap);
			out.close();
			fileOut.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public LinkedHashMap<String, String> readSerFile() {
		LinkedHashMap<String, String> mapval = new LinkedHashMap<String, String>();
		try {
			File fch = new File(
					"webapps/IdentityBased/BlocksMapping/mapping.ser");
			if (fch.exists()) {
				FileInputStream fileIn = new FileInputStream(
						"webapps/IdentityBased/BlocksMapping/mapping.ser");
				ObjectInputStream in = new ObjectInputStream(fileIn);

				mapval = (LinkedHashMap<String, String>) in.readObject();
				// System.out.println("LinkedHashmap=======read ser file >"+mapval);
				in.close();
				fileIn.close();
			} else {
				System.out.println("No Ser File in Directory...");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapval;
	}

	public void PrepareMapping() {
		String mappingcontent = "";
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < block.lkm.size(); i++) {
			list.add(new Integer(i));
		}
		Collections.shuffle(list);
		lkmFAT.putAll(readSerFile());
		for (int j = 0; j < block.lkm.size(); j++) {
			Random ran = new Random();
			int servcount = ran.nextInt(spl.size());
			String block1 = "BLOCK_" + list.get(j);
			mappingcontent = block1 + "_" + j + "@"
					+ block.lkmTag.get(("pt" + j)) + "_" + block.extention
					+ "_"
					+ block.lkmTag.get(("ft" + (block.lkmTag.size() - 1)));
			if (!lkMaping.containsKey(mappingcontent)) {
				lkMaping.put(mappingcontent, spl.get(servcount).toString());
			}
		}
	}

	public void updateFat(String uname) {
		PrepareMapping();
		lkmFAT
				.put(uname + "-" + block.fileNme.split("_")[0] + ".txt",
						lkMaping);
		writeSerFile(lkmFAT);
	}

	public LinkedHashMap retrieveFat(String fNme, String uNme) {
		LinkedHashMap lkm = (LinkedHashMap) lkmFAT.get(uNme + "-" + fNme);
		Iterator iPrint = lkm.keySet().iterator();
		while (iPrint.hasNext()) {
			String k = iPrint.next().toString();
			// System.out.println("retrieveFat for File "+k+" ======= "+lkm.get(k).toString());
			// System.out.println("No of packets in FIle "+lkm.size());
		}
		return lkm;
	}

	public String deleteUserFat(String fNme, String uNme) {
		String stat = "";
		if (lkmFAT.containsKey(uNme + "-" + fNme)) {
			lkmFAT.remove(uNme + "-" + fNme);
			writeSerFile(lkmFAT);
			stat = "success";
		}
		return stat;
	}
}
