package top.ixfosa.demo;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * Created by ixfosa on 2021/6/7 19:58
 */
public class QuartzDemo implements Job {
    // 任务被触发时所执行的方法
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Quartz: " + new Date());
    }
}
