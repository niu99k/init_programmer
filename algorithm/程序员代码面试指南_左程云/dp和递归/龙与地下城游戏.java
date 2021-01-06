/*
题目描述
给定一个二维数组map，含义是一张地图，例如，如下矩阵
\begin{Bmatrix} -2&-3&3 \\ -5&-10&1\\ 0&30&-5 \end{Bmatrix}
⎩
⎨
⎧
​

−2
−5
0
​

−3
−10
30
​

3
1
−5
​

⎭
⎬
⎫
​

游戏的规则如下:
      1）骑士从左上角出发，每次只能向右或向下走，最后到达右下角见到公主。
      2）地图中每个位置的值代表骑士要遭遇的事情。如果是负数，说明此处有怪兽，要让骑士损失血量。如果是非负数，代表此处有血瓶，能让骑士回血。
      3）骑士从左上角到右下角的过程中，走到任何一个位置时，血量都不能少于1。为了保证骑土能见到公主，初始血量至少是多少?
根据map,输出初始血量。

输入描述:
第一行两个正整数n，m  \left ( 1\leq n,m\leq 10^{3} \right )(1≤n,m≤10
3
 )，接下来n行，每行m个整数，代表map_{ij} \left( -10^3 \leq map_{ij} \leq 10^{3}\right )map
ij
​
 (−10
3
 ≤map
ij
​
 ≤10
3
 )。
输出描述:
输出一个整数，表示答案。
示例1
输入
复制
3 3
-2 -3 3
-5 -10 1
0 30 -5
输出
复制
7
示例2
输入
复制
2 2
1 1
1 1
输出
复制
1
 */

import java.util.*;

public class Main {
    static Scanner scanner;
    static int row;
    static int column;
    static int[][] matrix;

    public static void main(String[] args) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        System.out.println(minHpAtStart());
    }

    private static int minHpAtStart() {
        int result;
        int[] helpColume = helpRow();
        for (int i = row - 2; i >= 0; i--) {
            for (int j = column - 1; j >= 0; j--) {
                if (j == column - 1) {
                    helpColume[j] = Math.max(1, -matrix[i][j] + helpColume[j]);
                } else {
                    int down = Math.max(1, -matrix[i][j] + helpColume[j]);
                    int right = Math.max(1, -matrix[i][j] + helpColume[j + 1]);
                    helpColume[j] = Math.min(down, right);
                }
            }
        }
        result = helpColume[0];
        return result;
    }

    private static int[] helpRow() {
        int[] result = new int[column];
        result[column - 1] = Math.max(1, -matrix[row - 1][column - 1] + 1);
        for (int i = column - 2; i >= 0; i--) {
            result[i] = Math.max(1, -matrix[row - 1][i] + result[i + 1]);
        }
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        row = scanner.nextInt();
        column = scanner.nextInt();
        matrix = new int[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
    }
}