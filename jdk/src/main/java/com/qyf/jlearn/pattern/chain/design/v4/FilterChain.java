package com.qyf.jlearn.pattern.chain.design.v4;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述: FilterChain
 *
 * @author liumohui
 * @since 2022/03/17 22:50
 */
class FilterChain implements Filter {

    // 纪录当前已经遍历到了第几个过滤器了
    private int CURRENT_INDEX = 0;

    // Filter集合
    private List<Filter> filterList = new ArrayList<>();

    /**
     * 添加过滤器
     *
     * @param filter
     * @return 返回FilerChain对象，便于链式编程
     */
    public FilterChain addFilter(Filter filter) {
        filterList.add(filter);
        return this;
    }

    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        //如果已经到了最后一个，退出（必需的，否则会进入死循环）
        if (CURRENT_INDEX >= filterList.size()) {
            return;
        }

        filterList.get(CURRENT_INDEX++).doFilter(request, response, filterChain);
    }
}
