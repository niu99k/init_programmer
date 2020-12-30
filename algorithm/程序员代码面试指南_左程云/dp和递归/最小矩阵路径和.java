/*
题目描述
给定一个 n * m 的矩阵 a，从左上角开始每次只能向右或者向下走，最后到达右下角的位置，路径上所有的数字累加起来就是路径和，输出所有的路径中最小的路径和。
输入描述:
第一行输入两个整数 n 和 m，表示矩阵的大小。

接下来 n 行每行 m 个整数表示矩阵。
输出描述:
输出一个整数表示答案。
示例1
输入
复制
4 4
1 3 5 9
8 1 3 4
5 0 6 1
8 8 4 0
输出
复制
12
备注:
1 \leq n, m \leq 20001≤n,m≤2000
0 \leq a_{ij} \leq 1000≤a
ij
​
 ≤100
 */

import java.util.*;

public class Main {
    static Scanner scanner;
    static int[][] matrix;
    static int[] arrayTemp;
    static int row;
    static int column;

    public static void main(String[] args) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    public static void result() {
        System.out.print(solve());
    }

    public static int solve() {
        int result;
        int[] arrayTemp = firstRow();
        int[] firstColumn = firstColumn();

        for (int i = 1; i < row; i++) {
            arrayTemp[0] = firstColumn[i];
            for (int j = 1; j < column; j++) {
                arrayTemp[j] = Math.min(arrayTemp[j], arrayTemp[j - 1]) + matrix[i][j];
            }
        }
        result = arrayTemp[column - 1];
        return result;
    }

    private static int[] firstColumn() {
        int[] result = new int[row];
        int sum = 0;
        for (int i = 0; i < row; i++) {
            sum += matrix[i][0];
            result[i] = sum;
        }
        return result;
    }


    private static int[] firstRow() {
        int[] result = new int[column];
        int sum = 0;
        for (int i = 0; i < column; i++) {
            sum += matrix[0][i];
            result[i] = sum;
        }
        return result;
    }

    public static void init() {
        scanner = new Scanner(System.in);
        row = scanner.nextInt();
        column = scanner.nextInt();
        matrix = new int[row][column];
        arrayTemp = new int[column];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
    }
}