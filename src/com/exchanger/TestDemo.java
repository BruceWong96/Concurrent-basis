package com.exchanger;

import java.util.concurrent.Exchanger;

/**
 * 交换机
 * 场景：两个间谍对暗号
 * 间谍1的暗号：回眸一笑
 * 间谍2的暗号：寸草不生
 *
 * 交换机的使用场景是两个线程做数据交换的场景。注意是两个线程。
 *
 */
public class TestDemo {
    public static void main(String[] args) {
        //创建交换机
        Exchanger<String> ex = new Exchanger<>();

        new Thread(new Spy1(ex)).start();
        new Thread(new Spy2(ex)).start();
    }
}
class Spy1 implements Runnable{

    private Exchanger<String> ex;

    public Spy1(Exchanger<String> ex){
        this.ex = ex;
    }

    @Override
    public void run() {
        String info = "回眸一笑";
        try {
            String spy2Info = ex.exchange(info);
            System.out.println("1收到2的暗号："+spy2Info);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Spy2 implements Runnable{

    private Exchanger<String> ex;

    public Spy2(Exchanger<String> ex){
        this.ex = ex;
    }

    @Override
    public void run() {
        String info = "寸草不生";
        try {
            String spy1Info = ex.exchange(info);
            System.out.println("2收到1的暗号："+spy1Info);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}