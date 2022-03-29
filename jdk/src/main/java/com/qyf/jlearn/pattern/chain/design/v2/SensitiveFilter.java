package com.qyf.jlearn.pattern.chain.design.v2;

/**
 * 描述: 敏感字眼过滤
 *
 * @author liumohui
 * @since 2022/03/17 21:33
 */
class SensitiveFilter implements Filter {

    @Override
    public String doFilter(String msg) {
        return msg.replace("共产党", "***");
    }
}
