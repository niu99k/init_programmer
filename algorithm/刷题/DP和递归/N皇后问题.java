/*
时间限制：C/C++ 2秒，其他语言4秒 空间限制：C/C++ 256M，其他语言512M 热度指数：881
本题知识点： 穷举
 算法知识视频讲解
校招时部分企业笔试将禁止编程题跳出页面，为提前适应，练习时请使用在线自测，而非本地IDE。
题目描述
N皇后问题是指在N*N的棋盘上要摆N个皇后，要求任何两个皇后不同行，不同列也不再同一条斜线上，求给一个整数n，返回n皇后的摆法。
输入描述:
输出一个整数，代表n(1 \leq n \leq 14 )(1≤n≤14)。
输出描述:
输出一个整数，代表n皇后的种数。
示例1
输入
复制
1
输出
复制
1
示例2
输入
复制
8
输出
复制
92
备注:
时间复杂度O（2^n）O（2
n
 ），空间复杂度O（1）O（1）
 */

import java.util.*;

public class Main {
    static Scanner scanner;
    static int count;
    static int possible;

    public static void main(String[] args) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        System.out.println(solve(possible, 0, 0, 0));
    }

    private static int solve(int possible, int columnRestric, int leftRestrict, int rightRestrict) {
        int result = 0;
        if (columnRestric == possible) {
            result = 1;
        } else {
            int pos = possible & (~(columnRestric | leftRestrict | rightRestrict));
            while (pos != 0) {
                int mostRight = pos & (~pos + 1);
                pos = pos - mostRight;
                result += solve(possible, columnRestric | mostRight, (leftRestrict | mostRight) << 1, (rightRestrict | mostRight) >> 1);
            }
        }


        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        count = scanner.nextInt();
        possible = count == 32 ? -1 : (1 << count )- 1;
    }
}