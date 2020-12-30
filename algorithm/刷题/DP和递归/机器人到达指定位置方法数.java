/*
题目描述
假设有排成一行的N个位置，记为1~N，开始时机器人在M位置，机器人可以往左或者往右走，如果机器人在1位置，那么下一步机器人只能走到2位置，
如果机器人在N位置，那么下一步机器人只能走到N-1位置。规定机器人只能走k步，最终能来到P位置的方法有多少种。由于方案数可能比较大，所以答案需要对1e9+7取模。
输入描述:
输出包括一行四个正整数N（2<=N<=5000）、M(1<=M<=N)、K(1<=K<=5000)、P(1<=P<=N)。
输出描述:
输出一个整数，代表最终走到P的方法数对10^9+710
9
 +7取模后的值。
示例1
输入
复制
5 2 3 3
输出
复制
3
说明
1).2->1,1->2,2->3

2).2->3,3->2,2->3

3).2->3,3->4,4->3
示例2
输入
复制
1000 1 1000 1
输出
复制
591137401
说明
注意答案要取模
备注:
时间复杂度O(n*k)O(n∗k),空间复杂度O(N)O(N)。
 */

import java.util.*;

public class Main {
    static Scanner scanner;
    static int positionCount;
    static int positionIndex;
    static int stepCount;
    static int targetPositionIndex;
    final static int mod = 1000000007;

    public static void main(String[] args) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        int[][] dpMap = dpMap();
        System.out.println(dpMap[stepCount][positionIndex]);
    }

    private static int[][] dpMap() {
        int[][] result = new int[stepCount + 1][positionCount];
        result[0][targetPositionIndex] = 1;
        for (int i = 1; i <= stepCount; i++) {
            for (int j = 0; j < positionCount; j++) {
                if (j == 0) {
                    result[i][j] = result[i - 1][j + 1];
                } else if (j == positionCount - 1) {
                    result[i][j] = result[i - 1][j - 1];
                } else {
                    result[i][j] = (result[i - 1][j - 1] + result[i - 1][j + 1]) % mod;
                }
            }
        }
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        positionCount = scanner.nextInt();
        positionIndex = scanner.nextInt() - 1;
        stepCount = scanner.nextInt();
        targetPositionIndex = scanner.nextInt() - 1;
    }
}