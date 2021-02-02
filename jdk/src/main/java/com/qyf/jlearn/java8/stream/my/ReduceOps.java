package com.qyf.jlearn.java8.stream.my;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * 描述：
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2021/2/2 16:43
 */
public class ReduceOps {

    /**
     * @param <T>       输入元素的类型
     * @param <I>       intermediate reduction结果的类型
     * @param collector a {@code Collector} defining the reduction
     * @return a {@code ReduceOp} implementing the reduction
     */

    public static <T, I> TerminalOp<T, I> makeRef(Collector<? super T, I> collector) {

        Supplier<I> supplier = Objects.requireNonNull(collector).supplier();

        BiConsumer<I, ? super T> accumulator = collector.accumulator();

        //BinaryOperator<I> combiner = collector.combiner();

        class ReducingSink extends Box<I> implements AccumulatingSink<T, I, ReducingSink> {

            @Override
            public void begin(long size) {
                state = supplier.get();
            }

            @Override
            public void accept(T t) {
                accumulator.accept(state, t);
            }

        }

        return new ReduceOp<T, I, ReducingSink>() {

            @Override
            public ReducingSink makeSink() {
                return new ReducingSink();
            }

        };

    }


    private static abstract class ReduceOp<T, R, S extends AccumulatingSink<T, R, S>> implements TerminalOp<T, R> {

        public abstract S makeSink();

        @Override
        public <P_IN> R evaluateSequential(PipelineHelper<T> helper, Spliterator<P_IN> spliterator) {
            return helper.wrapAndCopyInto(makeSink(), spliterator).get();
        }

    }

    private interface AccumulatingSink<T, R, K extends AccumulatingSink<T, R, K>> extends TerminalSink<T, R> {

    }

    private static abstract class Box<U> {

        U state;

        Box() {
        } // Avoid creation of special accessor

        public U get() {
            return state;
        }

    }

}
