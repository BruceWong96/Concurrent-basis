package com.concurrent.countdown;

import java.util.concurrent.CountDownLatch;

/**
 * 闭锁也叫线程递减锁，可以通过闭锁让线程挂起或继续执行
 * 主要是通过CountDown() 和 await() 来实现
 */

public class TestDemo {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(2);

        new Thread(new BuyGuo(countDownLatch)).start();
        new Thread(new BuyCai(countDownLatch)).start();

        //--此方法会产生阻塞，阻塞放开的条件是闭锁初始数量=0
        countDownLatch.await();
        System.out.println("开始做饭");
    }
}

class BuyGuo implements Runnable{

    private CountDownLatch countDownLatch;

    public BuyGuo(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println("锅买回来了！");

        //此方法每调用一次，闭锁的初始数量-1
        countDownLatch.countDown();
    }
}

class  BuyCai implements Runnable{

    private CountDownLatch countDownLatch;

    public BuyCai(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }


    @Override
    public void run() {
        System.out.println("菜买回来了！");

        countDownLatch.countDown();
    }
}

