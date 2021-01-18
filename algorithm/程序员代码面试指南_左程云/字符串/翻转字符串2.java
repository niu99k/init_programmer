/*
题目描述
给一个字符类型的数组chas和一个整数size，请把大小为size的左半区整体右移到右半区，右半区整体移动到左边。
输入描述:
输入两行，第一行一个整数，代表size,第二行一个字符串，代表chas（1  \leq size\leq length_{chas} \leq 10^5)（1≤size≤length
chas
​
 ≤10
5
 )
输出描述:
输出一行字符串，代表翻转后的字符串。
示例1
输入
复制
3
abcdefg
输出
复制
defgabc
备注:
时间复杂度O（n）O（n），额外空间复杂度O（1）O（1）。
 */

import java.util.*;

public class Main {
    static Scanner scanner;
    static int reverseSize;
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
        reverse(0, reverseSize - 1);
        reverse(reverseSize, chars.length - 1);
        reverse(0, chars.length - 1);
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
        reverseSize = scanner.nextInt();
        scanner.nextLine();
        chars = scanner.nextLine().toCharArray();
    }
}