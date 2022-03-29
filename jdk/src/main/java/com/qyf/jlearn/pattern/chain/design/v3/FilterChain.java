package com.qyf.jlearn.pattern.chain.design.v3;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述: 由原来的MsgProcess演变而来
 *
 * @author liumohui
 * @since 2022/03/17 22:50
 */
class FilterChain implements Filter {               //为什么FilterChain要实现Filter接口呢？有什么好处?

    // Filter集合(动态大小，由数组调整为List)
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
    public String doFilter(String msg) {
        //挨个调用，返回最终结果
        String text = msg;
        for (Filter filter : filterList) {
            text = filter.doFilter(text);
        }
        return text;
    }
}
