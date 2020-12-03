/*
题目描述
给定两个升序链表，打印两个升序链表的公共部分。
输入描述:
第一个链表的长度为 n。

第二个链表的长度为 m。

链表结点的值为 val。
输出描述:
输出一行整数表示两个升序链表的公共部分的值 (按升序输出)。
示例1
输入
复制
4
1 2 3 4
5
1 2 3 5 6
输出
复制
1 2 3
备注:
1 \le n,m\le 10000001≤n,m≤1000000
INTMIN \le val \le INTMAXINTMIN≤val≤INTMAX
 */

import java.util.*;

public class Main {
    static int firsListCount;
    static int secondListCount;
    static LinkedList<Integer> firstList;
    static LinkedList<Integer> secondList;
    static Scanner scanner;

    public static void main(String strs[]) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        while (!firstList.isEmpty() && !secondList.isEmpty()) {
            int firstListTop = firstList.peek();
            int secondListTop = secondList.peek();
            if (firstListTop < secondListTop) {
                firstList.pop();
            } else if (firstListTop > secondListTop) {
                secondList.pop();
            } else {
                System.out.print(firstListTop + " ");
                secondList.pop();
            }
        }
    }

    private static void init() {
        scanner = new Scanner(System.in);
        firstList = new LinkedList<>();
        secondList = new LinkedList<>();
        firsListCount = scanner.nextInt();
        for (int i = 0; i < firsListCount; i++) {
            firstList.offer(scanner.nextInt());
        }
        secondListCount = scanner.nextInt();
        for (int i = 0; i < secondListCount; i++) {
            secondList.offer(scanner.nextInt());
        }
    }
}