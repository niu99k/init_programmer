/*
题目描述
给定两个字符串str1和str2,输出两个字符串的最长公共子串，如果最长公共子串为空，输出-1。
输入描述:
输入包括两行，第一行代表字符串srr1，第二行代表字符串str2。\left( 1\leq length(str1),length(str2) \leq 5000 \right)(1≤length(str1),length(str2)≤5000)
输出描述:
输出包括一行，代表最长公共子串。
示例1
输入
复制
1AB2345CD
12345EF
输出
复制
2345
备注:
时间复杂度O(n^{2})O(n
2
 )，额外空间复杂度O(1)O(1)。（n可以为其中任意一个字符串长度）

23AxcA
323AAxcA
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
        System.out.print(solve());
    }

    private static String solve() {
        String result;
        StringBuffer stringBuffer = new StringBuffer();
        int[] maxLenEndIndex = maxLenEndIndex();
        int str1EndIndex = maxLenEndIndex[0];
        int str2EndIndex = maxLenEndIndex[1];
        while (str1EndIndex >= 0 && str2EndIndex >= 0 && str1.charAt(str1EndIndex) == str2.charAt(str2EndIndex)) {
            stringBuffer.append(str1.charAt(str1EndIndex));
            str1EndIndex--;
            str2EndIndex--;
        }
        result = stringBuffer.reverse().toString();
        result = result.equals("") ? "-1" : result;
        return result;
    }

    private static int[] maxLenEndIndex() {
        int[] result = new int[2];
        int str1Len = str1.length();
        int str2Len = str2.length();
        int str2Index;
        int str1Index;
        int column = 0;
        int row = 0;
        int maxLen = 0;
        int initLen = 0;
        while (column < str2Len) {
            str2Index = column;
            str1Index = 0;
            int len = 0;
            while (true) {
                if (str1.charAt(str1Index) == str2.charAt(str2Index)) {
                    if (str2Index == 0 || str1Index == 0) {
                        initLen++;
                        len = initLen;
                    } else {
                        len++;
                    }
                } else {
                    if (str2Index == 0 || str1Index == 0) {
                        initLen = 0;
                        len = initLen;
                    } else {
                        len = 0;
                    }
                }
                if (len > maxLen) {
                    maxLen = len;
                    result[0] = str1Index;
                    result[1] = str2Index;
                }
                str1Index++;
                str2Index++;
                if (str2Index >= str2Len || str1Index >= str1Len) {
                    break;
                }
            }
            column++;
        }
        initLen = 0;
        while (row < str1Len) {
            str2Index = 0;
            str1Index = row;
            int len = 0;
            while (true) {
                if (str1.charAt(str1Index) == str2.charAt(str2Index)) {
                    if (str2Index == 0 || str1Index == 0) {
                        initLen++;
                        len = initLen;
                    } else {
                        len++;
                    }
                } else {
                    if (str2Index == 0 || str1Index == 0) {
                        initLen = 0;
                        len = initLen;
                    } else {
                        len = 0;
                    }
                }
                if (len > maxLen) {
                    maxLen = len;
                    result[0] = str1Index;
                    result[1] = str2Index;
                }
                str1Index++;
                str2Index++;
                if (str2Index >= str2Len || str1Index >= str1Len) {
                    break;
                }
            }
            row++;
        }

        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        str1 = scanner.next();
        str2 = scanner.next();
    }
}