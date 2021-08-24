package top.ixfosa.quartz;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**
 * Created by ixfosa on 2021/6/7 21:00
 */
@Component
public class MyAdaptableJobFactory extends AdaptableJobFactory {

    // AutowireCapableBeanFactory 可以将一个对象添加到 SpringIOC 容器中，并且完成该对象注入
    @Autowired
    private AutowireCapableBeanFactory autowireCapableBeanFactory;

    // 该方法需要将实例化的任务对象手动的添加到 springIOC 容器中并且完成对象的注入
    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object instance = super.createJobInstance(bundle);
        // 将 obj 对象添加 Spring IOC 容器中，并完成注入
        this.autowireCapableBeanFactory.autowireBean(instance);
        return instance;
    }
}
