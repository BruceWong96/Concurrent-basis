package com.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NonLockDemo {

    public static String name = "李雷";
    public static String gender = "男";

    public static void main(String[] args) {
/**
 *  创建重入锁
 *  底层支持两种锁的机制：1.公平锁机制；2.非公平锁机制
 *  参数如果是 false是非公平锁（默认）
 */
        Lock lock = new ReentrantLock(false);

        new Thread(new WriteRunner(lock)).start();
        new Thread(new ReadRunner(lock)).start();
    }

}

class WriteRunner implements Runnable{

    private Lock lock;

    public WriteRunner(Lock lock){
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true){
//            synchronized (NonLockDemo.class){
                lock.lock();
                if (NonLockDemo.name=="李雷"){
                    NonLockDemo.name="韩梅梅";
                    NonLockDemo.gender="女";
                }else {
                    NonLockDemo.name="李雷";
                    NonLockDemo.gender="男";
                }
                lock.unlock();
//            }
        }
    }
}

class ReadRunner implements Runnable{

    private Lock lock;

    public ReadRunner(Lock lock){
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true){
//            synchronized (NonLockDemo.class){
            lock.lock();
                System.out.println(NonLockDemo.name+"性别："+NonLockDemo.gender);
            lock.unlock();
//            }

        }
    }
}