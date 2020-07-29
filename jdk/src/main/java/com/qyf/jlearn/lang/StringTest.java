package com.qyf.jlearn.lang;

import java.util.Arrays;

/**
 * 类描述：
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/7/28 16:07
 */
public class StringTest {
    private static void testStringBuffer() {
        char[] value = {'h', 'e', 'l', 'l', 'o', 'w', 'o', 'r', 'l', 'd'};
        System.out.println(value);
        //helloworld
        int count = value.length;
        int start = 3, end = 5;
        int len = end - start;
        if (len > 0) {
            // 删除方法是将后面的字符往前移，value值存在无效的字符，减少count，在toString方法才能返回删除后的字符
            //System.arraycopy(value, start + len, value, start, count - end);
            //helworldld
            StringBuffer sbf = new StringBuffer();
            sbf.append(value);
            sbf.delete(3, 5);
            System.out.println(sbf.toString());
            //helworld

            sbf = new StringBuffer();
            sbf.append(value);
            sbf.replace(3, 5,"123");
            System.out.println(sbf.toString());
        }

        value = Arrays.copyOf(value, value.length + 1);
        int offset = 2;
        System.arraycopy(value, offset, value, offset + 1, count - offset);
        value[offset] = 'z';
        System.out.println(value);
        //hezlloworld

    }

    public static void main(String[] args) {
        testStringBuffer();
    }
}
