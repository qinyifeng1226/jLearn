package com.qyf.jlearn.third.lombok.sneakyThrows;

import lombok.Lombok;
import lombok.SneakyThrows;

import java.io.UnsupportedEncodingException;

/**
 * 描述：
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2021/3/10 17:26
 */
public class SneakyThrowsTest {

    @SneakyThrows(UnsupportedEncodingException.class)
    public String utf8ToString(byte[] bytes) {
        return new String(bytes, "UTF-8");
    }

    /**
     * 真正生成的代码
     *
     * @param bytes
     * @return
     */
    public String utf8ToString2(byte[] bytes) {
        try {
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw Lombok.sneakyThrow(e);
        }
    }

    /**
     * 显然魔法 藏在Lombok.sneakyThrow(t);中。可能大家都会以为这个方法就是new RuntimeException()之类的。然而事实并非如此。阅读代码可以看出整个方法其实最核心的逻辑是throw (T)t;，利用泛型将我们传入的Throwable强转为RuntimeException。虽然事实上我们不是RuntimeException。但是没关系。因为JVM并不关心这个。泛型最后存储为字节码时并没有泛型的信息。这样写只是为了骗过javac编译器。源码中注释有解释。
     */
    public static RuntimeException sneakyThrow(Throwable t) {
        if (t == null) throw new NullPointerException("t");
        // return Lombok.<RuntimeException>sneakyThrow0(t);
        return SneakyThrowsTest.<RuntimeException>sneakyThrow0(t);
    }

    private static <T extends Throwable> T sneakyThrow0(Throwable t) throws T {
        throw (T) t;
    }

}
