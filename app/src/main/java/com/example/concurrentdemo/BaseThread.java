package com.example.concurrentdemo;

import java.util.concurrent.locks.Condition;

public class BaseThread extends Thread{
    protected volatile int mCount = 0;
    protected boolean flagThread1 = true;
    protected Object mLocker = new Object();
//    protected Object mLocker2 = new Object();
}
