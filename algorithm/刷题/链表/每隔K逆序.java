/*
题目描述
给定一个单链表，实现一个调整单链表的函数，使得每 K 个节点之间的值逆序，如果最后不够 K 个节点一组，则不调整最后几个节点。
输入描述:
第一行一个整数 n，n 表示单链表的节点数量。

第二行 n 个整数 val 表示链表的各个节点的值。

第三行一个整数 K。
输出描述:
在给定的函数内返回链表的头指针。
示例1
输入
复制
5
1 2 3 4 5
3
输出
复制
3 2 1 4 5
备注:
1 \le K \le n \le 10000001≤K≤n≤1000000
-1000000 \le val_i \le 1000000−1000000≤val
i
​
 ≤1000000
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
    static Node head;
    static int k;
    static int listLength;

    public static void main(String args[]) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        solveWithStack();
        printResult();
    }

    private static void printResult() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
    }

    private static void solveWithStack() {
        Stack<Node> stack = new Stack<>();
        Node temp = head;
        Node tempFirst = new Node(0);
        head = tempFirst;
        while (temp != null) {
            for (int i = 0; i < k; i++) {
                if (temp == null) {
                    break;
                }
                stack.push(temp);
                temp = temp.next;
            }
            if (stack.size() == k) {
                while (!stack.isEmpty()) {
                    tempFirst.next = stack.pop();
                    tempFirst = tempFirst.next;
                }
            } else {
                Stack<Node> stack2Reverse = new Stack<>();
                while (!stack.isEmpty()) {
                    stack2Reverse.push(stack.pop());
                }
                while (!stack2Reverse.isEmpty()) {
                    tempFirst.next = stack2Reverse.pop();
                    tempFirst = tempFirst.next;
                }
            }
        }
        head = head.next;
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
        k = scanner.nextInt();
    }
}