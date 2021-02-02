package com.qyf.jlearn.lang.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

/**
 * 类描述：
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/7/20 16:51
 */
public class ConstructorTest {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // 使用反射第一步:获取操作类ConstructorDemo所对应的Class对象
        Class<?> cls = Class.forName("com.qyf.jlearn.lang.reflect.ConstructorDemo");

        // 获取默认的构造函数
        Constructor constructor1 = cls.getConstructor(new Class[]{});
        constructor1 = cls.getDeclaredConstructor();

        System.out.println("修饰符：" + Modifier.toString(constructor1.getModifiers()));
        System.out.println("构造函数名: " + constructor1.getName());
        System.out.println("参数列表: " + constructor1.getParameterTypes());
        System.out.println("getAnnotatedReturnType: " + constructor1.getAnnotatedReturnType());
        System.out.println("getDeclaredAnnotations: " + constructor1.getDeclaredAnnotations());
        System.out.println("getDeclaringClass: " + constructor1.getDeclaringClass());
        System.out.println("getGenericParameterTypes: " + constructor1.getGenericParameterTypes());
        System.out.println("getParameterAnnotations: " + constructor1.getParameterAnnotations());
        System.out.println("getParameterCount: " + constructor1.getParameterCount());
        System.out.println("getTypeParameters: " + constructor1.getTypeParameters());

        System.out.println("getAnnotations: " + constructor1.getAnnotations());
        //System.out.println("getAnnotation: " + constructor1.getAnnotation());


        //通过默认的构造函数创建ConstructorDemo类的实例
        Object obj = constructor1.newInstance();
        System.out.println("调用默认构造函数生成实例:" + obj.toString());

        System.out.println("===============================================");
        //获取带参数的构造函数
        Constructor constructor2 = cls.getConstructor(new Class[]{int.class, String.class});
        System.out.println("getTypeParameters: " + constructor2.getTypeParameters());

        //获得File类的Constructor对象
        Constructor constructor3 = ConstructorDemo.class.getDeclaredConstructor(new Class[]{int.class});

        //通过带参数的构造函数创建ConstructorDemo类的实例
        Object obj2 = constructor2.newInstance(new Object[]{111, "hello"});
        System.out.println("调用带参数构造函数生成实例:" + obj2.toString());

        // 设置可访问：否则报错，私有构造函数不能被实例化
        constructor3.setAccessible(true);
        Object obj3 = constructor3.newInstance(new Object[]{222222});
        System.out.println("调用带参数私有构造函数生成实例:" + obj3.toString());

    }
}
