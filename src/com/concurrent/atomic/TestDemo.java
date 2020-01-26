package com.concurrent.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子性类型 底层用的CAS算法（无锁算法），即可以保证线程并发安全问题，
 *          而且性能高
 */
public class TestDemo {

    //原子性整型
    public static AtomicInteger i = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(2);
        //传递countDownLatch
        new Thread(new AddRunner1(countDownLatch)).start();
        new Thread(new AddRunner2(countDownLatch)).start();
        countDownLatch.await();

        System.out.println(i);

    }

}

class AddRunner1 implements Runnable{
    private CountDownLatch countDownLatch;

    public AddRunner1(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        for (int j = 0; j < 1000000; j++) {
            TestDemo.i.getAndIncrement();
        }
        countDownLatch.countDown();
    }                                         
}


class AddRunner2 implements Runnable{

    private CountDownLatch countDownLatch;

    public AddRunner2(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        for (int j = 0; j < 1000000; j++) {
            TestDemo.i.incrementAndGet();
        }
        countDownLatch.countDown();
    }
}