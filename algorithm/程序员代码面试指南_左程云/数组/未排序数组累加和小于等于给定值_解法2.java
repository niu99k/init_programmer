/*
题目描述
给定一个无序数组arr，其中元素可正、可负、可0。给定一个整数k，求arr所有的子数组中累加和小于或等于k的最长子数组长度
例如：arr = [3, -2, -4, 0, 6], k = -2. 相加和小于等于-2的最长子数组为{3, -2, -4, 0}，所以结果返回4
[要求]
时间复杂度为O(n)O(n)，空间复杂度为O(n)O(n)

输入描述:
第一行两个整数N, k。N表示数组长度，k的定义已在题目描述中给出
第二行N个整数表示数组内的数
输出描述:
输出一个整数表示答案
示例1
输入
复制
5 -2
3 -2 -4 0 6

6 -2
10 3 -2 -4 0 6

7 -2
10 3 -2 -4 0 6 -10
6
10 0
100 -10 2 3 20 -3 -3 -7 -7 11
8

34 -6
1 -1 -1 -1 -1 1 -1 1 1 1 -1 -1 -1 0 0 -1 1 1 -1 -1 0 0 1 1 -1 -1 -1 1 0 0 1 -1 -1 1

20 0
100 10 -1 -9 1 10 -88  100 -20 -3 -5 -50 25 25 5 3 20 100 -50 -49
输出
复制
4
备注:
1 \leqslant N \leqslant 10^51⩽N⩽10
5

-10^9 \leqslant k \leqslant 10^9−10
9
 ⩽k⩽10
9

-100 \leqslant arr_i \leqslant 100−100⩽arr
i
​
 ⩽100
 */

import java.util.*;

public class Main {
    static Scanner scanner;
    static int arrayLen;
    static int maxLimit;
    static List<Integer> array;

    public static void main(String[] args) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        int minLen = solve();
        System.out.println(minLen);
    }

    private static int solve() {
        int result;
        result = maxWindowLen(minSumFromBeginIndex());
        return result;
    }

    private static int maxWindowLen(List<Integer> minSumFromBeginIndex) {
        int result;
        int beginIndex = 0;
        int endIndex = 0;
        int sum = array.get(0);
        int maxLen = 0;
        while (endIndex < array.size()) {
            if (sum <= maxLimit) {
                int len = endIndex == beginIndex ? 1 : endIndex - beginIndex + 1;
                if (maxLen < len) {
                    maxLen = len;
                }
                endIndex++;
                if (endIndex < array.size()) {
                    sum += array.get(endIndex);
                }
            } else {
                int nextMinSum;
                if (endIndex < arrayLen - 1) {
                    nextMinSum = minSumFromBeginIndex.get(endIndex + 1);
                } else {
                    nextMinSum = 0;
                }
                if (nextMinSum + sum > maxLimit) {
                    sum -= array.get(beginIndex);
                    beginIndex++;
                    if (beginIndex > endIndex) {
                        endIndex = beginIndex;
                        if (beginIndex >= arrayLen) {
                            break;
                        }
                        sum = array.get(beginIndex);
                    }
                } else {
                    endIndex++;
                    if (endIndex < array.size()) {
                        sum += array.get(endIndex);
                    }
                }
            }
        }
        result = maxLen;
        return result;
    }

    private static List<Integer> minSumFromBeginIndex() {
        List<Integer> result = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        int preSum = 0;
        for (int i = array.size() - 1; i >= 0; i--) {
            if (preSum <= 0) {
                stack.push(preSum + array.get(i));
                preSum += array.get(i);
            } else {
                stack.push(array.get(i));
                preSum = array.get(i);
            }
        }
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        array = new ArrayList<>();

        arrayLen = scanner.nextInt();
        maxLimit = scanner.nextInt();
        for (int i = 0; i < arrayLen; i++) {
            array.add(scanner.nextInt());
        }
    }
}