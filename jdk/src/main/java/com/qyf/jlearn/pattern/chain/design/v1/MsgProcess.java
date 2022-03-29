package com.qyf.jlearn.pattern.chain.design.v1;

/**
 * 描述: 消息处理器
 *
 * @author liumohui
 * @since 2022/03/17 20:50
 */
class MsgProcess {

    /**
     * 要处理的消息
     */
    private String msg;

    /**
     * 描述：消息的处理
     *
     * @return java.lang.String  返回处理后的消息
     */
    public String process() {
        String text = msg.replace("共产党", "***");
        text = text.replace("<", "[").replace(">", "]");
        if (text.length() > 40) {
            text = text.substring(0, 40) + "...";
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
