package com.zhao.rpn;

import com.zhao.rpn.impl.*;
import com.zhao.rpn.utils.NumberUtil;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 计算器总处理类
 * @auther zhaoxin
 * @create 2019-01-20 19:30
 */
public class CalculateDelegate {
    //计算处理Map
    private static final Map<String, Calculate> CALCULATE_MAP = new HashMap<String, Calculate>() {
        {
            this.put("+", new AddCalculate());
            this.put("-", new SubtractCalculate());
            this.put("*", new MultiplyCalculate());
            this.put("/", new DivideCalculate());
            this.put("sqrt", new SqrtCalculate());
            this.put("undo", new UndoCalculate());
            this.put("clear", new ClearCalculate());
        }
    };

    private static final String WARN_PARAM_INFO = "operator %s (position: %s): insucient parameters";

    private static final String ONE_SPACE = " ";

    /**
     * 根据用户的输入，循环遍历处理，并返回回显屏幕的结果
     *
     * @param allDeque 用于保存历史记录，包括最新的deque
     * @param readLine 用户输入字符串
     */
    public static String calculate(Deque<Deque<BigDecimal>> allDeque, String readLine) {
        String[] tokenArray = readLine.split(CalculateDelegate.ONE_SPACE);//分隔用户输入字符串

        int readIndex = 0;//输入的字符串读到的位置，解析用户输入非法参数时用到
        for (int i = 0; i < tokenArray.length; i++) {
            String token = tokenArray[i];
            readIndex += token.length();

            Calculate calculate = CALCULATE_MAP.get(token);
            //如果不为null，则需要计算
            if (calculate != null) {
                boolean calcResult = calculate.calc(allDeque);
                if (!calcResult) {
                    //如果失败，输入警告相关信息
                    return showWarnStackInfo(allDeque, tokenArray, readIndex, i, token);
                }
            } else {
                //否则只需要加入到deque即可
                addRecentDeque(allDeque, token);
            }
            readIndex += CalculateDelegate.ONE_SPACE.length();
        }
        return showStackInfo(allDeque.getLast());
    }

    /**
     * 把当前最新的deque+当前数字放入一个新的deque并且把该deque当做最新的，其它的做为备忘，undo时使用
     *
     * @param allDeque
     * @param token
     */
    private static void addRecentDeque(Deque<Deque<BigDecimal>> allDeque, String token) {
        Deque<BigDecimal> recentDeque;//最新的deque
        if (allDeque.size() == 0) {
            recentDeque = new ArrayDeque<>();
        } else {
            recentDeque = new ArrayDeque<>(allDeque.getLast());
        }
        recentDeque.add(new BigDecimal(token));
        allDeque.addLast(recentDeque);
    }

    /**
     * 输入不正确的，输出警告信息和stack信息
     *
     * @param allDeque
     * @param tokenArray
     * @param readIndex
     * @param i
     * @param token
     */
    private static String showWarnStackInfo(Deque<Deque<BigDecimal>> allDeque, String[] tokenArray, int readIndex, int i, String token) {
        String showResposne = String.format(WARN_PARAM_INFO, token, readIndex) + "\n";
        showResposne += showStackInfo(allDeque.getLast());
        if (i + 1 < tokenArray.length - 1) {
            showResposne += "\n(the ";
            for (int j = i + 1; j < tokenArray.length; j++) {
                if (j != tokenArray.length - 1) {
                    showResposne += tokenArray[j] + " and ";
                } else {
                    showResposne += tokenArray[j];
                }
            }
            showResposne += " were not pushed on to the stack due to the previous error)";
        }
        return showResposne;
    }

    /**
     * 美化输出
     *
     * @param recentDeque
     */
    private static String showStackInfo(Deque<BigDecimal> recentDeque) {
        StringBuilder sb = new StringBuilder("stack:");
        for (BigDecimal number : recentDeque) {
            sb.append(CalculateDelegate.ONE_SPACE).append(NumberUtil.outputFormat(number));
        }
        return sb.toString();
    }
}
