package com.qyf.jlearn.collection.set;

/**
 * 类描述：
 * <p>
 * 集合，不可重复
 * 数据无序且唯一,实现类都不是线程安全的类，解决方案：Set set = Collections.sysnchronizedSet(Set对象);
 * <p>
 * HashSet是基于HashMap实现的集合，对HashMap做了一些封装。
 * 与HashMap不同的是元素的保存为链表形式，插入数据时遍历链表查看是否有相同数据，有则返回false，没有返回true。
 * <p>
 * 是Set接口（Set接口是继承了Collection接口的）最常用的实现类，顾名思义，底层是用了哈希表（散列/hash）算法。
 * 其底层其实也是一个数组，存在的意义是提供查询速度，插入的速度也是比较快，但是适用于少量数据的插入操作，判断两个对象是否相等的规则：1、equals比较为true；2、hashCode值相同。
 * 要求：要求存在在哈希表中的对象元素都得覆盖equals和hashCode方法。
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/5/27 16:28
 */
public class HashSetTest {
}
