package com.qyf.jlearn.collection.list;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 类描述：
 * <p>
 * LinkedList 是双向链表，也即每个元素都有指向前后元素的指针。既然是链表那么顺序读取的效率非常高，而随机读取的效率较低。
 * 当随机获取一个 index 位元素时，链表先比较 index 和链表长度 1/2 的大小，小于时从链表头部查找元素，大于时就从链表尾部查找元素。
 * 对比 ArrayList 如果随机读取数据较多时使用 ArrayList 性能高（因为根据数组索引查询），插入删除较多时使用 LinkedList 性能高（不用重新拷贝数据，涉及节点变更较少）
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/5/19 17:32
 */
public class LinkedListTest {

    public static void main(String[] args) {
        // 50
        System.out.println(100 >> 1);

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
        System.out.println(list);
        // 检索但不删除此列表的头（第一个元素）
        assert list.peek() == 11 && list.size() == 14;

        // 当随机获取一个 index 位元素时，链表先比较 index 和链表长度 1/2 的大小（index < (size >> 1)），小于时从链表头部查找元素，大于时就从链表尾部查找元素。
        System.out.println(list.get(3));

    }

}
