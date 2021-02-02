package com.qyf.jlearn.java8.stream.my;

import java.util.Objects;

/**
 * 描述：
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2021/2/2 16:41
 */
public abstract class AbstractPipeline<E_IN, E_OUT> extends PipelineHelper<E_OUT> {

    @SuppressWarnings("rawtypes")
    private final AbstractPipeline sourceStage;

    @SuppressWarnings("rawtypes")
    private final AbstractPipeline previousStage;

//   @SuppressWarnings("rawtypes")

//   private AbstractPipeline nextStage;

    private Spliterator<?> sourceSpliterator;

    private int depth;

    private boolean linkedOrConsumed;

    private static final String MSG_STREAM_LINKED = "流已经被操作过或已经被关闭";

    private static final String MSG_CONSUMED = "source already consumed or closed";


    AbstractPipeline(Spliterator<?> source, boolean parallel) {
        this.previousStage = null;
        this.sourceSpliterator = source;
        this.sourceStage = this;
        this.depth = 0;
    }

    AbstractPipeline(AbstractPipeline<?, E_IN> previousStage) {
        if (previousStage.linkedOrConsumed) {
            throw new IllegalStateException(MSG_STREAM_LINKED);
        }
        this.previousStage = previousStage;
        this.sourceStage = previousStage.sourceStage;
        this.depth = previousStage.depth + 1;
    }

    private Spliterator<?> sourceSpliterator() {

        if (sourceStage.sourceSpliterator != null) {
            Spliterator<?> spliterator = sourceStage.sourceSpliterator;
            sourceStage.sourceSpliterator = null;
            return spliterator;
        }

        throw new IllegalStateException(MSG_CONSUMED);
    }

    /**
     * operation把接收的sink转为一个新的sink，这个新的sink用于接收输入元素
     * 因此这个新的sink可以更改sink的accept逻辑，比如{@code ReferencePipeline}的filter操作
     * 就是在accept中实现过滤逻辑
     *
     * @param sink
     * @return
     */

    abstract Sink<E_IN> opWrapSink(Sink<E_OUT> sink);

    @Override
    final <P_IN> void copyInto(Sink<P_IN> wrappedSink, Spliterator<P_IN> spliterator) {
        Objects.requireNonNull(wrappedSink);
        wrappedSink.begin(spliterator.estimateSize());
        spliterator.forEachRemaining(wrappedSink);
        wrappedSink.end();
    }


    @Override
    @SuppressWarnings("unchecked")
    final <P_IN> Sink<P_IN> wrapSink(Sink<E_OUT> sink) {
        Objects.requireNonNull(sink);
        for (@SuppressWarnings("rawtypes") AbstractPipeline p = AbstractPipeline.this; p.depth > 0; p = p.previousStage) {
            sink = p.opWrapSink(sink);
        }
        return (Sink<P_IN>) sink;
    }

    @Override
    final <P_IN, S extends Sink<E_OUT>> S wrapAndCopyInto(S sink, Spliterator<P_IN> spliterator) {
        copyInto(wrapSink(Objects.requireNonNull(sink)), spliterator);
        return sink;
    }

    final <R> R evaluate(TerminalOp<E_OUT, R> terminalOp) {
        if (linkedOrConsumed) {
            throw new IllegalStateException(MSG_STREAM_LINKED);
        }
        linkedOrConsumed = true;

        return terminalOp.evaluateSequential(this, sourceSpliterator());
    }

}
