package com.qyf.jlearn.collection.list;

import com.qyf.jlearn.object.clone.A;
import com.qyf.jlearn.object.clone.B;

import java.util.Arrays;
import java.util.Vector;

/**
 * 类描述：
 * <p>
 * 与 ArrayList 一样也是通过数组实现的，不同的是 Vector 是线程安全的，也即同一时间下只能有一个线程访问 Vector，线程安全的同时带来了性能的耗损，所以一般都使用 ArrayList。
 * Vector 的扩容也与 ArrayList 不同，可以设置扩容值，默认每次扩容原来的一倍。
 * 底层是数组结构array，与ArrayList相同，查询速度快，增删改慢
 * <p>
 * 如果集合中的元素数量大于当前集合数组的长度时，Vector的增长率是目前数组长度的100%，而ArryaList增长率为目前数组长度的50%。
 * 所以，如果集合中使用数据量比较大的数据，用Vector有一定优势
 * 线程同步ArrayList是线程不同步，所以Vector线程安全，但是因为每个方法都加上了synchronized，所以在效率上小于ArrayList
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/5/21 10:04
 */
public class VectorTest {

    public static void main(String[] args) {
        Vector<Integer> vc = new Vector<>();
        vc.add(1);
        vc.add(0, 0);
        vc.addAll(Arrays.asList(2, 3, 4, 5, 6, 7));
        vc.remove(Integer.valueOf(7));
        vc.remove(Integer.valueOf(3));
        vc.remove(0);
        vc.add(2, 3);
        vc.add(8);
        vc.add(80000);

        vc.get(2);

        //Object vc2=vc.clone();

        Vector<String> vc3 = new Vector<>();
        vc3.add("hello");
        //Object vc4=vc3.clone();

        /**
         * ArrayList.clone()是浅层拷贝。那么什么是浅拷贝呢？
         * 对于ArrayList变量就是指：两个变量指示内存中的地址是不一样的，但是变量中的元素指向同一个元素。
         * 深层拷贝是指，不仅仅变量指示的内存地址不一样，而且变量中的各个元素所指地址也是不一样的。
         */
        Vector<A> vc5 = new Vector<>();
        vc5.add(new A(1));
        Vector<A> vc6 = (Vector<A>) vc5.clone();
        vc5.get(0).setA(2);
        vc5.get(0).setB(new B(3, 4));
        // 基础数据类型拷贝数据修改互不影响，但是非基础数据类型互相影响
        assert vc5.get(0).getA() == vc6.get(0).getA();

    }

}
