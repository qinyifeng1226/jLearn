package com.qyf.jlearn.pattern.chain.design.v3;

/**
 * 描述: 超长的过滤
 *
 * @author liumohui
 * @since 2022/03/17 21:37
 */
class SuperLongFilter implements Filter {

    private static int MAX_LENGTH = 40;

    @Override
    public String doFilter(String msg) {
        if (msg.length() > MAX_LENGTH) {
            return msg.substring(0, MAX_LENGTH) + "...";
        } else {
            return msg;
        }
    }
}
