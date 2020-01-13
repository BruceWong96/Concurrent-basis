package concurrent.demo.map;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

/*
    学习并发Map的创建和使用
    HashMap  线程并发非安全，性能高
    HashTable  线程并发安全，性能低


    HashTable
    ①底层所有的方法都上锁，包括读方法
    ②HashTable存在锁整表

    ConcurrentMap
    并发安全的，引入了分段锁机制

 */
public class Demo {

    /*
        ConcurrentHashMap引入分段锁机制，底层分了16个段（segment）
        理论上并发性能要比Hash高16倍
        每个segment可以看作是一个HashTable，即某个线程操作某个K,V的时候，
        只会锁此K,V所在的segment，而不锁其他的segment

        此外ConcurrentHashMap的存取方法和HashMap一致
        而且负载因子概念和HashMap一致

        此外，底层的链表结构也和HashMap一致

        --注意：：以上所讲是JDK1.8版本之前的ConcurrentHashMap

        JDK1.8的ConcurrentHashMap用到了无锁算法，所以性能更高
        此外，把链表结构变成红黑树

        CAS（Compare and Set）算法
        实际上是一种CPU指令
        优点：没有引入锁的机制，避免上锁和释放锁的开销，
            以及上锁释放锁CPU上下文件切换的开销。
        可能存在的问题：会导致失败重试次数很多。
        还可能存在的问题：ABA问题


     */
    @Test
    public void create(){
        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
        map.put(1,1);

        for (int i = 0; i < 1000; i++) {
            map.put(i, i);
        }

        System.out.println();


    }
}
