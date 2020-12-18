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
        int max = 0;

        List<Integer> sumList = sumList();
        Map<Integer, Integer> sumMap2SearchIndex = sumMap2SearchIndex(sumList);
        for (int i = 0; i < arrayLen; i++) {
            int maxLen = maxLen4ByEnd(i, sumList, sumMap2SearchIndex);
            if (maxLen > max) {
                max = maxLen;
            }
        }
        result = max;
        return result;
    }

    private static Map<Integer, Integer> sumMap2SearchIndex(List<Integer> sumList) {
        Map<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i < sumList.size(); i++) {
            if (!result.containsKey(sumList.get(i))) {
                result.put(sumList.get(i), i - 1);
            }
        }
        return result;
    }

    private static List<Integer> sumList() {
        List<Integer> result = new ArrayList<>();
        int sum = 0;
        result.add(sum);
        int pre = sum;
        for (int i = 0; i < arrayLen; i++) {
            sum += array.get(i);
            if (pre > sum) {
                result.add(pre);
            } else {
                result.add(sum);
                pre = sum;
            }
        }
        return result;
    }

    private static int maxLen4ByEnd(int i, List<Integer> sumList, Map<Integer, Integer> sumMap2SearchIndex) {
        int result;
        int sum = sum(0, i);
        int minSum = sum - maxLimit;
        int firstIndexOverMinSum = firstIndexOverMinSum(minSum, sumList, sumMap2SearchIndex);
        result = i - firstIndexOverMinSum;
        return result;
    }

    private static int firstIndexOverMinSum(int minSum, List<Integer> sumList, Map<Integer, Integer> sumMap2SearchIndex) {
        int result = sumList.size();
        int beginindex = 0;
        int endIndex = sumList.size() - 1;
        while (beginindex <= endIndex) {
            int midIndex = (beginindex + endIndex) / 2;
            if (isFirstOverMinSum(midIndex, minSum, sumList, sumMap2SearchIndex)) {
                result = sumMap2SearchIndex.get(sumList.get(midIndex));
                break;
            } else if (sumList.get(midIndex) < minSum) {
                beginindex = midIndex + 1;
            } else {
                endIndex = midIndex - 1;
            }
        }
        return result;
    }

    private static boolean isFirstOverMinSum(int midIndex, int minSum, List<Integer> sumList, Map<Integer, Integer> sumMap2SearchIndex) {
        boolean result;
        if (sumList.get(midIndex) == minSum) {
            result = true;
        } else if (sumList.get(midIndex) < minSum) {
            result = false;
        } else {
            int preDif = preDif(sumList, midIndex, sumMap2SearchIndex);
            if (preDif < minSum) {
                result = true;
            } else {
                result = false;
            }
        }

        return result;
    }

    private static int preDif(List<Integer> sumList, int midIndex, Map<Integer, Integer> sumMap2SearchIndex) {
        int result;
        int sumWithIndex = sumList.get(midIndex);
        int firstIndexWithSum = sumMap2SearchIndex.get(sumWithIndex) + 1;
        if (firstIndexWithSum == 0) {
            result = Integer.MIN_VALUE;
        } else {
            result = sumList.get(firstIndexWithSum - 1);
        }
        return result;
    }

    private static int sum(int begin, int end) {
        int result = 0;
        for (int i = begin; i <= end; i++) {
            result += array.get(i);
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