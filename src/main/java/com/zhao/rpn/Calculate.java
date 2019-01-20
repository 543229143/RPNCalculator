package com.zhao.rpn;

import java.math.BigDecimal;
import java.util.Deque;

public interface Calculate {
    boolean calc(Deque<Deque<BigDecimal>> allDeque);
}
