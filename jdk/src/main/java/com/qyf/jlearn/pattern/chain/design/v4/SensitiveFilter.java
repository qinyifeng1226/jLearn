package com.qyf.jlearn.pattern.chain.design.v4;

/**
 * 描述: 敏感字眼过滤
 *
 * @author liumohui
 * @since 2022/03/17 21:33
 */
class SensitiveFilter implements Filter {

    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        // 执行请求过滤
        System.out.println("2-SensitiveFilter 过滤了请求参数");
        request.setMsg(request.getMsg().replace("共产党", "***"));

        // 交由下一个Filter
        filterChain.doFilter(request, response, filterChain);

        // 执行响应过滤
        System.out.println("2-SensitiveFilter 过滤了响应参数");
        response.setMsg(response.getMsg().replace("共产党", "***"));
    }
}
