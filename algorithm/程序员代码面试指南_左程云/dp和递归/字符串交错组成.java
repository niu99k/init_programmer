/*
题目描述
给定三个字符串str1、str2 和aim,如果aim包含且仅包含来自str1和str2的所有字符，而且在aim中属于str1的字符之间保持原来在str1中的顺序属于str2的字符之间保持原来在str2中的顺序，那么称aim是str1和str2的交错组成。实现一个函数，判断aim是否是str1和str2交错组成，如果是请输出“YES”，否则输出“NO”。

输入描述:
输出三行，分别表示三个字符串str1，str2和aim。1 \leq length\left ( str1 \right ),length\left ( str2 \right ) \leq 5000 ,1 \leq length\left(aim \right) \leq100001≤length(str1),length(str2)≤5000,1≤length(aim)≤10000 ， length()length()表示字符串长度。
输出描述:
输出“YES”或者“NO”。（不包含引号）
示例1
输入
复制
AB
12
1AB2
输出
复制
YES
示例2
输入
复制
2019
9102
22001199
输出
复制
NO
备注:
时间复杂度O\left( n*m \right)O(n∗m),空间复杂度O\left( min(n,m) \right)O(min(n,m))。（n表示字符串str1长度，m表示s字符串tr2长度）
 */

import java.util.*;

public class Main {
    static Scanner scanner;
    static String str1;
    static String str2;
    static String aimStr;

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
        if (aimStr.length() != shortStr.length() + longStr.length()) {
            System.out.println("NO");
        } else {
            System.out.println(solve(shortStr, longStr) ? "YES" : "NO");
        }
    }

    private static boolean solve(String shortStr, String longStr) {
        boolean result;
        boolean[] helpArray = helpArray(shortStr);
        for (int longStrIndex = 1; longStrIndex <= longStr.length(); longStrIndex++) {
            for (int shortStrIndex = 0; shortStrIndex <= shortStr.length(); shortStrIndex++) {
                if (shortStrIndex == 0) {
                    helpArray[shortStrIndex] = shortStr.substring(0, shortStrIndex).equals(aimStr.substring(0, shortStrIndex));
                } else {
                    if (helpArray[shortStrIndex] && longStr.charAt(longStrIndex - 1) == aimStr.charAt(longStrIndex + shortStrIndex - 1)) {
                        helpArray[shortStrIndex] = true;
                    } else if (helpArray[shortStrIndex - 1] && shortStr.charAt(shortStrIndex - 1) == aimStr.charAt(longStrIndex + shortStrIndex - 1)) {
                        helpArray[shortStrIndex] = true;
                    } else {
                        helpArray[shortStrIndex] = false;
                    }
                }
            }
        }
        result = helpArray[shortStr.length()];
        return result;
    }

    private static boolean[] helpArray(String str) {
        boolean[] result = new boolean[str.length() + 1];
        result[0] = true;
        for (int i = 1; i <= str.length(); i++) {
            result[i] = str.substring(0, i).equals(aimStr.substring(0, i));
        }
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        str1 = scanner.next();
        str2 = scanner.next();
        aimStr = scanner.next();
    }
}