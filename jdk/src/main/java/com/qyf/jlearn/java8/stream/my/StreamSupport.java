package com.qyf.jlearn.java8.stream.my;

/**
 * 描述：
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2021/2/2 16:40
 */
public class StreamSupport {

    public static <T> Stream<T> stream(Spliterator<T> spliterator, boolean parallel) {
        return new ReferencePipeline.Head<>(spliterator, parallel);
    }
}
