package org.unnati.multithreading.locks;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerWithLocks {
    
    public static void main(String args[]){
        System.out.println("---------Producer Consumer Locks-----------");
        Queue<String> queue=new LinkedList<String>();
        ReentrantLock reentrantLock=new ReentrantLock(true);
        Condition notFull=reentrantLock.newCondition();
        Condition notEmpty=reentrantLock.newCondition();
        
        Thread producer=new Thread(new Producer(reentrantLock,queue,notFull,notEmpty),"PRODUCER");
        Thread consumer1=new Thread(new Consumer(reentrantLock,queue,notFull,notEmpty),"CONSUMER 1");
        Thread consumer2=new Thread(new Consumer(reentrantLock,queue,notFull,notEmpty),"CONSUMER 2");

        consumer1.start();
        producer.start();
        consumer2.start();
        
    }
}

class Constants{
    public static final int MAX_BUFFER_SIZE=10;
    public static final int MIN_BUFFER_SIZE=0;
}

class Producer implements Runnable{

    Lock lock;
    Queue<String> queue;
    Condition notFull;
    Condition notEmpty;
    Producer(Lock lock,Queue<String> queue,Condition notFull,Condition notEmpty){
        this.lock=lock;
        this.queue=queue;
        this.notEmpty=notEmpty;
        this.notFull=notFull;
    }
    
    public void run() {
        System.out.println(Thread.currentThread().getName() +": started ");
        int counter=1;
        while (true){
            lock.lock();
            try{
                while (queue.size()==Constants.MAX_BUFFER_SIZE){
                   notFull.await();
                }
                String message="Message"+counter++;
                System.out.println(Thread.currentThread().getName()+" : offered : "+message);
                queue.offer(message);
                Thread.sleep(5000);
                notEmpty.signalAll();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
        
    }
}

class Consumer implements Runnable{

    Lock lock;
    Queue<String> queue;
    Condition notFull;
    Condition notEmpty;
    Consumer(Lock lock,Queue<String> queue,Condition notFull,Condition notEmpty){
        this.lock=lock;
        this.queue=queue;
        this.notEmpty=notEmpty;
        this.notFull=notFull;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() +": started ");
        while (true){
            lock.lock();
            try{
                while (queue.size()== Constants.MIN_BUFFER_SIZE){
                    notEmpty.await();
                }
                String message=queue.poll();
                System.out.println(Thread.currentThread().getName()+" : polled : "+message);
                Thread.sleep(5000);
                notFull.signalAll();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }

    }
}
