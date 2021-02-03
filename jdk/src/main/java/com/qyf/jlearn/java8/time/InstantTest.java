package com.qyf.jlearn.java8.time;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;

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

        System.out.println("start: " + start);

        Instant now = Instant.now().plusMillis(TimeUnit.HOURS.toMillis(8));
        System.out.println("now: " + now);
        System.out.println("秒数: " + now.getEpochSecond());
        System.out.println("毫秒数: " + now.toEpochMilli());

        LocalDateTime localDateTime = LocalDateTime.now();
        //LocalDateTime转Instant
        Instant localDateTime2Instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        System.out.println("LocalDateTime 毫秒数:" + localDateTime2Instant.toEpochMilli());
    }
}
