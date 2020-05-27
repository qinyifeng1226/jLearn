package com.qyf.jlearn.map;

/**
 * 类描述：
 * <p>
 * 映射/字典，无序，键值对，键唯一
 * <p>
 * HashMap就是key->value的键值对数据，key是唯一的，而且key和value都可以为null。
 * HashMap和HashTable相似，HashTable实现了线程同步，在 "Object超类解析" 章节中简单介绍过HashTable的数据存储方式。
 * HashMap 是个无序的字典，遍历时不保证元素顺序。HashMap创建时默认会设置初始容量大小（默认16），和装载因子（默认0.75，扩充容量的阀值），
 * 装载因子=已存入元素个数/总容量大小。当然这两个值也可以手动设置。
 * <p>
 * <p>
 * HashMap当插入一个数据时，先对key值做hash，用得到的值与容器的大小n减1做&运算得到桶的位置，即：i = (n - 1) & hash，i就是桶的位置。
 * 在桶中查找有无元素，没有直接插入，有则比较元素key值是否相同，相同用新值替换。
 * <p>
 * 桶的位置计算为什么是 (n - 1) & hash？先看hash值的计算:
 * return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
 * hash() 函数对 key 取值后返回一个整数。又因为 HashMap 的容量 n 大小始终为 2 的幂（默认为 16），那么 n - 1 的二进制始终是最高位为 1，其它位为 0 的数，
 * 如：10...0，这个数与整数做 & 运算就得到 hash / n 的余数，余数的取值范围在 0 ~ n-1，很巧妙的设计
 * <p>
 * 哈希表的实现原理中，先采用一个数组表示位桶，每个位桶的实现在1.8之前都是使用链表，但当每个位桶的数据较多的时候，链表查询的效率就会不高，
 * 因此在1.8之后，当位桶的数据超过阈值（8）的时候，就会采用红黑树来存储该位桶的数据（在阈值之前还是使用链表来进行存储），所以，哈希表的实现包括数组+链表+红黑树，
 * 在使用哈希表的集合中我们都认为他们的增删改查操作的时间复杂度都是O(1)的，不过常数项很大，因为哈希函数在进行计算的代价比较高,HashMap和Hashtable类似，
 * 不同之处在于HashMap是非同步的，并且允许null，即null value和null key。，但是将HashMap视为Collection时（values()方法可返回Collection），
 * 其迭代子操作时间开销和HashMap 的容量成比例。因此，如果迭代操作的性能相当重要的话，不要将HashMap的初始化容量设得过高，或者load factor过低。
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/5/27 16:18
 */
public class HashMapTest {
}
