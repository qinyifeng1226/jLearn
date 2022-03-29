package com.qyf.jlearn.pattern.chain.design.v4;

/**
 * 描述: 超长的过滤
 *
 * @author liumohui
 * @since 2022/03/17 21:37
 */
class SuperLongFilter implements Filter {

    private static int MAX_LENGTH = 50;

    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        // 执行请求过滤
        System.out.println("3-SuperLongFilter 过滤了请求参数");
        if (request.getMsg().length() > MAX_LENGTH) {
            request.setMsg(request.getMsg().substring(0, MAX_LENGTH) + "...");
        }

        // 交由下一个Filter
        filterChain.doFilter(request, response, filterChain);

        // 执行响应过滤
        System.out.println("3-SuperLongFilter 过滤了响应参数");
        if (response.getMsg().length() > MAX_LENGTH) {
            response.setMsg(response.getMsg().substring(0, MAX_LENGTH) + "...");
        }
    }
}
