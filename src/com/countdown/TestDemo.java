package com.countdown;

public class TestDemo {
    public static void main(String[] args) {
        new Thread(new BuyGuo()).start();
        new Thread(new BuyCai()).start();
        System.out.println("开始做饭");
    }
}

class BuyGuo implements Runnable{

    @Override
    public void run() {
        System.out.println("锅买回来了！");
    }
}

class  BuyCai implements Runnable{

    @Override
    public void run() {
        System.out.println("菜买回来了！");
    }
}

