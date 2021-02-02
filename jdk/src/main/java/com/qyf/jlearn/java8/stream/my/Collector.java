package com.qyf.jlearn.java8.stream.my;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * 描述：
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2021/2/2 16:43
 */
public interface Collector<T, A> {

    Supplier<A> supplier();

    BiConsumer<A, T> accumulator();

    //BinaryOperator<A> combiner();

}
