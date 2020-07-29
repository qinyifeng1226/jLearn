package com.qyf.jlearn.reservedword;

/**
 * 类描述：
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/7/28 15:45
 */
public class ReservedWordTest {

    /**
     * 一旦你将引用声明作final，你将不能改变这个引用了，编译器会检查代码，如果你试图将变量再次初始化的话，编译器会报编译错误。
     * final也可以声明方法。方法前面加上final关键字，代表这个方法不可以被子类的方法重写。
     * 使用final来修饰的类叫作final类。final类通常功能是完整的，它们不能被继承。
     * 被final修饰的对象内容是可变的
     */
    private static void finalTest() {
        int c = 0;
        char[] value3 = {'h'};
        char[] value1 = new char[5];
        final char[] value2 = value1;
        value2[c++] = 'n';
        value2[c++] = 'u';
        // final修饰符不能修改其指向地址，可以改变指向地址对象的内容
        //value2=null;
        System.out.println(String.valueOf(value2));
    }

    public static void main(String[] args) {
        finalTest();
    }
}
