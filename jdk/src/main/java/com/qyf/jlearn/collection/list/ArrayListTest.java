package com.qyf.jlearn.collection.list;

import java.util.*;
import java.util.function.Consumer;

/**
 * 类描述：
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/5/11 17:53
 */
public class ArrayListTest {


    public static void addTest() {
        // 1 1 1 1 1 1 1 1 1 1
        System.out.println(1 << 25);

        List<Integer> list = new ArrayList<>();
        // length从0->10，扩容后elementData指向新的数组
        list.add(1);
        List<Integer> list2 = Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10);
        // length还是10
        list.addAll(list2);
        // length从10->15 (10+10>>1)，扩容后elementData指向新的数组
        list.add(11);
        // length从15->22 (15+15>>1)，扩容后elementData指向新的数组，size=20
        list.addAll(list2);
        //list.set(-1,11);
        // 从5->20开始复制，elementData[5] = 55;
        list.add(5, 55);
        System.out.println(list);

        list.remove(5);
        System.out.println(list);

        list.remove(new Integer(6));
        System.out.println(list);

        // protected方法其他包不能调用
        //list.removeRange(2,4);

        // 子列表实际返回的数据是原列表的数据引用
        List subList = list.subList(2, 4);
        // 删除原列表，会导致子列表后面操作保存
        // list.clear();
        // 修改子列表会影响原列表的数据
        subList.set(1, 99);
        System.out.println(subList);
        System.out.println(list);

        // 删除等于2,3的元素，从0开始重新赋值，多余的item赋null回收
        list.removeAll(Arrays.asList(2, 3));
        System.out.println(list);

        // 保留等于4,5的元素，从0开始重新赋值，多余的item赋null回收
        list.retainAll(Arrays.asList(4, 5));
        System.out.println(list);

        // 并行分割集合
        Spliterator<Integer> spliterator0 = list.spliterator();
        Spliterator<Integer> spliterator1 = list.spliterator();
        // 将spliterator1再次分割
        Spliterator<Integer> spliterator2 = spliterator1.trySplit();

        spliterator0.forEachRemaining(new Consumer<Integer>() {
            @Override
            public void accept(Integer t) {
                System.out.println(Thread.currentThread().getName() + "---" + t + " ");
            }
        });

        spliterator1.forEachRemaining(t -> {
            t++;
            System.out.println(Thread.currentThread().getName() + "1---" + t + " ");
        });

        spliterator2.forEachRemaining(t -> System.out.println(Thread.currentThread().getName() + "2---" + t + " "));

        // 不能在for循环中删除、新增，因为for循环本质上是Itr遍历，next方法会检查修改次数checkForComodification，但是remove方法调用的是ArrayList的方法，所以会导致expectedModCount != modCount
        /*
        System.out.println(list.hashCode());
        for (Integer a : list) {
            System.out.println(a.hashCode());
            list.add(6);
            list.remove(a);
        }

        for (int i = 0; i < list.size(); i++) {
            list.remove(i);
        }
        */
        Iterator itr = list.iterator();
        while (itr.hasNext()) {
            itr.next();
            // 调用remove之前必须先调用next，因为lastRet<0会报错IllegalStateException
            // 虽然最终调用的remove还是ArrayList的方法，但是移除之后将 expectedModCount = modCount，所以在next会正确通过检查checkForComodification
            itr.remove();
        }

        list.clear();
        System.out.println(list);

        List<String>  arrs = new ArrayList<>();
        arrs.add("a");
        arrs.add("b");
        arrs.add("c");
        arrs.add("d");
        arrs.add("e");
        arrs.add("f");
        arrs.add("h");
        arrs.add("i");
        arrs.add("j");
        Spliterator<String> a =  arrs.spliterator();
//        a.tryAdvance(t -> System.out.println("tryAdvance1---" + t + " "));
//        // 打印tryAdvance1---a
//        a.tryAdvance(t -> System.out.println("tryAdvance2---" + t + " "));
//        // 打印tryAdvance2---b
//        a.tryAdvance(t -> System.out.println("tryAdvance3---" + t + " "));
        // 打印tryAdvance2---c

        //此时结果：a:0 ~ -1（index-fence）
        Spliterator<String> b = a.trySplit();
        //此时结果：b:0-4,a:4-9
        Spliterator<String> c = a.trySplit();
        //此时结果：c:4-6,b:0-4,a:6-9
        Spliterator<String> d = a.trySplit();
        //此时结果：d:6-7,c:4-6,b:0-4,a:7-9
    }

    public static void main(String[] args) {
        addTest();
    }


}
