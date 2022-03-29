package com.qyf.jlearn.pattern.chain.design.v3;

/**
 * 描述: 定义接口
 *
 * @author liumohui
 * @since 2022/03/17 21:31
 */
interface Filter {

    /**
     * 描述：对于输入的字符串进行处理，同时返回处理后的字符串
     *
     * @param msg
     * @return java.lang.String
     * @author liumohui
     * @since 2022/3/17 21:32
     */
    String doFilter(String msg);
}
