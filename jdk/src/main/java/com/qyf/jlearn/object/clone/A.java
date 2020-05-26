package com.qyf.jlearn.object.clone;

/**
 * 类描述：
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/5/14 18:04
 */
public class A implements Cloneable {

    private int a;
    private B b;

    public A() {
    }

    public A(int a) {
        this.a = a;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public B getB() {
        return b;
    }

    @Override
    public String toString() {
        return "A{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }

    public void setB(B b) {
        this.b = b;
    }

    @Override
    protected A clone() throws CloneNotSupportedException {
        return (A) super.clone();
    }
}
