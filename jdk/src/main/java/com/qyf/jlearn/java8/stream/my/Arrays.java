package com.qyf.jlearn.java8.stream.my;

/**
 * 描述：
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2021/2/2 16:39
 */
public class Arrays {

    @SuppressWarnings("unchecked")
    public static <T> Stream<T> stream(T[] array) {
        return StreamSupport.stream((Spliterator<T>) Spliterators.spliterator(array), false);
    }
}
