package com.qyf.jlearn.collection.list;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 类描述：
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/5/19 17:32
 */
public class LinkedListTest {

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(1, 11);
        List<Integer> list2 = Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10);
        // length==11
        list.addAll(list2);
        list.add(2, 12);
        // length==14
        list.addAll(2, new ArrayDeque(Arrays.asList(21, 22)));
        // length==15
        list.addFirst(0);
        System.out.println(list);

        // 浅拷贝，只拷贝值，两者互不影响
        LinkedList<Integer> copyList = (LinkedList<Integer>) list.clone();
        System.out.println(copyList);

        list.set(0, 100);
        copyList.set(0, 200);
        // 100
        System.out.println(list.get(0));
        // 200
        System.out.println(copyList.get(0));
        // 添加到尾部，length==16
        list.offer(16);
        System.out.println(list);
        // 检索但不删除此列表的头（第一个元素）
        System.out.println(list.peek() + "=====" + list.peekLast());
        assert list.peek() == 100 && list.size() == 16;
        assert list.peekLast() == 16 && list.size() == 16;

        // 检索并删除此列表的头（第一个元素）
        assert list.poll() == 100 && list.size() == 15;

        // 从此列表表示的堆栈中弹出一个元素,检索并删除此列表的头
        assert list.pop() == 1 && list.size() == 14;

        // 将元素推送到由此列表表示的堆栈上，添加到列表头, length==15
        list.push(0);
        assert list.peek() == 0 && list.size() == 15;
        System.out.println(list);

        // 检索并删除此列表的头（第一个元素）
        list.remove();
        assert list.peek() == 0 && list.size() == 14;
    }

}
