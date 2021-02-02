package com.qyf.jlearn.java8.stream.my;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * 描述：
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2021/2/2 16:40
 */
public abstract class ReferencePipeline<P_IN, P_OUT> extends AbstractPipeline<P_IN, P_OUT> implements Stream<P_OUT> {

    public ReferencePipeline(AbstractPipeline<?, P_IN> previousStage) {
        super(previousStage);
    }

    public ReferencePipeline(Spliterator<?> source, boolean parallel) {
        super(source, parallel);
    }

    @Override
    public Stream<P_OUT> filter(Predicate<? super P_OUT> predicate) {
        Objects.requireNonNull(predicate);
        return new StatelessOp<P_OUT, P_OUT>(this) {
            @Override
            Sink<P_OUT> opWrapSink(Sink<P_OUT> sink) {

                return new Sink.ChainedReference<P_OUT, P_OUT>(sink) {

                    @Override
                    public void accept(P_OUT u) {
                        if (predicate.test(u)) {
                            downstream.accept(u);
                        }
                    }

                };

            }

        };

    }

    @SuppressWarnings("unchecked")
    @Override
    public <R, A> R collect(Collector<? super P_OUT, A> collector) {
        return (R) evaluate(ReduceOps.makeRef(collector));
    }


    static class Head<E_IN, E_OUT> extends ReferencePipeline<E_IN, E_OUT> {

        public Head(Spliterator<?> source, boolean parallel) {
            super(source, parallel);
        }


        @Override
        final Sink<E_IN> opWrapSink(Sink<E_OUT> sink) {
            throw new UnsupportedOperationException();
        }

    }

    abstract static class StatelessOp<E_IN, E_OUT> extends ReferencePipeline<E_IN, E_OUT> {

        public StatelessOp(AbstractPipeline<?, E_IN> previousStage) {

            super(previousStage);

        }

    }

}
