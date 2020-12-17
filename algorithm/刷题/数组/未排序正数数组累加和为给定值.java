/*
题目描述
给定一个数组arr，该数组无序，但每个值均为正数，再给定一个正数k。求arr的所有子数组中所有元素相加和为k的最长子数组的长度
例如，arr = [1, 2, 1, 1, 1], k = 3
累加和为3的最长子数组为[1, 1, 1]，所以结果返回3
[要求]
时间复杂度为O(n)O(n)，空间复杂度为O(1)O(1)

输入描述:
第一行两个整数N, k。N表示数组长度，k的定义已在题目描述中给出
第二行N个整数表示数组内的数
输出描述:
输出一个整数表示答案
示例1
输入
复制
5 3
1 2 1 1 1

10 100
1 99 2 9 9 50 20 10 88 12
输出
复制
3
备注:
1 \leqslant N \leqslant 10^51⩽N⩽10
5

1 \leqslant k \leqslant 10^91⩽k⩽10
9

1 \leqslant arr_i \leqslant 1001⩽arr
i
​
 ⩽100
 */

import java.util.*;

public class Main {
    static Scanner scanner;
    static List<Integer> input;
    static int numCount;
    static int limitSum;

    public static void main(String[] args) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        int max = solve();
        System.out.println(max);
    }

    private static int solve() {
        int result;
        int left = 0;
        int right = 0;
        int max = 0;
        int tempSum = input.get(0);
        while (true) {
            if (tempSum == limitSum) {
                int len = right - left + 1;
                if (len > max) {
                    max = len;
                }
                right++;
                if (right == numCount) {
                    break;
                }
                tempSum += input.get(right);
            } else if (tempSum < limitSum) {
                right++;
                if (right == numCount) {
                    break;
                }
                tempSum += input.get(right);
            } else {
                tempSum -= input.get(left);
                left++;
                if (left == numCount) {
                    break;
                }
            }

        }
        result = max;
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        input = new ArrayList<>();

        numCount = scanner.nextInt();
        limitSum = scanner.nextInt();
        for (int i = 0; i < numCount; i++) {
            input.add(scanner.nextInt());
        }
    }
}