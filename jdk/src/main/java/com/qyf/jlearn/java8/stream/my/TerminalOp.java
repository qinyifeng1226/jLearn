package com.qyf.jlearn.java8.stream.my;

/**
 * 描述：
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2021/2/2 16:44
 */
public interface TerminalOp<E_IN, R> {
    <P_IN> R evaluateSequential(PipelineHelper<E_IN> helper, Spliterator<P_IN> spliterator);
}
