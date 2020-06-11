package com.qyf.jlearn.concurrent.locks;

/**
 * 类描述：
 * ReentrantLock是JDK1.5引入的，它拥有与synchronized相同的并发性和内存语义，并提供了超出synchonized的其他高级功能(例如，中断锁等候、条件变量等)，并且使用ReentrantLock比synchronized能获得更好的可伸缩性。
 * <p>
 * ReentrantLock的实现基于AQS(AbstractQueuedSynchronizer)和LockSupport。
 * AQS主要利用硬件原语指令(CAS compare-and-swap)，来实现轻量级多线程同步机制，并且不会引起CPU上文切换和调度，同时提供内存可见性和原子化更新保证(线程安全的三要素：原子性、可见性、顺序性)。
 * <p>
 * AQS的本质上是一个同步器/阻塞锁的基础框架，其作用主要是提供加锁、释放锁，并在内部维护一个FIFO等待队列，用于存储由于锁竞争而阻塞的线程。
 * <p>
 * AQS中有三个protected方法：getState, setState, compareAndSetState。
 * 这三个方法是AQS中提供给自定义同步器来获取state状态的三个基本工具方法，那么setState和compareAndSetState都是用来修改状态的，两者有什么不一样，为什么两者能共存，其各自使用场景分别是什么？
 * 分析AQS和ReentrantLock的源码可知：
 * compareAndSetState通常用于在获取到锁之前，尝试加锁时，对state进行修改，这种场景下，由于当前线程不是锁持有者，所以对state的修改是线程不安全的，也就是说可能存在多个线程都尝试修改state,所以需要保证对state修改的原子性操作，即使用了unsafe类的本地CAS方法；
 * setState方法通常用于当前正持有锁的线程对state变量进行修改，不存在竞争，是线程安全的，所以此处没必要用CAS保证原子性，修改的性能更重要。
 * <p>
 * CAS：Compare and Swap, 翻译成比较并交换。 
 * java.util.concurrent包中借助CAS实现了区别于synchronouse同步锁的一种乐观锁，使用这些类在多核CPU的机器上会有比较好的性能.
 * CAS有3个操作数，内存值V，旧的预期值A，要修改的新值B。当且仅当预期值A和内存值V相同时，将内存值V修改为B，否则什么都不做。
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/6/5 16:06
 */
public class ReentrantLockTest {

}
