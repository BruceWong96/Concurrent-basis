package concurrent.demo.queue;

/*
    学习阻塞队列的使用。
    阻塞队列在高并发以及很多大数据框架底层有应用。
    队列的使用场景：将数据存入队列，可以起到缓冲的作用。消峰限流。
    此外，队列另外的作用就是实现生产者和消费者之间的解耦。

 */

import org.junit.Test;

import java.util.concurrent.*;

public class Demo {

    /*
        add方法当队列已满时，会抛出异常。使用这种方法来插入队列，可以通过捕获具体的异常来处理
        offer方法当队列已满，会抛出false，未满则抛出true
        put方法当队列已满，会产生阻塞，当队列有剩余容量，则阻塞放开。此方法常用
        offer超时方法当队列已满，会产生阻塞。
        阻塞放开有两个：
        ①队列有剩余容量；
        ②超过了指定的超时时间
     */
    @Test
    public void testPut() throws Exception {
        BlockingQueue queue = new ArrayBlockingQueue<>(10);
        for (int i=0 ; i<10 ; i++){
            queue.put(i);


        }

//        queue.add(11);



        System.out.println(queue.offer(11)); //false
//        queue.put(11);
//        queue.offer(11,3, TimeUnit.SECONDS);
        System.out.println("你好ArrayBlockingQueue！！！");

    }

    /*
        1.remove方法对队列为空时，会抛异常NoSuchElementException
            如果队列不为空，则按照FIFO的原则，取出相应的元素
        2.poll方法当队列为空，会抛出null值，一般会根据poll返回值是否为null
            来判断队列是否为空
        3.take会产生阻塞（当队列为空）
        4.poll超时方法会产生阻塞，阻塞放开条件：队列中有数据可取或者超时时间到达
     */
    @Test
    public void testGet() throws InterruptedException {
        BlockingQueue queue = new ArrayBlockingQueue<>(10);

//        queue.add(1);
//
//        System.out.println(queue.remove());
//        System.out.println(queue.poll()); //null
//
//        queue.take();

//        queue.poll(3,TimeUnit.SECONDS);
//        System.out.println("你好ArrayBlockingQueue");

    }
    /*
        ArrayBlockingQueue:数组阻塞队列的特点：
        ①有界队列，容量在创建时指定
        ②底层时通过数组的数据结构实现的。所以查询快，增删慢。
        LinkedBlockingQueue：链阻塞队列的特点：
        ①是无界队列，默认的大小是Integer.MaxValue
        ②底层时链数据结构，增删快。因为队列的使用一般是增加多删除所以比Array常用

        阻塞队列之所以能够保证并发安全，底层是通过锁的机制来实现的（重入锁）

     */
    public void createQueue(){
        BlockingQueue queue1 = new ArrayBlockingQueue<>(10);
        BlockingQueue queue2 = new LinkedBlockingQueue<>();
    }


    /*
        优先级队列
        要求插入队里的元素必须实现Comparable接口
        队列会按照CompareTo()方法中的比较规则，实现元素的排序。
        取出元素时，是按照排序后的顺序来取的
     */
    @Test
    public void createCompareQueue() throws InterruptedException {
        BlockingQueue<Student> queue =
                new PriorityBlockingQueue<Student>();
        Student stu1 = new Student("tom", 100);
        Student stu2 = new Student("rose", 149);
        Student stu3 = new Student("jim", 80);

        queue.add(stu1);
        queue.add(stu2);
        queue.add(stu3);

        for (int i = 0; i < 3; i++) {
            System.out.println(queue.take());
        }
    }






}
