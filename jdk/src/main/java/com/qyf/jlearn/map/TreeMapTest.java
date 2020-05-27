package com.qyf.jlearn.map;

/**
 * 类描述：
 * <p>
 * 红黑树实现的 key->value 容器，可排序
 * 红黑树是一种自平衡二叉查找树
 * <p>
 * TreeMap 是一个有序的key-value集合，它是通过红黑树实现的。TreeMap 继承于AbstractMap，所以它是一个Map，即一个key-value集合，value可为空，可以不能为空。
 * TreeMap 实现了NavigableMap接口，意味着它支持一系列的导航方法。比如返回有序的key集合。TreeMap 实现了Cloneable接口，意味着它能被克隆。
 * TreeMap 实现了java.io.Serializable接口，意味着它支持序列化。
 * <p>
 * TreeMap基于红黑树（Red-Black tree）实现。该映射根据其键的自然顺序进行排序，或者根据创建映射时提供的 Comparator 进行排序，具体取决于使用的构造方法。
 * TreeMap的基本操作 containsKey、get、put 和 remove 的时间复杂度是 log(n) 。另外，TreeMap是非同步的。 它的iterator 方法返回的迭代器是fail-fast的。
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/5/27 16:18
 */
public class TreeMapTest {
}
