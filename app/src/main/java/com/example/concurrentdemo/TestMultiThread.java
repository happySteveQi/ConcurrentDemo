package com.example.concurrentdemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestMultiThread {
    private static Lock mLocker = new ReentrantLock();
    private static Condition condition1 = mLocker.newCondition();
    private static Condition condition2 = mLocker.newCondition();

    public static void main(String[] args) {

//        testOneCase();
        testTwoCase();
    }

    private static void testTwoCase() {

    }

    public static void testOneCase() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i <= 100; i += 2) {
                    try {
                        mLocker.lock();
                        System.out.println("current: T1 = " + i);

                        condition2.signal();
                        condition1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        mLocker.unlock();
                    }
                }


            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 99; i += 2) {
                    mLocker.lock();
                    try {

                        System.out.println("current: T2 = " + i);
                        condition1.signal();
                        condition2.await();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        mLocker.unlock();
                    }
                }
            }
        });
        t1.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        t2.start();
    }

    public void printOne() {

    }
}
