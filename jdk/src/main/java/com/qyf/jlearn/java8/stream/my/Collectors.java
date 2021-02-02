package com.qyf.jlearn.java8.stream.my;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * 描述：
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2021/2/2 16:43
 */
public class Collectors {

    public static <T>

    Collector<T, List<T>> toList() {

        return new Collector<T, List<T>>() {

            @Override
            public Supplier<List<T>> supplier() {
                return ArrayList::new;
            }

            @Override
            public BiConsumer<List<T>, T> accumulator() {

                return (state, element) -> {
                    System.out.println("accumulator：" + state + "  " + element);
                    state.add(element);
                };

            }

        };

    }

}
