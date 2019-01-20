package com.zhao.rpn.impl;

import com.zhao.rpn.Calculate;

import java.math.BigDecimal;
import java.util.Deque;

/**
 * @Description 回滚操作
 * @auther zhaoxin
 * @create 2019-01-20 18:53
 */
public class UndoCalculate implements Calculate {
    @Override
    public boolean calc(Deque<Deque<BigDecimal>> allDeque) {
        if (allDeque.size() == 0) {
            return false;
        }
        allDeque.removeLast();
        return true;
    }
}
