/*
题目描述
如果一个字符串为str，把字符串的前面任意部分挪到后面形成的字符串交str的旋转词。比如str=“12345”，str的旋转串有“12345”、“45123”等等。给定两个字符串，判断是否为旋转词。
输入描述:
输出包含三行，第一个两个整数n和m（1 \leq n,m \leq10^5 ）（1≤n,m≤10
5
 ），分别表示两个字符串的长度。第二行和第三行各输入一个字符串。
输出描述:
如果两个字符串互为旋转词请输出“YES”，否则输出“NO”。
示例1
输入
复制
4 4
abcd
cdab
输出
复制
YES
示例2
输入
复制
2 3
aa
aaa
输出
复制
NO
备注:
时间复杂度O（n）O（n），空间复杂度O（n）O（n）。
4 4
aaaa
baaa
 */

import java.util.*;

public class Main {
    static Scanner scanner;
    static int str1Len;
    static int str2Len;
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
        if (str1.length() != str2.length()) {
            System.out.println("NO");
        } else {
            boolean isStr1PartOfStr2Str2 = isPartOf(str1, str2 + str2);
            boolean isStr2PartOfStr1Str1 = isPartOf(str2, str1 + str1);
            if (isStr1PartOfStr2Str2 && isStr2PartOfStr1Str1) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    private static boolean isPartOf(String match, String str) {
        boolean result;
        int[] next = next(match);
        int matchIndex = 0;
        int strIndex = 0;
        while (true) {
            if (match.charAt(matchIndex) == str.charAt(strIndex)) {
                matchIndex++;
                strIndex++;
            } else if (matchIndex == 0) {
                strIndex++;
            } else {
                matchIndex = next[matchIndex];
            }
            if (matchIndex == match.length()) {
                result = true;
                break;
            }
            if (strIndex == str.length()) {
                result = false;
                break;
            }
        }
        return result;
    }

    private static int[] next(String match) {
        int[] result = new int[match.length()];
        result[0] = -1;
        result[1] = 0;
        for (int i = 2; i < match.length(); i++) {
            int matchIndex = i - 1;
            while (true) {
                if (match.charAt(result[matchIndex]) == match.charAt(i - 1)) {
                    result[i] = result[matchIndex] + 1;
                    break;
                } else {
                    matchIndex = result[matchIndex];
                }
                if (matchIndex <= 0) {
                    result[i] = 0;
                    break;
                }
            }

        }
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        str1Len = scanner.nextInt();
        str2Len = scanner.nextInt();
        str1 = scanner.next();
        str2 = scanner.next();
    }
}
