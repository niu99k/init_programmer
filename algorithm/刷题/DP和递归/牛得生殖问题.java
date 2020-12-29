/*
题目描述
假设农场中成熟的母牛每年只会生 1 头小母牛，并且永远不会死。第一年农场中有一只成熟的母牛，从第二年开始，母牛开始生小母牛。每只小母牛 3 年之后成熟又可以生小母牛。给定整数 n，求出 n 年后牛的数量。
输入描述:
输入一个整数 n。
输出描述:
输出 n 年后牛的数量对 1e9 + 7 取模的值。
示例1
输入
复制
6
输出
复制
9
备注:
1 \leq n \leq 1e181≤n≤1e18
 */

import java.util.*;

public class Main {
    static Scanner scanner;
    static long endYearIndex;
    static long[][] baseMatrix;
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
        long result;
        if (endYearIndex == 1) {
            result = 1;
        } else if (endYearIndex == 2) {
            result = 2;
        } else if (endYearIndex == 3) {
            result = 3;
        } else {
            String binaryStringN = binaryStringN();
            result = multiplyInMatrixMapWithBinaryStringN(binaryStringN, matrixMap(binaryStringN.length()));
        }
        return result;
    }

    private static long multiplyInMatrixMapWithBinaryStringN(String binaryStringN, long[][][] matrixMap) {
        long result;
        long[][] finalMatrix = finalMatrix(binaryStringN, matrixMap);
        result = (3 * finalMatrix[0][0] + 2 * finalMatrix[1][0] + finalMatrix[2][0]) % mod;
        return result;
    }

    private static long[][] finalMatrix(String binaryStringN, long[][][] matrixMap) {
        long[][] result = new long[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        for (int i = 0; i < binaryStringN.length(); i++) {
            if (binaryStringN.charAt(i) == '1') {
                result = mutiplyMatrix(result, matrixMap[i]);
            }
        }
        return result;
    }

    private static long[][][] matrixMap(int length) {
        long[][][] result = new long[length][][];
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                result[i] = baseMatrix;
            } else {
                result[i] = mutiplyMatrix(result[i - 1], result[i - 1]);
            }
        }
        return result;
    }

    private static long[][] mutiplyMatrix(long[][] matrix1, long[][] matrix2) {
        long[][] result = new long[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                long[] cloumn = new long[3];
                for (int k = 0; k < 3; k++) {
                    cloumn[k] = matrix2[k][j];
                }
                result[i][j] = rowMutiplyByColumn(matrix1[i], cloumn);
            }
        }
        return result;
    }

    private static long rowMutiplyByColumn(long[] row, long[] cloumn) {
        long result = 0;
        for (int i = 0; i < 3; i++) {
            result += row[i] * cloumn[i];
        }
        result = result % mod;
        return result;
    }

    private static String binaryStringN() {
        String result;
        long num = endYearIndex - 3;
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            stringBuffer.append(num % 2);
            num = num / 2;
            if (num == 0) {
                break;
            }
        }
        result = stringBuffer.toString();
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        endYearIndex = scanner.nextLong();
        baseMatrix = new long[][]{{1, 1, 0}, {0, 0, 1}, {1, 0, 0}};
    }
}