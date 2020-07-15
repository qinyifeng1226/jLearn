package com.qyf.jlearn.map;

import com.qyf.jlearn.map.hashMap.HashKey;
import com.qyf.jlearn.map.hashMap.User;

import java.util.HashMap;
import java.util.Map;

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
 * <p>
 * https://www.cnblogs.com/qingfei1994/p/9151915.html
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/5/27 16:18
 */
public class HashMapTest {
    static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * 根据期望容量cap返回2的n次方的哈希桶的实际容量，返回值一般大于等于cap
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        System.out.println(n);
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    /**
     * 核心方法：
     * 1、get 方法
     * 1.1、getTreeNode：首先找到红黑树的根节点，然后使用根节点调用find方法
     * 1.2、find：红黑树节点的查找，从调用此方法的节点开始查找, 通过hash值和key找到对应的节点
     * 1.3、comparableClassFor：获取节点的KEY是否实现了Comparable接口
     * 1.4、compareComparables：因为实现了Comparable接口，所以能比较KEY大小
     * <p>
     * 2、put 方法
     * 2.1、putTreeVal：节点为TreeNode, 则调用红黑树的put方法插入新节点
     * 2.2、tieBreakOrder：用于不可比较或者hashCode相同时进行比较的方法，定义一套规则用于极端情况下比较两个参数的大小
     * 2.3、treeifyBin：校验节点数是否超过 8 个，如果超过则调用 treeifyBin方法 将链表节点转为红黑树节点
     * 2.4、treeify：构建该节点的红黑树
     * 2.5、moveRootToFront：如果root节点不在table索引位置的头节点, 则将其调整为头节点
     * 2.6、checkInvariants：递归遍历所有节点，校验节点的合法性，主要是保证该树符合红黑树的规则，左节点<当前节点<右节点
     * <p>
     * 3、resize 扩容方法
     * 3.1、resize： 扩容，将newCap赋值为oldCap的2倍
     * 3.2、split： 扩容后，红黑树的hash分布，只可能存在于两个位置：原索引位置、原索引位置+oldCap
     * 3.3、untreeify：如果节点个数<=6个则将红黑树转为链表结构
     * <p>
     * 4、remove 方法
     * 4.1、remove：如果是TreeNode则调用红黑树的移除方法，否则为链表节点，从链表中移除
     * 4.2、removeTreeNode： 目的就是移除调用此方法的节点，也就是该方法中的 this 节点。移除包括链表的处理和红黑树的处理
     * <p>
     * 5、平衡调整：
     * 5.1、balanceInsertion: 将传入的节点作为根节点，遍历所有节点，校验节点的合法性，主要是保证该树符合红黑树的规则。
     * 5.2、balanceDeletion：如果删除的节点是红色则不会破坏红黑树的平衡无需调整
     * 在进行插入和删除时有可能会触发红黑树的插入平衡调整（balanceInsertion 方法）或删除平衡调整（balanceDeletion 方法），调整的方式主要有以下手段：
     * 左旋转（rotateLeft 方法）、右旋转（rotateRight 方法）、改变节点颜色（x.red = false、x.red = true），进行调整的原因是为了维持红黑树的数据结构。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(HashMapTest.tableSizeFor(9));

        Map map1 = new HashMap();
        map1.put(1, 1);
        map1.put(2, 2);
        map1.put(1, 3);

        Map map2 = new HashMap<Integer, User>();
        map2.put(1, new User(1, "user1"));

        Map map3 = new HashMap<HashKey, User>();
        for (int i = 0; i < 56; i++) {
            map3.put(new HashKey(i), new User(i, "user" + i));
        }
        //map3.put(new HashKey(1110), new User(110, "user0"));
        for (int i = 1; i <= 8; i++) {
            map3.put(new HashKey(1111), new User(111, "user" + i));
        }
        map3.put(new HashKey(1111), new User(222, "user"));
        System.out.println(map3.toString());
    }
}
