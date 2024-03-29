package com.example.concurrentdemo;

import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch {

    public static void main(String[] args) {
        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(2);
        for (int i = 0; i < 2; i++) {
            Thread thread = new Thread(new Player(begin,end));
            thread.start();
        }
        try {
            System.out.println("the race begin");
            begin.countDown();
            end.await();
            System.out.println("the race end");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 选手
     */
    public static class Player implements Runnable{

        private CountDownLatch begin;

        private CountDownLatch end;

        Player(CountDownLatch begin,CountDownLatch end){
            this.begin = begin;
            this.end = end;
        }
        @Override
        public void run() {
            try {
                begin.await();
                System.out.println(Thread.currentThread().getName()+" arrived !");
                end.countDown();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
