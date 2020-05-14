package com.qyf.jlearn.object.clone;

/**
 * 类描述：
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/5/14 17:54
 */
public class CloneTest {

    public static String name = "hell0";

    public String getName() {
        return name;
    }

    public static void main2(String[] args) {

        try {
            CloneTest aa = new CloneTest();

            // 如果CloneTest未实现Cloneable，则不能调用clone方法，否则报错
            CloneTest ee = (CloneTest) aa.clone();
            System.out.println("Clone succeed");
            System.out.println(ee.getName());
        } catch (CloneNotSupportedException e) {
            System.out.print("clone failed");
        }
    }

    public static void main(String[] args) {

        A a = new A();
        B b = new B(1, 2);

        a.setA(10);
        a.setB(b);
        try {
            A a1 = a.clone();
            System.out.println("a=" + a.toString());
            // a=A{a=10, b=B{b1=1, b2=2}}
            System.out.println("a1=" + a1.toString());
            // a1=A{a=10, b=B{b1=1, b2=2}}

            a.setA(1000);
            a.getB().setB1(10000);
            a.getB().setB2(8000);

            System.out.println("a=" + a.toString());
            // a=A{a=1000, b=B{b1=10000, b2=8000}}
            System.out.println("a1=" + a1.toString());
            // a1=A{a=10, b=B{b1=10000, b2=8000}}

            // 创建一个B新实例，但是a1还是持有的B旧实例，所以此处修改对于a1.B无感知
            a.setB(new B(9999, 9999));

            System.out.println("a=" + a.toString());
            // a=A{a=1000, b=B{b1=9999, b2=9999}}
            System.out.println("a1=" + a1.toString());
            // a1=A{a=10, b=B{b1=10000, b2=8000}}
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
