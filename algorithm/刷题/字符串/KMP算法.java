/*
题目描述
给定两个字符串str和match，长度分别为N和M。实现一个算法，如果字符串str中含有子串match，则返回match在str中的开始位置，不含有则返回-1
若出现了多次，则按照升序输出所有出现位置
[要求]
时间复杂度为O(n)O(n)
输入描述:
第一行一个字符串str
第二行一个字符串match
输出描述:
输出若干个数，分别为match在str中出现的位置，从0开始标号。
若不存在输出-1
示例1
输入
复制
acbc
bc
输出
复制
2
示例2
输入
复制
acbc
bcc
输出
复制
-1
示例3
输入
复制
ababab
ab
输出
复制
0 2 4
备注:
1 \leqslant length(str), length(match) \leqslant 5 * 10^51⩽length(str),length(match)⩽5∗10
5

保证字符集为小写字母
 */

import java.util.*;

public class Main {
    static Scanner scanner;
    static String str;
    static String match;
    static int matchBeginIndex;
    static int strBeginIndex;

    public static void main(String[] args) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        List<Integer> matchIndexInStr = solve(nextArray());
        if (matchIndexInStr.isEmpty()) {
            System.out.println(-1);
        } else {
            for (int index : matchIndexInStr) {
                System.out.print(index + " ");
            }
        }
    }

    private static List<Integer> solve(int[] nextArray) {
        List<Integer> result = new ArrayList<>();
        while (true) {
            while (
                    strBeginIndex < str.length()
                            && matchBeginIndex < match.length()
                            && str.charAt(strBeginIndex) == match.charAt(matchBeginIndex)
            ) {
                matchBeginIndex++;
                strBeginIndex++;
            }

            if (matchBeginIndex >= match.length()) {
                result.add(strBeginIndex - match.length());
                matchBeginIndex = 0;
            } else if (matchBeginIndex == 0) {
                strBeginIndex++;
            } else {
                matchBeginIndex = nextArray[matchBeginIndex];
            }

            if (strBeginIndex >= str.length()) {
                break;
            }

        }
        return result;
    }

    private static int[] nextArray() {
        int[] result = new int[match.length()];
        for (int i = 0; i < match.length(); i++) {
            if (i == 0) {
                result[i] = -1;
            } else if (i == 1) {
                result[i] = 0;
            } else {
                int rightEndIndex = i - 1;
                int leftEndIndex = result[i - 1];
                int sameLen = match.charAt(rightEndIndex) == match.charAt(leftEndIndex) ? leftEndIndex + 1 : 0;
                while (match.charAt(rightEndIndex) != match.charAt(leftEndIndex) && leftEndIndex != 0) {
                    leftEndIndex = result[leftEndIndex];
                    sameLen = match.charAt(rightEndIndex) == match.charAt(leftEndIndex) ? leftEndIndex + 1 : 0;
                }
                result[i] = sameLen;
            }
        }
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        str = scanner.next();
        match = scanner.next();
    }
}