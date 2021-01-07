package com.qyf.jlearn.pattern.template;

/**
 * @author : qinyifeng
 * @since: 2021/01/07 11:30
 */
public abstract class GameTemplate {
    //启动游戏
    protected abstract void runGame();

    //选择人物（可选方法，可以不重写）
    protected void choosePerson() {
    }

    //开始玩游戏
    protected abstract void startPlayGame();

    //结束游戏
    protected abstract void endPlayGame();

    //模板方法
    public final void play() {
        runGame();
        choosePerson();
        startPlayGame();
        endPlayGame();
    }
}
