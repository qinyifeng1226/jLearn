package com.qyf.jlearn.jvm;

import org.springframework.cglib.proxy.CallbackFilter;
import org.springframework.cglib.proxy.Dispatcher;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import sun.misc.Unsafe;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：
 * 不算最新出现的神器 ZGC，历史上出现过 7 种经典的垃圾回收器。
 * Serial、ParNew、Parallel Scavenge、Serial Old、Parallel Old、CMS（Concurrent Mark Sweep）、G1（Garbage-First）收集器
 * <p>
 * 在第一部分 JVM 内存布局中，我们知道了 thread 独享的区域：PC Regiester、JVM Stack、Native Method Stack，其生命周期都与线程相同（即：与线程共生死），所以无需 GC。线程共享的 Heap 区、Method Area 则是 GC 关注的重点对象。
 *
 *
 * 与 CMS 相比，G1 有内存整理过程（标记 - 压缩），避免了内存碎片；STW 时间可控（能预测 GC 停顿时间）
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/6/4 13:42
 */
public class OOMTest {
    public static void main(String[] args) {
        OOMTest test = new OOMTest();
        // java -Xmx10M -XX:+UseG1GC -Xlog:gc* -Xlog:gc:gc.log -XX:+HeapDumpBeforeFullGC  OOMTest.java
        // heap 区 OOM 测试
        //test.heapOOM();

        // java -Xmx20M -Xss180k -XX:+UseG1GC -Xlog:gc* -Xlog:gc:gc.log  -XX:+HeapDumpBeforeFullGC OOMTest.java
        // 虚拟机栈和本地方法栈溢出
        //test.stackOverflow();

        // java Xmx20M -XX:+UseConcMarkSweepGC -XX:MaxMetaspaceSize=10M  -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xlog
        // metaspace OOM 测试 （JDK1.8同1.7比，最大的差别就是：元数据区取代了永久代）
        test.metaspaceOOM2();

        // 堆外内存 OOM 测试
        //test.directOOM();
    }

    /**
     * heap OOM 测试
     */
    public void heapOOM() {
        List<OOMTest> list = new ArrayList<>();
        while (true) {
            list.add(new OOMTest());
        }
    }


    private int stackLength = 1;

    public void stackLeak() {
        stackLength += 1;
        stackLeak();
    }

    /**
     * VM Stack / Native method Stack 溢出测试
     */
    public void stackOverflow() {
        OOMTest test = new OOMTest();
        try {
            test.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + test.stackLength);
            throw e;
        }
    }

    public void genString() {
        List<String> list = new ArrayList<>();
        int i = 0;
        while (true) {
            list.add("string-" + i);
            i++;
        }
    }

    /**
     * metaspace/ 常量池 OOM 测试
     */
    public void metaspaceOOM() {
        //System.out.println("metaspaceOOM");
        OOMTest test = new OOMTest();
        test.metaspaceOOM();
        //test.genString();
    }

    public void metaspaceOOM2() {
        ClassLoadingMXBean loadingBean = ManagementFactory.getClassLoadingMXBean();
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMTest.class);
            enhancer.setCallbackTypes(new Class[]{Dispatcher.class, MethodInterceptor.class});
            enhancer.setCallbackFilter(new CallbackFilter() {
                @Override
                public int accept(Method method) {
                    return 1;
                }

                @Override
                public boolean equals(Object obj) {
                    return super.equals(obj);
                }
            });

            Class clazz = enhancer.createClass();
            System.out.println(clazz.getName());
            //显示数量信息（共加载过的类型数目，当前还有效的类型数目，已经被卸载的类型数目）
            System.out.println("total: " + loadingBean.getTotalLoadedClassCount());
            System.out.println("active: " + loadingBean.getLoadedClassCount());
            System.out.println("unloaded: " + loadingBean.getUnloadedClassCount());
        }
    }

    public void allocDirectMemory() {
        final int _1MB = 1024 * 1024;

        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = null;
        try {
            unsafe = (Unsafe) unsafeField.get(null);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }

        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }

    /**
     * 堆外内存 OOM 测试
     */
    public void directOOM() {
        OOMTest test = new OOMTest();
        test.allocDirectMemory();
    }

}
