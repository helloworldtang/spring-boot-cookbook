package com.tangcheng.learning.number;

import java.math.BigDecimal;

/**
 * @author tangcheng
 * 2017/10/31
 */
public class BigDecimalStripTrailingZeros {

    public static void main(String[] args) {
        /**
         * 问题1：取整，截取掉小数点后的数字
         * 打印结果：123
         */
        String number = "123.456";
        String intNumber = number.substring(0, number.indexOf("."));
        System.out.println(intNumber);

        /**
         * 问题2：去掉小数点后的数字，保留为一个0
         * 解决：转成数字然后转换为整数再转换为字符串
         * 打印结果：123.0
         */
        String number2 = "123.456";
        System.out.println(String.valueOf(Math.floor(Double
                .parseDouble(number2))));

        /**
         * 问题3：用小数点分割取第一个结果
         * 打印结果：123.4
         */
        String number3 = "123.456";
        System.out.println(number3.split(".")[0]);

        /**
         * 问题4：保留小数的有效字符
         * 打印结果：12.34
         */
        String doubleNumber1 = "00012.340";
        System.out.println(getPrettyNumber(doubleNumber1));

        /**
         * 问题5：去掉有效数字前无效的0
         * 打印结果：12340
         */
        String intNumber1 = "00012340";
        System.out.println(getPrettyNumber(intNumber1));

        /**
         * 问题6：只保留小数前的一个0，去掉无效的0
         * 打印结果：0.34
         */
        String doubleNumber = "000.340";
        System.out.println(getPrettyNumber(doubleNumber));

        /**
         * 问题7：将小数后面的无效为0显示
         * 打印结果：1200
         */
        String eNumber = "1.2e3";
        System.out.println(getPrettyNumber(eNumber));
    }

    /**
     * stripTrailingZeros:小数点后的0去掉
     * Returns a BigDecimal which is numerically equal to this one but with any trailing zeros removed from the representation.
     * For example, stripping the trailing zeros from the BigDecimal value 600.0,
     * which has [BigInteger, scale] components equals to [6000, 1], yields 6E2 with [BigInteger, scale] components equals to [6, -2].
     * If this BigDecimal is numerically equal to zero, then BigDecimal.ZERO is returned.
     * Returns:
     * a numerically equal BigDecimal with any trailing zeros removed.
     *
     * @param number
     * @return
     */
    private static String getPrettyNumber(String number) {
        return BigDecimal.valueOf(Double.parseDouble(number))
                .stripTrailingZeros().toPlainString();
    }

}
