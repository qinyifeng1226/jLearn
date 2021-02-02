package com.qyf.jlearn.java8.stream.my;

import java.util.function.Predicate;

/**
 * 描述：
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2021/2/2 16:03
 */
public interface Stream<T> {
    Stream<T> filter(Predicate<? super T> predicate);

    <R, A> R collect(Collector<? super T, A> collector);

    @SuppressWarnings("unchecked")
    public static <T> Stream<T> of(T... values) {
        return Arrays.stream(values);
    }
}
