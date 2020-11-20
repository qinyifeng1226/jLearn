package com.qyf.jlearn.collection.list;

import com.qyf.jlearn.object.clone.A;
import com.qyf.jlearn.object.clone.B;

import java.util.*;
import java.util.function.Consumer;

/**
 * 类描述：
 * https://www.runoob.com/java/java-collections.html
 * <p>
 * 有序，可重复，内部是通过 Array 实现
 * 对数组列表进行插入、删除操作时都需要对数组进行拷贝并重排序。所以如果能知道大概存储多少数据时，尽量初始化初始容量，提升性能
 * 对比 ArrayList 如果随机读取数据较多时使用 ArrayList 性能高（因为根据数组索引查询），插入删除较多时使用 LinkedList 性能高（不用重新拷贝数据，涉及节点变更较少）
 * 底层数据结构使数组结构array，查询速度快，增删改慢，因为是一种类似数组的形式进行存储，因此它的随机访问速度极快
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/5/11 17:53
 */
public class ArrayListTest {


    public static void addTest() {
        // 1 1 1 1 1 1 1 1 1 1
        System.out.println(1 << 25);

        ArrayList<Integer> list = new ArrayList<>();
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

        ArrayList<String> arrs = new ArrayList<>();
        arrs.add("a");
        arrs.add("b");
        arrs.add("c");
        arrs.add("d");
        arrs.add("e");
        arrs.add("f");
        arrs.add("h");
        arrs.add("i");
        arrs.add("j");
        Spliterator<String> a = arrs.spliterator();
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
        a.forEachRemaining(o->System.out.println(o));
    }

    /**
     * ArrayList.clone()是浅层拷贝。那么什么是浅拷贝呢？
     * 对于ArrayList变量就是指：两个变量指示内存中的地址是不一样的，但是变量中的元素指向同一个元素。
     * 深层拷贝是指，不仅仅变量指示的内存地址不一样，而且变量中的各个元素所指地址也是不一样的。
     */
    private static void cloneTest() {
        ArrayList<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(1, 2));

        // 基础数据类型拷贝数据修改互不影响，但是非基础数据类型互相影响
        ArrayList<Integer> copy1 = (ArrayList<Integer>) list.clone();
        list.set(0, 111);
        System.out.println(copy1.get(0));

        list.remove(1);
        assert list.size() != copy1.size();

        ArrayList<String> arrs = new ArrayList<>();
        arrs.add("a");
        ArrayList<String> copy2 = (ArrayList<String>) arrs.clone();
        arrs.set(0, "aaa");
        System.out.println(copy2.get(0));

        ArrayList<A> vc5 = new ArrayList<>();
        vc5.add(new A(1));
        ArrayList<A> vc6 = (ArrayList<A>) vc5.clone();
        vc5.get(0).setA(2);
        vc5.get(0).setB(new B(3, 4));

        // 非基础数据类型互相影响
        assert vc5.get(0).getA() == vc6.get(0).getA();

        ArrayList<B> vc7 = new ArrayList<>();
        vc7.add(new B(1, 2));
        ArrayList<B> vc8 = (ArrayList<B>) vc7.clone();
        vc7.get(0).setB1(111);

        ArrayList<Integer> a1 = new ArrayList<Integer>();
        a1.add(1);
        a1.add(10);
        a1.add(13);
        a1.add(56);
        a1.add(199);
        ArrayList<Integer> a2 = (ArrayList<Integer>) a1.clone();

        System.out.println("删除之前");
        System.out.println(a1);
        System.out.println(a2);

        a2.remove(3);
        a2.remove(1);

        System.out.println("删除之后");
        System.out.println(a1);
        System.out.println(a2);

        ArrayList<Integer> c1 = new ArrayList<Integer>();
        c1.add(1);
        c1.add(10);
        c1.add(13);
        c1.add(56);
        c1.add(199);
        ArrayList<Integer> c2 = new ArrayList<Integer>();
        c2.add(21);
        c2.add(20);
        c2.add(23);
        c2.add(26);
        c2.add(299);
        ArrayList<ArrayList<Integer>> c = new ArrayList<ArrayList<Integer>>();
        c.add(c1);
        c.add(c2);

        ArrayList<ArrayList<Integer>> d = (ArrayList<ArrayList<Integer>>) c.clone();
        System.out.println("删除之前");
        System.out.println(c);
        System.out.println(d);

        c2.remove(3);
        c2.remove(1);

        System.out.println("删除之后");
        System.out.println(c);
        System.out.println(d);
    }

    public static void main(String[] args) {
        addTest();
        cloneTest();
    }


}
