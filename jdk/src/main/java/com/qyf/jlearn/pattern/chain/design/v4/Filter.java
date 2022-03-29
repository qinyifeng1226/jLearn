package com.qyf.jlearn.pattern.chain.design.v4;

/**
 * 描述:
 *
 * @author liumohui
 * @since 2022/03/17 21:31
 */
interface Filter {

    /**
     * 描述：
     *      1、新的过滤不仅需要对Request进行过滤还需要对Response进行过滤，所以前面两个参数就不难理解了
     *      2、让Filter也拥有FilterChain的实例
     * @param request
     * @param response
     * @param filterChain
     */
    void doFilter(Request request, Response response, FilterChain filterChain);
}
