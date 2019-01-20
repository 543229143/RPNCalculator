package com.zhao.rpn;

import junit.framework.TestCase;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Description
 * @auther zhaoxin
 * @create 2019-01-20 20:53
 */
public class RPNCalculatorTest {
    @Test
    public void Example1() throws Exception {
        Deque<Deque<BigDecimal>> allDeque = new ArrayDeque<>();
        String input = "5 2";
        TestCase.assertEquals("stack: 5 2", CalculateDelegate.calculate(allDeque, input));
    }

    @Test
    public void Example2() throws Exception {
        Deque<Deque<BigDecimal>> allDeque = new ArrayDeque<>();
        String input = "2 sqrt";
        TestCase.assertEquals("stack: 1.4142135623", CalculateDelegate.calculate(allDeque, input));
        input = "clear 9 sqrt";
        TestCase.assertEquals("stack: 3", CalculateDelegate.calculate(allDeque, input));
    }

    @Test
    public void Example3() throws Exception {
        Deque<Deque<BigDecimal>> allDeque = new ArrayDeque<>();
        String input = "5 2 -";
        TestCase.assertEquals("stack: 3", CalculateDelegate.calculate(allDeque, input));
        input = "3 -";
        TestCase.assertEquals("stack: 0", CalculateDelegate.calculate(allDeque, input));
        input = "clear";
        TestCase.assertEquals("stack:", CalculateDelegate.calculate(allDeque, input));
    }

    @Test
    public void Example4() throws Exception {
        Deque<Deque<BigDecimal>> allDeque = new ArrayDeque<>();
        String input = "5 4 3 2";
        TestCase.assertEquals("stack: 5 4 3 2", CalculateDelegate.calculate(allDeque, input));
        input = "undo undo *";
        TestCase.assertEquals("stack: 20", CalculateDelegate.calculate(allDeque, input));
        input = "5 *";
        TestCase.assertEquals("stack: 100", CalculateDelegate.calculate(allDeque, input));
        input = "undo";
        TestCase.assertEquals("stack: 20 5", CalculateDelegate.calculate(allDeque, input));
    }

    @Test
    public void Example5() throws Exception {
        Deque<Deque<BigDecimal>> allDeque = new ArrayDeque<>();
        String input = "7 12 2 /";
        TestCase.assertEquals("stack: 7 6", CalculateDelegate.calculate(allDeque, input));
        input = "*";
        TestCase.assertEquals("stack: 42", CalculateDelegate.calculate(allDeque, input));
        input = "4 /";
        TestCase.assertEquals("stack: 10.5", CalculateDelegate.calculate(allDeque, input));
    }

    @Test
    public void Example6() throws Exception {
        Deque<Deque<BigDecimal>> allDeque = new ArrayDeque<>();
        String input = "1 2 3 4 5";
        TestCase.assertEquals("stack: 1 2 3 4 5", CalculateDelegate.calculate(allDeque, input));
        input = "*";
        TestCase.assertEquals("stack: 1 2 3 20", CalculateDelegate.calculate(allDeque, input));
        input = "clear 3 4 -";
        TestCase.assertEquals("stack: -1", CalculateDelegate.calculate(allDeque, input));
    }


    @Test
    public void Example7() throws Exception {
        Deque<Deque<BigDecimal>> allDeque = new ArrayDeque<>();
        String input = "1 2 3 4 5";
        TestCase.assertEquals("stack: 1 2 3 4 5", CalculateDelegate.calculate(allDeque, input));
        input = "* * * *";
        TestCase.assertEquals("stack: 120", CalculateDelegate.calculate(allDeque, input));
    }

    @Test
    public void Example8() throws Exception {
        Deque<Deque<BigDecimal>> allDeque = new ArrayDeque<>();
        String input = "1 2 3 * 5 + * * 6 5";
        TestCase.assertEquals("operator * (position: 15): insucient parameters\n" +
                "stack: 11\n" +
                "(the 6 and 5 were not pushed on to the stack due to the previous error)", CalculateDelegate.calculate(allDeque, input));
    }

}
