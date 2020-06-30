package com.qyf.jlearn.object.hash;

import java.util.ArrayList;

/**
 * 类描述：
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/6/30 22:05
 */
public class HashCodeMean {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        int numberExist = 0;

        //证明hashcode的值不是内存地址
        for (int i = 0; i < 1; i++) {
            Object obj = new Object();
            if (list.contains(obj.toString())) {
                System.out.println(obj.toString() + "  exists in the list. " + i);
                numberExist++;
            } else {
                list.add(obj.toString());
            }
        }

        System.out.println("repetition number:" + numberExist);
        System.out.println("list size:" + list.size());

        //证明内存地址是不同的。
        numberExist = 0;
        list.clear();
        for (int i = 0; i < 1; i++) {
            Object obj = new Object();
            if (list.contains(obj)) {
                System.out.println(obj + "  exists in the list. " + i);
                numberExist++;
            } else {
                list.add(obj);
            }
        }

        System.out.println("repetition number:" + numberExist);
        System.out.println("list size:" + list.size());
    }
}