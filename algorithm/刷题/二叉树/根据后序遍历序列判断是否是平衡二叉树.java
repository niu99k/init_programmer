/*
题目描述
给定一个有 n 个不重复整数的数组 arr，判断 arr 是否可能是节点值类型为整数的搜索二叉树后序遍历的结果。
输入描述:
第一行一个整数 n，表示数组的长度。

第二行 n 个整数 arr_i。
输出描述:
如果是搜索二叉树后序遍历的结果则输出 "true"，否则输出 "false"。
示例1
输入
复制
3
1 3 2
输出
复制
true
备注:
1 \leq n \leq 5000001≤n≤500000
-1000000 \leq arr_i \leq 1000000−1000000≤arr
i
​
 ≤1000000
 */

import java.util.*;

public class Main {
    static Scanner scanner;
    static int[] array;
    static int arrayCount;

    public static void main(String[] args) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        System.out.println(isArraySatisFy(0, arrayCount - 1));
    }

    private static boolean isArraySatisFy(int beginIndex, int endIndex) {
        boolean result;
        if (endIndex - beginIndex <= 2) {
            result = true;
        } else {
            boolean isThisTreeSatisfy = true;
            int leftBeginIndex = beginIndex;
            int rightBeginIndex;
            int leftEndIndex;
            int rightEndIndex = endIndex - 1;

            int rootValue = array[endIndex];
            int splitIndex = endIndex;
            for (int i = beginIndex; i < endIndex; i++) {
                if (array[i] > rootValue) {
                    splitIndex = i;
                    break;
                }
            }
            for (int i = splitIndex; i < endIndex; i++) {
                if (array[i] < rootValue) {
                    isThisTreeSatisfy = false;
                    break;
                }
            }
            rightBeginIndex = splitIndex;
            leftEndIndex = rightBeginIndex - 1;
            if (isThisTreeSatisfy) {
                boolean leftResult = isArraySatisFy(leftBeginIndex, leftEndIndex);
                boolean rightReault = isArraySatisFy(rightBeginIndex, rightEndIndex);
                result = leftResult && rightReault;
            } else {
                result = false;
            }
        }


        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        arrayCount = scanner.nextInt();
        array = new int[arrayCount];

        for (int i = 0; i < arrayCount; i++) {
            array[i] = scanner.nextInt();
        }
    }
}