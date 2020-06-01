package com.qyf.jlearn.collection.list;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 类描述：
 * <p>
 * 线程安全，插入顺序，并发读取性能高，占用冗余内存空间
 * <p>
 * 写入时复制（CopyOnWrite，简称COW）思想是计算机程序设计领域中的一种优化策略。其核心思想是，如果有多个调用者（Callers）同时要求相同的资源（如内存或者是磁盘上的数据存储），
 * 他们会共同获取相同的指针指向相同的资源，直到某个调用者视图修改资源内容时，系统才会真正复制一份专用副本（private copy）给该调用者，
 * 而其他调用者所见到的最初的资源仍然保持不变。这过程对其他的调用者都是透明的（transparently）。
 * 此做法主要的优点是如果调用者没有修改资源，就不会有副本（private copy）被创建，因此多个调用者只是读取操作时可以共享同一份资源。
 * <p>
 * 几个要点
 * 实现了List接口
 * 内部持有一个ReentrantLock lock = new ReentrantLock();
 * 底层是用volatile transient声明的数组 array
 * 读写分离，写时复制出一个新的数组，完成插入、修改或者移除操作后将新数组赋值给array
 * 注：
 * <p>
 * 　　volatile （挥发物、易变的）：变量修饰符，只能用来修饰变量。volatile修饰的成员变量在每次被线程访问时，都强迫从共享内存中重读该成员变量的值。
 * 而且，当成员变量发生变 化时，强迫线程将变化值回写到共享内存。这样在任何时刻，两个不同的线程总是看到某个成员变量的同一个值。
 * <p>
 * 代码很简单，但是使用CopyOnWriteMap需要注意两件事情：
 * <p>
 * 　　1. 减少扩容开销。根据实际需要，初始化CopyOnWriteMap的大小，避免写时CopyOnWriteMap扩容的开销。
 * 　　2. 使用批量添加。因为每次添加，容器每次都会进行复制，所以减少添加次数，可以减少容器的复制次数。如使用上面代码里的addBlackList方法。
 * <p>
 * CopyOnWrite的缺点
 * CopyOnWrite容器有很多优点，但是同时也存在两个问题，即内存占用问题和数据一致性问题。所以在开发的时候需要注意一下。
 * <p>
 * 　　内存占用问题。因为CopyOnWrite的写时复制机制，所以在进行写操作的时候，内存里会同时驻扎两个对象的内存，旧的对象和新写入的对象（注意:在复制的时候只是复制容器里的引用，只是在写的时候会创建新对象添加到新容器里，而旧容器的对象还在使用，所以有两份对象内存）。如果这些对象占用的内存比较大，比如说200M左右，那么再写入100M数据进去，内存就会占用300M，那么这个时候很有可能造成频繁的Yong GC和Full GC。之前我们系统中使用了一个服务由于每晚使用CopyOnWrite机制更新大对象，造成了每晚15秒的Full GC，应用响应时间也随之变长。
 * <p>
 * 　　针对内存占用问题，可以通过压缩容器中的元素的方法来减少大对象的内存消耗，比如，如果元素全是10进制的数字，可以考虑把它压缩成36进制或64进制。或者不使用CopyOnWrite容器，而使用其他的并发容器，如ConcurrentHashMap。
 * <p>
 * 　　数据一致性问题。CopyOnWrite容器只能保证数据的最终一致性，不能保证数据的实时一致性。所以如果你希望写入的的数据，马上能读到，请不要使用CopyOnWrite容器。
 * <p>
 * CopyOnWriteArrayList为什么并发安全且性能比Vector好
 * 　我知道Vector是增删改查方法都加了synchronized，保证同步，但是每个方法执行的时候都要去获得锁，性能就会大大下降，而CopyOnWriteArrayList 只是在增删改上加锁，但是读不加锁，在读方面的性能就好于Vector，CopyOnWriteArrayList支持读多写少的并发情况。
 * <p>
 * <p>
 * 这个容器很简单，虽然是采用了读写分离的思想，但是却有很大不同，不同之处在于copy。
 * <p>
 * 1、读写锁
 * 读线程具有实时性，写线程会阻塞。解决了数据不一致的问题。但是读写锁依然会出现读线程阻塞等待的情况
 * 2、CopyOnWriteArrayList
 * 读线程具有实时性，写线程会阻塞。不能解决数据不一致的问题。但是CopyOnWriteArrayList 不会出现读线程阻塞等待的情况
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/5/27 16:49
 */
public class CopyOnWriteArrayListTest {

    public static void main(String[] args) {
        CopyOnWriteArrayList<String> cowList = new CopyOnWriteArrayList();
        cowList.add("1");
        cowList.addAll(Arrays.asList("2", "3", "4", "5"));
        System.out.println(cowList);

        System.out.println(cowList.get(2));


    }

}
