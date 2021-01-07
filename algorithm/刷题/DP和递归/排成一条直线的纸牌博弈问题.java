/*
题目描述
给定一个整型数组arr，代表数值不同的纸牌排成一条线，玩家A和玩家B依次拿走每张纸牌，规定玩家A先拿，玩家B后拿，但是每个玩家每次只能拿走最左和最右的纸牌，玩家A和玩家B绝顶聪明。请返回最后的获胜者的分数。
输入描述:
输出包括两行，第一行一个整数n(1 \leq n \leq 5000 )(1≤n≤5000)，代表数组arr长度，第二行包含n个整数，第i个代表arr[i]( 1 \leq arr[i] \leq 10^5 )(1≤arr[i]≤10
5
 )。
输出描述:
输出一个整数，代表最后获胜者的分数。
示例1
输入
复制
4
1 2 100 4
输出
复制
101
备注:
时间复杂读O(n^2)O(n
2
 ),空间复杂度O(n^2)O(n
2
 )
 */

import java.util.*;

public class Main {
    static Scanner scanner;
    static int arrayLen;
    static int[] array;

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
        int[][] firstDpMap = firstDpMap();
        int[][] lastDpMap = lastDpMap();
        calcDpMap(firstDpMap, lastDpMap);
        result = Math.max(firstDpMap[0][arrayLen - 1], lastDpMap[0][arrayLen - 1]);
        return result;
    }

    private static void calcDpMap(int[][] firstDpMap, int[][] lastDpMap) {
        int baseEndIndex = 1;
        while (baseEndIndex < arrayLen) {
            int beginIndex = 0;
            int endIndex = baseEndIndex;
            while (endIndex < arrayLen) {
                int left = array[beginIndex] + lastDpMap[beginIndex + 1][endIndex];
                int right = array[endIndex] + lastDpMap[beginIndex][endIndex - 1];
                if (left > right) {
                    firstDpMap[beginIndex][endIndex] = left;
                    lastDpMap[beginIndex][endIndex] = firstDpMap[beginIndex + 1][endIndex];
                } else {
                    firstDpMap[beginIndex][endIndex] = right;
                    lastDpMap[beginIndex][endIndex] = firstDpMap[beginIndex][endIndex - 1];
                }
                beginIndex++;
                endIndex++;
            }
            baseEndIndex++;
        }
    }

    private static int[][] lastDpMap() {
        int[][] result = new int[arrayLen ][arrayLen ];
        return result;
    }

    private static int[][] firstDpMap() {
        int[][] result = new int[arrayLen ][arrayLen ];
        for (int i = 0; i < arrayLen; i++) {
            result[i][i] = array[i];
        }
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        arrayLen = scanner.nextInt();
        array = new int[arrayLen];
        for (int i = 0; i < arrayLen; i++) {
            array[i] = scanner.nextInt();
        }
    }
}