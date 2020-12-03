/*
在链表中删除倒数第K个节点

输入描述:
n 表示链表的长度。
val 表示链表中节点的值。
输出描述:
在给定的函数内返回链表的头指针。
示例1
输入
复制
5 4
1 2 3 4 5
输出
复制
1 3 4 5
备注:
1 \le K \le n \le 10000001≤K≤n≤1000000
-1000000 \le val \le 1000000−1000000≤val≤1000000
 */

import java.util.*;

public class Main {
    static class Node {
        int value;
        Node next;

        public String toString() {
            if (next == null) {
                return value + "";
            } else {
                return value + " " + next.value;
            }
        }
    }

    static int listLength;
    static int deleteReverseIndex;
    static Scanner scanner;
    static Node node;

    public static void main(String strs[]) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        if (deleteReverseIndex > listLength) {
            Node temp = node.next;
            while (temp != null) {
                System.out.print(temp.value + " ");
                temp = temp.next;
            }
        } else {
            int deleteIndex = listLength - deleteReverseIndex;
            Node pre = node;
            for (int i = 0; i < deleteIndex; i++) {
                pre = pre.next;
            }
            pre.next = pre.next.next;
            Node temp = node.next;
            while (temp != null) {
                System.out.print(temp.value + " ");
                temp = temp.next;
            }
        }

    }

    private static void init() {
        node = new Node();
        scanner = new Scanner(System.in);
        listLength = scanner.nextInt();
        deleteReverseIndex = scanner.nextInt();
        Node temp = node;
        for (int i = 0; i < listLength; i++) {
            Node newNode = new Node();
            newNode.value = scanner.nextInt();
            temp.next = newNode;
            temp = newNode;
        }
    }
}