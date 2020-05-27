package com.qyf.jlearn.collection.set;

/**
 * 类描述：
 * <p>
 * 继承自 HashSet 与 LinkedHashMap 相似，是对 LinkedHashMap 的封装。
 * 继承了HashSet类，所以它的底层用的也是哈希表的数据结构，但因为保持数据的先后添加顺序，所以又加了链表结构，但因为多加了一种数据结构，所以效率较低，不建议使用，
 * 如果要求一个集合急要保证元素不重复，也需要记录元素的先后添加顺序，才选择使用LinkedHashSet
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/5/27 16:28
 */
public class LinkedHashSetTest {
}
