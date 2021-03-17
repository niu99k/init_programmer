/*
题目描述
假设你有一个数组，其中第\ i i 个元素是股票在第\ i i 天的价格。
你有一次买入和卖出的机会。（只有买入了股票以后才能卖出）。请你设计一个算法来计算可以获得的最大收益。
示例1
输入
复制
[1,4,2]
返回值
复制
3
示例2
输入
复制
[2,4,1]
返回值
复制
2
 */

import java.util.*;


public class Solution {
    /**
     * @param prices int整型一维数组
     * @return int整型
     */
    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][prices.length];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp.length; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
//        return process(prices, 0, prices.length - 1);
        return Math.max(process2(prices, 0, prices.length - 1, dp), 0);
    }

    public int maxProfit2(int[] prices) {
        int profit = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            }
            profit = Math.max(profit, prices[i] - min);
        }
        return profit;
    }

    private int process2(int[] prices, int buyDay, int sellDay, int[][] dp) {
        if (dp[buyDay][sellDay] != Integer.MAX_VALUE) {
            return dp[buyDay][sellDay];
        }
        if (buyDay >= sellDay) {
            return Integer.MIN_VALUE;
        }
        dp[buyDay + 1][sellDay] = process2(prices, buyDay + 1, sellDay, dp);
        dp[buyDay][sellDay - 1] = process2(prices, buyDay, sellDay - 1, dp);
        int other = Math.max(dp[buyDay + 1][sellDay], dp[buyDay][sellDay - 1]);
        int doNow = prices[sellDay] - prices[buyDay];
        dp[buyDay][sellDay] = Math.max(doNow, other);
        return dp[buyDay][sellDay];
    }

    private int process(int[] prices, int buyDay, int sellDay) {
        if (buyDay >= sellDay) {
            return Integer.MIN_VALUE;
        }
        int other = Math.max(process(prices, buyDay + 1, sellDay), process(prices, buyDay, sellDay - 1));
        int doNow = prices[sellDay] - prices[buyDay];
        return Math.max(doNow, other);
    }

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 5000; i++) {
            int size = random.nextInt(1000) + 1;
            int[] array = new int[size];
            for (int j = 0; j < size; j++) {
                array[j] = random.nextInt(1000);
            }
            int r1 = new Solution().maxProfit(array);
            int r2 = new Solution().maxProfit2(array);
            if (r1 != r2) {
                System.out.println("gg");
            }
        }
    }
}