package com.qyf.jlearn.collection.list.skiplist;

/**
 * 类描述：
 * <p>
 * Skip list是一个“概率型”的数据结构，可以在很多应用场景中替代平衡树。Skip list算法与平衡树相比，有相似的渐进期望时间边界，但是它更简单，更快，使用更少的空间。
 * Skip list是一个分层结构多级链表，最下层是原始的链表，每个层级都是下一个层级的“高速跑道”。
 * <p>
 * 为什么选择跳表
 * 目前经常使用的平衡数据结构有：B树，红黑树，AVL树，Splay Tree, Treep等。
 * 想象一下，给你一张草稿纸，一只笔，一个编辑器，你能立即实现一颗红黑树，或者AVL树
 * 出来吗？ 很难吧，这需要时间，要考虑很多细节，要参考一堆算法与数据结构之类的树，
 * 还要参考网上的代码，相当麻烦。
 * 用跳表吧，跳表是一种随机化的数据结构，目前开源软件 Redis 和 LevelDB 都有用到它，
 * 它的效率和红黑树以及 AVL 树不相上下，但跳表的原理相当简单，只要你能熟练操作链表，就能轻松实现一个 SkipList。
 * <p>
 * 跳表具有如下性质：
 * <p>
 * (1) 由很多层结构组成
 * (2) 每一层都是一个有序的链表
 * (3) 最底层(Level 1)的链表包含所有元素
 * (4) 如果一个元素出现在 Level i 的链表中，则它在 Level i 之下的链表也都会出现。
 * (5) 每个节点包含两个指针，一个指向同一链表中的下一个元素，一个指向下面一层的元素。
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/8/4 17:59
 */
public class SkipListTest {
    public static void main(String[] args) {
        SkipList<String> list = new SkipList<>();
        list.put(10, "sho");
        list.put(1, "sha");
        list.put(9, "na");
        list.put(2, "bing");
        list.put(8, "ling");
        list.put(7, "xiao");
        list.put(100, "你好，skiplist");
        list.put(5, "冰");
        list.put(6, "灵");
        System.out.println("列表元素：\n" + list);
        System.out.println("删除100：" + list.remove(100));
        System.out.println("列表元素：\n" + list);
        System.out.println("5对于的value：\n" + list.get(5).value);
        System.out.println("链表大小：" + list.size() + ",深度：" + list.getLevel());
    }

}
