/*
题目描述
给定一个字符串chas[],其中只含有字母字符和“*”字符，现在想把所有“*”全部挪到chas的左边，字母字符移到chas的右边。完成调整函数。
输入描述:
输入一行字符串代表chas（1  \leq length_{chas} \leq 10^5 ）（1≤length
chas
​
 ≤10
5
 ）。
输出描述:
输出一行，代表返回的字符串。
示例1
输入
复制
qw**23
输出
复制
**qw23
示例2
输入
复制
o*f*f*e*r
输出
复制
****offer
备注:
时间复杂度O（n）O（n），额外空间复杂度O（1）O（1）。
 */

import java.util.*;

public class Main {
    static Scanner scanner;
    static char[] chars;

    public static void main(String[] args) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        replaceChars();
        for (int i = 0; i < chars.length; i++) {
            System.out.print(chars[i]);
        }
    }

    private static void replaceChars() {
        int endIndex4All = chars.length-1;
        int endIndex4Num = chars.length-1;
        while (true) {
            if (endIndex4All < 0) {
                break;
            } else if (endIndex4Num < 0) {
                chars[endIndex4All--] = '*';
            } else {
                if (chars[endIndex4Num] != '*') {
                    chars[endIndex4All--] = chars[endIndex4Num--];
                } else {
                    endIndex4Num--;
                }
            }
        }

    }

    private static void init() {
        scanner = new Scanner(System.in);
        chars = scanner.nextLine().toCharArray();
    }
}