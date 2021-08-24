package com.ixfosa.util;

import java.util.Date;

/**
 * Created by ixfosa on 2021/4/23 14:22
 */
public class ServiceTools {
    public static void doLog(){
        System.out.println("非业务方法，方法的执行时间："+ new Date());
    }

    public static void doTrans(){
        //方法的最后，提交事务
        System.out.println("非业务方法,方法执行完毕后，提交事务");
    }
}
