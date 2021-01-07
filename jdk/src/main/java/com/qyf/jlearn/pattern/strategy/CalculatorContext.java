package com.qyf.jlearn.pattern.strategy;

/**
 * @author : qinyifeng
 * @since: 2021/01/07 11:30
 */
public class CalculatorContext {
    private CalculateStrategy strategy;

    public CalculatorContext(CalculateStrategy strategy) {
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2) {
        return strategy.doOperation(num1, num2);
    }
}
