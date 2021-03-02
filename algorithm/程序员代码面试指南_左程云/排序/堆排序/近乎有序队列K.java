/*
几乎有序问题
 */

import java.util.*;

public class Main {
    public static void main(String[] args) {
        myCheck();
    }

    private static void myCheck() {
        boolean flag = true;
        for (int i = 0; i < 1000; i++) {
            final int k = new Random().nextInt(10) + 1;
            int[] randomArray = randomArray(k);
            int[] result1 = solve1(randomArray);
            int[] result2 = solve2(randomArray, k);
            if (!Arrays.toString(result1).equals(Arrays.toString(result2))) {
                System.out.println(k);
                System.out.println(Arrays.toString(randomArray));
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

    private static int[] solve1(int[] randomArray) {
        int[] result = new int[randomArray.length];
        for (int i = 0; i < randomArray.length; i++) {
            result[i] = randomArray[i];
        }
        Arrays.sort(result);
        return result;
    }

    private static int[] solve2(int[] randomArray, int k) {
        int[] result = new int[randomArray.length];
        for (int i = 0; i < randomArray.length; i++) {
            result[i] = randomArray[i];
        }
        int initSize = Math.min(k + 1, randomArray.length);
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < initSize; i++) {
            queue.offer(randomArray[i]);
        }
        int index = 0;
        int end = initSize;
        while (!queue.isEmpty()) {
            result[index] = queue.poll();
            if (end < randomArray.length) {
                queue.offer(result[end]);
                end++;
            }
            index++;
        }
        return result;
    }

    private static int[] randomArray(int k) {
        int[] result;
        final int maxSize = 100;
        final int maxNum = Integer.MAX_VALUE;
        final int size = (int) (Math.random() * maxSize) + 1;
        result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = (int) (Math.random() * maxNum) + 1;
        }
        Arrays.sort(result);

        int beginIndex = 0;
        while (beginIndex < size - 1) {
            int endIndex = Math.min(size - 1, beginIndex + k);
            for (int i = 0; i < 5; i++) {
                int index1 = beginIndex + new Random().nextInt(endIndex - beginIndex + 1);
                int index2 = beginIndex + new Random().nextInt(endIndex - beginIndex + 1);
                swap(index1, index2, result);
            }
            beginIndex = endIndex + 1;
        }
        return result;
    }

    private static void swap(int index1, int index2, int[] result) {
        int temp = result[index1];
        result[index1] = result[index2];
        result[index2] = temp;
    }
}
