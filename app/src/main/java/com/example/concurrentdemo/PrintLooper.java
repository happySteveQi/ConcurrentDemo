package com.example.concurrentdemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintLooper {
    private int n;

    public PrintLooper(int n) {
        this.n = n;
    }

    Lock lock = new ReentrantLock(true);
    volatile boolean flagOfOdd = true;

    public void odd(Runnable printOdd) {
        for (int i = 0; i < n; ) {
            lock.lock();
            try {
                if (flagOfOdd) {
                    printOdd.run();
                    i++;
                    System.out.println(" odd i ==="+i);
                    flagOfOdd = false;
                }
            } finally {
                lock.unlock();
            }
        }
    }
    public void even(Runnable printEven) {
        for (int i = 0; i < n;) {
            lock.lock();
            try {
                if (!flagOfOdd) {
                    printEven.run();
                    i++;
                    System.out.println(" even i === "+i);
                    flagOfOdd = true;
                }
            } finally {
                lock.unlock();
            }
        }
    }

}
