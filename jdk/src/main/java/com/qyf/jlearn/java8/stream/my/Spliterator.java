package com.qyf.jlearn.java8.stream.my;

import java.util.function.Consumer;

/**
 * 描述：
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2021/2/2 16:42
 */
public interface Spliterator<T> {

    /**
     * 遍历并处理数据源的元素
     *
     * @param action
     */

    void forEachRemaining(Consumer<? super T> action);

    /**
     * 剩余元素个数
     *
     * @return
     */
    long estimateSize();

}
