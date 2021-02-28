/*
链接：https://www.nowcoder.com/questionTerminal/edfe05a1d45c4ea89101d936cac32469
来源：牛客网

数组小和的定义如下：
例如，数组s = [1, 3, 5, 2, 4, 6]，在s[0]的左边小于或等于s[0]的数的和为0；在s[1]的左边小于或等于s[1]的数的和为1；在s[2]的左边小于或等于s[2]的数的和为1+3=4；在s[3]的左边小于或等于s[3]的数的和为1；
在s[4]的左边小于或等于s[4]的数的和为1+3+2=6；在s[5]的左边小于或等于s[5]的数的和为1+3+5+2+4=15。所以s的小和为0+1+4+1+6+15=27
给定一个数组s，实现函数返回s的小和
[要求]
时间复杂度为O(nlogn)O(nlogn)，空间复杂度为O(n)O(n)

输入描述:
第一行有一个整数N。表示数组长度
接下来一行N个整数表示数组内的数


输出描述:
一个整数表示答案
示例1
输入
6
1 3 5 2 4 6
输出
27

备注:
1 \leqslant N \leqslant 10^51⩽N⩽10
5

-100 \leqslant arr_i \leqslant 100−100⩽arr
i
​
 ⩽100

 5
 10 30 99 48 80 37

 8
 83 14  45  77  90  60  79  67

 8
 40  37  95  91  6  90  37  65
 */

import java.util.*;

public class Main {
    static Scanner scanner;
    static long[] array;
    static int arrayLen;

    public static void main(String[] args) {
//        myCheck();
        answer();
    }

    private static void answer() {
        scanner = new Scanner(System.in);
        arrayLen = scanner.nextInt();
        array = new long[arrayLen];
        for (int i = 0; i < arrayLen; i++) {
            array[i] = scanner.nextInt();
        }
        System.out.println(solve2(array));
        scanner.close();
    }


    private static void myCheck() {
        for (int i = 0; i < 1000; i++) {
            long[] randomArray = randomArray();
            long result1 = solve1(randomArray);
            long result2 = solve2(randomArray);
            if (result1 != result2) {
                System.out.println(Arrays.toString(randomArray));
                System.out.println(result1);
                System.out.println(result2);
                System.out.println("GG");
                break;
            }
        }
    }

    private static long solve2(long[] randomArray) {
        long result = 0;
        long[] arrayCopy = new long[randomArray.length];
        for (int i = 0; i < arrayCopy.length; i++) {
            arrayCopy[i] = randomArray[i];
        }

        result = sort(arrayCopy, 0, arrayCopy.length - 1);
        return result;
    }

    private static long sort(long[] arrayCopy, int begin, int end) {
        long result;
        if (begin >= end) {
            result = 0;
        } else {
            final int midIndex = begin + (end - begin) / 2;

            final long left = sort(arrayCopy, begin, midIndex);
            final long right = sort(arrayCopy, midIndex + 1, end);
            final long mergeCount = merge(arrayCopy, begin, midIndex, midIndex + 1, end);
            result = left + right + mergeCount;
        }
        return result;
    }

    private static long merge(long[] arrayCopy, int begin1, int end1, int begin2, int end2) {
        long result = 0;
        final int start1 = begin1;
        int index = 0;
        long[] tempArray = new long[end2 - begin1 + 1];
        while (begin1 <= end1 && begin2 <= end2) {
            if (arrayCopy[begin1] <= arrayCopy[begin2]) {
                final long tempNum = arrayCopy[begin1];
                tempArray[index++] = arrayCopy[begin1];
                begin1++;
                result += (end2 - begin2 + 1) * tempNum;
            } else {
                tempArray[index++] = arrayCopy[begin2];
                begin2++;
            }
        }
        while (begin1 <= end1) {
            tempArray[index++] = arrayCopy[begin1++];
        }
        while (begin2 <= end2) {
            tempArray[index++] = arrayCopy[begin2++];
        }
        index = start1;
        for (int i = 0; i <= end2 - start1; i++) {
            arrayCopy[index++] = tempArray[i];
        }
        return result;
    }

    private static long solve1(long[] randomArray) {
        int result = 0;
        for (int i = 0; i < randomArray.length; i++) {
            for (int j = 0; j < i; j++) {
                if (randomArray[j] <= randomArray[i]) {
                    result += randomArray[j];
                }
            }
        }
        return result;

    }

    private static long[] randomArray() {
        final int maxArrayLen = 100;
        final int maxArrayNum = 1000;
        final int randomArrayLen = (int) ((Math.random() * maxArrayLen + 1));
        long[] randomArray = new long[randomArrayLen];
        for (int i = 0; i < randomArrayLen; i++) {
            randomArray[i] = (int) (Math.random() * maxArrayNum + 1);
        }
        return randomArray;
    }
}