package com.qyf.jlearn.java8.stream.my;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * 描述：
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2021/2/2 16:42
 */
public interface Sink<T> extends Consumer<T> {

    default void begin(long size) {
    }

    default void end() {
    }

    static abstract class ChainedReference<T, E_OUT> implements Sink<T> {

        protected final Sink<? super E_OUT> downstream;

        public ChainedReference(Sink<? super E_OUT> downstream) {

            this.downstream = Objects.requireNonNull(downstream);

        }

        @Override
        public void begin(long size) {
            downstream.begin(size);
        }

        @Override
        public void end() {
            downstream.end();
        }

    }

}
