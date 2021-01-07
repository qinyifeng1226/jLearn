package com.qyf.jlearn.pattern.template;

/**
 * @author : qinyifeng
 * @since: 2021/01/07 11:30
 */
public class TMNTGame extends GameTemplate {

    @Override
    protected void runGame() {
        System.out.println("启动忍者神龟III...");
    }

    @Override
    protected void choosePerson() {
        System.out.println("1P选择了Raph ！");
    }

    @Override
    protected void startPlayGame() {
        System.out.println("Raph正在使用绝技 “火箭头槌” ");
    }

    @Override
    protected void endPlayGame() {
        System.out.println("Raph 掉进井盖里死了，游戏结束了！ ");
    }
}

