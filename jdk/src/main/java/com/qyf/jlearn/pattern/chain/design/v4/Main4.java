package com.qyf.jlearn.pattern.chain.design.v4;

/**
 * 描述:
 *
 * @author liumohui
 * @since 2022/03/17 22:48
 */
class Main4 {

    public static void main(String[] args) {
        // 构建消息
        String reqMsg = "Request：大家好！共产党，<script>alert('警告')</script>，后面还有很长的内容，后面还有很长的内容。";
        String resMsg = "Response: 大家好！共产党，<script>alert('警告')</script>，后面还有很长的内容，后面还有很长的内容。";

        Request request = new Request(reqMsg);
        Response response = new Response(resMsg);

        // 定义过滤规则或顺序(可任意调整顺序)
        FilterChain filterChain = new FilterChain()
                .addFilter(new SensitiveFilter())
                .addFilter(new HtmlFilter())
                .addFilter(new SuperLongFilter());

        // 执行过滤
        filterChain.doFilter(request, response, filterChain);

        //打印结果
        System.out.println("------------------------分隔线-----------------------------");
        System.out.println(request.getMsg());
        System.out.println(response.getMsg());
    }
}
