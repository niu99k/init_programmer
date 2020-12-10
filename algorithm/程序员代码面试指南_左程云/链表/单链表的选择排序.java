/*
题目描述
给定一个无序单链表，实现单链表的选择排序(按升序排序)。
输入描述:
第一行一个整数 n，表示单链表的节点数量。
第二行 n 个整数 val 表示单链表的各个节点。
输出描述:
在给出的函数内返回给定链表的头指针。
示例1
输入
复制
5
1 3 2 4 5

6
-426572 -406609 724427 -157789 -132713 720732

5
-426572 -406609 724427 -157789 -869091

3
347325 80573 -869091
输出
复制
1 2 3 4 5
备注:
1 \leq n \leq 10000001≤n≤1000000
-1000000 \leq val \leq 1000000−1000000≤val≤1000000


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

    static class NodeAndPre {
        Node node;
        Node pre;
    }

    static Scanner scanner;
    static int listLength;
    static Node head;
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
        sortBySelection();
        printResult();
    }

    private static void printResult() {
        Node temp = result.next;
        while (temp != null) {
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
    }

    private static void sortBySelection() {
        Node tempBegin = head;
        Node pre = null;
        Node temp = result;
        while (tempBegin != null) {
            NodeAndPre smallNodeAndPre = smallestNode(tempBegin, pre);
            Node small = smallNodeAndPre.node;
            Node smallPre = smallNodeAndPre.pre;
            if (small == tempBegin) {
                tempBegin = tempBegin.next;
            } else {
                smallPre.next = small.next;
            }
            temp.next = small;
            temp = temp.next;
        }
    }

    private static void deleteNode(Node tempBegin, Node small) {
        Node pre = null;
        while (true) {
            if (tempBegin.value == small.value) {
                pre.next = tempBegin.next;
                tempBegin.next = null;
                break;
            }
            pre = tempBegin;
            tempBegin = tempBegin.next;
        }
    }

    private static NodeAndPre smallestNode(Node tempBegin, Node tempPre) {
        NodeAndPre result = new NodeAndPre();
        Node small = tempBegin;
        Node pre = tempPre;
        Node smallPre = tempPre;
        while (tempBegin != null) {
            if (tempBegin.value < small.value) {
                small = tempBegin;
                smallPre = pre;
            }
            pre = tempBegin;
            tempBegin = tempBegin.next;
        }
        result.node = small;
        result.pre = smallPre;
        return result;
    }

    private static Node end() {
        Node result;
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        result = temp;
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        listLength = scanner.nextInt();
        head = new Node(scanner.nextInt());
        result = new Node(0);
        Node pre = head;
        for (int i = 1; i < listLength; i++) {
            Node temp = new Node(scanner.nextInt());
            pre.next = temp;
            pre = temp;
        }
    }
}