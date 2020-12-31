/*
题目描述
      给定一个数组arr,长度为n。代表排有分数的气球。 每打爆一个气球都能获得分数，假设打爆气球的分数为X，获得分数的规则如下:
      1)如果被打爆气球的左边有没被打爆的气球，找到离被打爆气球最近的气球，假设分数为L:如果被打爆气球的右边有没被打爆的气球，找到离被打爆气球最近的气球，假设分数为R.获得分数为L*X*R
      2)如果被打爆的气球的左边有没被打爆的气球，找到离被打爆气球最近的气球，假设分数为L:如果被打爆气球的右边所有气球都已经被打爆，获得分数为L*X。
      3)如果被打爆气球的左边所有的气球都已经被打爆:如果被打爆气球的右边有没被打爆的气球，找到离被打爆气球最近的气球。获得分数为X*R.
      4)如果被打爆气球的左边和右边所有的气球都已经被打爆。获得分数为X。
      目标是打爆所有气球，获得每次打爆的分数。通过选择打爆气球的顺序，可以得到不同的总分，请返回能获得的最大分数。
输入描述:
输出包括两行，第一行包括一个整数n（0<=n<=500），第二行包括n个整数，代表数组arr （1<=arr[i]<=100）。
输出描述:
输出包括一个整数，代表可能获得的最大分数。
示例1
输入
复制
3
3 2 5
输出
复制
50
说明
2->1->3  3*2*5+3*5+5=50
示例2
输入
复制
8
23 4 45 65 23 43 54 56
输出
复制
639019
备注:
时间复杂度O(n^{3})O(n
3
 ),空间复杂度O(n^{2})O(n
2
 )。
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
        int[][] dp = dpInit();
        for (int endIndex = 1; endIndex <= arrayLen; endIndex++) {
            for (int startIndex = endIndex - 1; startIndex >= 1; startIndex--) {
                dp[startIndex][endIndex] = maxByEnumeration(startIndex, endIndex, dp);
            }
        }
        result = dp[1][arrayLen];
        return result;
    }

    private static int maxByEnumeration(int startIndex, int endIndex, int[][] dpMap) {
        int result;
        int max = Integer.MIN_VALUE;
        for (int i = startIndex; i <= endIndex; i++) {
            max = Math.max(max, array[i] * array[startIndex - 1] * array[endIndex + 1] + leftOrRight(startIndex, i - 1, dpMap) + leftOrRight(i + 1, endIndex, dpMap));
        }
        result = max;
        return result;
    }

    private static int leftOrRight(int startIndex, int endIndex, int[][] dpMap) {
        int result;
        if (endIndex < startIndex) {
            result = 0;
        } else {
            result = dpMap[startIndex][endIndex];
        }
        return result;
    }

    private static int[][] dpInit() {
        int[][] result = new int[arrayLen + 2][arrayLen + 2];
        for (int i = 1; i <= arrayLen; i++) {
            result[i][i] = array[i] * array[i - 1] * array[i + 1];
        }
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        arrayLen = scanner.nextInt();
        array = new int[arrayLen + 2];
        for (int i = 0; i < arrayLen + 2; i++) {
            if (i == 0 || i == arrayLen + 1) {
                array[i] = 1;
            } else {
                array[i] = scanner.nextInt();
            }
        }
    }
}