package com.qyf.jlearn.third.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.qyf.jlearn.map.hashMap.User;
import com.qyf.jlearn.third.json.test.Apple;
import com.qyf.jlearn.third.json.test.Store;

import java.math.BigDecimal;
import java.util.Comparator;

/**
 * 类描述：
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/7/15 11:08
 */
public class FastJSONTest {
    public static void main(String[] args) {
        // 设置了safeMode后，@type 字段不再生效，即当解析形如{"@type": "com.java.class"}的JSON串时，将不再反序列化出对应的类
        //ParserConfig.getGlobalInstance().setSafeMode(true);

        User user = new User(111, "user1");
        String jsonStr = JSON.toJSONString(user, SerializerFeature.WriteClassName);
        System.out.println(jsonStr);

        Comparator user2 = JSON.parseObject(jsonStr, Comparator.class);
        System.out.println(user2);

        Store store = new Store();
        store.setName("hello");
        Apple apple = new Apple();
        apple.setPrice(new BigDecimal(0.5));
        store.setFruit(apple);

        String jsonString = JSON.toJSONString(store, SerializerFeature.WriteClassName);
        System.out.println("toJSONString : " + jsonString);

        Store newStore = JSON.parseObject(jsonString, Store.class);
        System.out.println("parseObject : " + newStore);

        Apple newApple = (Apple) newStore.getFruit();
        System.out.println("getFruit : " + newApple);

    }
}
