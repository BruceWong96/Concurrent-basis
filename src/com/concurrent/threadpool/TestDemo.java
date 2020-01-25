package com.concurrent.threadpool;


import java.util.concurrent.*;

public class TestDemo {

    public static void main(String[] args) {
        TestDemo testDemo = new TestDemo();
        testDemo.testCreate();
    }

    public void testCreate(){
        /**
         * --corePoolSize   核心线程数
         * 当线程池初次创建时，是没有任何线程的。当有请求发起是，线程会创建核心线程
         * 在请求过程中，无论核心线程是否闲置，线程池都会创建核心线程，直到满足数量位置
         */

        /**--maximumPoolSize   最大线程数
         * 最大线程数 = 核心线程数 + 临时线程数
         */

        //--keepAliveTime：临时线程的存活时间
        //--unit:时间单位，一般用毫秒单位
        //--workQueue：等待队列，用于存放还未处理的请求
        //RejectedExecutionHandler()拒绝服务助手
        ExecutorService service = new ThreadPoolExecutor(
                5,
                10,
                3000,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(10),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println("请稍后尝试！");
                    }
                }
        );

        //通过线程池启动
        for (int i=0 ; i<22 ; i++){
            service.execute(new ClientRunner());
            System.out.println("第"+i+"个线程");
        }

        //关闭线程池.此方法调用时，线程池不会接收外部请求了。
        //但内部的线程并不会马上销毁，而是等到线程工作完之后再销毁
        //使用线程池的好处时可以避免线程的频繁创建和销毁，节省CPU性能
        service.shutdown();

    }


    /**
     * newCachedThreadPool
     * 1.没有核心线程
     * 2.都是临时线程
     * 3.队列是同步队列
     * 4.最大线程数是 Integer.MAX_VALUE
     * 总结：大池子小队列的好处：
     *      很好的响应客户端的请求（及时响应），因为不需要排队
     *   但是注意：这种线程池适用于短请求，如果都是长请求会导致线程一致创建而不销毁，
     *            最后内存溢出
     */
    public void  testCreate_1(){
        ExecutorService service = Executors.newCachedThreadPool();
    }


    /**
     * newFixedThreadPool
     * 1.都是核心线程，没有临时线程
     * 2.队列是无界队列（LinkedBlockingQueue）
     * 总结：小池子大队列
     * 这种线程池的作用：消峰限流。  局限性：不能及时的响应
     */
    public void  testCreate_2(){
        ExecutorService service = Executors.newFixedThreadPool(10);
    }
}



class ClientRunner implements Runnable{

    @Override
    public void run() {
        System.out.println("处理客户端请求！");
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
