package com.qyf.jlearn.java8.stream;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 类描述：
 * <p>
 * 整个Collectors工具类就是在为Collector服务，用于创建各种不同的Collector。部分功能与Stream中的方法重合了，为了简化代码，完全不必采用Collectors实现，优先Stream方法。
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/6/4 21:55
 */
public class CollectorsTest {

    /**
     * // supplier参数用于生成结果容器，容器类型为A
     * Supplier<A> supplier();
     * // accumulator用于消费元素，也就是归纳元素，这里的T就是元素，它会将流中的元素一个一个与结果容器A发生操作
     * BiConsumer<A, T> accumulator();
     * // combiner用于两个两个合并并行执行的线程的执行结果，将其合并为一个最终结果A
     * BinaryOperator<A> combiner();
     * // finisher用于将之前整合完的结果R转换成为A
     * Function<A, R> finisher();
     * // characteristics表示当前Collector的特征值，这是个不可变Set
     * Set<Characteristics> characteristics();
     */

    public static void toCollectionTest(List<String> list) {
        List<String> ll = list.stream().collect(Collectors.toCollection(LinkedList::new));
    }

    public static void toListTest(List<String> list) {
        List<String> ll = list.stream().collect(Collectors.toList());
    }

    public static void toSetTest(List<String> list) {
        Set<String> ss = list.stream().collect(Collectors.toSet());
    }

    public static void joiningTest(List<String> list) {
        // 无参方法
        String s = list.stream().collect(Collectors.joining());
        System.out.println(s);
        // 指定连接符
        String ss = list.stream().collect(Collectors.joining("-"));
        System.out.println(ss);
        // 指定连接符和前后缀
        String sss = list.stream().collect(Collectors.joining("-", "S", "E"));
        System.out.println(sss);
    }

    public static void mapingTest(List<String> list) {
        List<Integer> ll = list.stream().limit(5).collect(Collectors.mapping(Integer::valueOf, Collectors.toList()));
    }

    public static void collectingAndThenTest(List<String> list) {
        int length = list.stream().collect(Collectors.collectingAndThen(Collectors.toList(), e -> e.size()));
        System.out.println(length);
    }

    public static void countingTest(List<String> list) {
        long size = list.stream().collect(Collectors.counting());
        System.out.println(size);
    }

    public static void maxByAndMinByTest(List<String> list) {
        System.out.println(list.stream().collect(Collectors.maxBy((a, b) -> a.length() - b.length())));
        System.out.println(list.stream().collect(Collectors.minBy((a, b) -> a.length() - b.length())));
    }

    public static void summingTest(List<String> list) {
        int i = list.stream().limit(3).collect(Collectors.summingInt(Integer::valueOf));
        long l = list.stream().limit(3).collect(Collectors.summingLong(Long::valueOf));
        double d = list.stream().limit(3).collect(Collectors.summingDouble(Double::valueOf));
        System.out.println(i + "\n" + l + "\n" + d);
    }

    public static void averagingTest(List<String> list) {
        double i = list.stream().limit(3).collect(Collectors.averagingInt(Integer::valueOf));
        double l = list.stream().limit(3).collect(Collectors.averagingLong(Long::valueOf));
        double d = list.stream().limit(3).collect(Collectors.averagingDouble(Double::valueOf));
        System.out.println(i + "\n" + l + "\n" + d);
    }

    public static void reducingTest(List<String> list) {
        System.out.println(list.stream().limit(4).map(String::length).collect(Collectors.reducing(Integer::sum)));
        System.out.println(list.stream().limit(3).map(String::length).collect(Collectors.reducing(0, Integer::sum)));
        System.out.println(list.stream().limit(4).collect(Collectors.reducing(0, String::length, Integer::sum)));
    }

    public static void groupingByTest(List<String> list) {
        Map<Integer, List<String>> s = list.stream().collect(Collectors.groupingBy(String::length));
        Map<Integer, List<String>> ss = list.stream().collect(Collectors.groupingBy(String::length, Collectors.toList()));
        Map<Integer, Set<String>> sss = list.stream().collect(Collectors.groupingBy(String::length, HashMap::new, Collectors.toSet()));
        System.out.println(s.toString() + "\n" + ss.toString() + "\n" + sss.toString());
    }

    public static void partitioningByTest(List<String> list) {
        Map<Boolean, List<String>> map = list.stream().collect(Collectors.partitioningBy(e -> e.length() > 5));
        Map<Boolean, Set<String>> map2 = list.stream().collect(Collectors.partitioningBy(e -> e.length() > 6, Collectors.toSet()));
        System.out.println(map.toString() + "\n" + map2.toString());
    }

    public static void toMapTest(List<String> list) {
        // 第一种方式中，如果不添加limit限制，就会抛出异常。因为有两个key=1，1：123，1:1101
        Map<String, String> map = list.stream().limit(3).collect(Collectors.toMap(e -> e.substring(0, 1), e -> e));
        Map<String, String> map1 = list.stream().collect(Collectors.toMap(e -> e.substring(0, 1), e -> e, (a, b) -> b));
        Map<String, String> map2 = list.stream().collect(Collectors.toMap(e -> e.substring(0, 1), e -> e, (a, b) -> b, HashMap::new));
        System.out.println(map.toString() + "\n" + map1.toString() + "\n" + map2.toString());
    }

    public static void summarizingTest(List<String> list) {
        IntSummaryStatistics intSummary = list.stream().collect(Collectors.summarizingInt(String::length));
        LongSummaryStatistics longSummary = list.stream().limit(4).collect(Collectors.summarizingLong(Long::valueOf));
        DoubleSummaryStatistics doubleSummary = list.stream().limit(3).collect(Collectors.summarizingDouble(Double::valueOf));
        System.out.println(intSummary.toString() + "\n" + longSummary.toString() + "\n" + doubleSummary.toString());
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("123", "456", "789", "1101", "212121121", "asdaa", "3e3e3e", "2321eew");
        //toMapTest(list);
        summarizingTest(list);
    }
}
