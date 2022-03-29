package com.qyf.jlearn.pattern.chain.design.v4;

/**
 * 描述: 把响应消息抽象成一个Response类
 *
 * @author liumohui
 * @since 2022/03/17 22:48
 */
class Response {

    /**
     * 消息值
     */
    private String msg;

    /**
     * 为了设值方便，我们在这里写了一个构造函数
     * @param msg
     */
    Response(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
