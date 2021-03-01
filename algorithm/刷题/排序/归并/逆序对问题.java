/*
链接：https://www.nowcoder.com/questionTerminal/f50f89c3b4624cd6aa3312747cd45879
来源：牛客网

给定一个长度为n的排列A,逆序的定义：(i,j)为逆序对，当i<j && A[i]>A[j]

求排列A的逆序对数量。


输入描述:
第一行一个整数n，表示排列的长度.
第二行n个元素，表示A排列.


输出描述:
输出逆序对的数量
示例1
输入
5
3 2 4 1 5
输出
4
 */

import java.util.*;

public class Main {

    public static void main(String[] args) {
//        mycheck();
        answer();
    }

    private static void answer() {
        Scanner scanner = new Scanner(System.in);
        int len = scanner.nextInt();
        long[] array = new long[len];
        for (int i = 0; i < len; i++) {
            array[i] = scanner.nextLong();
        }
        System.out.println(solve2(array));
        scanner.close();

    }

    private static void mycheck() {
        for (int i = 0; i < 1000; i++) {
            long[] randomInput = randomInput();
            final long result1 = solve1(randomInput);
            final long result2 = solve2(randomInput);

            if (result1 != result2) {
                System.out.println(Arrays.toString(randomInput).replace(",", " ").replace("[", " ").replace("]", " "));
                System.out.println(result1);
                System.out.println(result2);
                System.out.println("gg");
                break;
            }

        }
        System.out.println("nice!");

    }

    private static long solve2(long[] inputArray) {
        long result;
        if (inputArray.length == 0 || inputArray == null) {
            result = 0;
        } else {
            result = mergeSort(0, inputArray.length - 1, inputArray);
        }
        return result;
    }

    private static long mergeSort(int begin, int end, long[] inputArray) {
        long result;
        if (begin >= end) {
            result = 0;
        } else {
            final int midIndex = begin + ((end - begin) >> 1);
            final long left = mergeSort(begin, midIndex, inputArray);
            final long right = mergeSort(midIndex + 1, end, inputArray);
            final long merge = merge(begin, midIndex, midIndex + 1, end, inputArray);
            result = left + right + merge;
        }
        return result;
    }

    private static long merge(int begin1, int end1, int begin2, int end2, long[] inputArray) {
        long result = 0;
        long[] help = new long[end2 - begin1 + 1];
        final int start = begin1;
        int index = 0;
        while (begin1 <= end1 && begin2 <= end2) {
            if (inputArray[begin1] > inputArray[begin2]) {
                help[index++] = inputArray[begin1++];
                result += end2 - begin2 + 1;
            } else {
                help[index++] = inputArray[begin2++];
            }
        }
        while (begin1 <= end1) {
            help[index++] = inputArray[begin1++];
        }
        while (begin2 <= end2) {
            help[index++] = inputArray[begin2++];
        }
        int begin = start;
        for (int i = 0; i < help.length; i++) {
            inputArray[begin++] = help[i];
        }
        return result;
    }

    private static int solve1(long[] inputArray) {
        int result = 0;
        for (int i = 0; i < inputArray.length; i++) {
            for (int j = 0; j < i; j++) {
                if (inputArray[j] > inputArray[i]) {
                    result++;
                }
            }
        }
        return result;
    }

    private static long[] randomInput() {
        long[] result;
        final int maxRandomArrayLen = 100;
        final long maxNum = Long.MAX_VALUE;
        final int randomArrayLen = (int) (Math.random() * maxRandomArrayLen) + 1;
        result = new long[randomArrayLen];
        for (int i = 0; i < randomArrayLen; i++) {
            result[i] = (long) (Math.random() * maxNum) + 1;
        }
        return result;
    }
}