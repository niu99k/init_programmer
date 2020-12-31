/*
题目描述
给定数组arr，设数组长度为n，arr中所有的值都为正整数且不重复。每个值代表一种面值的货币，每种面值的货币可以使用任意张，再给定一个整数aim，代表要找的钱数，求换钱的方法数有多少种。由于方法的种数比较大，所以要求输出对10^9+710
9
 +7进行取模后的答案。
输入描述:
输出包括两行，第一行包括两个整数n(0 \leq n \leq 1000)(0≤n≤1000)和aim(0 \leq aim \leq 20000)(0≤aim≤20000)。第二行包含n个整数，表示arr数组\left( 1 \leq arr_i \leq 1e9 \right)(1≤arr
i
​
 ≤1e9)。
输出描述:
输出一个整数，表示换钱的方法数对10^9+710
9
 +7取模后的答案。
示例1
输入
复制
4 15
5 10 25 1
输出
复制
6
说明
5*3=15

10*1+5*1=15

10*1+1*5=15

1*10+5*1=15

5*2+1*5=15

1*15=15
示例2
输入
复制
5 1000
2 3 5 7 10
输出
复制
20932712
备注:
时间复杂度O(N*aim)O(N∗aim),空间复杂度O(aim)O(aim)。
 */

import java.util.*;

public class Main {
    static Scanner scanner;
    static int moneyCategoryCount;
    static int tagerMoneyNumber;
    static int[] moneyArray;
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
        System.out.println(solve());
    }

    private static int solve() {
        int result;
        int row = tagerMoneyNumber + 1;
        int column = moneyCategoryCount;
        int[] tempArray = firstColumn(row);

        for (int categoryIndex = 1; categoryIndex < column; categoryIndex++) {
            for (int restMoney = 0; restMoney < row; restMoney++) {
                if (restMoney == 0) {
                    tempArray[restMoney] = 1;
                } else if (restMoney - moneyArray[categoryIndex] < 0) {
                    tempArray[restMoney] = tempArray[restMoney];
                } else {
                    tempArray[restMoney] = (tempArray[restMoney] + tempArray[restMoney - moneyArray[categoryIndex]]) % mod;
                }
            }
        }
        result = tempArray[tagerMoneyNumber];
        return result;
    }

    private static int[] firstColumn(int row) {
        int[] result = new int[row];
        for (int i = 0; i < row; i++) {
            if (i % moneyArray[0] == 0) {
                result[i] = 1;
            }
        }
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        moneyCategoryCount = scanner.nextInt();
        tagerMoneyNumber = scanner.nextInt();
        moneyArray=new int[moneyCategoryCount];
        for (int i = 0; i < moneyCategoryCount; i++) {
            moneyArray[i] = scanner.nextInt();
        }
    }
}
