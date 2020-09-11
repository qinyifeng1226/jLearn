package com.qyf.jlearn.lang;

import java.lang.reflect.Field;

/**
 * 类描述：基础数据类型
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/7/30 11:12
 */
public class PrimitiveDataTypeTest {
    public static void main(String[] args) throws Exception {
        //byteTest();
        //integerTest();
        //shortTest();
        longTest();
        //doubleTest();
    }

    private static void byteTest() {
        byte a128 = (byte) 128;
        //  -128
        System.out.println(a128);

        byte a129 = (byte) 129;
        //  -127
        System.out.println(a129);

        byte a_128 = (byte) -128;
        //  -128
        System.out.println(a_128);

        byte a_129 = (byte) -129;
        //   127
        System.out.println(a_129);

        Byte b1 = Byte.valueOf((byte) 12);
        // 12 调用的Integer.toString
        System.out.println(b1.toString());
        // 12
        System.out.println(b1.hashCode());
        // 254 , 负数用补码表示，正数的补码等于其原码，-2  先计算原码=1000 0010，然后反码=1111 1101，最后补码1111 1110
        // 1111 1110 & 0xff  =  1111 1110 && 1111 1111 = 1111 1110 = 254
        System.out.println(Byte.toUnsignedInt((byte) -2));
    }


    /**
     * 由于 Integer 这个类本身就跟 String 一样，是用 final 修饰的，并且 value 属性也是 final 修饰的，所以无论是调用方法重新赋值或者直接在 main 方法重新赋值，
     * 都会产生一个新的对象，原来的那个对象不会做任何改变，表面上都是同一个变量名，实际上却是一个新的对象 。
     * IntegerCache[-128,127]
     */
    private static void integerTest() throws Exception {
        Integer i = new Integer(2);
        // 新建 Integer对象时尽量使用 Integer.valueOf 方法以优化性能，优先从缓存中取
        i = Integer.valueOf(3);

        System.out.println(new Integer(125).toString());

        System.out.println("十进制转换为二进制: " + Integer.toBinaryString(-2));
        System.out.println("十进制转换为八进制: " + Integer.toOctalString(12));
        System.out.println("十进制转换为十六进制: " + Integer.toHexString(12));

        Integer a = 1000;
        Integer b = 2000;
        // 交换前：a = 1 b = 2
        System.out.println("交换前：" + "a = " + a + " " + "b = " + b);
        swap2(a, b);
        // 交换后：a = 1 b = 2
        System.out.println("交换后：" + "a = " + a + " " + "b = " + b);

        // 表示十进制的100，所以值为100
        System.out.println(Integer.parseInt("100", 10));
        // 表示二进制的100，所以值为4
        System.out.println(Integer.parseInt("100", 2));
        // 会抛出java.lang.NumberFormatException异常，因为 "666" 不符合二进制数规则
        //System.out.println(Integer.parseInt("666", 2));
        // 会抛出java.lang.NumberFormatException异常，因为该数值大于Integer的最大值
        //System.out.println(Integer.parseInt("10000000000", 10));
        // throws a NumberFormatException
        //System.out.println(Integer.parseInt("99", 8));
        // throws a NumberFormatException
        //System.out.println(Integer.parseInt("Kona", 10));
        // return 411787
        System.out.println(Integer.parseInt("Kona", 27));

        // 计算二进制数中1的个数 2个 = 0110
        // 核心思想就是先每两位一组求和看有多少个1，然后在前面的基础上再算每四位一组求和看有多少个1，接着每8位一组求和看有多少个1，接着16位，32位，最终在与0x3f进行与运算，得到的数即为1的个数。依次扩大组的范围，将前面的计算结果再次合并在一起，最终将全部32位所包含的1都计算出来 。
        System.out.println(Integer.bitCount(6));

        // 作用：获取 i 数值的二进制中最高位的 1 ，其他全为0的值。(例如：输入 101011 ，返回 100000 )
        System.out.println(Integer.highestOneBit(9));

        // 作用：与 highestOneBit 方法相反，lowestOneBit 方法获取的是获取最低位的 1，其他全为0的值
        // 在计算机中，负数以其正值的补码形式表达。利用 n 的原码与补码进行 & 运算，正好能够去掉其他位置的1，留下最低位的 1
        System.out.println(Integer.lowestOneBit(5));

        // =28 作用：计算 i 的二进制从头开始有多少个连续的 0
        System.out.println(Integer.numberOfLeadingZeros(8));

        // =3 作用：计算 i 的二进制从末尾开始有多少个连续的 0
        System.out.println(Integer.numberOfTrailingZeros(8));

        System.out.println((byte) (2 & 0xFF));
        System.out.println((byte) ((2 >> 8) & 0xFF));
        System.out.println(8 >> 1);

    }

