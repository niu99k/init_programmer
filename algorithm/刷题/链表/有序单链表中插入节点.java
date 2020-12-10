/*
题目描述
一个环形单链表从头节点 head 开始不降序，同时由最后的节点指回头节点。给定这样一个环形单链表的头节点 head 和 一个整数 num， 请生成节点值为 num 的新节点，并插入到这个环形链表中，保证调整后的链表依然有序。
输入描述:
环形单链表的头节点 head 和 一个整数 num。
输出描述:
在给定的函数内返回新的环形单链表的头指针。
示例1
输入
复制
5
1 2 3 4 5
6
输出
复制
1 2 3 4 5 6
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
    static Node insertNode;
    static Node head;
    static Node preHead;

    public static void main(String args[]) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        findNotBiggerAndInsert();
        printResult();
    }

    private static void printResult() {
        Node temp = head;
        for (int i = 0; i < listLength + 1; i++) {
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
    }

    private static void findNotBiggerAndInsert() {
        Node temp = head;
        Node pre = preHead;
        boolean isAllNodeSmallerThanInsertNode = true;
        for (int i = 0; i < listLength; i++) {
            if (temp.value >= insertNode.value) {
                pre.next = insertNode;
                insertNode.next = temp;
                isAllNodeSmallerThanInsertNode = false;

                if (i == 0) {
                    head = insertNode;
                }
                break;
            }
            pre = temp;
            temp = temp.next;
        }
        if (isAllNodeSmallerThanInsertNode) {
            preHead.next = insertNode;
            insertNode.next = head;
        }
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
        pre.next = head;
        preHead = pre;

        insertNode = new Node(scanner.nextInt());
    }
}