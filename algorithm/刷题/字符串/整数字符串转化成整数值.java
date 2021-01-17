/*
题目描述
给定一个字符串str，如果str符合日常书写的整数形式，并且属于32位整数范围，返回str代表的整数值，否则返回0。
输入描述:
输出包括一行代表str（1 \leq length(str) \leq 100）（1≤length(str)≤100）。
输出描述:
输出一行，代表返回的值。
示例1
输入
复制
123
输出
复制
123
示例2
输入
复制
023
输出
复制
0
示例3
输入
复制
A13
输出
复制
0
示例4
输入
复制
2147483647
输出
复制
2147483647
示例5
输入
复制
2147483648
输出
复制
0
示例6
输入
复制
-127
输出
复制
-127
备注:
时间复杂度O（length（str））O（length（str）），空间复杂度O（length（str））O（length（str））。
 */

import java.util.*;

public class Main {
    static Scanner scanner;
    static String str;

    public static void main(String[] args) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        if (isValid()) {
            System.out.println(transfer());
        }else{
            System.out.println(0);
        }
    }

    private static long transfer() {
        long result;
        boolean isPositive = str.charAt(0) == '-' ? false : true;
        result = num(isPositive);
        return result;
    }

    private static long num(boolean isPositive) {
        long result;
        int mutipower = 1;
        long sum = 0;
        if (isPositive) {
            for (int i = str.length() - 1; i >= 0; i--) {
                int num = str.charAt(i) - '0';
                sum += num * mutipower;
                mutipower *= 10;
                if (sum > Integer.MAX_VALUE) {
                    sum = 0;
                    break;
                }
            }
            result = sum;
        } else {
            for (int i = str.length() - 1; i >= 1; i--) {
                int num = str.charAt(i) - '0';
                sum += num * mutipower;
                mutipower *= 10;
                if (-sum < Integer.MIN_VALUE) {
                    sum = 0;
                    break;
                }
            }
            result = -sum;
        }
        return result;
    }

    private static boolean isValid() {
        boolean result;
        if (isOnlyNumberAndMinus() && isMinusAtFirstAndWithNumber() && isFirstNumNotZero()) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    private static boolean isFirstNumNotZero() {
        boolean result;
        if (str.charAt(0) == '0') {
            result = false;
        } else {
            result = true;
        }
        return result;
    }

    private static boolean isMinusAtFirstAndWithNumber() {
        boolean result;
        if (str.charAt(0) == '-') {
            if (str.length() == 1) {
                result = false;
            } else if (str.charAt(1) == '0') {
                result = false;
            } else {
                result = true;
            }
        } else {
            result = true;
        }
        return result;
    }

    private static boolean isOnlyNumberAndMinus() {
        boolean result = true;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '-' && !isNumber(str.charAt(i))) {
                result = false;
            }
        }
        return result;
    }

    private static boolean isNumber(char chr) {
        boolean result;
        if (chr >= '0' && chr <= '9') {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public static void init() {
        scanner = new Scanner(System.in);
        str = scanner.next();
    }
}