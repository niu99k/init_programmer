/*
题目描述
据说著名犹太历史学家 Josephus 有过以下故事：在罗马人占领乔塔帕特后，39 个犹太人与 Josephus 及他的朋友躲到一个洞中，
39 个犹太人决定宁愿死也不要被敌人抓到，于是决定了一种自杀方式，41 个人排成一个圆圈，由第 1 个人开始报数，报数到 3 的人就自杀，然后再由下一个人重新报 1，报数到 3 的人再自杀，这样依次下去，
直到剩下最后一个人时，那个人可以自由选择自己的命运。这就是著名的约瑟夫问题。现在请用单向环形链表得出最终存活的人的编号。

输入描述:
一行两个整数 n 和 m， n 表示环形链表的长度， m 表示每次报数到 m 就自杀。
输出描述:
输出最后存活下来的人编号(编号从1开始到n)
示例1
输入
复制
5 2
输出
复制
3
备注:
1 \le n, m\le 100001≤n,m≤10000
 */

import java.util.*;

public class Main {
    static class Node {
        int value;
        Node next;
        boolean isSearched = false;
    }

    static int listLength;
    static int stepCount;
    static Scanner scanner;
    static Node head;

    public static void main(String strs[]) {
        init();
        result();
        printResult();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void printResult() {
        Node temp = head;
        while (temp.isSearched) {
            temp = temp.next;
        }
        System.out.println(temp.value);
    }

    private static void result() {
        int searchedCount = 0;
        Node temp = head;
        while (searchedCount < listLength - 1) {
            for (int i = 0; i < stepCount - 1; i++) {
                while (temp.isSearched) {
                    temp = temp.next;
                }
                temp = temp.next;
            }
            while (temp.isSearched) {
                temp = temp.next;
            }
            temp.isSearched = true;
            temp = temp.next;
            searchedCount++;
        }
    }

    private static void init() {
        scanner = new Scanner(System.in);
        head = new Node();

        listLength = scanner.nextInt();
        stepCount = scanner.nextInt();
        Node pre = head;
        head.value = 1;
        Node temp = head;
        for (int i = 2; i <= listLength; i++) {
            temp = new Node();
            temp.value = i;
            if (pre != null) {
                pre.next = temp;
            }
            pre = temp;
        }
        temp.next = head;
    }
}