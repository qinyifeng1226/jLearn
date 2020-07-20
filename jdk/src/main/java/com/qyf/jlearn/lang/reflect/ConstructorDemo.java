package com.qyf.jlearn.lang.reflect;

/**
 * 类描述：
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/7/20 16:52
 */
public class ConstructorDemo {
    private int num;
    private String name;

    public Integer age = 20;

    public ConstructorDemo() {
        num = 2;
        name = "xixi";
    }

    public ConstructorDemo(int num, String name) {
        this.num = num;
        this.name = name;
    }

    private ConstructorDemo(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "ConstructorDemo [num=" + num + ", name=" + name + "]";
    }
}
