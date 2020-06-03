package com.qyf.jlearn.dataStructure;

import java.util.BitSet;

/**
 * 类描述：
 * 一个Bitset类创建一种特殊类型的数组来保存位值。BitSet中数组大小会随需要增加。这和位向量（vector of bits）比较类似
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/6/3 15:38
 */
public class BitsetTest {
    public static void main(String args[]) {
        BitSet bits1 = new BitSet(16);
        BitSet bits2 = new BitSet(16);

        // set some bits
        for (int i = 0; i < 16; i++) {
            if ((i % 2) == 0) {
                // 将指定索引处的位设置为 true
                bits1.set(i);
            }
            if ((i % 5) != 0) {
                bits2.set(i);
            }
        }
        System.out.println("Initial pattern in bits1: ");
        System.out.println(bits1);
        System.out.println("\nInitial pattern in bits2: ");
        System.out.println(bits2);

        // AND bits 对此目标位 set 和参数位 set 执行逻辑与操作。
        bits2.and(bits1);
        System.out.println("\nbits2 AND bits1: ");
        System.out.println(bits2);

        // OR bits 对此位 set 和位 set 参数执行逻辑或操作。
        bits2.or(bits1);
        System.out.println("\nbits2 OR bits1: ");
        System.out.println(bits2);

        // XOR bits 对此位 set 和位 set 参数执行逻辑异或操作。同为0，异为1
        bits2.xor(bits1);
        System.out.println("\nbits2 XOR bits1: ");
        System.out.println(bits2);
    }
}
