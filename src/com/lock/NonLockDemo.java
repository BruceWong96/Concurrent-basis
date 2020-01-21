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
 *
 *  同步代码块默认就是一种非公平锁
 *
 *  注意：使用重入锁，一定要注意锁的释放问题
 *  需要在finally{lock.unlock}  避免产生死锁
 *
 *  官方建议：如果使用非公平锁，用同步代码块
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
            try{
                lock.lock();
                if (NonLockDemo.name=="李雷"){
                    NonLockDemo.name="韩梅梅";
                    NonLockDemo.gender="女";
                }else {
                    NonLockDemo.name="李雷";
                    NonLockDemo.gender="男";
                }
            }finally {
                lock.unlock();
            }

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
            try{
                lock.lock();
                System.out.println(NonLockDemo.name+"性别："+NonLockDemo.gender);
            }finally {
                lock.unlock();
            }
//            }

        }
    }
}