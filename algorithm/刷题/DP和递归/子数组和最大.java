/*
题目描述
给定一个数组arr，返回子数组的最大累加和
例如，arr = [1, -2, 3, 5, -2, 6, -1]，所有子数组中，[3, 5, -2, 6]可以累加出最大的和12，所以返回12.
题目保证没有全为负数的数据
[要求]
时间复杂度为O(n)O(n)，空间复杂度为O(1)O(1)

示例1
输入
复制
[1, -2, 3, 5, -2, 6, -1]
返回值
复制
12
备注:
1 \leq N \leq 10^51≤N≤10
5

|arr_i| \leq 100∣arr
i
​
 ∣≤100
 */

import java.util.*;


public class Solution {
    /**
     * max sum of the subarray
     *
     * @param arr int整型一维数组 the array
     * @return int整型
     */


    public int maxsumofSubarray(int[] arr) {
        List<Integer> posMap = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                posMap.add(i);
            }
        }
        int[][] dp = new int[arr.length + 1][arr.length + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                dp[i][j] = -1;
            }
        }
//        return process2(arr, 0, posMap.size() - 1, posMap, dp);
        return process3(arr, posMap);
    }

    private int process(int[] arr, int begin, int end, List<Integer> posMap) {
        if (begin > end) {
            return 0;
        }
        if (begin == end) {
            return arr[posMap.get(begin)];
        }
        int sum = 0;
        for (int i = posMap.get(begin); i <= posMap.get(end); i++) {
            sum += arr[i];
        }
        int reduce = Math.max(process(arr, begin + 1, end, posMap), process(arr, begin, end - 1, posMap));
        return Math.max(reduce, sum);
    }

    private int process2(int[] arr, int begin, int end, List<Integer> posMap, int[][] dp) {
        if (dp[begin][end] != -1) {
            return dp[begin][end];
        }
        if (begin > end) {
            return 0;
        }
        if (begin == end) {
            return arr[posMap.get(begin)];
        }
        int sum = 0;
        for (int i = posMap.get(begin); i <= posMap.get(end); i++) {
            sum += arr[i];
        }
        dp[begin + 1][end] = process(arr, begin + 1, end, posMap);
        dp[begin][end - 1] = process(arr, begin, end - 1, posMap);

        int reduce = Math.max(dp[begin + 1][end], dp[begin][end - 1]);
        return Math.max(reduce, sum);
    }

    private int process3(int[] arr, List<Integer> posMap) {
        int[] dp = new int[posMap.size()];

        final int len = posMap.size();

        int end = 0;
        while (end < len) {
            int _end = end;
            int begin = 0;
            while (_end < len) {
                if (begin == _end) {
                    dp[_end] = arr[posMap.get(_end)];
                } else {
                    int other = Math.max(dp[_end - 1], dp[_end]);
                    int sum = 0;
                    for (int i = posMap.get(begin); i <= posMap.get(_end); i++) {
                        sum += arr[i];
                    }
                    dp[_end] = Math.max(other, sum);
                }
                _end++;
                begin++;
            }
            end++;
        }
        return dp[len - 1];
    }

    public int maxsumofSubarray2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int max = Integer.MIN_VALUE;
        int cur = 0;
        for (int i = 0; i < arr.length; i++) {
            cur += arr[i];
            max = Math.max(cur, max);
            cur = cur < 0 ? 0 : cur;
        }
        return max;
    }

    public static void main(String[] args) {
//        int[] array = new int[7];
//        array[0] = 1;
//        array[1] = -2;
//        array[2] = 3;
//        array[3] = 5;
//        array[4] = -2;
//        array[5] = 6;
//        array[6] = -1;
        Random random = new Random();
        for (int i = 0; i < 5000; i++) {
            int size = random.nextInt(1000) + 1;
            int[] arry = new int[size];
            for (int j = 0; j < size; j++) {
                arry[j] = random.nextInt(100) - 500;
                arry[0] = 1;
            }
            int r1 = (new Solution().maxsumofSubarray(arry));
            int r2 = (new Solution().maxsumofSubarray2(arry));
            if (r1 != r2) {
                System.out.println("gg");
            }
        }
    }
}