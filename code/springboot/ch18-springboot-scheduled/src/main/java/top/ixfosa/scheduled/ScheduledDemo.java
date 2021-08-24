package top.ixfosa.scheduled;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by ixfosa on 2021/6/7 19:33
 */

@Component
// @EnableScheduling   // 或在启动器中开启
public class ScheduledDemo {
    /**
     * 定时任务方法
     * @Scheduled:设置定时任务
     * cron 属性：cron 表达式。定时任务触发是时间的一个字符串表达形式
     */
    @Scheduled(cron = "0/2 * * * * ?")
    public void scheduledMethod() {
        System.out.println("Scheduled: " + new Date());
    }
}
