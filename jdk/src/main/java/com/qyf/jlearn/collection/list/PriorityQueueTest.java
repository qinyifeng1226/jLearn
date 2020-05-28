package com.qyf.jlearn.collection.list;

import java.util.PriorityQueue;

/**
 * 类描述：
 * <p>
 * 优先队列，数组实现的二叉树
 * 是一个完全二叉树实现的小顶堆（任意一个非叶子节点的权值，都不大于其左右子节点的权值）
 * <p>
 * 是一种无界的，线程不安全的队列
 * 是一种通过数组实现的，并拥有优先级的队列
 * 存储的元素要求必须是可比较的对象， 如果不是就必须明确指定比较器
 * 不允许放入null元素
 * <p>
 * 入列操作：并没对所有加入的元素进行优先级排序，仅仅保证数组第一个元素是最小的即可，简单比较后，使用选择排序法将入列的元素放左边或者右边
 * 出列操作：当第一个元素出列之后，对剩下的元素进行再排序，挑选出最小的元素排在数组第一个位置。
 * 注意这里的比较可以是元素的自然顺序，也可以是依靠比较器的顺序。
 * <p>
 * leftNo = parentNo*2+1
 * rightNo = parentNo*2+2
 * parentNo = (nodeNo-1)/2
 * <p>
 * int parent = (k - 1) >>> 1; // parentNo = (nodeNo-1)/2
 * <p>
 * <p>
 * 入队列时，将元素添加到最后一个位置，然后依次跟父节点比较，直到父节点大于当前值，则停止比较且为当前节点赋值为新插入值；
 * 出队列时，首先将第一个元素移除，然后用最后一个位置的元素，从顶端第一个位置依次跟其左右叶子节点比较，如果大于叶子节点数值，当前左右叶子节点数值较小的节点被赋予末尾元素，较小叶子节点替换父节点，继续循环跟下一层叶子节点比较，直到该末尾元素小于叶子节点，停止比较
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/5/27 16:17
 */
public class PriorityQueueTest {
    public static void main(String[] args) {
        PriorityQueue<String> q = new PriorityQueue<>();

        /**
         * 入列
         * 新加入的元素x可能会破坏小顶堆的性质，因此需要进行调整。调整的过程为：从k指定的位置开始，将x逐层与当前点的parent进行比较并交换，直到满足x >= queue[parent]为止。
         * int parent = (k - 1) >>> 1; // parentNo = (nodeNo-1)/2  获取父节点
         */
        q.offer("1");
        q.offer("2");
        q.offer("5");
        q.offer("3");
        q.offer("4");
        q.offer("6");
        q.offer("7");
        q.offer("0");

        System.out.println(q);

        /**
         * 出列
         * 首先记录0下标处的元素，并用最后一个元素替换0下标位置的元素，之后调用siftDown()方法对堆进行调整，最后返回原来0下标处的那个元素（也就是最小的那个元素）。
         * 重点是siftDown(int k, E x)方法，该方法的作用是从k指定的位置开始，将x逐层向下与当前点的左右孩子中较小的那个交换，直到x小于或等于左右孩子中的任何一个为止。
         *
         * 首先找到左右孩子中较小的那个，记录到c里，并用child记录其下标
         * int child = (k << 1) + 1; 获取叶子节点
         * Object c = queue[child]; //leftNo = parentNo*2+1 左叶子节点
         * int right = child + 1; 右叶子节点
         *
         */

        System.out.println(q.poll());  //1
        System.out.println(q.poll());  //2
        System.out.println(q.poll());  //3
        System.out.println(q.poll());  //4
        System.out.println(q.poll());  //5
    }

}