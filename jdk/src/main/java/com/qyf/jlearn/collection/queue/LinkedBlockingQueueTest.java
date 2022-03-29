package com.qyf.jlearn.collection.queue;

/**
 * 类描述：
 * <p>
 * 线程安全，插入顺序，阻塞、无界
 * https://blog.csdn.net/luluyo/article/details/92403054
 * 实际上就是ReentrantLock实现的生产者-消费者模式。
 * offer与poll是非阻塞的，put与take是阻塞的。
 * 入队使用入队锁，出队使用出队锁。
 * 单形参的offer与poll调用的是lock()方法，不支持中断。
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/5/27 16:59
 */
public class LinkedBlockingQueueTest {


}
