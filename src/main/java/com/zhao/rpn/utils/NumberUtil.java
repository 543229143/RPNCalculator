package com.zhao.rpn.utils;

import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @Description 数字处理工具类
 * @auther zhaoxin
 * @create 2019-01-20 19:17
 */
public final class NumberUtil {
    private NumberUtil() {
    }

    /**
     * 保存时格式化数字，保留精度15位
     *
     * @param bigDecimal 需要格式化的数字s
     * @return
     */
    public static BigDecimal storeFormat(BigDecimal bigDecimal) {
        if (bigDecimal != null) {
            return bigDecimal.setScale(15, RoundingMode.HALF_UP);
        }
        return null;
    }

    /**
     * 输出时格式化数字，最多保留精度10位
     *
     * 11.11  输出：11.11
     * 11     输出：11
     *
     * @param bigDecimal
     * @return
     */
    public static String outputFormat(BigDecimal bigDecimal) {
        if (bigDecimal != null) {
            String output = bigDecimal.setScale(10, RoundingMode.DOWN).toPlainString();
            String[] dotSplit = output.split("\\.");
            if (dotSplit.length > 1) {
                String afterDot = dotSplit[1];
                String s = afterDot.replaceAll("[0]*$", "");
                if (StringUtils.isNotBlank(s)) {
                    return dotSplit[0] + "." + s;
                } else {
                    return dotSplit[0];
                }
            }
        }
        return null;
    }
}
