package top.ixfosa.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import top.ixfosa.service.UserService;

import java.util.Date;

/**
 * Created by ixfosa on 2021/6/7 20:52
 */
public class QuartzDemo implements Job {

    @Autowired
    private UserService userService;


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Quartz: " + new Date());
        userService.addUser();
    }
}
