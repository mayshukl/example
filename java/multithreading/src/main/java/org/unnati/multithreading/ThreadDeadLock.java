package org.unnati.multithreading;

public class ThreadDeadLock {
    
    private class DeadLockThread implements Runnable{
        private String obj1;
        private String obj2;
        private String name;
        
        DeadLockThread(String obj1,String obj2,String name){
            this.obj1=obj1;
            this.obj2=obj2;
            this.name=name;
        }


        public void run() {
            System.out.println(name+" Locking : "+obj1);
            synchronized (obj1){
                System.out.println(name+" Locked : "+obj1);
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name+" Locking : "+obj2);
                synchronized (obj2){
                    System.out.println(name+" Locked : "+obj2); 
                }
            }
            
        }
    }
    
    public static void main(String args[]) throws InterruptedException {
        ThreadDeadLock threadDeadLock=new ThreadDeadLock();
        System.out.println("Dead Lock");
        String obj1="Object1";
        String obj2="Object2";
        Thread t1=new Thread(threadDeadLock.new DeadLockThread(obj1,obj2,"Thread1"));
        Thread t2=new Thread(threadDeadLock.new DeadLockThread(obj2,obj1,"Thread2"));
        t1.start();
        t2.start();
        
        t1.join();
        t2.join();
    }
}
