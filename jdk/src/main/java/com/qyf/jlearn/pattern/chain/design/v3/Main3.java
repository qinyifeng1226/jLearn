package com.qyf.jlearn.pattern.chain.design.v3;

/**
 * 描述: 测试类
 *
 * @author liumohui
 * @since 2022/03/17 21:49
 */
class Main3 {

    /**
     * 测试主入口
     * @param args
     */
    public static void main(String[] args) {
        String msg = "大家好！共产党，<script>alert('警告')</script>，后面还有很长的内容，后面还有很长的内容。";

        // 系统中最初的FilterChain(过滤链)，由调用者决定需要执行哪些Filter
        FilterChain orgFilterChain = new FilterChain();
        orgFilterChain.addFilter(new HtmlFilter());


        // 可能后续需求增加Filter实现类
        FilterChain newFilterChain = new FilterChain();
        newFilterChain
                .addFilter(new SuperLongFilter())
                .addFilter(new SensitiveFilter());    //链式编程的作用在这里

        //  我们如何将新的链条(newFilterChain)快速的加入到之前(现有)的可能正在运作的链条中去呢？
        //  假设我们把新增的这个newFilterChain看成一个整体（反正都是消息进去，然后过滤后又出来）其实它也就是一个特殊的Filter
        // 由于FilterChain类已经实现了Filter接口，所以可以这样做


        orgFilterChain.addFilter(newFilterChain);    // 体现为什么FilterChain也实现Filter接口的作用

        // 输出结果
        System.out.println(orgFilterChain.doFilter(msg));
    }
}
