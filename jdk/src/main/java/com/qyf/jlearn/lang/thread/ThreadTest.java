package com.qyf.jlearn.lang.thread;

import java.io.IOException;

/**
 * 类描述：
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/8/3 17:57
 */
public class ThreadTest {

    private int i = 10;
    private Object object = new Object();

    /**
     * sleep方法不会释放锁，也就是说如果当前线程持有对某个对象的锁，则即使调用sleep方法，其他线程也无法访问这个对象。
     * 在thread1睡眠过程中获取thread1/thread2状态结果：thread1.state=TIMED_WAITING    thread2.state=BLOCKED
     */
    class MyThread extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                i++;
                System.out.println("i:" + i);
                try {
                    System.out.println("线程" + Thread.currentThread().getName() + "进入睡眠状态");
                    // sleep相当于让线程睡眠，交出CPU，让CPU去执行其他的任务。
                    sleep(5000);
                    //Thread.currentThread().sleep(5000);
                } catch (InterruptedException e) {
                    // TODO: handle exception
                }
                System.out.println("线程" + Thread.currentThread().getName() + "睡眠结束");
                i++;
                System.out.println("i:" + i);
            }
        }
    }

    /**
     * 测试sleep
     *
     * @param args
     * @throws IOException
     */
    public static void main1(String[] args) throws IOException {
        ThreadTest test = new ThreadTest();
        MyThread thread1 = test.new MyThread();
        MyThread thread2 = test.new MyThread();
        thread1.start();
        thread2.start();
    }

    /**
     * 将Cpu让给其它线程优先执行，自己进入等待执行（Runnable）状态。yield函数没有设置等待执行的时间，一切听从cpu的调度，当没有其它线程抢占cpu时，当前线程又会被cpu调度进入Running状态。它跟sleep方法类似，同样不会释放锁
     * 当增加yield()执行下面这段代码和没有yield()时的区别在于：当增加yield()函数后循环执行到149时将cpu的使用权让给了另一个线程执行，知道另一个线程执行完毕再从149自增打印输出。
     */
    public static class ReadThread extends Thread {
        int i = 0;

        @Override
        public void run() {
            while (i < 300) {
                System.out.println("*******  " + Thread.currentThread().getId() + "    **********: " + i++);
                if (150 == i) {
                    System.out.println(Thread.currentThread().getId() + "==================================");
                    Thread.yield();
                    System.out.println(Thread.currentThread().getId() + "    **********: " + Thread.currentThread().getState());
                }
            }
            System.out.println(i + "   currentThread: " + Thread.currentThread());
        }
    }

    /**
     * 测试yield
     *
     * @param args
     */
    public static void main(String[] args) {
        threadYieldTest();
//        new ReadThread().start();
//        new ReadThread().start();
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 测试yield
     * 1）yield, sleep 都能暂停当前线程，sleep 可以指定具体休眠的时间，而 yield 则依赖 CPU 的时间片划分。
     * 2）yield, sleep 两个在暂停过程中，如已经持有锁，则都不会释放锁资源。
     * 3）yield 不能被中断，而 sleep 则可以接受中断。
     * yield 即 "谦让"，也是 Thread 类的方法。它让掉当前线程 CPU 的时间片，使正在运行中的线程重新变成就绪状态，并重新竞争 CPU 的调度权。
     * 它可能会获取到，也有可能被其他线程获取到。
     */
    private static void threadYieldTest() {
        Runnable runnable = () -> {
            for (int i = 0; i <= 100; i++) {
                System.out.println(Thread.currentThread().getName() + "-----" + i);
                if (i % 20 == 0) {
                    Thread.yield();
                }
            }
        };
        //new Thread(runnable, "AAA").start();
        //new Thread(runnable, "BBB").start();

        Thread thread1 = new Thread(runnable, "AAA");
        Thread thread2 = new Thread(runnable, "BBB");
        // 最高优先权有很大的概率先会输出完的
        thread1.setPriority(Thread.MIN_PRIORITY);
        thread2.setPriority(Thread.MAX_PRIORITY);

        thread1.start();
        thread2.start();
    }
}
