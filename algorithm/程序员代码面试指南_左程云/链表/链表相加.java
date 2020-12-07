/*
题目描述
假设链表中每一个节点的值都在 0 - 9 之间，那么链表整体就可以代表一个整数。
给定两个这种链表，请生成代表两个整数相加值的结果链表。
例如：链表 1 为 9->3->7，链表 2 为 6->3，最后生成新的结果链表为 1->0->0->0。
输入描述:
第一行两个整数 n 和 m，分别表示两个链表的长度。

第二行 n 个整数 ai 表示第一个链表的节点。

第三行 m 个整数 bi 表示第二个链表的节点。
输出描述:
输出一行整数表示结果链表。
示例1
输入
复制
3 2
9 3 7
6 3
输出
复制
1 0 0 0
备注:
1 \le n, m \le 10000001≤n,m≤1000000
0 \le a_i, b_i \le 90≤a
i
​
 ,b
i
​
 ≤9
a_1, b_1 != 0a
1
​
 ,b
1
​
 !=0
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
    static int firstListLength;
    static int secondListLength;
    static Node firstHead;
    static Node secondtHead;
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
        reverseAndAdd();
        printResult();
    }

    private static void printResult() {
        result = reverseList(result);
        Node temp = result;
        while (temp != null) {
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
    }

    private static void reverseAndAdd() {
        reverse();
        add();
    }

    private static void add() {
        int maxLength = Math.max(firstListLength, secondListLength);
        Node tempFirstHead = firstHead;
        Node tempSecondHead = secondtHead;
        Node pre = new Node(0);
        result = pre;
        int acc = 0;
        for (int i = 0; i < maxLength; i++) {
            int firstValue = 0;
            int secondValue = 0;
            if (tempFirstHead != null) {
                firstValue = tempFirstHead.value;
                tempFirstHead = tempFirstHead.next;
            }
            if (tempSecondHead != null) {
                secondValue = tempSecondHead.value;
                tempSecondHead = tempSecondHead.next;
            }
            int value = 0;
            value = firstValue + secondValue + acc;
            if (acc != 0) {
                acc = 0;
            }
            if (value >= 10) {
                acc = 1;
            }


            Node temp = new Node(value >= 10 ? value - 10 : value);
            pre.next = temp;
            pre = temp;
        }
        if (acc != 0) {
            pre.next = new Node(acc);
        }
        result = result.next;
    }

    private static void reverse() {
        firstHead = reverseList(firstHead);
        secondtHead = reverseList(secondtHead);
    }

    private static Node reverseList(Node firstHead) {
        Node result;
        Node next = firstHead.next;
        Node pre = null;
        while (next != null) {
            firstHead.next = pre;
            pre = firstHead;
            firstHead = next;
            next = next.next;
        }
        firstHead.next = pre;
        result = firstHead;
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        firstListLength = scanner.nextInt();
        secondListLength = scanner.nextInt();

        Node pre = new Node(scanner.nextInt());
        firstHead = pre;
        for (int i = 1; i < firstListLength; i++) {
            Node temp = new Node(scanner.nextInt());
            pre.next = temp;
            pre = temp;
        }
        pre = new Node(scanner.nextInt());
        secondtHead = pre;
        for (int i = 1; i < secondListLength; i++) {
            Node temp = new Node(scanner.nextInt());
            pre.next = temp;
            pre = temp;
        }
    }
}