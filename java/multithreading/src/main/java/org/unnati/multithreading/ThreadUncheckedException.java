package org.unnati.multithreading;

public class ThreadUncheckedException {
    
    private class RuntimeExThread implements Runnable{

        public void run() {
            
            throw new RuntimeException("This is to Catch Runtime Exception in a thread");
        }
    }
    
    private class RuntimeExThreadUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

        public void uncaughtException(Thread t, Throwable e) {
            System.out.println(t.getName() +" has never caught "+e.getClass());
        }
    }
    
    public static void main(String[] args){
        ThreadUncheckedException threadUncheckedException=new ThreadUncheckedException();
        Thread thread=new Thread(threadUncheckedException.new RuntimeExThread());
        thread.setUncaughtExceptionHandler(threadUncheckedException.new RuntimeExThreadUncaughtExceptionHandler());
        
        thread.start();
        
    }
    
}
