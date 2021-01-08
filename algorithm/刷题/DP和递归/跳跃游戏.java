/*
题目描述
给定数组arr，arr[i]==k代表可以从位置向右跳1~k个距离。比如，arr[2]==3,代表可以从位置2跳到位置3、位置4或位置5。如果从位置0出发，返回最少跳几次能跳到arr最后的位置上。
输入描述:
输出包括两行，第一行一个整数n(1 \leq n \leq 1e5 )(1≤n≤1e5)，代表arr数组长度，第二行n个整数代表数组arr[i](1 \leq arr[i] \leq n)(1≤arr[i]≤n)。
输出描述:
输出一个整数，代表最少跳的次数。
示例1
输入
复制
6
3 2 3 1 1 4
输出
复制
2
说明
arr[0]==3，选择跳到位置2，arr[2]==3,可以跳到最后的位置。所以返回2。
备注:
时间复杂度O(n)，额外空间复杂度O(1)
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
        int furtherestIndex = array[0];
        int step = 1;
        int max = 0;
        for (int i = 0; i < arrayLen; i++) {
            if (i > furtherestIndex) {
                step++;
                furtherestIndex = max;
                if (furtherestIndex >= arrayLen - 1) {
                    break;
                }
            }
            max = Math.max(max, array[i] + i);
        }
        result = step;
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