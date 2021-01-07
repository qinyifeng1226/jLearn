package com.qyf.jlearn.pattern.template;

/**
 * @author : qinyifeng
 * @since: 2021/01/07 11:30
 */
public class ContraGame extends GameTemplate {

    @Override
    protected void runGame() {
        System.out.println("启动魂斗罗II...");
    }

    @Override
    protected void startPlayGame() {
        System.out.println("1P正在使用S弹打aircraft...");
    }

    @Override
    protected void endPlayGame() {
        System.out.println("1P被流弹打死了，游戏结束！");
    }
}

