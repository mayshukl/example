package org.unnati.multithreading.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPool {
    static final int MAX_T = 3;

    public static  void main(String[] args){

        Runnable r1 = new Task("task 1");
        Runnable r2 = new Task("task 2");
        Runnable r3 = new Task("task 3");
        Runnable r4 = new Task("task 4");
        Runnable r5 = new Task("task 5");
        Runnable r6 = new Task("Schedule overLap");
        Runnable r7 = new Task("Schedule No overLap");


        
        ExecutorService pool = Executors.newScheduledThreadPool(2);
        ((ThreadPoolExecutor)pool).setCorePoolSize(7);
        
     //   pool.execute(r1);
     //   pool.execute(r2);
     //   pool.execute(r3);
     //   pool.execute(r4);
     //   pool.execute(r5);
        ((ScheduledThreadPoolExecutor)pool).scheduleAtFixedRate(r6,0,4000,TimeUnit.MILLISECONDS);
        ((ScheduledThreadPoolExecutor)pool).scheduleWithFixedDelay(r7,0,4000,TimeUnit.MILLISECONDS);
        
        
        System.out.println("Executor Class : "+pool.getClass());
        System.out.println("-------------"+pool.getClass()+"-------------");
        System.out.println("Core Pool Size -------------  "+((ThreadPoolExecutor)pool).getCorePoolSize());
        System.out.println("Core Pool Size -------------  "+((ThreadPoolExecutor)pool).getMaximumPoolSize());
        
        // this will stop the thread pool
        //pool.shutdown();
    }
}
