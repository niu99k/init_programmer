import java.util.*;

/*
题目描述
给定数组arr，设长度为n，输出arr的最长递增子序列。（如果有多个答案，请输出其中字典序最小的）
输入描述:
输出两行，第一行包括一个正整数n（n<=100000），代表数组长度。第二行包括n个整数，代表数组arr \left(1 \leq arr_i \leq 1e9 \right)(1≤arr
i
​
 ≤1e9)。
输出描述:
输出一行。代表你求出的最长的递增子序列。
示例1
输入
复制
9
2 1 5 3 6 4 8 9 7
输出
复制
1 3 4 8 9
示例2
输入
复制
5
1 2 8 6 4
输出
复制
1 2 4
说明
其最长递增子序列有3个，（1，2，8）、（1，2，6）、（1，2，4）其中第三个字典序最小，故答案为（1，2，4）
备注:
时间复杂度O(n\log n)O(nlogn)，空间复杂度O(n)O(n)。

10
6 7 8 9 1 2 3 4 5 99
 */
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
        System.out.print(maxLengthPath(dpMap()));
    }

    private static String maxLengthPath(int[] dpMap) {
        String result;
        StringBuffer stringBuffer = new StringBuffer();
        int target = 0;
        for (int i = 0; i < arrayLen; i++) {
            target = Math.max(target, dpMap[i]);
        }
        Stack<String> stack = new Stack();
        for (int i = arrayLen - 1; i >= 0; i--) {
            if (dpMap[i] == target) {
                stack.push(array[i] + " ");
                target--;
            }
        }
        while (!stack.isEmpty()) {
            stringBuffer.append(stack.pop());
        }
        result = stringBuffer.toString();
        return result;
    }

    private static int[] dpMap() {
        int[] result = new int[arrayLen];
        int[] memo = new int[arrayLen];
        int maxLen = -1;
        for (int i = 0; i < arrayLen; i++) {
            int firstBiggerNumIndex = firstBiggerNumIndex(memo, i, maxLen);
            if (firstBiggerNumIndex == -1) {
                maxLen += 1;
                memo[maxLen] = array[i];
                result[i] = maxLen + 1;
            } else {
                if (array[i] < memo[firstBiggerNumIndex]) {
                    memo[firstBiggerNumIndex] = array[i];
                    result[i] = firstBiggerNumIndex + 1;
                }
            }
        }
        return result;
    }

    private static int firstBiggerNumIndex(int[] memo, int index, int maxLen) {
        int result;
        if (maxLen < 0) {
            result = -1;
        } else {
            int beginIndex = 0;
            int endIndex = maxLen;
            result = -1;
            while (endIndex >= beginIndex) {
                int midIndex = (beginIndex + endIndex) / 2;
                if (memo[midIndex] >= array[index]) {
                    if (isFirstBigger(memo, midIndex, index)) {
                        result = midIndex;
                        break;
                    }
                    endIndex = midIndex - 1;
                } else {
                    beginIndex = midIndex + 1;
                }
            }
        }
        return result;
    }

    private static boolean isFirstBigger(int[] memo, int index, int baseIndex) {
        boolean result = true;
        if (index == 0) {
            result = true;
        } else if (memo[index - 1] < array[baseIndex]) {
            result = true;
        } else {
            result = false;
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