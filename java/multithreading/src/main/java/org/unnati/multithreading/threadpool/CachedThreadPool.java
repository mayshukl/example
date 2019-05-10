package org.unnati.multithreading.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CachedThreadPool {
    static final int MAX_T = 3;

    public static  void main(String[] args){

        Runnable r1 = new Task("task 1");
        Runnable r2 = new Task("task 2");
        Runnable r3 = new Task("task 3");
        Runnable r4 = new Task("task 4");
        Runnable r5 = new Task("task 5");


        
        ExecutorService pool = Executors.newCachedThreadPool();
        ((ThreadPoolExecutor)pool).setCorePoolSize(4);
        ((ThreadPoolExecutor)pool).setKeepAliveTime(1, TimeUnit.MILLISECONDS);
        ((ThreadPoolExecutor)pool).allowCoreThreadTimeOut(true);
        
        pool.execute(r1);
        pool.execute(r2);
        pool.execute(r3);
        pool.execute(r4);
        pool.execute(r5);
        
        System.out.println("Executor Class : "+pool.getClass());
        System.out.println("-------------"+pool.getClass()+"-------------");
        System.out.println("Core Pool Size -------------  "+((ThreadPoolExecutor)pool).getCorePoolSize());
        System.out.println("Core Pool Size -------------  "+((ThreadPoolExecutor)pool).getMaximumPoolSize());
        
        //pool.shutdown();
    }
}
