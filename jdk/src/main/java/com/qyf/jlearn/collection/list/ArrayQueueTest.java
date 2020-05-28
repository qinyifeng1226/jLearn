package com.qyf.jlearn.collection.list;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.Arrays;

/**
 * 类描述：
 * <p>
 * 数组队列，先进后出（FIFO）
 * ArrayQueue 是数组实现的队列，从队尾加入数据，只能队头删除数据，可随机读取队列数据
 * <p>
 * ArrayDeque 是队列，但也可以作为栈使用，而且对比 Stack 更高效。作为双端队列那就可以在队列两端插入和删除元素。
 * 当追加元素超过容量限制时，则抛出索引越界异常，也可以通过resize重新指定集合大小。
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/5/27 16:14
 */
public class ArrayQueueTest {

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(2);

        queue.addAll(Arrays.asList(1, 2));

        // 重新设置集合容量大小，将原数组数据拷贝到新数组
        queue.resize(4);

        queue.addAll(Arrays.asList(3, 4));

        /*
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("o(offer)");
            System.out.println("p(poll)");

            char cmd = scanner.next().charAt(0);
            switch (cmd) {
                case 'o':
                    System.out.println("请输入值需要添加的值....");
                    int value = scanner.nextInt();
                    queue.add(value);
                    System.out.println(String.format("queue is : %s", Arrays.toString(queue.toArray())));
                    break;
                case 'p':
                    System.out.println(String.format("value is : %s", queue.get(queue.size() - 1)));
                    System.out.println(String.format("queue is : %s", Arrays.toString(queue.toArray())));
                    break;
                default:
                    System.out.println("未知的命令" + cmd);
                    break;
            }
        }
        */
    }

}
