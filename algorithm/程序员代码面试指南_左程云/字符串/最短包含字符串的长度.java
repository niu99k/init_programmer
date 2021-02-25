/*
题目描述
给定字符串str1和str2，求str1的字串中含有str2所有字符的最小字符串长度。
输入描述:
输入包括两行，第一行一个字符串，代表str1( 1 \leq length_{str1} \leq 10^5)(1≤length
str1
​
 ≤10
5
 )，第二行也是一个字符串，代表str2( 1 \leq length_{str2} \leq 10^5)(1≤length
str2
​
 ≤10
5
 )。
输出描述:
输出str1的字串中含有str2所有字符的最小字符串长度，如果不存在请输出0。
示例1
输入
复制
abcde
ac
输出
复制
3
说明
“abc”中包含“ac”，且“abc”是所有满足条件中最小的。
示例2
输入
复制
12345
344
输出
复制
0
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
        if (isPossible()) {
            System.out.print(solve());
        }
    }

    private static boolean isPossible() {
        boolean result;
        if (str2.length() > str1.length()) {
            result = false;
        } else {
            result = true;
        }
        return result;
    }

    private static int solve() {
        int result = 0;
        int leftIndex = 0;
        int rightIndex = 0;
        int match = str2.length();
        int[] oweMap = oweMap();
        int minLen = Integer.MAX_VALUE;

        while (rightIndex < str1.length()) {
            final int chr = str1.charAt(rightIndex);
            oweMap[chr]--;
            if (oweMap[chr] >= 0) {
                match--;
            }
            if (match == 0) {
                minLen = Math.min(minLen, rightIndex - leftIndex + 1);
                while (leftIndex<rightIndex) {
                    leftIndex++;
                    oweMap[str1.charAt(leftIndex)]++;
                    if (oweMap[str1.charAt(leftIndex)] > 0) {
                        minLen = Math.min(minLen, rightIndex - leftIndex + 1);
                        match++;
                        break;
                    } else {
                        minLen = Math.min(minLen, rightIndex - leftIndex + 1);
                    }
                }
            }
            rightIndex++;
        }
        if (Integer.MAX_VALUE == minLen) {
            result = 0;
        } else {
            result = minLen;
        }
        return result;
    }

    private static int[] oweMap() {
        int[] result = new int[256];
        for (int i = 0; i < str2.length(); i++) {
            result[str2.charAt(i)]++;
        }
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        str1 = scanner.next();
        str2 = scanner.next();
    }
}
