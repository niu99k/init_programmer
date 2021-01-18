/*
题目描述
给定一个字符串数组strs[]，在strs中有些位置为null，但在不为null的位置上，其字符串是按照字典序由小到大出现的。在给定一个字符串str，请返回str在strs中出现的最左位置，如果strs中不存在str请输出“-1”。
输入描述:
输出包含多行，第一行包含一个整数n代表strs的长度，第二行一个字符串，代表str，接下来n行，每行包含一个字符串构成，字符串只包含小写字符，如果这一行为“0”，代表该行字符串为空，这n行字符串代表strs。（数据保证当字符串不为空时，所有字符均为小写字母；保证所有的字符串长度都小于10，1 \leq n \leq 10^51≤n≤10
5
 ）
输出描述:
输出一个整数，代表要返回的值。
示例1
输入
复制
8
a
0
a
0
a
ab
ac
0
b
输出
复制
1
说明
在strs中，最左边的“a”在位置1，strs为[null,"a",null,"a","ab","ac",null,"b"]
示例2
输入
复制
4
grep
awk
grep
0
sed
输出
复制
1
说明
strs为["awk","grep",null,"sed"],grep在位置1
备注:
时间复杂度O（log _{2}n）O（log
2
​
 n）,额外空间复杂度O（1）O（1）。

3
hh
hh
0
hh
 */

import java.util.*;

public class Main {
    static Scanner scanner;
    static int strCount;
    static String match;
    static String[] strs;

    public static void main(String[] args) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        if (match.equals("0") || strCount == 0) {
            System.out.println(-1);
        } else {
            System.out.println(solve());
        }
    }

    private static int solve() {
        int result;
        int beginIndex = 0;
        int endIndex = strCount - 1;
        int mostLeftIndex = -1;
        while (true) {
            if (beginIndex > endIndex) {
                break;
            }
            int midIndex = (beginIndex + endIndex) / 2;
            if (strs[midIndex].equals(match)) {
                mostLeftIndex = midIndex;
                endIndex = midIndex - 1;
            } else {
                if (strs[midIndex].equals("0")) {
                    midIndex = midIndexAfterMod(midIndex, beginIndex, endIndex);
                }
                if (midIndex == -1) {
                    break;
                } else if (strs[midIndex].equals(match)) {
                    mostLeftIndex = midIndex;
                    endIndex = midIndex - 1;
                } else if ((strs[midIndex].compareTo(match) > 0)) {
                    endIndex = midIndex - 1;
                } else {
                    beginIndex = midIndex + 1;
                }
            }
        }
        result = mostLeftIndex;
        return result;
    }

    private static int midIndexAfterMod(int index, int beginIndex, int endIndex) {
        int result;
        int delta = 1;
        while (true) {
            if (index - delta < beginIndex && index + delta > endIndex) {
                result = -1;
                break;
            } else if (index - delta >= beginIndex && !strs[index - delta].equals("0")) {
                result = index - delta;
                break;
            } else if (index + delta <= endIndex && !strs[index + delta].equals("0")) {
                result = index + delta;
                break;
            } else {
                delta++;
            }
        }
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        strCount = scanner.nextInt();
        match = scanner.next();
        strs = new String[strCount];
        for (int i = 0; i < strCount; i++) {
            strs[i] = scanner.next();
        }
    }
}