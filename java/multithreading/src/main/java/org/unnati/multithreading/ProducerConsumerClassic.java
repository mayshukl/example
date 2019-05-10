package org.unnati.multithreading;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerClassic {
    
    private Queue<String> queue=new LinkedList<String>();
    private final int MAX_BUFFER=10;
    
    private class Producer implements Runnable{
        
        public void run() {
            int counter=0;
            while (true) { 
                synchronized (queue) {
                    String message="Message"+(++counter);
                    System.out.println("Producer has produced : "+message);
                    if (queue.size() == MAX_BUFFER) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else{
                        queue.offer(message);
                        queue.notify();
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private class Consumer implements Runnable{

        public void run() {
             while (true) {
                synchronized (queue) {
                    if (queue.size() == 0) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else{
                        String message= queue.poll();
                        System.out.println("Consumer has consumed : "+message);
                        queue.notify();
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
    
    
    public static void main(String args[]) throws InterruptedException{
        ProducerConsumerClassic classic=new ProducerConsumerClassic();
        System.out.println("Producer and Consumer");
        Thread producer=new Thread(classic.new Producer());
        producer.start();
        Thread consumer=new Thread(classic.new Consumer());
        consumer.start();
         producer.join();
         consumer.join();
    }
}
