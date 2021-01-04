/*
题目描述
给定一个整数n，代表汉诺塔游戏中从小到大放置n个圆盘，假设开始所有圆盘都在左边的柱子上，那么用最优的办法把所有圆盘都移动到右边的柱子上的过程，就称为最优移动轨迹。
给定一个整型数组arr, 其中只含有1、2和3,代表所有圆盘目前的状态，1代表左柱，2代表中柱，3代表右柱，a[i]的值代表第i+1个圆盘的位置（a[i]下标从0开始）。
比如，arr=[3,3,2,1], 代表第1个圆盘在右柱上、第2个圆盘在右柱上、第3个圆盘在中柱上、第4个圆盘在左柱上。如果arr代表的状态是最优移动轨迹过程中出现的状态，输出arr这种状态是最优移动轨迹中的第几个状态（由于答案可能较大，请输出对10^9+710
9
 +7取模后的答案）。如果arr代表的状态不是最优移动轨迹过程中出现的状态，则输出-1。

输入描述:
输入包括两行，第一行一个整数n( 1 \leq n \leq 2*10^6)(1≤n≤2∗10
6
 )，表示圆盘的个数，第二行n个正整数，且均为1或2或3，第i个整数表示第i个圆盘位置。
输出描述:
输出一个整数，表示这种状态是第几个最优移动状态（输出对10^9+710
9
 +7取模后的答案），无解输出-1。
示例1
输入
复制
2
1 1
输出
复制
0
示例2
输入
复制
2
3 3
输出
复制
3
备注:
时间复杂度O(n)O(n)，空间复杂度O(n)O(n)。
 */

import java.util.*;

public class Main {
    static Scanner scanner;
    static int arrayLen;
    static int[] array;
    final static long mod = 1000000007;

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

    private static long solve() {
        long result = 0;
        int from = 1;
        int to = 3;
        int left = 2;
        long[] twoPowHelper = twoPowHelper();
        for (int i = arrayLen - 1; i >= 0; i--) {
            if (array[i] != from && array[i] != to) {
                result = -1;
                break;
            } else if (array[i] == to) {
                if (i != 0) {
                    result = (result + (twoPowHelper[i])) % mod;
                } else {
                    result = (result + 1) % mod;
                }
                int temp = from;
                from = left;
                left = temp;
            } else {
                int temp = to;
                to = left;
                left = temp;
            }
        }
        return result;
    }

    private static long[] twoPowHelper() {
        long[] result = new long[arrayLen];
        result[0] = 1;
        for (int i = 1; i < arrayLen; i++) {
            result[i] = (result[i - 1] << 1) % mod;
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