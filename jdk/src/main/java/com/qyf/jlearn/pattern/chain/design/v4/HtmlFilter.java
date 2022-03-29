package com.qyf.jlearn.pattern.chain.design.v4;

/**
 * 描述: javascript脚本过滤
 *
 * @author liumohui
 * @since 2022/03/17 21:33
 */
class HtmlFilter implements Filter {

    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        // 执行请求过滤
        System.out.println("1-HtmlFilter 过滤了请求参数");
        request.setMsg(request.getMsg().replace("<", "[").replace(">", "]"));

        // 交由下一个Filter
        filterChain.doFilter(request, response, filterChain);

        // 执行响应过滤
        System.out.println("1-HtmlFilter 过滤了响应参数");
        response.setMsg(response.getMsg().replace("<", "[").replace(">", "]"));
    }
}
