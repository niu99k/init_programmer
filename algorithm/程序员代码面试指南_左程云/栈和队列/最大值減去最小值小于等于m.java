/*
题目描述
给定数组 arr 和整数 num，共返回有多少个子数组满足如下情况：
max(arr[i...j]) - min(arr[i...j]) <= num
max(arr[i...j])表示子数组arr[i...j]中的最大值，min[arr[i...j])表示子数组arr[i...j]中的最小值。

输入描述:
第一行输入两个数 n 和 num，其中 n 表示数组 arr 的长度
第二行输入n个整数X_iX
i
​
 ，表示数组arr中的每个元素
输出描述:
输出给定数组中满足条件的子数组个数
示例1
输入
复制
5 2
1 2 3 4 5
输出
复制
12
备注:
1\le n \le 10000001≤n≤1000000
-1000000 \le arr_i \le 1000000−1000000≤arr
i
​
 ≤1000000
0 \le num \le 20000000≤num≤2000000
5 2
5 3 1 2 4
 */

import java.util.*;

public class Main {
    static int arraySize;
    static int limit;
    static LinkedList<Integer> maxQueue;
    static LinkedList<Integer> minQueue;
    static List<Integer> array;
    static Scanner scanner;
    static int result;
    static int beginIndex;
    static int endIndex;

    public static void main(String strs[]) {
        init();
        result();
        printResult();
        deInit();
    }

    private static void printResult() {
        System.out.println(result);
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        while (beginIndex < arraySize) {
            if (queuesFeasible() && endIndex < arraySize) {
                pushNum2MaxQueue();
                pushNum2MinQueue();
                endIndex++;
            } else {
                refreshResult();
                pollBeginIndexNumInMaxQueue();
                pollBeginIndexNumInMinQueue();
                beginIndex++;
            }
        }
    }

    private static void pollBeginIndexNumInMinQueue() {
        if (minQueue.peek() <= beginIndex) {
            minQueue.poll();
        }
    }

    private static void pollBeginIndexNumInMaxQueue() {
        if (maxQueue.peek() <= beginIndex) {
            maxQueue.poll();
        }
    }

    private static void refreshResult() {
        if (queuesFeasible()) {
            result += endIndex - beginIndex;
        } else {
            result += endIndex - beginIndex - 1;
        }
    }

    private static boolean queuesFeasible() {
        boolean result;
        if (minQueue.isEmpty() || maxQueue.isEmpty()) {
            result = true;
        } else {
            result = array.get(maxQueue.peek()) - array.get(minQueue.peek()) <= limit;
        }
        return result;
    }

    private static void pushNum2MinQueue() {
        int newNum = array.get(endIndex);
        if (minQueue.isEmpty()) {
            minQueue.offer(endIndex);
        } else if (array.get(minQueue.getLast()) <= newNum) {
            minQueue.offer(endIndex);
        } else {
            while (!minQueue.isEmpty()) {
                int lastIndex = minQueue.getLast();
                if (array.get(lastIndex) > newNum) {
                    minQueue.removeLast();
                } else {
                    break;
                }
            }
            minQueue.offer(endIndex);
        }
    }

    private static void pushNum2MaxQueue() {
        int newNum = array.get(endIndex);
        if (maxQueue.isEmpty()) {
            maxQueue.offer(endIndex);
        } else if (array.get(maxQueue.getLast()) >= newNum) {
            maxQueue.offer(endIndex);
        } else {
            while (!maxQueue.isEmpty()) {
                int lastIndex = maxQueue.getLast();
                if (array.get(lastIndex) < newNum) {
                    maxQueue.removeLast();
                } else {
                    break;
                }
            }
            maxQueue.offer(endIndex);
        }
    }


    private static void init() {
        array = new ArrayList<>();
        maxQueue = new LinkedList<>();
        minQueue = new LinkedList<>();
        scanner = new Scanner(System.in);
        arraySize = scanner.nextInt();
        limit = scanner.nextInt();
        for (int i = 0; i < arraySize; i++) {
            array.add(scanner.nextInt());
        }

    }
}