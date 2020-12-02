/*
题目描述
有一个整型数组arr和一个大小为w的窗口从数组的最左边滑到最右边，窗口每次向右边滑一个位置，求每一种窗口状态下的最大值。（如果数组长度为n，窗口大小为w，则一共产生n-w+1个窗口的最大值）
输入描述:
第一行输入n和w，分别代表数组长度和窗口大小
第二行输入n个整数Xi，表示数组中的各个元素
输出描述:
输出一个长度为n-w+1的数组res，res[i]表示每一种窗口状态下的最大值
示例1
输入
复制
8 3
4 3 5 4 3 3 6 7
输出
复制
5 5 5 4 6 7
说明
例如，数组为[4，3，5，4，3，3，6，7]，窗口大小为3时：

[4 3 5] 4 3 3 6 7        窗口中最大值为5

4 [3 5 4] 3 3 6 7        窗口中最大值为5

4 3 [5 4 3] 3 6 7        窗口中最大值为5

4 3 5 [4 3 3] 6 7        窗口中最大值为4

4 3 5 4 [3 3 6] 7        窗口中最大值为6

4 3 5 4 3 [3 6 7]        窗口中最大值为7

输出的结果为{5 5 5 4 6 7}
备注:
1<=w<=n<=1000000
-1000000<=Xi<=1000000
 */

import java.util.*;

public class Main {
    static Scanner scanner;
    static int arrayCount;
    static int windowLength;
    static List<Integer> array;
    static LinkedList<Integer> windowMaxMapQueue;
    static List<Integer> result;

    public static void main(String strs[]) {
        init();
        result();
        printResult();
        deInit();
    }

    private static void printResult() {
        for (int i = windowLength - 1; i < result.size(); i++) {
            System.out.print(result.get(i) + " ");
        }
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        for (int i = 0; i < arrayCount; i++) {
            if (windowMaxMapQueue.isEmpty()) {
                windowMaxMapQueue.offer(i);
                result.add(array.get(windowMaxMapQueue.peek()));
            } else if (i - windowMaxMapQueue.peek() >= windowLength) {
                windowMaxMapQueue.poll();
                pushNumIntoQueue(i);
                result.add(array.get(windowMaxMapQueue.peek()));
            } else {
                pushNumIntoQueue(i);
                result.add(array.get(windowMaxMapQueue.peek()));
            }
        }
    }

    private static void pushNumIntoQueue(Integer index) {
        if (array.get(windowMaxMapQueue.getLast()) > array.get(index)) {
            windowMaxMapQueue.offer(index);
        } else {
            while (!windowMaxMapQueue.isEmpty()) {
                if (array.get(windowMaxMapQueue.getLast()) > array.get(index)) {
                    windowMaxMapQueue.offer(index);
                    break;
                } else if (windowMaxMapQueue.size() == 1) {
                    windowMaxMapQueue.removeLast();
                    windowMaxMapQueue.offer(index);
                    break;
                } else {
                    windowMaxMapQueue.removeLast();
                }
            }
        }
    }

    private static void init() {
        scanner = new Scanner(System.in);
        array = new ArrayList<>();
        windowMaxMapQueue = new LinkedList<>();
        result = new ArrayList<>();

        arrayCount = scanner.nextInt();
        windowLength = scanner.nextInt();
        for (int i = 0; i < arrayCount; i++) {
            array.add(scanner.nextInt());
        }
    }
}