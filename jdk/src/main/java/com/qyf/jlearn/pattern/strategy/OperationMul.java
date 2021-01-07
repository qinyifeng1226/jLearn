package com.qyf.jlearn.pattern.strategy;

/**
 * @author : qinyifeng
 * @since: 2021/01/07 11:30
 */
public class OperationMul implements CalculateStrategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 * num2;
    }
}
