package org.unnati.multithreading;

import java.util.concurrent.Semaphore;

public class PrintNumber {

    static class SharedPrinter {

        private Semaphore semEven = new Semaphore(0);
        private Semaphore semOdd = new Semaphore(1);

        void printEvenNum(int num) {
            try {
                System.out.println(Thread.currentThread().getName()+" Even Semaphore" + semEven.availablePermits());
                semEven.acquire();
                System.out.println(Thread.currentThread().getName()+" Even Semaphore" + semEven.availablePermits());
                System.out.println(Thread.currentThread().getName()+" Odd Semaphore" + semOdd.availablePermits());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println(Thread.currentThread().getName() + num);
            semOdd.release();
        }

        void printOddNum(int num) {
            try {
                semOdd.acquire();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println(Thread.currentThread().getName() + num);
            System.out.println(Thread.currentThread().getName()+" Even Semaphore" + semEven.availablePermits());
            semEven.release();
            System.out.println(Thread.currentThread().getName()+" Even Semaphore" + semEven.availablePermits());

        }
    }

    static class Even implements Runnable {
        private SharedPrinter sp;
        private int max;

        // standard constructor

        public Even(SharedPrinter sp, int i) {
            this.sp=sp;
            this.max=i;
        }
        public void run() {
            for (int i = 2; i <= max; i = i + 2) {
                sp.printEvenNum(i);
            }
        }
    }

    static class Odd implements Runnable {
        private SharedPrinter sp;
        private int max;
        public Odd(SharedPrinter sp, int i) {
            this.sp=sp;
            this.max=i;
        }
        // standard constructors 
        
        public void run() {
            for (int i = 1; i <= max; i = i + 2) {
                sp.printOddNum(i);
            }
        }
    }
    
    public static void main(String[] args) {
        SharedPrinter sp = new SharedPrinter();

        Thread even = new Thread(new Even(sp, 10),"Even");
        Thread odd = new Thread(new Odd(sp, 10),"Odd");
        even.start();
        odd.start();
        
    }
}
