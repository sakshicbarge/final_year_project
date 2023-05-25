package com.logic;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;


public class Scheduling {
	static Scheduler scheduler;
	public static void cal1(String time) throws Exception {
		
		JobDetail job = JobBuilder.newJob(AuditingJob.class)
		.withIdentity("dummyJobName", "group1").build();
		
		Trigger trigger = TriggerBuilder
		.newTrigger()
		.withIdentity("TriggerName", "group")
		.withSchedule(
			CronScheduleBuilder.cronSchedule(time+" * * * * ?"))
		.build();

		// schedule it
		scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.start();
		scheduler.scheduleJob(job, trigger);
		
	}
	public static void pauseJob() throws SchedulerException
	{
		scheduler.pauseAll();
		
	}
	public static void resumeJob() throws SchedulerException
	{
		scheduler.resumeAll();
	}
}
