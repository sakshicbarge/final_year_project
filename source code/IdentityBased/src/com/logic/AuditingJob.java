package com.logic;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;

public class AuditingJob implements Job, CommonInter {
	boolean stat = false;

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			arg0.getScheduler();

			Date nextRunTim = arg0.getNextFireTime();
			long runTim = arg0.getJobRunTime();

			Random r = new Random();
			File f = new File("webapps/IdentityBased/BlocksMapping/mapping.ser");
			if (f.exists()) {
				FATFS fs = new FATFS();
				LinkedHashMap lkm = fs.readSerFile();
				Iterator it = lkm.keySet().iterator();
				System.out.println("No of Files in FAT " + lkm.size());
				System.out.println("Files in FAT " + lkm.keySet());
				while (it.hasNext()) {
					String userFile = it.next().toString();
					LinkedHashMap lkmUser = (LinkedHashMap) lkm.get(userFile);
					System.out.println("No Of Packets in File " + userFile
							+ " is " + lkmUser.size());
					if (!auditingMap.isEmpty()) {
						if (userFile.contains(auditingMap.get("UserProcess")
								.toString())) {
							System.out
									.println("==============File skipped====================");
							continue;
						}
					}
					int blckRan = r.nextInt(lkmUser.size() + 1);
					while (blckRan < 1) {
						blckRan = r.nextInt(lkmUser.size() + 1);
					}
					System.out.println("Random packets taken for audit "
							+ blckRan);
					Vector vCheckUser = new Vector();
					for (int ii = 0; ii < blckRan; ii++) {
						int uBlckNo = r.nextInt(lkmUser.size());
						if (!vCheckUser.contains(uBlckNo)) {
							vCheckUser.add(uBlckNo);
						}
					}
					System.out.println("No of Packets For AUditng "
							+ vCheckUser.size() + " with packets "
							+ vCheckUser.toString());
					String userAccessFile = "";
					String auditfNme = userFile.split("-")[1].toString().trim();
					currentAuditList.add(auditfNme);
					prepareAuditMap(vCheckUser, lkmUser, userFile, nextRunTim,
							runTim);
					currentAuditList.clear();
				}
				auditingMap.clear();
			}
			// if(auditingMap.containsKey("UserProcess"))
			// {
			// userAccessFile=auditingMap.get("UserProcess").toString();
			// System.out.println("useraccessfile if"+userAccessFile);
			//
			// while (auditfNme.equalsIgnoreCase(userAccessFile))
			// {
			// System.out.println("useraccessfile "+userAccessFile);
			// userAccessFile=auditingMap.get("UserProcess").toString();
			// Thread.sleep(100);
			// }
			// auditingMap.put("AuditProcess", auditfNme);
			// prepareAuditMap(vCheckUser,lkmUser,userFile,nextRunTim,runTim);
			// }
			// else
			// {
			// auditingMap.put("AuditProcess", auditfNme);
			// prepareAuditMap(vCheckUser,lkmUser,userFile,nextRunTim,runTim);
			// }
			// }
			// auditingMap.remove("AuditProcess");

			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String prepareAuditMap(Vector vCheckUser, LinkedHashMap lkmUser,
			String userFile, java.util.Date nextRunTim, long runTim)
			throws InterruptedException {
		boolean bool = false;
		String ext = "";
		ArrayList al = new ArrayList();
		if (!vCheckUser.isEmpty()) {
			LinkedHashMap auditMap = new LinkedHashMap();
			HashMap<String, String> blckPacketMap = null;
			HashMap<String, HashMap> blckPacketMapIp = new HashMap<String, HashMap>();
			Iterator lkmIt = lkmUser.entrySet().iterator();
			while (lkmIt.hasNext()) {
				ArrayList alCheck = new ArrayList();
				String entryVal = lkmIt.next().toString();
				StringTokenizer str = new StringTokenizer(entryVal, "=");
				String mapping = str.nextToken().toString().trim();
				String ip = str.nextToken().toString().trim();
				String blckNo = mapping.split("_")[1].toString();
				String tagwithPacket = mapping.split("_")[2].toString().trim();
				ext = mapping.split("_")[3].toString().trim();
				int packNo = Integer.parseInt(tagwithPacket.split("@")[0]
						.toString().trim());
				String tag = tagwithPacket.split("@")[1].toString();

				if (blckPacketMapIp.containsKey(ip)) {
					blckPacketMap = blckPacketMapIp.get(ip);
					blckPacketMap.put(blckNo, String.valueOf(packNo));
					// blckPacketMap.put(String.valueOf(packNo),blckNo);
					blckPacketMapIp.put(ip, blckPacketMap);
				} else {
					blckPacketMap = new HashMap<String, String>();
					blckPacketMap.put(blckNo, String.valueOf(packNo));
					// blckPacketMap.put(String.valueOf(packNo),blckNo);
					blckPacketMapIp.put(ip, blckPacketMap);
				}
				// System.out.println("blckPacketMap "+blckPacketMap.toString());
				// System.out.println("blckPacketMapIp "+blckPacketMapIp.toString());

				if (vCheckUser.contains(packNo)) {

					String auditContent = blckNo + "@"
							+ userFile.split("-")[1].toString() + "@" + tag;
					// System.out.println(auditContent);
					if (auditMap.containsKey(ip)) {
						alCheck = (ArrayList) auditMap.get(ip);
						alCheck.add(auditContent);
						auditMap.put(ip, alCheck);
					} else {
						alCheck.add(auditContent);
						auditMap.put(ip, alCheck);
					}
				}
			}
			Iterator auditIt = auditMap.keySet().iterator();

			while (auditIt.hasNext()) {
				String ip = auditIt.next().toString();
				ArrayList alAuditIp = (ArrayList) auditMap.get(ip);
				System.out.println(ip + " auditmap " + alAuditIp.toString());
				// Thread.sleep(1000);
				AccessServiceCall asc = new AccessServiceCall();
				// String bol=asc.auditCal(ip,
				// alAuditIp,blckPacketMapIp,userFile.split("-")[1].toString()+"."+ext,nextRunTim,runTim);
				String bol = asc.auditCal(ip, alAuditIp, blckPacketMapIp,
						userFile, nextRunTim, runTim, ext);
				if (bol.equalsIgnoreCase("success")) {
					bool = true;
				} else {
					bool = false;
				}
				al.add(bool);
			}
		} else {
			System.out.println("Auditing process Skipped for File..."
					+ userFile);
		}
		if (!al.contains(false)) {
			return "success";
		} else {
			return "";
		}
	}

	public String pauseAuditing() {

		try {
			Scheduling sch = new Scheduling();
			sch.pauseJob();

		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}

	public String resumeAuditing() {

		try {
			Scheduling sch = new Scheduling();
			sch.resumeJob();

		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}

}
