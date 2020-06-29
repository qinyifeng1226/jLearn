package com.qyf.jlearn.concurrent.locks;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 类描述：测试重入锁状态
 * <p>
 * ReentrantLock 和synchronized 都是 可重入锁
 * 如果是同一个线程加锁，Sync#nonfairTryAcquire中获取的AQS状态state将自增，释放锁将自减
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/6/5 16:06
 */
public class ReentrantLockTest2 implements Runnable {

    ReentrantLock lock = new ReentrantLock();

    public void get() {
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        set();
        lock.unlock();
    }

    public void set() {
        lock.lock();
        System.out.println(Thread.currentThread().getId());

        // nonfairTryAcquire中获取的AQS状态state==2，设置为3
        {
//            lock.lock();
//            System.out.println(Thread.currentThread().getId());
//            lock.unlock();

            // 或者使用这种方式，AQS的state==2
            set2();
        }

        lock.unlock();
    }

    public void set2() {
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        lock.unlock();
    }

//    public synchronized void get() {
//        System.out.println(Thread.currentThread().getId());
//        set();
//    }
//
//    public synchronized void set() {
//        System.out.println(Thread.currentThread().getId());
//    }

    @Override
    public void run() {
        get();
    }

    public static void main(String[] args) {
        ReentrantLockTest2 ss = new ReentrantLockTest2();
        new Thread(ss).start();
        new Thread(ss).start();
        new Thread(ss).start();
    }

}
