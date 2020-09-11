package com.qyf.jlearn.collection.list.skiplist;

/**
 * 类描述：
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/8/4 18:01
 */
public class SkipListNode<T> {
    public int key;
    public T value;
    /**
     * 上下左右四个节点，pre和up存在的意义在于 "升层"的时候需要查找相邻节点
     */
    public SkipListNode<T> pre, next, up, down;

    // 负无穷
    public static final int HEAD_KEY = Integer.MIN_VALUE;
    // 正无穷
    public static final int TAIL_KEY = Integer.MAX_VALUE;

    public SkipListNode(int k, T v) {
        key = k;
        value = v;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof SkipListNode<?>)) {
            return false;
        }
        SkipListNode<T> ent;
        try {
            //检测类型
            ent = (SkipListNode<T>) o;
        } catch (ClassCastException ex) {
            return false;
        }
        return (ent.getKey() == key) && (ent.getValue() == value);
    }

    @Override
    public String toString() {
        return "key-value:" + key + "," + value;
    }
}
