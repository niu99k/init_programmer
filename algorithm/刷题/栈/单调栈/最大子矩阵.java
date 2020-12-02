/*
题目描述
给定一个整型矩阵 map，其中的值只有 0 和 1 两种，求其中全是 1 的所有矩形区域中，最大的矩形区域里 1 的数量。
输入描述:
第一行输入两个整数 n 和 m，代表 n*m 的矩阵
接下来输入一个 n*m 的矩阵
输出描述:
输出其中全是 1 的所有矩形区域中，最大的矩形区域里 1 的数量。
示例1
输入
复制
1 4
1 1 1 0
输出
复制
3
说明
最大的矩形区域有3个1，所以返回3
备注:
1≤n,m≤2000

3 4
1 1 1 0
1 1 1 1
0 1 1 0

5 8
1 1 1 0 1 1 1 0
1 1 1 1 1 1 1 0
0 1 1 1 1 1 1 0
1 1 1 1 1 1 1 0
0 1 1 0 1 1 1 0
 */

import java.util.*;

public class Main {
    static int[][] matrix;
    static int n;
    static int m;
    static Scanner scanner;
    static int result;

    public static void main(String strs[]) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        initMaxAreaOfheighMap(heighMap());
        System.out.println(result);
    }

    private static void initMaxAreaOfheighMap(int[][] heighMap) {
        for (int i = 0; i < n; i++) {
            initMaxSubArrayOfArray(heighMap[i]);
        }
    }

    private static void initMaxSubArrayOfArray(int[] array) {
        List<Integer> maxLength4Element = maxArea4Element(array);
        int max = maxLength4Element.stream()
                .max((o1, o2) -> {
                    return o1 - o2;
                })
                .get();
        if (max > result) {
            result = max;
        }
    }

    private static List<Integer> maxArea4Element(int[] array) {
        List<Integer> result = new ArrayList<>();
        int[] left = initLeftBorderArray(m);
        int[] right = initRightBorderArray(m);
        initLeftAndRoght(left, right, array);
        for (int i = 0; i < m; i++) {
            result.add((right[i] - left[i] + 1) * array[i]);
        }

        return result;
    }

    private static void initLeftAndRoght(int[] left, int[] right, int[] array) {
        Stack<Integer> mpStack = new Stack<>();
        for (int i = 0; i < m; i++) {
            if (mpStack.isEmpty()) {
                mpStack.push(i);
            } else if (array[mpStack.peek()] < array[i]) {
                left[i] = mpStack.peek() + 1;
                mpStack.push(i);
            } else if (array[mpStack.peek()] == array[i]) {
                left[i] = left[i - 1];
                mpStack.push(i);
            } else {
                while (!mpStack.isEmpty()) {
                    if (array[mpStack.peek()] < array[i]) {
                        left[i] = mpStack.peek() + 1;
                        mpStack.push(i);
                        break;
                    } else if (array[mpStack.peek()] == array[i]) {
                        left[i] = left[i - 1];
                        mpStack.push(i);
                        break;
                    } else if (mpStack.size() == 1) {
                        right[mpStack.pop()] = i - 1;
                        mpStack.push(i);
                        break;
                    } else {
                        right[mpStack.pop()] = i - 1;
                    }
                }
            }
        }
    }

    private static int[] initRightBorderArray(int m) {
        int[] result = new int[m];
        for (int i = 0; i < m; i++) {
            result[i] = m - 1;
        }
        return result;
    }

    private static int[] initLeftBorderArray(int m) {
        int[] result = new int[m];
        for (int i = 0; i < m; i++) {
            result[i] = 0;
        }
        return result;
    }

    private static int[][] heighMap() {
        int[][] result = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {
                    result[i][j] = 0;
                } else if (i == 0) {
                    result[i][j] = 1;
                } else {
                    result[i][j] = result[i - 1][j] + 1;
                }
            }
        }
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
    }
}