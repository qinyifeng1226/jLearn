package com.qyf.jlearn.java8;

/**
 * 类描述：
 * <p>
 * Java 8允许在接口中加入具体方法。接口中的具体方法有两种，default方法和static方法，identity()就是Function接口的一个静态方法。
 * Function.identity()返回一个输出跟输入一样的Lambda表达式对象，等价于形如t -> t形式的Lambda表达式。
 *
 * 接口中的default方法是一个无奈之举，在Java 7及之前要想在定义好的接口中加入新的抽象方法是很困难甚至不可能的，因为所有实现了该接口的类都要重新实现。
 * 试想在Collection接口中加入一个stream()抽象方法会怎样？default方法就是用来解决这个尴尬问题的，直接在接口中实现新加入的方法。
 * 既然已经引入了default方法，为何不再加入static方法来避免专门的工具类呢！
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/6/4 20:34
 */
public class FunctionTest {
}
