package com.qyf.jlearn.third.json.test;

import java.math.BigDecimal;

/**
 * 类描述：
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/7/15 13:49
 */
public class Apple implements Fruit {
    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
