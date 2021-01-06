package com.qyf.jlearn.lang;

import sun.swing.BakedArrayList;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/7/20 23:31
 */
public class ClassTest {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        Class cls1 = String.class;

        String s = "Hello";
        Class cls2 = s.getClass();

        Class cls3 = Class.forName("java.lang.String");

        // true
        assert cls1 == cls2;
        assert cls1 == cls3;

        // 用instanceof不但匹配当前类型，还匹配当前类型的子类。而用==判断class实例可以精确地判断数据类型，但不能作子类型比较。
        Integer n = new Integer(123);
        // true
        boolean b3 = n instanceof Integer;
        // true
        boolean b4 = n instanceof Number;
        // true
        boolean b1 = n.getClass() == Integer.class;
        // false
        //boolean b2 = n.getClass() == Number.class;

        Class<?> cls = Class.forName("com.qyf.jlearn.lang.reflect.ConstructorDemo");
        System.out.println("getName：" + cls.getName());

        //获得类的public类型的属性
        Field[] fields = cls.getFields();
        for (Field field : fields) {
            System.out.println("getFields：" + field.getName());
        }
        //获得类的所有属性，包括私有的
        Field[] allFields = cls.getDeclaredFields();
        for (Field field : allFields) {
            System.out.println("getDeclaredFields：" + field.getName());
        }
        //获得类的public类型的方法，包括 Object 类的一些方法
        Method[] methods = cls.getMethods();
        for (Method method : methods) {
            //work waid equls toString hashCode等
            System.out.println("getMethods：" + method.getName());
        }
        //获得类本身定义的所有方法
        Method[] allMethods = cls.getDeclaredMethods();
        for (Method method : allMethods) {
            System.out.println("getDeclaredMethods：" + method.getName());
        }
        //获得指定的方法
        System.out.println("getMethod：" + cls.getMethod("getNum").getName());

        //获得指定的公共属性
        Field f1 = cls.getField("age");
        System.out.println("age：" + f1);

        //获得指定的私有属性
        Field f2 = cls.getDeclaredField("name");
        //启用和禁用访问安全检查的开关，值为 true，则表示反射的对象在使用时应该取消 java 语言的访问检查；反之不取消
        f2.setAccessible(true);
        System.out.println("name：" + f2);

        //创建这个类的一个对象
        Object p2 = cls.newInstance();
        //将 p2 对象的  f2 属性赋值为 Bob，f2 属性即为 私有属性 name
        f2.set(p2, "Bob");
        //使用反射机制可以打破封装性，导致了java对象的属性不安全
        System.out.println("name：" + f2.get(p2));

        //获取构造方法
        Constructor[] constructors = cls.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println("getConstructors：" + constructor.toString());
        }
        //获取默认构造方法
        System.out.println("getConstructor：" + cls.getConstructor().toString());

        /**
         * isAssignableFrom() ：是从类继承的角度去判断，是判断是否为某个类的父类。
         * 父类.class.isAssignableFrom(子类.class)
         */
        System.out.println(ArrayList.class.isAssignableFrom(Object.class));  //false
        System.out.println(List.class.isAssignableFrom(ArrayList.class));  //true
        System.out.println(ArrayList.class.isAssignableFrom(BakedArrayList.class));  //true
        System.out.println(Object.class.isAssignableFrom(ArrayList.class));  //true

        /**
         * instanceof：关键字是从实例继承的角度去判断，instanceof关键字是判断是否某个类的子类。
         * 使用方法：
         * 子类实例 instanceof 父类类型
         * instanceof关键字两个参数，前一个为类的实例，后一个为其本身或者父类的类型。
         */
        List arrayList = new ArrayList();
        System.out.println(arrayList instanceof List);//true

    }
}
