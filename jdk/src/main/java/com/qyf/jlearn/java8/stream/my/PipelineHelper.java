package com.qyf.jlearn.java8.stream.my;

/**
 * 描述：
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2021/2/2 16:41
 */
public abstract class PipelineHelper<P_OUT> {

    abstract<P_IN> void copyInto(Sink<P_IN> wrappedSink, Spliterator<P_IN> spliterator);

    abstract<P_IN> Sink<P_IN> wrapSink(Sink<P_OUT> sink);

    abstract<P_IN, S extends Sink<P_OUT>> S wrapAndCopyInto(S sink, Spliterator<P_IN> spliterator);

}
