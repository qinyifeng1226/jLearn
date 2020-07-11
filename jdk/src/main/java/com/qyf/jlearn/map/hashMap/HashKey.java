package com.qyf.jlearn.map.hashMap;

import java.util.Objects;

/**
 * 类描述：
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/7/10 13:47
 */
public class HashKey {
    private Integer userId;

    public HashKey() {
    }

    public HashKey(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashKey hashKey = (HashKey) o;
        // 人为制造相同hash，但在hashMap比较Key时却不等，用于测试同一个hash下多个next子节点
        return userId == hashKey.userId;
        //return Objects.equals(userId, hashKey.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
