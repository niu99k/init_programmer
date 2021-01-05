/*
题目描述
给定两个字符串str1和str2，输出连个字符串的最长公共子序列。如过最长公共子序列为空，则输出-1。
输入描述:
输出包括两行，第一行代表字符串str1，第二行代表str2。\left( 1\leq length(str1),length(str2) \leq 5000\right)(1≤length(str1),length(str2)≤5000)
输出描述:
输出一行，代表他们最长公共子序列。如果公共子序列的长度为空，则输出-1。
示例1
输入
复制
1A2C3D4B56
B1D23CA45B6A
输出
复制
123456
说明
"123456"和“12C4B6”都是最长公共子序列，任意输出一个。
备注:
时间复杂度O(n*m)O(n∗m)，空间复杂度O(n*m)O(n∗m)。(n,m分别表示两个字符串长度)

1234A
B234CD

123
123

1111111111
14411144111

1c23
14423
 */

import java.util.*;

public class Main {
    static Scanner scanner;
    static String str1;
    static String str2;

    public static void main(String[] args) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        String shortStr = str1.length() > str2.length() ? str2 : str1;
        String longStr = str1.length() > str2.length() ? str1 : str2;
        System.out.println(solve(shortStr, longStr));
    }

    private static String solve(String shortStr, String longStr) {
        String result;
        StringBuffer stringBuffer = new StringBuffer();
        int[][] dpMap = dpMap(shortStr, longStr);
        int shortStrIndex = shortStr.length() - 1;
        int longStrIndex = longStr.length() - 1;
        if (dpMap[longStrIndex][shortStrIndex] == 0) {
            stringBuffer.append("1-");
        } else {
            int maxLen = dpMap[longStrIndex][shortStrIndex];
            int len = 0;
            while (true) {
                int up = longStrIndex - 1 < 0 ? Integer.MIN_VALUE : dpMap[longStrIndex - 1][shortStrIndex];
                int left = shortStrIndex - 1 < 0 ? Integer.MIN_VALUE : dpMap[longStrIndex][shortStrIndex - 1];
                if (longStr.charAt(longStrIndex) == shortStr.charAt(shortStrIndex)) {
                    stringBuffer.append(shortStr.charAt(shortStrIndex));
                    longStrIndex = longStrIndex == 0 ? 0 : longStrIndex - 1;
                    shortStrIndex = shortStrIndex == 0 ? 0 : shortStrIndex - 1;
                    len++;
                } else if (left > up) {
                    shortStrIndex--;
                } else {
                    longStrIndex--;
                }
                if (len == maxLen) {
                    break;
                }
            }
        }
        result = stringBuffer.reverse().toString();
        return result;
    }

    private static int[][] dpMap(String shortStr, String longStr) {
        int[][] result = new int[longStr.length()][shortStr.length()];
        for (int longStrIndex = 0; longStrIndex < longStr.length(); longStrIndex++) {
            for (int shortStrIndex = 0; shortStrIndex < shortStr.length(); shortStrIndex++) {
                if (longStrIndex == 0) {
                    if (longStr.charAt(longStrIndex) == shortStr.charAt(shortStrIndex)) {
                        result[longStrIndex][shortStrIndex] = 1;
                    } else if (shortStrIndex > 0 && result[longStrIndex][shortStrIndex - 1] != 0) {
                        result[longStrIndex][shortStrIndex] = 1;
                    }
                } else if (shortStrIndex == 0) {
                    if (longStr.charAt(longStrIndex) == shortStr.charAt(shortStrIndex)) {
                        result[longStrIndex][shortStrIndex] = 1;
                    } else if (longStrIndex > 0 && result[longStrIndex - 1][shortStrIndex] != 0) {
                        result[longStrIndex][shortStrIndex] = 1;
                    }
                } else {
                    result[longStrIndex][shortStrIndex] = Math.max(result[longStrIndex][shortStrIndex - 1], result[longStrIndex - 1][shortStrIndex]);
                    if (longStr.charAt(longStrIndex) == shortStr.charAt(shortStrIndex)) {
                        result[longStrIndex][shortStrIndex] = Math.max(result[longStrIndex][shortStrIndex], result[longStrIndex - 1][shortStrIndex - 1] + 1);
                    }
                }
            }
        }
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        str1 = scanner.next();
        str2 = scanner.next();
    }
}