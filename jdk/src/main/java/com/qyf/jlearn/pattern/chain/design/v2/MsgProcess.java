package com.qyf.jlearn.pattern.chain.design.v2;

/**
 * 描述: 消息处理类
 *
 * @author liumohui
 * @since 2022/03/17 20:50
 */
class MsgProcess {

    private String msg;

    /**
     * 定义一个数组，保于所有的过滤规则
     * 好处：便于随时增减，可随意调整顺序，简单的调整也不再担心会影响其它的代码
     */
    private Filter[] filterList = {new HtmlFilter(), new SuperLongFilter(), new SensitiveFilter()};

    /**
     * 描述：消息的处理
     *
     * @return java.lang.String  返回处理后的消息
     */
    public String process() {
        //挨个调用，返回最终结果
        String text = msg;
        for(Filter filter : filterList) {
            text = filter.doFilter(text);
        }
        return text;
    }

    /**
     * set msg
     * @param msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
