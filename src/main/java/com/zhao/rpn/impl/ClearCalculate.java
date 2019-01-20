package com.zhao.rpn.impl;

import com.zhao.rpn.Calculate;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Description 清空操作
 * @auther zhaoxin
 * @create 2019-01-20 18:53
 */
public class ClearCalculate implements Calculate {
    @Override
    public boolean calc(Deque<Deque<BigDecimal>> allDeque) {
        Deque<BigDecimal> recentDeque = new ArrayDeque<>();
        return allDeque.add(recentDeque);
    }
}
