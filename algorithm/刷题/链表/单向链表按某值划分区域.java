/*
题目描述
给定一个链表，再给定一个整数 pivot，请将链表调整为左部分都是值小于 pivot 的节点，中间部分都是值等于 pivot 的节点， 右边部分都是大于 pivot 的节点。
除此之外，对调整后的节点顺序没有更多要求。
输入描述:
第一行两个整数 n 和 pivot，n 表示链表的长度。

第二行 n 个整数 ai 表示链表的节点。
输出描述:
请在给定的函数内返回链表的头指针。
示例1
输入
复制
5 3
9 0 4 5 1
输出
复制
1 0 4 5 9
备注:
1 \le n \le 10000001≤n≤1000000
-1000000 \leq a_i, pivot \leq 1000000−1000000≤a
i
​
 ,pivot≤1000000
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
    static int base;
    static int listLength;
    static Node head;

    static Node listWithSmallerValue;
    static Node listWithEqualValue;
    static Node listWithBiggerValue;

    public static void main(String args[]) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        initList();
        head = head(listWithSmallerValue, listWithEqualValue, listWithBiggerValue);
        linkNodes(listWithSmallerValue, listWithEqualValue, listWithBiggerValue);

        printResesult();

    }

    private static void printResesult() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
    }

    private static void linkNodes(Node listWithSmallerValue, Node listWithEqualValue, Node listWithBiggerValue) {
        if (listWithSmallerValue != null) {
            while (listWithSmallerValue.next != null) {
                listWithSmallerValue = listWithSmallerValue.next;
            }
            listWithSmallerValue.next = listWithEqualValue == null ? listWithBiggerValue : listWithEqualValue;
        }
        if (listWithEqualValue != null) {
            while (listWithEqualValue.next != null) {
                listWithEqualValue = listWithEqualValue.next;
            }
            listWithEqualValue.next = listWithBiggerValue;
        }
    }

    private static Node head(Node listWithSmallerValue, Node listWithEqualValue, Node listWithBiggerValue) {
        Node result;
        if (listWithSmallerValue != null) {
            result = listWithSmallerValue;
        } else if (listWithEqualValue != null) {
            result = listWithEqualValue;
        } else {
            result = listWithBiggerValue;
        }
        return result;
    }

    private static void initList() {
        Node temp = head;
        Node tempListWithSmallerValue = null;
        Node tempListWithEqualValue = null;
        Node tempListWithBiggerValue = null;
        while (temp != null) {
            if (temp.value < base) {
                if (tempListWithSmallerValue == null) {
                    tempListWithSmallerValue = new Node(temp.value);
                    listWithSmallerValue = tempListWithSmallerValue;
                } else {
                    tempListWithSmallerValue.next = new Node(temp.value);
                    tempListWithSmallerValue = tempListWithSmallerValue.next;
                }
            } else if (temp.value == base) {
                if (tempListWithEqualValue == null) {
                    tempListWithEqualValue = new Node(temp.value);
                    listWithEqualValue = tempListWithEqualValue;
                } else {
                    tempListWithEqualValue.next = new Node(temp.value);
                    tempListWithEqualValue = tempListWithEqualValue.next;
                }
            } else {
                if (tempListWithBiggerValue == null) {
                    tempListWithBiggerValue = new Node(temp.value);
                    listWithBiggerValue = tempListWithBiggerValue;
                } else {
                    tempListWithBiggerValue.next = new Node(temp.value);
                    tempListWithBiggerValue = tempListWithBiggerValue.next;
                }
            }
            temp = temp.next;
        }
    }

    private static void init() {
        scanner = new Scanner(System.in);
        listLength = scanner.nextInt();
        base = scanner.nextInt();


        head = new Node(scanner.nextInt());
        Node pre = head;
        Node temp;
        for (int i = 1; i < listLength; i++) {
            temp = new Node(scanner.nextInt());
            pre.next = temp;
            pre = temp;
        }
    }
}