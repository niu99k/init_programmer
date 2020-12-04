/*
题目描述
给定一个链表，实现删除链表第 K 个节点的函数。
输入描述:
n 表示链表的长度。

m 表示删除链表第几个节点。

val 表示链表节点的值。
输出描述:
在给定的函数中返回链表的头指针。
示例1
输入
复制
5 3
1 2 3 4 5
输出
复制
1 2 4 5
备注:
1 \le K \le n \le 10000001≤K≤n≤1000000
-1000000 \le val \le 1000000−1000000≤val≤1000000
 */

import java.util.*;

public class Main {
    static class Node {
        int value;
        Node next;
    }

    static Scanner scanner;
    static int listLength;
    static int deleteIndex;
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
        Node temp = head.next;
        while (temp != null) {
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
    }

    private static void result() {
        if (deleteIndex <= listLength) {
            Node temp = head;
            for (int i = 0; i < deleteIndex - 1; i++) {
                temp = temp.next;
            }
            temp.next = temp.next.next;
        }

    }

    private static void init() {
        scanner = new Scanner(System.in);
        head = new Node();
        listLength = scanner.nextInt();
        deleteIndex = scanner.nextInt();
        Node temp = head;
        for (int i = 0; i < listLength; i++) {
            temp.next = new Node();
            temp = temp.next;
            temp.value = scanner.nextInt();
        }
    }
}