/*
题目描述
给定两个升序的单链表的头节点 head1 和 head2，请合并两个升序链表， 合并后的链表依然升序，并返回合并后链表的头节点。
输入描述:
两个升序的单链表的头节点 head1 和 head2
输出描述:
在给定的函数内返回新链表的头指针。
示例1
输入
复制
5
1 2 3 4 5
6
7 8 9 10 11 12
输出
复制
1 2 3 4 5 7 8 9 10 11 12
备注:
保证链表的长度不大于1000000
 */

import java.util.*;

public class Main {
    static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    static Scanner scanner;
    static int listLength1;
    static int listLength2;
    static Node head1;
    static Node head2;
    static Node result;

    public static void main(String args[]) {
        init();
        result();
        deInit();

    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        merge();
        printResult();
    }

    private static void printResult() {
        result = result.next;
        while (result != null) {
            System.out.print(result.value + " ");
            result = result.next;
        }
    }

    private static void merge() {
        result = new Node(0);
        Node resultEnd = result;
        Node begin1 = head1;
        Node begin2 = head2;
        while (begin1 != null || begin2 != null) {
            if (begin1 == null) {
                resultEnd.next = begin2;
                break;
            } else if (begin2 == null) {
                resultEnd.next = begin1;
                break;
            } else {
                if (begin1.value < begin2.value) {
                    resultEnd.next = begin1;
                    resultEnd = resultEnd.next;
                    begin1 = begin1.next;
                } else {
                    resultEnd.next = begin2;
                    resultEnd = resultEnd.next;
                    begin2 = begin2.next;
                }
            }
        }
    }

    private static void init() {
        scanner = new Scanner(System.in);
        listLength1 = scanner.nextInt();
        head1 = new Node(scanner.nextInt());
        Node pre = head1;
        for (int i = 1; i < listLength1; i++) {
            Node temp = new Node(scanner.nextInt());
            pre.next = temp;
            pre = temp;
        }
        listLength2 = scanner.nextInt();
        head2 = new Node(scanner.nextInt());
        pre = head2;
        for (int i = 1; i < listLength2; i++) {
            Node temp = new Node(scanner.nextInt());
            pre.next = temp;
            pre = temp;
        }
    }
}