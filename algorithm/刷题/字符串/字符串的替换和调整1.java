/*
题目描述
给定一个字符类型的数组chas[],chas右半区全是空字符，左半区不含有空字符。现在想将左半区的空格字符串替换成“%20”，假设chas右半区足够大，可以满足替换需要的空间，请完成替换函数。
输入描述:
输出一行，代表chas的左半区(1 \leq length(chas_{left}) \leq 10^5 )(1≤length(chas
left
​
 )≤10
5
 ) 。
输出描述:
输出一行，代表替换后的字符串。
示例1
输入
复制
a  b    c
输出
复制
a%20%20b%20%20%20%20c
备注:
时间复杂度O（n）O（n），额外空间复杂度O（1）O（1）。
 */

import java.util.*;

public class Main {
    static Scanner scanner;
    static char[] left;
    static char[] str;

    public static void main(String[] args) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        replaceStr();
        for(int i=0;i<str.length;i++){
            System.out.print(str[i]);
        }
    }

    private static void replaceStr() {
        int spaceCount = spaceCount();
        int endIndex4All = left.length + 2 * spaceCount - 1;
        int endIndex4Left = left.length - 1;
        while (true) {
            if (endIndex4Left < 0) {
                break;
            }
            if (left[endIndex4Left] == ' ') {
                endIndex4Left--;
                str[endIndex4All--] = '0';
                str[endIndex4All--] = '2';
                str[endIndex4All--] = '%';
            } else {
                str[endIndex4All--] = str[endIndex4Left--];
            }
        }
    }

    private static int spaceCount() {
        int result = 0;
        for (int i = 0; i < left.length; i++) {
            if (left[i] == ' ') {
                result++;
            }
        }
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        left = scanner.nextLine().toCharArray();
        str = new char[left.length * 3];
        for (int i = 0; i < left.length; i++) {
            str[i] = left[i];
        }
    }
}