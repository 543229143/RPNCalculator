package com.zhao.rpn;

import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * @Description 计算器启动类
 * @auther zhaoxin
 * @create 2019-01-20 18:28
 */
public class RPNCalculatorMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deque<Deque<BigDecimal>> allDeque = new ArrayDeque<>();
        while (scanner.hasNext()) {
            String readLine = scanner.nextLine();
            if (StringUtils.isNotBlank(readLine)) {
                //处理并且结果回显屏幕
                System.out.println(CalculateDelegate.calculate(allDeque, readLine));
            }
        }
    }
}