    /**
     * ShortCache[-128,127]
     */
    private static void shortTest() {
        Short s = new Short((short) 200);
        System.out.println(Short.parseShort("123"));
        System.out.println(Short.decode("-123"));
    }

    /**
     * LongCache[-128,127]
     */
    private static void longTest() {
        Long lg = new Long(1234);
        System.out.println(new Long(125).toString());

        System.out.println("十进制转换为二进制: " + Long.toBinaryString(-2));
        System.out.println("十进制转换为二进制: " + Long.toBinaryString(2));
        System.out.println("十进制转换为八进制: " + Long.toOctalString(12));
        System.out.println("十进制转换为十六进制: " + Long.toHexString(12));
        System.out.println("十进制转换为十六进制: " + Long.toString(12, 16));

        System.out.println("八进制转换为十进制: " + Long.decode("012"));
        System.out.println("十六进制转换为十进制: " + Long.decode("0x12"));
        System.out.println("十六进制转换为十进制: " + Long.decode("#11"));
        System.out.println("十六进制转换为十进制: " + Long.decode("0X11"));
    }

    /**
     * 关于值传递和引用传递的问题 。这里要特殊考虑String，以及Integer、Double等基本类型包装类，它们的类前面都有final修饰，为不可变的类对象，
     * 每次操作（new或修改值）都是新生成一个对象，对形参的修改时，实参不受影响，与值传递的效果类似，但实际上仍是引用传递。
     *
     * @param numA
     * @param numB
     */
    private static void swap(Integer numA, Integer numB) {
        Integer temp = numA;
        numA = numB;
        numB = temp;
    }

    /**
     * 如果交换的是缓存中的数字，结果会错误，
     *
     * @param numA
     * @param numB
     * @throws Exception
     */
    private static void swap2(Integer numA, Integer numB) throws Exception {
        Field field = Integer.class.getDeclaredField("value");
        field.setAccessible(true);
        // numA.intValue()
        int temp = numA;
        // Integer.valueOf(1) 这一步是将 IntegerCache.cache[129] 设置为 IntegerCache.cache[130]
        field.set(numA, numB);
        // 如果是缓存值，则会影响temp的取值
        field.set(numB, temp);
    }


    private static void doubleTest() {
        Double d1 = new Double(123);
        System.out.println(0.1d + 0.2d);
        System.out.println(d1.toString());
        System.out.println(Double.toHexString(d1));
        System.out.println(d1.hashCode());
        System.out.println(Double.isFinite(88888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888333333333333333333333333333333333388888888888888888888866666666666666666666666666666666666666666666666666666666666666666666666666666666666666833d));
        System.out.println(Double.isInfinite(2));
        System.out.println(Double.isNaN(3333333333333333333333333333333333888888888888888888888833d));

        Float f2 = new Float(123);
        System.out.println(0.1f + 0.2f);
        System.out.println(f2.toString());
        System.out.println(Double.toHexString(f2));
        System.out.println(f2.hashCode());
    }
}
