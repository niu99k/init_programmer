/*
题目描述
给定两个字符串str1和str2，再给定三个整数ic，dc和rc，分别代表插入、删除和替换一个字符的代价，请输出将str1编辑成str2的最小代价。
输入描述:
输出三行，第一行和第二行均为一行字符串，分别表示两个字符串str1，str2。\left( 1\leq length(str1),length(str2) \leq 5000 \right)(1≤length(str1),length(str2)≤5000)。第三行为三个正整数，代表ic，dc和rc。（1<=ic<=10000、1<=dc<=10000、1<=rc<=10000）
输出描述:
输出一个整数，表示编辑的最小代价。
示例1
输入
复制
abc
adc
5 3 2
输出
复制
2
示例2
输入
复制
abc
adc
5 3 100
输出
复制
8
示例3
输入
复制
abc
abc
5 3 2
输出
复制
0
备注:
时间复杂度O(n*m)O(n∗m)，空间复杂度O(n)O(n)。(n,m代表两个字符串长度)

abdc
adc
1 10 1
 */

import java.util.*;

public class Main {
    static Scanner scanner;
    static String str1;
    static String str2;
    static int insertCost;
    static int deleteCost;
    static int replaceCost;

    public static void main(String[] args) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        System.out.println(solve());
    }

    private static int solve() {
        int result;
        int[] helpArray = helpArray();
        int obliqueTemp = helpArray[0];
        for (int str1Index = 1; str1Index <= str1.length(); str1Index++) {
            for (int str2Index = 0; str2Index <= str2.length(); str2Index++) {
                if (str2Index == 0) {
                    obliqueTemp = helpArray[0];
                    helpArray[0] = deleteCost * (str1Index);
                } else {
                    int left = helpArray[str2Index - 1] + insertCost;
                    int up = helpArray[str2Index] + deleteCost;
                    int oblique = str1.charAt(str1Index - 1) == str2.charAt(str2Index - 1) ? obliqueTemp : obliqueTemp + replaceCost;
                    obliqueTemp = helpArray[str2Index];
                    helpArray[str2Index] = Math.min(Math.min(left, up), oblique);
                }
            }
        }
        result = helpArray[str2.length()];
        return result;
    }

    private static int[] helpArray() {
        int[] result = new int[str2.length() + 1];
        for (int i = 0; i <= str2.length(); i++) {
            result[i] = insertCost * (i);
        }
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        str1 = scanner.next();
        str2 = scanner.next();
        insertCost = scanner.nextInt();
        deleteCost = scanner.nextInt();
        replaceCost = scanner.nextInt();
    }
}