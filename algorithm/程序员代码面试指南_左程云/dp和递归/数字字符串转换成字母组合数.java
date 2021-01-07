/*
题目描述
给定一个字符串str，str全部由数字字符组成，如果str中的某一个或者相邻两个字符组成的子串值在1~26之间，
则这个子串可以转换为一个字母。规定‘1’转换为“A”，“2”转换为“B”......"26"转化为“Z“。请求出str有多少种不同的转换结果，由于答案可能会比较大，所以请输出对10^{9}+710
9
 +7取模后的答案。
输入描述:
输出一行仅有’0‘~’9‘组成的字符串，代表str \left(  0\leq length\left( str\right) \leq 100000 \right)(0≤length(str)≤100000)。
输出描述:
输出一个整数，代表你所求出的取模后答案。
示例1
输入
复制
1111
输出
复制
5
说明
能转换出来的结果有：“AAAAA”，“LAA”，“ALA”，“AAL”，“LL”。
示例2
输入
复制
01
输出
复制
0
说明
“0”没有对应的字符，而“01”是不可转换的。
备注:
时间复杂度O(n)O(n),空间复杂度O(1)O(1)

1234454512012312445
 */

import java.util.*;

public class Main {
    static Scanner scanner;
    static String str;
    static final int mod = 1000000007;

    public static void main(String[] args) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void init() {
        scanner = new Scanner(System.in);
        str = scanner.next();
    }

    private static void result() {
        System.out.println(solve());
    }

    private static int solve() {
        int result = 0;
        int pre1 = 0;
        int pre2 = 0;
        for (int i = 0; i < str.length(); i++) {
            if (i == 0) {
                pre1 = isA2Z(str.substring(0, 1)) ? 1 : 0;
            } else {
                if (!isA2Z(str.substring(i, i + 1))) {
                    pre1 = 0;
                }
                if (!isA2Z(str.substring(i - 1, i + 1))) {
                    pre2 = 0;
                }
            }
            result = (pre1 + pre2) % mod;
            pre2 = pre1;
            pre1 = result;
        }
        return result;
    }

    private static boolean isA2Z(String numStr) {
        boolean result;
        int num = Integer.valueOf(numStr);
        if (numStr.length() == 1) {
            result = num >= 1 && num <= 9 ? true : false;
        } else {
            result = num >= 10 && num <= 26 ? true : false;
        }
        return result;
    }
}