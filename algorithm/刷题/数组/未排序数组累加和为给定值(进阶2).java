/*
题目描述
给定一个无序数组arr，其中元素只能是1或0。求arr所有的子数组中0和1个数相等的最长子数组的长度
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
1 0 1 0 1
输出
复制
4
备注:
1 \leqslant N \leqslant 10^51⩽N⩽10
5

arr_i = 0或arr_i = 1arr
i
​
 =0或arr
i
​
 =1
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
            } else {
                array.add(-1);
            }
        }
    }
}