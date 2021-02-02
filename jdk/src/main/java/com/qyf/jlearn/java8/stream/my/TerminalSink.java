package com.qyf.jlearn.java8.stream.my;

import java.util.function.Supplier;

/**
 * 描述：
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2021/2/2 16:44
 */
public interface TerminalSink<T, R> extends Sink<T>, Supplier<R> {
}
