/*
题目描述
给定一个个数字arr，判断数组arr中是否所有的数字都只出现过一次。
输入描述:
输入包括两行，第一行一个整数n(1 \leq n \leq 10^5)(1≤n≤10
5
 )，代表数组arr的长度。第二行包括n个整数，代表数组arr(1 \leq arr[i] \leq 10^7 )(1≤arr[i]≤10
7
 )。
输出描述:
如果arr中所有数字都只出现一次，输出“YES”，否则输出“NO”。
示例1
输入
复制
3
1 2 3
输出
复制
YES
示例2
输入
复制
3
1 2 1
输出
复制
NO
备注:
要求
1.时间复杂度O（n）O（n）。
2.额外空间复杂度O（1）O（1）。

4
16 2 5 4

7
1 2 4 99 4 2 66
 */

import java.util.*;

public class Main {
    static Scanner scanner;
    static int arrayCount;
    static int[] array;

    public static void main(String[] args) {
        init();
        result();
        deinit();
    }

    private static void deinit() {
        scanner.close();
    }

    private static void result() {
        sortWithHeapSort();
        System.out.println(isRepetitive() ? "YES" : "NO");
    }

    private static void sortWithHeapSort() {
        for (int i = 0; i < arrayCount; i++) {
            sortWithSmallerisParent(i);
        }
        for (int i = arrayCount - 1; i >= 0; i--) {
            swap(0, i);
            sortWithSmallerAreChildren(0, i - 1);
        }
    }

    private static void sortWithSmallerAreChildren(int index, int maxIndex) {
        int smallest = 0;
        while (true) {
            int left = index * 2;
            int right = index * 2 + 1;
            if (left <= maxIndex && array[left] < array[smallest]) {
                smallest = left;
            }
            if (right <= maxIndex && array[right] < array[smallest]) {
                smallest = right;
            }
            if (smallest != index) {
                swap(index, smallest);
                index = smallest;
            } else {
                break;
            }
        }
    }

    private static void sortWithSmallerisParent(int index) {
        int parent = 0;
        while (true) {
            if (index == 0) {
                break;
            }
            parent = (index - 1) / 2;
            if (array[parent] > array[index]) {
                swap(index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

    private static void swap(int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;

    }

    private static boolean isRepetitive() {
        boolean result = true;
        int compare = array[0];
        for (int i = 1; i < arrayCount; i++) {
            if (array[i] == compare) {
                result = false;
                break;
            } else {
                compare = array[i];
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
