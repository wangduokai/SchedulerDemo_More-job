package com.ws.job;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;
import static org.quartz.CronTrigger.*;

public class QuartzTest {
	public static void main(String[] args) {
		try {
			
			// Grab the Scheduler instance from the Factory
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			// and start it off
			scheduler.start();
			
			
			// define the job and tie it to our DumbJob class
			 JobDetail job = newJob(DumbJob.class)
			 .withIdentity("myJob") // name "myJob", group "group1"
			 .usingJobData("jobSays", "Hello !")
			 .usingJobData("myFloatValue", 3.141f)
			 .build();
			 
			 
			 // Trigger the job to run now, and then repeat every 40 seconds
			  Trigger trigger = newTrigger()
			 .withIdentity("trigger")
			 .startNow()
			 .withSchedule(simpleSchedule()
			 .withIntervalInSeconds(1)
			 .repeatForever())
			 .build();
			 // Tell quartz to schedule the job using our trigger
			  
			  scheduler.scheduleJob(job, trigger);
			  
			// define the job and tie it to our DumbJob class
			 JobDetail job1 = newJob(DumbJob.class)
			 .withIdentity("myJob1") // name "myJob", group "group1"
			 .usingJobData("jobSays", "Hello EveryOne!")
			 .usingJobData("myFloatValue", 1024f)
			 .build();
			 
			 
			 // Trigger the job to run now, and then repeat every 40 seconds
			 Trigger trigger1 = newTrigger()
			 .withIdentity("trigger1")
			 .startNow()
			 .withSchedule(simpleSchedule()
			 .withIntervalInSeconds(1)
			 .repeatForever())
			 .build();
			 // Tell quartz to schedule the job using our trigger
//			
			 scheduler.scheduleJob(job1, trigger1);
  //		 scheduler.shutdown();
		} catch (SchedulerException se) {
			se.printStackTrace();
		}
	}
}