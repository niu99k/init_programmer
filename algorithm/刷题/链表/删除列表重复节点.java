/*
题目描述
给定一个无序链表，删除其中值重复出现的节点(保留当中顺序遍历第一个出现的节点)。
输入描述:
第一行一个整数 n，表示单链表的节点数量。
第二行 n 个整数表示单链表的节点的值。
输出描述:
顺序输出单链表每个节点的值。
示例1
输入
复制
5
1 3 2 3 1
输出
复制
1 3 2
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
        solveWithMap();
        printResult();
    }

    private static void printResult() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
    }

    private static void solveWithMap() {
        Map<Integer, Integer> nodeMap = new HashMap<>();
        Node temp = head;
        Node pre = temp;
        while (temp != null) {
            if (!nodeMap.containsKey(temp.value)) {
                nodeMap.put(temp.value, 0);
                pre = temp;
                temp = temp.next;
            } else {
                Node next = temp.next;
                pre.next = next;
                temp = next;
            }
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
    }
}