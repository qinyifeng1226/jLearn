package com.qyf.jlearn.java8.time;

import java.time.Duration;
import java.time.Instant;

/**
 * 类描述：
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/12/3 11:40
 */
public class InstantTest {
    public static void main(String[] args) throws InterruptedException {
        Instant start = Instant.now();
        Thread.sleep(10L);
        Instant end = Instant.now();
        System.out.println("耗时：" + Duration.between(start, end).toMillis() + "ms");
    }
}
