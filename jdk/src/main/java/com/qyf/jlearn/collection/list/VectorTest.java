package com.qyf.jlearn.collection.list;

import com.qyf.jlearn.object.clone.A;
import com.qyf.jlearn.object.clone.B;

import java.util.Arrays;
import java.util.Vector;

/**
 * 类描述：
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
