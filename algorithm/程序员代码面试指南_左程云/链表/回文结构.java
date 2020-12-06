/*
题目描述
给定一个链表，请判断该链表是否为回文结构。
输入描述:
n 表示链表的长度

ai 表示链表的各个节点的值。
输出描述:
如果为回文结构输出 "true" , 否则输出 "false"。
示例1
输入
复制
4
1 2 2 1
输出
复制
true
备注:
1 \le n \le 10000001≤n≤1000000
-1000000 \le a_i \le 1000000−1000000≤a
i
​
 ≤1000000
 */

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
        System.out.println(isListTNT());
    }

    private static boolean isListTNT() {
        boolean result = true;
        Stack<Integer> stack = new Stack<>();
        int numCount2PutIntoStack = listLength / 2;

        Node node2PushStack = head;
        for (int i = 0; i < (numCount2PutIntoStack % 2 == 0 ? numCount2PutIntoStack : numCount2PutIntoStack + 1); i++) {
            node2PushStack = node2PushStack.next;
        }
        while (node2PushStack != null) {
            stack.push(node2PushStack.value);
            node2PushStack = node2PushStack.next;
        }

        Node startCompNode = head;
        while (!stack.isEmpty()) {
            if (!stack.pop().equals(startCompNode.value)) {
                result = false;
                break;
            }
            startCompNode = startCompNode.next;
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