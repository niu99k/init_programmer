/*
题目描述
给定一个无序数组arr，其中元素可正、可负、可0。求arr所有子数组中正数与负数个数相等的最长子数组的长度。
[要求]
时间复杂度为O(n)O(n)，空间复杂度为O(n)O(n)
输入描述:
第一行一个整数N，表示数组长度
接下来一行有N个数表示数组中的数
输出描述:
输出一个整数表示答案
示例1
输入
复制
5
1 -2 1 1 1

5
1 -2 1 2 -1
输出
复制
2
备注:
1 \leqslant N \leqslant 10^51⩽N⩽10
5

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
        int max = 0;
        int sum = 0;
        Map<Integer, Integer> sumMap = new HashMap<>();
        sumMap.put(0, -1);
        for (int i = 0; i < arrayLength; i++) {
            sum += array.get(i);

            if (!sumMap.containsKey(sum)) {
                sumMap.put(sum, i);
            }
            if (sumMap.containsKey(sum)) {
                int len = i - sumMap.get(sum);
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

        for (int i = 0; i < arrayLength; i++) {
            int temp = scanner.nextInt();
            if (temp > 0) {
                array.add(1);
            } else if (temp < 0) {
                array.add(-1);
            } else {
                array.add(0);
            }
        }
    }
}