/*
题目描述
给定字符类型的数组chas，请在单词间做逆序调整。只要做到单词的顺序逆序即可，对空格的位置没有要求。
输入描述:
输入一行只含字母和空格的字符串，代表chas（1 \leq length_{chas} \leq 10^5）（1≤length
chas
​
 ≤10
5
 ）。
输出描述:
输出一行字符串，代表逆转顺序后的字符串。
示例1
输入
复制
i am a student
输出
复制
i ma a tneduts
备注:
时间复杂度O（n）O（n），空间复杂度O（1）O（1）。
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
        reverse();
        for (int i = 0; i < chars.length; i++) {
            System.out.print(chars[i]);
        }
    }

    private static void reverse() {
        int begenIndex = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                reverse(begenIndex, i - 1);
                begenIndex = i + 1;
            }
        }
        reverse(begenIndex, chars.length - 1);
    }

    private static void reverse(int begenIndex, int endIndex) {
        while (true) {
            if (begenIndex >= endIndex) {
                break;
            } else {
                char temp = chars[begenIndex];
                chars[begenIndex] = chars[endIndex];
                chars[endIndex] = temp;
                endIndex--;
                begenIndex++;
            }
        }
    }

    private static void init() {
        scanner = new Scanner(System.in);
        chars = scanner.nextLine().toCharArray();
    }
}