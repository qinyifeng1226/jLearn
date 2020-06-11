package com.qyf.jlearn.sun.misc;

/**
 * 类描述：
 * CAS：Compare and Swap, 翻译成比较并交换。 
 * java.util.concurrent包中借助CAS实现了区别于synchronouse同步锁的一种乐观锁，使用这些类在多核CPU的机器上会有比较好的性能.
 * CAS有3个操作数，内存值V，旧的预期值A，要修改的新值B。当且仅当预期值A和内存值V相同时，将内存值V修改为B，否则什么都不做。
 * <p>
 * 今天我们主要是针对AtomicInteger的incrementAndGet做深入分析。
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/6/5 17:12
 */
public class UnSafeTest {
}
