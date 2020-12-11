/*
题目描述
给定一个单链表的头部节点 head，链表长度为 N，如果 N 是偶数，那么前 N / 2 个节点算作左半区，后 N / 2 个节点算作右半区；
如果 N 为奇数，那么前 N / 2 个节点算作左半区，后 N / 2 + 1个节点算作右半区。
左半区从左到右依次记为 L1->L2->...，右半区从左到右依次记为 R1->R2->...，请将单链表调整成 L1->R1->L2->R2->... 的形式。
输入描述:
单链表的头节点 head。
输出描述:
在给定的函数内返回链表的头指针。
示例1
输入
复制
6
1 2 3 4 5 6
输出
复制
1 4 2 5 3 6
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
    static int listLength;
    static Node head;

    public static void main(String args[]) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        Node result = merge();
        printResult(result);
    }

    private static void printResult(Node result) {
        Node temp = result;
        for (int i = 0; i < listLength; i++) {
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
    }

    private static Node merge() {
        Node result;
        Node head2 = head2();
        result = merge(head, head2);
        return result;
    }

    private static Node merge(Node head, Node head2) {
        Node result = new Node(0);
        Node temp1 = head;
        Node temp2 = head2;
        Node tempResult = result;
        for (int i = 0; i < listLength / 2; i++) {
            tempResult.next = temp1;
            tempResult = tempResult.next;
            temp1 = temp1.next;
            tempResult.next = temp2;
            tempResult = tempResult.next;
            temp2 = temp2.next;
        }
        if (temp2 != null) {
            tempResult.next = temp2;
        }
        result = result.next;
        return result;
    }

    private static Node head2() {
        Node result;
        Node temp = head;
        for (int i = 0; i < listLength / 2; i++) {
            temp = temp.next;
        }
        result = temp;
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        listLength = scanner.nextInt();
        head = new Node(scanner.nextInt());
        Node pre = head;
        for (int i = 1; i < listLength; i++) {
            Node temp = new Node(scanner.nextInt());
            pre.next = temp;
            pre = temp;
        }
    }
}