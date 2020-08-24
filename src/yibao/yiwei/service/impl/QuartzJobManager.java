package yibao.yiwei.service.impl;

import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import yibao.yiwei.entity.system.QuartzJob;
import yibao.yiwei.service.IQuartzJobManager;

@SuppressWarnings("unchecked")
@Service
@Transactional
public class QuartzJobManager<T> implements IQuartzJobManager<T> {
	
	//查询所有动态任务
	@Override
	public List<QuartzJob> selectAll() {
		//TODO Something
		return null;
	}
	

	//添加定时任务
	@Override
	public int addjobgroup(QuartzJob quartzjob,Class<T> entity) throws Exception {
		JobDetail jobDetail = JobBuilder.newJob((Class<? extends Job>) entity)
                .withIdentity(quartzjob.getJob_name(), quartzjob.getJob_group())
                .withDescription(quartzjob.getDescription())
                .build();
        //表达式调度构建器
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(quartzjob.getCron_expression());
        CronTrigger cronTrigger = TriggerBuilder
        		.newTrigger()
                .withIdentity(quartzjob.getTrigger_name())
                .withSchedule(scheduleBuilder)
                .startNow()
                .build();

        //创建scheduler
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.scheduleJob(jobDetail, cronTrigger);
        scheduler.start();
		return 1;
	}
	
	//删除定时任务,返回1说明移除成功，返回0则说明失败
	@Override
	public int deljobgroup(String trigger_name) {
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
	    TriggerKey triggerkey = new TriggerKey(trigger_name);
        try {
        	Scheduler scheduler = schedulerFactory.getScheduler();
        	scheduler.unscheduleJob(triggerkey);//移除触发器
		} catch (SchedulerException e) {
			//e.printStackTrace();
			return 0;
		}
		return 1;
	}

	//暂停某任务
	@Override
	public int pauseTrigger(String triggerName) {
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		TriggerKey triggerkey = new TriggerKey(triggerName);
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			scheduler.pauseTrigger(triggerkey); //暂停任务
		} catch (SchedulerException e) {
			//e.printStackTrace();
			return 0;
		}
		return 1;
	}

	//重新开始某任务
	@Override
	public int resumeTriggers(String triggerName) {
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		TriggerKey triggerkey = new TriggerKey(triggerName);
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			scheduler.resumeTrigger(triggerkey); //重新开始某任务
		} catch (SchedulerException e) {
			//e.printStackTrace();
			return 0;
		}
		return 1;
	}

}
