/*
题目描述
一个不含有负数的数组可以代表一圈环形山，每个位置的值代表山的高度。比如，{3,1,2,4,5}，{4,5,3,1,2}或{1,2,4,5,3}都代表同样结构的环形山。3->1->2->4->5->3 方向叫作 next 方向(逆时针)，3->5->4->2->1->3 方向叫作 last 方向(顺时针)。
山峰 A 和 山峰 B 能够相互看见的条件为:
1. 如果 A 和 B 是同一座山，认为不能相互看见。
2. 如果 A 和 B 是不同的山，并且在环中相邻，认为可以相互看见。
3. 如果 A 和 B 是不同的山，并且在环中不相邻，假设两座山高度的最小值为 min。如果 A 通过 next 方向到 B 的途中没有高度比 min 大的山峰，或者 A 通过 last 方向到 B 的途中没有高度比 min 大的山峰，认为 A 和 B 可以相互看见。
问题如下：
给定一个不含有负数且没有重复值的数组 arr，请问有多少对山峰能够相互看见？
输入描述:
第一行一个整数 T，表示测试数据的组数。

每组数据的第一行有三个数字 n, p, m，其中 n 表示 山峰的数量，

山峰的高度数组等于 1 - p 的 p! 个全排列按字典序排序后的第 m 个全排列的前 n 项。
输出描述:
输出一行一个整数表示答案。
示例1
输入
复制
1
5 5 2
输出
复制
7
说明
1-5 的全排列排序后的第二个排列 为 1 2 3 5 4
备注:
1 \le T \le 100001≤T≤10000
1 \le n \le p \le 10000001≤n≤p≤1000000
1 \le m \le p!1≤m≤p!
 */

import java.util.*;

public class Main {
    static int T;
    static int n;
    static int m;
    static int p;
    static Scanner scanner;

    public static void main(String strs[]) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        for (int i = 0; i < T; i++) {
            n = scanner.nextInt();
            p = scanner.nextInt();
            m = scanner.nextInt();

            if (n == 1) {
                System.out.println(0);
            } else {
                System.out.println(2 * n - 3);
            }
        }

    }

    private static void init() {
        scanner = new Scanner(System.in);
        T = scanner.nextInt();
    }

}
