package com.qyf.jlearn.object.clone;

import java.util.Date;
import java.util.Objects;

/**
 * 类描述：
 * <p>
 * 第一，它必须实现了Cloneable接口，否则会抛出CloneNotSupportedException异常。
 * 第二，它必须提供一个public的clone方法，也就是重写Object.clone()方法，否则编译不能通过。
 * 第三，对于存在可变域的类，在clone方法中需要对这些可变域进行拷贝。
 * <p>
 * obj.clone().getClass()==obj.getClass()，即它们具有相同的类型。
 * 还有一点，因为只是简单的将对象的空间进行复制，所以如果类具有引用类型的实例变量的话，也只是将这个引用进行拷贝，并不复制其引用的对象。
 * 这就导致拷贝对象的引用实例变量与原对象的指向相同的对象，这就是传说中的“浅拷贝”。
 * 如果实例变量引用的对象是不可变的，类似于String，则拷贝对象与原对象不能互相影响，这样的拷贝是成功的。
 * 但是如果引用的是可变对象，它们就会影响彼此，对于成功的拷贝而言，这是不允许的。
 * 可以对可变的实例变量对象进行特殊处理，以实现拷贝对象和原对象不能相互影响的“深拷贝”。
 * <p>
 * 由于Object.clone()方法是protected的，所以它只能在lang包中的类或是其子类的方法内部被调用，所以，如果像下面这样调用，会编译出错，
 * 在Person kobe_bak=kobe.clone();报错，说clone只能在Object的protected作用域访问。
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/5/14 18:22
 */
public class Person implements Cloneable {
    private int age;
    private String name;
    private Date birth;

    public Person(int a, String n, Date b) {
        age = a;
        name = n;
        birth = b;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", birth=" + birth +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Person p = (Person) super.clone();
        //Date类型的birth域是可变变的，需要对其克隆，进行深拷贝
        //Date类实现的克隆，直接调用即可
        p.birth = (Date) this.birth.clone();
        return p;
    }

    /**
     * 1.IDEA中默认assert(断言)是关闭，开启方式如下：
     * <p>
     * 简单来说：就是设置一下jvm的参数，参数是-enableassertions或者-ea（推荐）
     *
     * @param args
     * @throws CloneNotSupportedException
     */
    public static void main(String[] args) throws CloneNotSupportedException {
        Person kobe = new Person(33, "kobe", new Date());
        Person kobe_bak = (Person) kobe.clone();
        // 由于clone时使用的深拷贝，所以对原对象时间设置，不影响新对象的时间
        kobe.setBirth(new Date());

        System.out.println("kobe=" + kobe.toString());
        System.out.println("kobe_bak=" + kobe_bak.toString());

        assert kobe.getBirth() != kobe_bak.getBirth() : "two birthdate was same";

        System.out.println(Objects.hashCode(kobe));
    }

}