/*
桶排序
 */

import java.util.*;

public class Main {
    public static void main(String[] args) {
        myCheck();
    }

    private static void myCheck() {
        boolean flag = true;
        for (int i = 0; i < 1000; i++) {
            long[] radomArray = radomArray();
            long[] result1 = solve1(radomArray);
            long[] result2 = solve2(radomArray);
            if (!Arrays.toString(result1).equals(Arrays.toString(result2))) {
                System.out.println(Arrays.toString(radomArray));
                System.out.println(Arrays.toString(result1));
                System.out.println(Arrays.toString(result2));
                System.out.println("gg");
                flag = false;
                break;
            }
        }
        if (flag) {
            System.out.println("nice!");
        }
    }

    private static long[] solve2(long[] radomArray) {
        long[] result = new long[radomArray.length];
        for (int i = 0; i < radomArray.length; i++) {
            result[i] = radomArray[i];
        }
        long max = Long.MIN_VALUE;
        for (int i = 0; i < result.length; i++) {
            max = Math.max(max, result[i]);
        }
        Stack<Long>[] buckets = new Stack[10];
        for (int i = 0; i < 10; i++) {
            buckets[i] = new Stack<>();
        }
        for (int i = 0; i < (max + "").length(); i++) {
            for (int j = result.length - 1; j >= 0; j--) {
                final int num = num(result[j], i);
                buckets[num].push(result[j]);
            }
            int index = 0;
            for (int j = 0; j < buckets.length; j++) {
                while (!buckets[j].isEmpty()) {
                    result[index] = buckets[j].pop();
                    index++;
                }
            }
        }

        return result;
    }

    private static int num(long num, int numIndex) {
        int result;
        String numStr = num + "";
        if (numIndex >= numStr.length()) {
            result = 0;
        } else {
            result = numStr.charAt(numStr.length() - 1 - numIndex) - '0';
        }
        return result;
    }

    private static long[] solve1(long[] radomArray) {
        long[] result = new long[radomArray.length];
        for (int i = 0; i < radomArray.length; i++) {
            result[i] = radomArray[i];
        }
        Arrays.sort(result);
        return result;
    }

    private static long[] radomArray() {
        long[] result;
        final int maxSize = 10;
        final long maxNum = 1000;
        final int size = (int) (Math.random() * maxSize) + 1;
        result = new long[size];
        for (int i = 0; i < size; i++) {
            result[i] = (long) (Math.random() * maxNum) + 1;
        }
        return result;
    }
}