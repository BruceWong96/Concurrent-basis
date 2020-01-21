package com.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestDemo {
    public void testCreate(){
        //--corePoolSize
        //--maximumPoolSize
        //--keepAliveTime
        //--unit
        //--workQueue
        ExecutorService service = new ThreadPoolExecutor(
                5,
                10,
                3000,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(10));
    }
}
