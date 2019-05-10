package org.unnati.multithreading.locks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLocks {

    public static void main(String args[]) {
        System.out.println("Read Write Lock");
        List<String> bookList = new ArrayList<String>();
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.WriteLock writeLock = ((ReentrantReadWriteLock) readWriteLock).writeLock();
        ReentrantReadWriteLock.ReadLock readLock = ((ReentrantReadWriteLock) readWriteLock).readLock();
        Condition condition = writeLock.newCondition();
        Thread bookReader1 = new Thread(new BookReader(bookList, readLock, writeLock, condition), "READER 1");
        Thread bookReader2 = new Thread(new BookReader(bookList, readLock, writeLock, condition), "READER 2");
        Thread bookWriter = new Thread(new BookWriter(bookList, writeLock, condition), "WRITER");
        bookReader1.start();
        bookReader2.start();
        bookWriter.start();
    }
}

class BookReader implements Runnable {

    private List<String> bookList;
    private int hasReadIndex = -1;
    private Lock readerLock;
    private Lock writerLock;
    private Condition bookAdded;


    BookReader(List<String> bookList, Lock readerLock, Lock writerLock, Condition bookAdded) {
        this.bookList = bookList;
        this.readerLock = readerLock;
        this.writerLock = writerLock;
        this.bookAdded = bookAdded;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName()+" : Started");
        try {
            while (true) {
                readerLock.lock();
                System.out.println(Thread.currentThread().getName()+" : locking reader");
                try {
                    while (this.bookList.size() == this.hasReadIndex + 1) {
                        System.out.println(Thread.currentThread().getName()+" : locking writer");
                        /**The problem with upgrading a read lock to a write lock is, if two threads try to do it at the same time, it can lead to deadlock. Consider the following sequence:
                            Thread A: acquires read lock
                            Thread B: acquires read lock (Note that both threads now share the read lock).
                             Thread A: tries to acquire write lock (blocks as thread B holds read lock)
                            Thread B: tries to acquire write lock (blocks as thread A holds read lock)
                        */
                         this.readerLock.unlock();
                        this.writerLock.lock(); // get a write Lock , this will strop writer also
                        System.out.println(Thread.currentThread().getName()+" : locked writer");
                        try {
                            while (this.bookList.size() == this.hasReadIndex + 1) { // Check for condition
                                System.out.println(Thread.currentThread().getName()+" : unlocking writer");
                                this.bookAdded.await(); // We need to wait for this signal that tells that new book has been adde
                            }
                            this.readerLock.lock();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            writerLock.unlock();
                        }
                    }
                    Thread.sleep(2000);
                    String book = this.bookList.get(this.hasReadIndex++);
                    System.out.println(Thread.currentThread().getName() + " Book has been read " + book);
                } finally {
                    readerLock.unlock();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class BookWriter implements Runnable {

    private List<String> bookList;
    private Lock lock;
    private Condition bookAdded;


    BookWriter(List<String> bookList, Lock lock, Condition bookAdded) {
        this.bookList = bookList;
        this.lock = lock;
        this.bookAdded = bookAdded;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName()+" : Started");
        int bookCounter = 1;
        try {
            while (true) {
                System.out.println(Thread.currentThread().getName()+" : locking writer");
                lock.lock();
                System.out.println(Thread.currentThread().getName()+" : locked writer");
                try {
                    String book = "Book " + bookCounter++;
                    System.out.println(Thread.currentThread().getName() + " has written a book : " + book);
                    Thread.sleep(5000);
                    this.bookAdded.signalAll();
                } finally {
                    lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}