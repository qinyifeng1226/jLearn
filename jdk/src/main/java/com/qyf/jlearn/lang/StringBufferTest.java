package com.qyf.jlearn.lang;

import java.util.concurrent.CountDownLatch;

/**
 * 类描述：
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/7/29 16:01
 */
public class StringBufferTest {

    public static void main(String[] args) {
        // 测试append
        //testAppend();
        testAppend2();

        /*
         * 声明个字符串s，用下划线和井号是因为两个比较好区分。 分别实例化sf和sd两个对象
         */
        String s = "####____";
        StringBuffer sf = new StringBuffer(s);
        StringBuilder sd = new StringBuilder(s);

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                /*
                 * 似乎sleep一小段效果会好一些 奇奇怪怪的输出格式只是为了便于比较
                 */
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sd.reverse();
                //System.out.println("BUFFER->" + s);
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                /*
                 * 似乎sleep一小段效果会好一些 奇奇怪怪的输出格式只是为了便于比较
                 */
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sd.reverse();
                //System.out.println("BUFFER->" + s);
            }
        }).start();


    }

    /**
     * 测试append
     */
    private static void testAppend() {
        int threadNum = 100;
        CountDownLatch latch1 = new CountDownLatch(2);
        StringBuilder stringBuilder = new StringBuilder();
        StringBuffer stringBuffer = new StringBuffer();

        new Thread(() -> {
            for (int i = 0; i < threadNum; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stringBuilder.append("c");
                stringBuffer.append("c");
                //System.out.println(i + Thread.currentThread().getName() + "----" + stringBuilder.toString().length());
            }
            latch1.countDown();
        }, "线程1").start();

        new Thread(() -> {
            for (int i = 0; i < threadNum; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stringBuilder.append("c");
                stringBuffer.append("c");
                //System.out.println(i + Thread.currentThread().getName() + "----" + stringBuilder.toString().length());
            }
            latch1.countDown();
        }, "线程2").start();

        try {
            latch1.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("stringBuilder: " + stringBuilder.length());
        System.out.println("stringBuffer: " + stringBuffer.length());
    }

    /**
     * 测试append
     * StringBuffer不论运行多少次都是100长度
     * StringBuilder绝大多数情况长度都会小于100
     * StringBuffer线程安全，StringBuilder线程不安全得到证明
     */
    private static void testAppend2() {
        int threadNum = 100;
        CountDownLatch latch1 = new CountDownLatch(threadNum);
        CountDownLatch latch2 = new CountDownLatch(threadNum);
        StringBuilder stringBuilder = new StringBuilder();
        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < threadNum; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        stringBuilder.append(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        latch1.countDown();
                    }
                }
            }).start();
        }
        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                try {
                    stringBuffer.append(1);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    latch2.countDown();
                }

            }).start();
        }
        try {
            latch1.await();
            System.out.println("stringBuilder: " + stringBuilder.length());
            latch2.await();
            System.out.println("stringBuffer: " + stringBuffer.length());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
