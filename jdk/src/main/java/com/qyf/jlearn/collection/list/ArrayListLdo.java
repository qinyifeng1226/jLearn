package com.qyf.jlearn.collection.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * 类描述：
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/5/11 17:53
 */
public class ArrayListLdo {


    public static void addTest() {
        List<Integer> list=new ArrayList<>();
        // length从0->10，扩容后elementData指向新的数组
        list.add(1);
        List<Integer> list2=Arrays.asList(2,3,4,5,6,7,8,9,10);
        // length还是10
        list.addAll(list2);
        // length从10->15 (10+10>>1)，扩容后elementData指向新的数组
        list.add(11);
        // length从15->22 (15+15>>1)，扩容后elementData指向新的数组，size=20
        list.addAll(list2);
        //list.set(-1,11);
        // 从5->20开始复制，elementData[5] = 55;
        list.add(5,55);
        System.out.println(list);

        list.remove(5);
        System.out.println(list);

        list.remove(new Integer(6));
        System.out.println(list);

        // protected方法其他包不能调用
        //list.removeRange(2,4);

        // 子列表实际返回的数据是原列表的数据引用
        List subList=list.subList(2,4);
        // 删除原列表，会导致子列表后面操作保存
        // list.clear();
        // 修改子列表会影响原列表的数据
        subList.set(1,99);
        System.out.println(subList);
        System.out.println(list);

        // 删除等于2,3的元素，从0开始重新赋值，多余的item赋null回收
        list.removeAll(Arrays.asList(2,3));
        System.out.println(list);

        // 保留等于4,5的元素，从0开始重新赋值，多余的item赋null回收
        list.retainAll(Arrays.asList(4,5));
        System.out.println(list);

        // 并行分割集合
        Spliterator<Integer> spliterator0=list.spliterator();
        Spliterator<Integer> spliterator1=list.spliterator();
        // 将spliterator1再次分割
        Spliterator<Integer> spliterator2=spliterator1.trySplit();

        spliterator0.forEachRemaining(new Consumer<Integer>() {
            @Override
            public void accept(Integer t) {
                System.out.println( Thread.currentThread().getName() + "---" + t + " ");
            }
        });

        spliterator1.forEachRemaining(t -> {
            t++;
            System.out.println( Thread.currentThread().getName() + "1---" + t + " ");
        });

        spliterator2.forEachRemaining(t -> {
            System.out.println( Thread.currentThread().getName() + "2---" + t + " ");
        });

        list.clear();
        System.out.println(list);
    }

    public static void main(String[] args) {
        addTest();
    }


}
