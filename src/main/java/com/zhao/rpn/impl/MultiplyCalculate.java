package com.zhao.rpn.impl;

import com.zhao.rpn.Calculate;
import com.zhao.rpn.utils.NumberUtil;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Description 乘法计算
 * @auther zhaoxin
 * @create 2019-01-20 18:52
 */
public class MultiplyCalculate implements Calculate {
    @Override
    public boolean calc(Deque<Deque<BigDecimal>> allDeque) {
        //从deque的历史记录中找到最新的，复制一份再加到deque的最后面
        Deque<BigDecimal> recentDeque = new ArrayDeque<>(allDeque.getLast());
        if (recentDeque.size() < 2) {
            return false;
        }
        allDeque.addLast(recentDeque);
        BigDecimal last = recentDeque.removeLast();
        BigDecimal aheadLast = recentDeque.removeLast();
        return recentDeque.add(NumberUtil.storeFormat(aheadLast.multiply(last)));
    }
}
