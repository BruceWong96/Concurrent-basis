package com.barrier;

import java.util.concurrent.CyclicBarrier;

/**
 * 赛马场景
 * 有两匹马（两个线程）
 * 要求必须等到所有的赛马都到达栅栏前，才能一起跑
 *
 * 栅栏机制可以实现线程的同步协调。
 * 实现的方式i就是通过await()方法来实现
 */

public class TestDemo  {
    public static void main(String[] args) {

        /**
         * 创建栅栏，并分配一个初始的计数器
         */
        CyclicBarrier barrier = new CyclicBarrier(2);

        new Thread(new Horse1(barrier)).start();
        new Thread(new Horse2(barrier)).start();
    }
}


class Horse1 implements Runnable{

    private CyclicBarrier barrier;
    public Horse1(CyclicBarrier barrier){
        this.barrier = barrier;
    }

    @Override
    public void run() {
        System.out.println("马1来到栅栏前");

        try {
            //此方法会产生阻塞，阻塞放开的条件是栅栏的初始计数器=0
            //此方法每调用一次，计数器就-1
            barrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("马1开始跑");
    }
}

class Horse2 implements Runnable{

    private CyclicBarrier barrier;
    public Horse2(CyclicBarrier barrier){
        this.barrier = barrier;
    }

    @Override
    public void run() {
        System.out.println("马2正在拉肚子");
        try {
            Thread.sleep(5000);
            System.out.println("马2到达栅栏前");
            barrier.await();
            System.out.println("马2开始跑");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}