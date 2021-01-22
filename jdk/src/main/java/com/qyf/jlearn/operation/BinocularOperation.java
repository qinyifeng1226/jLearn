package com.qyf.jlearn.operation;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述：三目运算
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2021/1/22 14:25
 */
public class BinocularOperation {
    /**
     * javac -encoding UTF-8 BinocularOperation
     * javap -v BinocularOperation.class
     *
     * @param args
     */
    public static void main(String[] args) {
        Integer city = null;
        Integer city2 = true ? city : new Integer(0);
        // 由于三目运算会自动拆箱装箱
        // 等价于 int i = null.intValue();在此拆箱 所以此次会NPE
        //int city3 = true ? city : 0;

        System.out.println(city);
        System.out.println(city2);

        Map<String, Boolean> map = new HashMap<>();
        Boolean b = map != null ? map.get("test") : false;
        // 等价于 Boolean bool = (hashMap != null) ? (Boolean)hashMap.get("test") : Boolean.valueOf(false);
        System.out.println(b);
    }
}
