package com.qyf.jlearn.third.json.test;

/**
 * 类描述：
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/7/15 13:48
 */
public class Store {
    private String name;

    private Fruit fruit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Fruit getFruit() {
        return fruit;
    }

    public void setFruit(Fruit fruit) {
        this.fruit = fruit;
    }

    @Override
    public String toString() {
        return "Store{" +
                "name='" + name + '\'' +
                ", fruit=" + fruit +
                '}';
    }
}
