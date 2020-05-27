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

    }
}
