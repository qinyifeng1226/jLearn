package com.qyf.jlearn.dataStructure;

import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

/**
 * 类描述：
 * <p>
 * Java工具包提供了强大的数据结构。在Java中的数据结构主要包括以下几种接口和类：
 * 枚举（Enumeration）
 * 位集合（BitSet）
 * 向量（Vector）
 * 栈（Stack）
 * 字典（Dictionary）
 * 哈希表（Hashtable）
 * 属性（Properties）
 * <p>
 * Properties 继承于 Hashtable.表示一个持久的属性集.属性列表中每个键及其对应值都是一个字符串。
 * Properties 类被许多Java类使用。例如，在获取环境变量时它就作为System.getProperties()方法的返回值。
 * Properties 定义如下实例变量.这个变量持有一个Properties对象相关的默认属性列表。
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/6/3 15:36
 */
public class PropertiesTest {

    public static void main(String[] args) {
        System.out.println(System.getProperties());

        Properties capitals = new Properties();
        Set states;
        String str;

        capitals.put("Illinois", "Springfield");
        capitals.put("Missouri", "Jefferson City");
        capitals.put("Washington", "Olympia");
        capitals.put("California", "Sacramento");
        capitals.put("Indiana", "Indianapolis");

        // Show all states and capitals in hashtable.
        // get set-view of keys
        states = capitals.keySet();
        Iterator itr = states.iterator();
        while (itr.hasNext()) {
            str = (String) itr.next();
            System.out.println("The capital of " + str + " is " + capitals.getProperty(str) + ".");
        }
        System.out.println();

        // look for state not in list -- specify default
        str = capitals.getProperty("Florida", "Not Found");
        System.out.println("The capital of Florida is " + str + ".");
    }
}
