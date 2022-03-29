package com.qyf.jlearn.pattern.chain.design.v2;

/**
 * 描述: javascript脚本过滤
 *
 * @author liumohui
 * @since 2022/03/17 21:33
 */
class HtmlFilter implements Filter {

    @Override
    public String doFilter(String msg) {
        return msg.replace("<", "[").replace(">", "]");
    }
}
