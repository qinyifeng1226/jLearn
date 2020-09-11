package com.qyf.jlearn.collection.set;

/**
 * 类描述：
 * <p>
 * 非线程安全，无序，性能与并发数无关，内存空间占用较大
 * <p>
 * 跳表的应用场景
 * Java API中提供了支持并发操作的跳跃表ConcurrentSkipListSet和ConcurrentSkipListMap。
 * 有序的情况下：
 * 在非多线程的情况下，应当尽量使用TreeMap（红黑树实现）。
 * 对于并发性相对较低的并行程序可以使用Collections.synchronizedSortedMap将TreeMap进行包装，也可以提供较好的效率。
 * <p>
 * 但是对于高并发程序，应当使用ConcurrentSkipListMap。
 * 无序情况下：
 * 并发程度低，数据量大时，ConcurrentHashMap 存取远大于ConcurrentSkipListMap。
 * 数据量一定，并发程度高时，ConcurrentSkipListMap比ConcurrentHashMap效率更高。
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/5/27 16:53
 */
public class ConcurrentSkipListSetTest {
}
