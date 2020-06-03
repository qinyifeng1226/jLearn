package com.qyf.jlearn.collection.list;

import java.util.Stack;

/**
 * 类描述：
 * <p>
 * 后进先出（LIFO）
 * Stack 继承自 Vector 所以也是数组实现的，线程安全的栈。
 * 因为 Stack 继承自 Vector 所以就拥有 Vector 中定义的方法，但作为栈数据类型，不建议使用 Vector 中与栈无关的方法，
 * 尽量只用 Stack 中的定义的栈相关方法，这样不会破坏栈数据类型
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/5/27 16:14
 */
public class StackTest {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.add(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack);

        // 返回最后一个顶端数据：3
        System.out.println(stack.peek());

        // 检索并删除此列表的头（最后一个元素）
        assert stack.pop() == 3 && stack.size() == 2;
        System.out.println(stack);

        test(127);

    }

    /**
     * 如果x在[-128, 127]，那么两次都会输出true；如果不是在这个范围，会输出true和false
     * 原因在于autobox，对于某些值，s1.push(x)会转化为s1.push(Integer.valueOf(x))，然后会利用cache的值，导致实例复用
     *
     * @param x
     */
    public static void test(int x) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        // 自动装箱：s1.push(x)会转化为s1.push(Integer.valueOf(x))
        s1.push(x);
        s2.push(x);
        int p1 = s1.peek();
        int p2 = s2.peek();
        System.out.println(p1 == p2);
        // 自动拆箱 s1.peek()
        System.out.println(s1.peek() == s2.peek());
    }
}
