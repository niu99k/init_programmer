/*
题目描述
给定一个无序数组arr, 其中元素可正、可负、可0。给定一个整数k，求arr所有子数组中累加和为k的最长子数组长度
输入描述:
第一行两个整数N, k。N表示数组长度，k的定义已在题目描述中给出
第二行N个整数表示数组内的数
输出描述:
输出一个整数表示答案
示例1
输入
复制
5 0
1 -2 1 1 1

10 5
2 3 3 1 1 1 0 1 1 1
输出
复制
3
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
    static List<Integer> array;
    static int arrayLength;
    static int limitNum;

    public static void main(String[] args) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        int result = solve();
        System.out.println(result);
    }

    private static int solve() {
        int result = 0;
        int sum = 0;
        int max = 0;
        Map<Integer, Integer> sumMap = new HashMap<>();
        sumMap.put(0, -1);
        for (int i = 0; i < arrayLength; i++) {
            sum += array.get(i);
            if (!sumMap.containsKey(sum)) {
                sumMap.put(sum, i);
            }
            if (sumMap.containsKey(sum - limitNum)) {
                int len = i - sumMap.get(sum - limitNum);
                if (len > max) {
                    max = len;
                }
            }
        }
        result = max;
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        array = new ArrayList<>();

        arrayLength = scanner.nextInt();
        limitNum = scanner.nextInt();

        for (int i = 0; i < arrayLength; i++) {
            array.add(scanner.nextInt());
        }
    }
}