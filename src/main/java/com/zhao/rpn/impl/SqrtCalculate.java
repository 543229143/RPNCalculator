package com.zhao.rpn.impl;

import com.zhao.rpn.Calculate;
import com.zhao.rpn.utils.NumberUtil;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Description 开平方根计算
 * @auther zhaoxin
 * @create 2019-01-20 18:53
 */
public class SqrtCalculate implements Calculate {
    @Override
    public boolean calc(Deque<Deque<BigDecimal>> allDeque) {
        if (allDeque.size() < 1) {
            return false;
        }
        //从deque的历史记录中找到最新的，复制一份再加到deque的最后面
        Deque<BigDecimal> recentDeque = new ArrayDeque<>(allDeque.getLast());
        allDeque.addLast(recentDeque);
        BigDecimal last = recentDeque.removeLast();
        return recentDeque.add(NumberUtil.storeFormat(new BigDecimal(Math.sqrt(last.doubleValue()))));
    }
}
