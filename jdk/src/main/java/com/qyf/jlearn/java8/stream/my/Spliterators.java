package com.qyf.jlearn.java8.stream.my;

import java.util.function.Consumer;

/**
 * 描述：
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2021/2/2 16:42
 */
public final class Spliterators {

    public static <T> Spliterator<T> spliterator(Object[] array) {
        //省略检查参数的步骤
        //checkFromToBounds(Objects.requireNonNull(array).length, fromIndex, toIndex);

        return new ArraySpliterator<>(array);
    }

    static final class ArraySpliterator<T> implements Spliterator<T> {

        private final Object[] array;
        private int index;
        private final int fence;

        //fence是数组的边界，通过指定初始的index跟fence，可以截取数组的一个子集，不过我们这里暂时不支持截取子集，默认处理数组的所有元素
        public ArraySpliterator(Object[] array) {
            this.array = array;
            fence = array.length;
        }

        @SuppressWarnings("unchecked")
        @Override
        public void forEachRemaining(Consumer<? super T> action) {

            Object[] a;
            int i, hi; // hoist accesses and checks from loop

            if (action == null) {
                throw new NullPointerException();
            }
            if ((a = array).length >= (hi = fence) && (i = index) >= 0 && i < (index = hi)) {

                do {
                    action.accept((T) a[i]);
                } while (++i < hi);

            }

        }

        @Override
        public long estimateSize() {
            return (long) (fence - index);
        }

    }

}
