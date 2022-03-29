package com.qyf.jlearn.pattern.chain.design.v2;

/**
 * 描述:
 *
 * @author liumohui
 * @since 2022/03/17 21:49
 */
class Main2 {

    /**
     * 测试主入口
     * @param args
     */
    public static void main(String[] args) {
        String msg = "大家好！共产党，<script>alert('警告')</script>，后面还有很长的内容，后面还有很长的内容。";
        MsgProcess msgProcess = new MsgProcess();
        msgProcess.setMsg(msg);

        String text = msgProcess.process();
        System.out.println(text);
    }
}
