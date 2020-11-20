package com.qyf.jlearn.java8.stream;

import java.util.Arrays;
import java.util.List;

/**
 * 类描述：
 * <p>
 * Stream操作分类
 * 中间操作(Intermediate operations)	无状态(Stateless)	unordered() filter() map() mapToInt() mapToLong() mapToDouble() flatMap() flatMapToInt() flatMapToLong() flatMapToDouble() peek()
 * 有状态(Stateful)	distinct() sorted() limit() skip()
 * 结束操作(Terminal operations)	    非短路操作	forEach() forEachOrdered() toArray() reduce() collect() max() min() count()
 * 短路操作(short-circuiting)	anyMatch() allMatch() noneMatch() findFirst() findAny()
 *
 * Sink<T> extends Consumer
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/11/6 18:20
 */
public class StreamTest {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("123", "456", "789", "1101", "212121121", "asdaa", "3e3e3e", "2321eew");
        list.forEach(System.out::println);

        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");

        /**
         * 1、Collection.stream()，Spliterators.spliterator
         * 2、StreamSupport.stream，new ReferencePipeline.Head
         * 3、Stream.filter -> ReferencePipeline.filter -> new StatelessOp（无状态操作，重写AbstractPipeline#opWrapSink -> new Sink.ChainedReference ）
         * 4、Stream.count -> ReferencePipeline.count -> mapToLong-> new LongPipeline.StatelessOp（无状态操作，重写LongPipeline#opWrapSink -> new Sink.ChainedReference ）
         * 5、mapToLong(e -> 1L).sum() -> LongPipeline.reduce -> ReduceOps.makeLong(new ReduceOp 返回TerminalOp)
         * 6、AbstractPipeline.evaluate -> terminalOp.evaluateSequential 串行或者并行 terminalOp.evaluateParallel -> sourceSpliterator获得迭代器
         * 7、makeSink -> wrapAndCopyInto -> wrapSink（从后往前opWrapSink）
         * 8、copyInto（开始、循环数据、结束）
         *
         */

        long count = strings.stream().filter(string -> string.isEmpty()).count();
        System.out.println(count);
    }
}
