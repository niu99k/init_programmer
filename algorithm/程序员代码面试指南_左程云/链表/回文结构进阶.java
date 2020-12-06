/*
题目描述
给定一个链表，请判断该链表是否为回文结构。O(1)空间复杂度
输入描述:
n 表示链表的长度。
ai 表示链表节点的值
输出描述:
如果为回文结构输出 "true" , 否则输出 "false"。
示例1
输入
复制
5
1 2 3 2 1
输出
复制
true
备注:
1 \le n \le 20000001≤n≤2000000
-1000000 \le a_i \le 1000000−1000000≤a
i
​
 ≤1000000
* */

import java.util.*;

public class Main {
    static class Node {
        int value;
        Node next;

        public Node(int value) {
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
        Node endNode = headNodeWithreversePostPart();
        System.out.println(comparePrePartAndPostPart(head, endNode));
    }

    private static boolean comparePrePartAndPostPart(Node head, Node endNode) {
        boolean result = true;
        int half = listLength / 2;
        if (listLength % 2 != 0) {
            for (int i = 0; i < half; i++) {
                if (head.value != endNode.value) {
                    result = false;
                    break;
                }
                head = head.next;
                endNode = endNode.next;
            }
        } else {
            for (int i = 0; i < half - 1; i++) {
                if (head.value != endNode.value) {
                    result = false;
                    break;
                }
                head = head.next;
                endNode = endNode.next;
            }
        }
        return result;
    }

    private static Node headNodeWithreversePostPart() {
        Node result;
        int half = listLength / 2;
        Node begin = head;
        if (listLength % 2 == 0) {
            for (int i = 0; i < half - 1; i++) {
                begin = begin.next;
            }
            Node pre = begin;
            begin = begin.next;
            pre.next = null;
            while (begin != null) {
                Node next = begin.next;
                begin.next = pre;
                pre = begin;
                begin = next;
            }
            result = pre;
        } else {
            for (int i = 0; i < half; i++) {
                begin = begin.next;
            }
            Node pre = begin;
            begin = begin.next;
            pre.next = null;
            while (begin != null) {
                Node next = begin.next;
                begin.next = pre;
                pre = begin;
                begin = next;
            }
            result = pre;
        }
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);

        listLength = scanner.nextInt();
        head = new Node(scanner.nextInt());

        Node pre = head;
        Node temp = null;
        for (int i = 1; i < listLength; i++) {
            temp = new Node(scanner.nextInt());
            pre.next = temp;
            pre = temp;
        }
    }
}