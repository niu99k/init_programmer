/*
题目描述
给定无序数组arr，返回其中最长的连续序列的长度(要求值连续，位置可以不连续,例如 3,4,5,6为连续的自然数）
输入描述:
输出两行，第一行包括一个整数n（ 1 \leq n \leq10^5  ）（1≤n≤10
5
 ）,第二行包含n个整数，分别代表arr[i](1 \leq arr[i] \leq 10^8 )(1≤arr[i]≤10
8
 )
输出描述:
输出一个整数，代表最长连续子序列的长度。
示例1
输入
复制
6
100 4 200 1 3 2
输出
复制
4
示例2
输入
复制
3
1 1 1
输出
复制
1
备注:
时间复杂度(nlog_2n)(nlog
2
​
 n),空间复杂度（n）（n）

14
7 99 5 556 6 3434 22 1 8 3 3 2 4 4
13
9 85 7 6 5 5 3 8 55 4 33 2 1

 */

import java.util.*;

public class Main {
    static Scanner scanner;
    static int arrayLen;
    static int[] array;

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
        Map<Integer, Integer> maxLenMap = maxLenMap();
        int max = 0;
        for (int num : maxLenMap.keySet()) {
            if (maxLenMap.get(num) != 1) {
                continue;
            }
            if (maxLenMap.containsKey(num - 1)) {
                int leftMaxLen = maxLenMap.get(num - 1);
                int rightMaxLen = maxLenMap.get(num);
                int leftNum = num - maxLenMap.get(num - 1);
                int rightNum = num + maxLenMap.get(num) - 1;
                maxLenMap.put(leftNum, leftMaxLen + rightMaxLen);
                maxLenMap.put(rightNum, leftMaxLen + rightMaxLen);
                if (leftMaxLen + rightMaxLen > max) {
                    max = leftMaxLen + rightMaxLen;
                }
            }
            if (maxLenMap.containsKey(num + 1)) {
                int leftMaxLen = maxLenMap.get(num);
                int rightMaxLen = maxLenMap.get(num + 1);
                int leftNum = num - maxLenMap.get(num) + 1;
                int rightNum = num + maxLenMap.get(num + 1);
                maxLenMap.put(leftNum, leftMaxLen + rightMaxLen);
                maxLenMap.put(rightNum, leftMaxLen + rightMaxLen);
                if (leftMaxLen + rightMaxLen > max) {
                    max = leftMaxLen + rightMaxLen;
                }
            }
        }
        result = max;
        return result;
    }

    private static Map<Integer, Integer> maxLenMap() {
        Map<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i < arrayLen; i++) {
            result.put(array[i], 1);
        }
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        arrayLen = scanner.nextInt();
        array = new int[arrayLen];
        for (int i = 0; i < arrayLen; i++) {
            array[i] = scanner.nextInt();
        }
    }
}