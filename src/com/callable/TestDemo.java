package com.callable;

import java.util.concurrent.*;

/**
 * Callable 是 JDK1.5之后提供的新的线程机制，比Runnable的变化：
 * 1.call() 方法的返回值类型可以自定义
 * 2.call() 方法的返回值可以接到
 * 3.call() 方法可以抛出异常
 * 4.call() 方法只能通过线程池启动 不能通过new Thread().start()启动
 */

public class TestDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        Future<String> future = service.submit(new c1());

        //获取对应线程call()的返回值
        String result = future.get();
        System.out.println(result);
        service.shutdown();
    }
}


class c1 implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println("线程启动！");
        return "success";
    }
}