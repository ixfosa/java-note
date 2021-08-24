package top.ixfosa;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by ixfosa on 2021/8/23 18:02
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        // 创建 generator 对象
        AutoGenerator generator = new AutoGenerator();

        // 数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUrl("jdbc:mysql://127.0.0.1:3306/ssm?useUnicode=true&useSSL=false&characterEncoding=utf-8");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("ixfosa");
        generator.setDataSource(dataSourceConfig);

        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        // log.info(System.getProperty("user.dir"));
        globalConfig.setOutputDir(System.getProperty("user.dir") + "/generator" +  "/src/main/java");
        globalConfig.setOpen(false);
        globalConfig.setAuthor("ixfosa");
        globalConfig.setServiceName("%sService");
        generator.setGlobalConfig(globalConfig);

        // 包信息
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("top.ixfosa");
        // packageConfig.setModuleName("mybatisplus");
        packageConfig.setController("controller");
        packageConfig.setService("service");
        packageConfig.setServiceImpl("service.impl");
        packageConfig.setMapper("mapper");
        packageConfig.setEntity("entity");
        generator.setPackageInfo(packageConfig);

        // 配置策略
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        generator.setStrategy(strategyConfig);

        generator.execute();
    }
}
