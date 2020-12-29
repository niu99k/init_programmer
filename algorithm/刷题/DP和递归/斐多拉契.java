/*
题目描述
给出一个整数 n，请输出斐波那契数列的第 n 项对 1e9 + 7 取模的值。
输入描述:
第一行一个整数 n。
输出描述:
输出第 n 项对于 1e9 + 7 取模的值。
示例1
输入
复制
1
输出
复制
1
备注:
1 \leq n \leq 1e181≤n≤1e18
 */

import java.util.*;

public class Main {
    static Scanner scanner;
    static long endIndex;
    static long[][] matrix;
    static int mod = 1000000007;

    public static void main(String[] args) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        System.out.println(feibolaqi());
    }

    private static long feibolaqi() {
        long result;
        if (endIndex == 1 || endIndex == 2) {
            result = 1;
        } else {
            result = getResultWithBaseValueAndMatrixN(matrixN());
        }
        return result;
    }

    private static long getResultWithBaseValueAndMatrixN(long[][] matrixN) {
        long result;
        result = matrixN[0][0] + matrixN[1][0];
        result = result % mod;
        return result;
    }

    private static long[][] matrixN() {
        long[][] result;
        long n = endIndex - 2;
        String binaryStringN = binaryStringN(n);
        long[][][] matrixMap = matrixMap(binaryStringN.length());
        result = matrixMultiplyByMatrixMap(matrixMap, binaryStringN);
        return result;
    }

    private static long[][] matrixMultiplyByMatrixMap(long[][][] matrixMap, String binaryStringN) {
        long[][] result = new long[][]{{1, 0}, {0, 1}};
        for (int i = 0; i < binaryStringN.length(); i++) {
            if (binaryStringN.charAt(i) == '1') {
                result = matrixmultiply(result, matrixMap[i]);
            }
        }
        return result;
    }

    private static long[][][] matrixMap(int length) {
        long[][][] result = new long[length][][];
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                result[i] = matrix;
            } else {
                result[i] = matrixmultiply(result[i - 1], result[i - 1]);
            }
        }
        return result;
    }

    private static long[][] matrixmultiply(long[][] matrix1, long[][] matrix2) {
        long[][] result = new long[2][2];
        result[0][0] = (matrix1[0][0] * matrix2[0][0] + matrix1[0][1] * matrix2[1][0]) % mod;
        result[0][1] = (matrix1[0][0] * matrix2[0][1] + matrix1[0][1] * matrix2[1][1]) % mod;
        result[1][0] = (matrix1[1][0] * matrix2[0][0] + matrix1[1][1] * matrix2[1][0]) % mod;
        result[1][1] = (matrix1[1][0] * matrix2[0][1] + matrix1[1][1] * matrix2[1][1]) % mod;
        return result;
    }

    private static String binaryStringN(long n) {
        String result = "";
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            stringBuffer.append(n % 2);
            n = n / 2;
            if (n == 0) {
                break;
            }
        }
        result = stringBuffer.toString();
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        endIndex = scanner.nextLong();
        matrix = new long[][]{{1, 1}, {1, 0}};
    }
}