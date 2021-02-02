package com.qyf.jlearn.java8.stream;

import com.qyf.jlearn.java8.stream.my.Stream;
import java.util.List;
import static com.qyf.jlearn.java8.stream.my.Collectors.toList;
import static java.lang.Character.isDigit;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

/**
 * 描述：
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2021/2/2 16:00
 */
public class MyStream {
    public static void main(String[] args) {
        List<String> beginningWithNumbers = Stream.of("a", "abc1", "1abc", "2cb", "3dd", "2cb")
                .filter(value -> isDigit(value.charAt(0)))
                .collect(toList());

        System.out.println(beginningWithNumbers);
        assertEquals(asList("1abc", "2cb", "3dd", "2cb"), beginningWithNumbers);
    }
}
