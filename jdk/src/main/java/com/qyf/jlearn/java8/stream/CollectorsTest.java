package com.qyf.jlearn.java8.stream;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 类描述：
 * <p>
 * 整个Collectors工具类就是在为Collector服务，用于创建各种不同的Collector。部分功能与Stream中的方法重合了，为了简化代码，完全不必采用Collectors实现，优先Stream方法。
 * <p>
 * Stream上的所有操作分为两类：中间操作和结束操作，中间操作只是一种标记，只有结束操作才会触发实际计算。中间操作又可以分为无状态的(Stateless)和有状态的(Stateful)，
 * 无状态中间操作是指元素的处理不受前面元素的影响，而有状态的中间操作必须等到所有元素处理之后才知道最终结果，比如排序是有状态操作，在读取所有元素之前并不能确定排序结果；
 * 结束操作又可以分为短路操作和非短路操作，短路操作是指不用处理全部元素就可以返回结果，比如找到第一个满足条件的元素。之所以要进行如此精细的划分，
 * 是因为底层对每一种情况的处理方式不同。
 * <p>
 * >> 操作如何记录
 * 还有IntPipeline, LongPipeline, DoublePipeline没在图中画出，这三个类专门为三种基本类型（不是包装类型）而定制的，跟ReferencePipeline是并列关系。
 * 图中Head用于表示第一个Stage，即调用调用诸如Collection.stream()方法产生的Stage，很显然这个Stage里不包含任何操作；
 * StatelessOp和StatefulOp分别表示无状态和有状态的Stage，对应于无状态和有状态的中间操作。
 * <p>
 * 通过Collection.stream()方法得到Head也就是stage0，紧接着调用一系列的中间操作，不断产生新的Stream。这些Stream对象以双向链表的形式组织在一起，构成整个流水线，
 * 由于每个Stage都记录了前一个Stage和本次的操作以及回调函数，依靠这种结构就能建立起对数据源的所有操作。这就是Stream记录操作的方式。
 * <p>
 * >> 操作如何叠加
 * 以上只是解决了操作记录的问题，要想让流水线起到应有的作用我们需要一种将所有操作叠加到一起的方案。你可能会觉得这很简单，只需要从流水线的head开始依次执行每一步的操作（包括回调函数）就行了。这听起来似乎是可行的，但是你忽略了前面的Stage并不知道后面Stage到底执行了哪种操作，以及回调函数是哪种形式。换句话说，只有当前Stage本身才知道该如何执行自己包含的动作。这就需要有某种协议来协调相邻Stage之间的调用关系。
 * 这种协议由Sink接口完成，Sink接口包含的方法如下表所示：
 * <p>
 * 方法名	作用
 * void begin(long size)	开始遍历元素之前调用该方法，通知Sink做好准备。
 * void end()	所有元素遍历完成之后调用，通知Sink没有更多的元素了。
 * boolean cancellationRequested()	是否可以结束操作，可以让短路操作尽早结束。
 * void accept(T t)	遍历元素时调用，接受一个待处理元素，并对元素进行处理。Stage把自己包含的操作和回调方法封装到该方法里，前一个Stage只需要调用当前Stage.accept(T t)方法就行了。
 * <p>
 * 每个Stage都会将自己的操作封装到一个Sink里，前一个Stage只需调用后一个Stage的accept()方法即可，并不需要知道其内部是如何处理的。当然对于有状态的操作，
 * Sink的begin()和end()方法也是必须实现的。比如Stream.sorted()是一个有状态的中间操作，
 * 其对应的Sink.begin()方法可能创建一个乘放结果的容器，而accept()方法负责将元素添加到该容器，最后end()负责对容器进行排序。
 * 对于短路操作，Sink.cancellationRequested()也是必须实现的，比如Stream.findFirst()是短路操作，只要找到一个元素，cancellationRequested()就应该返回true，
 * 以便调用者尽快结束查找。Sink的四个接口方法常常相互协作，共同完成计算任务。实际上Stream API内部实现的的本质，就是如何重载Sink的这四个接口方法。
 * <p>
 * 有了Sink对操作的包装，Stage之间的调用问题就解决了，执行时只需要从流水线的head开始对数据源依次调用每个Stage对应的
 * Sink.{begin(), accept(), cancellationRequested(), end()}方法就可以了。一种可能的Sink.accept()方法流程是这样的：
 * void accept(U u){
 * 1. 使用当前Sink包装的回调函数处理u
 * 2. 将处理结果传递给流水线下游的Sink
 * }
 * <p>
 * Sink接口的其他几个方法也是按照这种[处理->转发]的模型实现。下面我们结合具体例子看看Stream的中间操作是如何将自身的操作包装成Sink以及Sink是如何将处理结果转发给下一个Sink的。先看Stream.map()方法：
 * <p>
 * // Stream.map()，调用该方法将产生一个新的Stream
 * public final <R> Stream<R> map(Function<? super P_OUT, ? extends R> mapper) {
 * ...
 * return new StatelessOp<P_OUT, R>(this, StreamShape.REFERENCE,
 * StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) {
 * <p>
 * Override //opWripSink()方法返回由回调函数包装而成Sink
 * Sink<P_OUT> opWrapSink(int flags,Sink<R> downstream){
 * return new Sink.ChainedReference<P_OUT, R>(downstream){
 * Override public void accept(P_OUT u){
 * R r=mapper.apply(u);// 1. 使用当前Sink包装的回调函数mapper处理u
 * downstream.accept(r);// 2. 将处理结果传递给流水线下游的Sink
 * }
 * };
 * }
 * };
 * }
 * <p>
 * 上述代码完美的展现了Sink的四个接口方法是如何协同工作的：
 * 首先beging()方法告诉Sink参与排序的元素个数，方便确定中间结果容器的的大小；
 * 之后通过accept()方法将元素添加到中间结果当中，最终执行时调用者会不断调用该方法，直到遍历所有元素；
 * 最后end()方法告诉Sink所有元素遍历完毕，启动排序步骤，排序完成后将结果传递给下游的Sink；
 * 如果下游的Sink是短路操作，将结果传递给下游时不断询问下游cancellationRequested()是否可以结束处理
 * <p>
 * <p>
 * >> 叠加之后的操作如何执行
 * Stream_pipeline_Sink
 * Sink完美封装了Stream每一步操作，并给出了[处理->转发]的模式来叠加操作。这一连串的齿轮已经咬合，就差最后一步拨动齿轮启动执行。是什么启动这一连串的操作呢？
 * 也许你已经想到了启动的原始动力就是结束操作(Terminal Operation)，一旦调用某个结束操作，就会触发整个流水线的执行。
 * <p>
 * 结束操作之后不能再有别的操作，所以结束操作不会创建新的流水线阶段(Stage)，直观的说就是流水线的链表不会在往后延伸了。结束操作会创建一个包装了自己操作的Sink，
 * 这也是流水线中最后一个Sink，这个Sink只需要处理数据而不需要将结果传递给下游的Sink（因为没有下游）。对于Sink的[处理->转发]模型，结束操作的Sink就是调用链的出口。
 * <p>
 * 我们再来考察一下上游的Sink是如何找到下游Sink的。一种可选的方案是在PipelineHelper中设置一个Sink字段，在流水线中找到下游Stage并访问Sink字段即可。
 * 但Stream类库的设计者没有这么做，而是设置了一个Sink AbstractPipeline.opWrapSink(int flags, Sink downstream)方法来得到Sink，
 * 该方法的作用是返回一个新的包含了当前Stage代表的操作以及能够将结果传递给downstream的Sink对象。为什么要产生一个新对象而不是返回一个Sink字段？
 * 这是因为使用opWrapSink()可以将当前操作与下游Sink（上文中的downstream参数）结合成新Sink。试想只要从流水线的最后一个Stage开始，不断调用上一个Stage的opWrapSink()
 * 方法直到最开始（不包括stage0，因为stage0代表数据源，不包含操作），就可以得到一个代表了流水线上所有操作的Sink
 * <p>
 * <p>
 * <p>
 * >> 执行后的结果在哪里
 * 最后一个问题是流水线上所有操作都执行后，用户所需要的结果（如果有）在哪里？首先要说明的是不是所有的Stream结束操作都需要返回结果，有些操作只是为了使用其副作用(Side-effects)，比如使用Stream.forEach()方法将结果打印出来就是常见的使用副作用的场景（事实上，除了打印之外其他场景都应避免使用副作用），对于真正需要返回结果的结束操作结果存在哪里呢？
 * <p>
 * 特别说明：副作用不应该被滥用，也许你会觉得在Stream.forEach()里进行元素收集是个不错的选择，就像下面代码中那样，但遗憾的是这样使用的正确性和效率都无法保证，因为Stream可能会并行执行。大多数使用副作用的地方都可以使用归约操作更安全和有效的完成。
 * <p>
 * // 错误的收集方式
 * ArrayList<String> results = new ArrayList<>();
 * stream.filter(s -> pattern.matcher(s).matches())
 * .forEach(s -> results.add(s));  // Unnecessary use of side-effects!
 * // 正确的收集方式
 * List<String>results =
 * stream.filter(s -> pattern.matcher(s).matches())
 * .collect(Collectors.toList());  // No side-effects!
 * 回到流水线执行结果的问题上来，需要返回结果的流水线结果存在哪里呢？这要分不同的情况讨论，下表给出了各种有返回结果的Stream结束操作。
 * <p>
 * 返回类型	对应的结束操作
 * boolean	anyMatch() allMatch() noneMatch()
 * Optional	findFirst() findAny()
 * 归约结果	reduce() collect()
 * 数组	toArray()
 * 对于表中返回boolean或者Optional的操作（Optional是存放 一个 值的容器）的操作，由于值返回一个值，只需要在对应的Sink中记录这个值，等到执行结束时返回就可以了。
 * 对于归约操作，最终结果放在用户调用时指定的容器中（容器类型通过收集器指定）。collect(), reduce(), max(), min()都是归约操作，虽然max()和min()也是返回一个Optional，但事实上底层是通过调用reduce()方法实现的。
 * 对于返回是数组的情况，毫无疑问的结果会放在数组当中。这么说当然是对的，但在最终返回数组之前，结果其实是存储在一种叫做Node的数据结构中的。Node是一种多叉树结构，元素存储在树的叶子当中，并且一个叶子节点可以存放多个元素。这样做是为了并行执行方便。
 gon*
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
