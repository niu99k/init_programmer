/*
题目描述
给定数组arr，arr中所有的值都为正整数且不重复。每个值代表一种面值的货币，
每种面值的货币可以使用任意张，再给定一个aim，代表要找的钱数，求组成aim的最少货币数。
输入描述:
输入包括两行，第一行两个整数n（0<=n<=1000）代表数组长度和aim（0<=aim<=5000），
第二行n个不重复的正整数，代表arr\left( 1 \leq arr_i \leq 10^9 \right)(1≤arr
i
​
 ≤10
9
 )。
输出描述:
输出一个整数，表示组成aim的最小货币数，无解时输出-1.
示例1
输入
复制
3 20
5 2 3
输出
复制
4
说明
20=5*4
示例2
输入
复制
3 0
5 2 3
输出
复制
0
示例3
输入
复制
2 2
3 5
输出
复制
-1
备注:
时间复杂度O(n*aim)O(n∗aim)，空间复杂度O(n)O(n)。

 81 4917
1040 1943 2373 2678 4861 4849 1702 2575 4574 482 3977 1230 2581 3044 2320 615 3170 15 3761 2522 2718 3762 4551 22 2876 4434 2928 496 1296 1986 553 988 4217 3738 4109 425 420 4772 4572 2157 3479 3128 1287 3096 97 1582 780 4744 4654 3595 195 2429 1148 1638 4579 2614 1487 1675 4635 100 233 3923 4449 1598 1949 2349 2153 211 2374 2805 2777 81 2020 963 3272 4594 1480 3666 1170 1010 2432

6 30
1 11 15 6 5 3
 */

import java.util.*;

public class Main {
    static Scanner scanner;
    static int moneyCategoryCount;
    static int[] momeyArray;
    static int[] tempArray;
    static int[] result;
    static int target;

    public static void main(String[] args) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        solve();
        if (target == 0) {
            System.out.println(0);
        } else {
            System.out.println(result[target]);
        }
    }

    private static void solve() {
        for (int restMoney = 1; restMoney <= target; restMoney++) {
            for (int j = 0; j < moneyCategoryCount; j++) {
                if (restMoney - momeyArray[j] < 0) {
                    tempArray[j] = 0;
                } else if (restMoney - momeyArray[j] == 0) {
                    tempArray[j] = 1;
                } else if (result[restMoney - momeyArray[j]] > 0) {
                    tempArray[j] = result[restMoney - momeyArray[j]] + 1;
                } else {
                    tempArray[j] = 0;
                }
            }

            int min = target + 1;
            for (int j = 0; j < moneyCategoryCount; j++) {
                if (tempArray[j] != 0 && tempArray[j] < min) {
                    min = tempArray[j];
                }
            }
            if (min == target + 1) {
                result[restMoney] = -1;
            } else {
                result[restMoney] = min;
            }
        }
    }

    private static void init() {
        scanner = new Scanner(System.in);
        moneyCategoryCount = scanner.nextInt();
        target = scanner.nextInt();
        result = new int[target + 1];
        tempArray = new int[moneyCategoryCount];
        momeyArray = new int[moneyCategoryCount];
        for (int i = 0; i < moneyCategoryCount; i++) {
            momeyArray[i] = scanner.nextInt();
        }
    }
}