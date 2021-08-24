package top.ixfosa.Config;

import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import top.ixfosa.demo.QuartzDemo;


// @Configuration
public class QuartzConfig2 {
    // 1.创建 Job 对象
    // @Bean
    public JobDetailFactoryBean getJobDetailFactoryBean() {
        JobDetailFactoryBean factory = new JobDetailFactoryBean();
        // 关联我们自己的 Job 类
        factory.setJobClass(QuartzDemo.class);
        return factory;
    }

    //  2.创建 Trigger 对象
    // @Bean
    public SimpleTriggerFactoryBean getSimpleTriggerFactoryBean(JobDetailFactoryBean jobDetailFactoryBean) {
        SimpleTriggerFactoryBean factory = new SimpleTriggerFactoryBean();
        // 关联 JobDetail 对象
        factory.setJobDetail(jobDetailFactoryBean.getObject());
        // 该参数表示一个执行的毫秒数
        factory.setRepeatInterval(2000);
        // 重复次数
        factory.setRepeatCount(5);
        return factory;
    }

    // 3.创建 Scheduler 对象
    // @Bean
    public SchedulerFactoryBean getSchedulerFactoryBean(SimpleTriggerFactoryBean simpleTriggerFactoryBean) {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        // 关联 trigger
        factory.setTriggers(simpleTriggerFactoryBean.getObject());
        return factory;
    }
}
