/*
题目描述
实现反转单向链表和双向链表的函数。
如 1->2->3 反转后变成 3->2->1。
输入描述:
第一行一个整数 n，表示单链表的长度。

第二行 n 个整数 val 表示单链表的各个节点。

第三行一个整数 m，表示双链表的长度。

第四行 m 个整数 val 表示双链表的各个节点。
输出描述:
在给定的函数内返回相应链表的头指针。
示例1
输入
复制
3
1 2 3
4
1 2 3 4
输出
复制
3 2 1
4 3 2 1
备注:
1 \le n,m\le10000001≤n,m≤1000000
-1000000 \le val \le 1000000−1000000≤val≤1000000
 */

import java.util.*;

public class Main {
    static class SlNode {
        int value;
        SlNode next;
    }

    static class DlNode {
        int value;
        DlNode next;
        DlNode pre;
    }

    static Scanner scanner;
    static int slListLength;
    static int dlListLength;
    static SlNode slHead;
    static DlNode dlNode;

    public static void main(String strs[]) {
        init();
        result();
        printResult();
        deInit();
    }

    private static void printResult() {
        printResult4Sl();
        printResult4Dl();
    }

    private static void printResult4Dl() {
        while (dlNode != null) {
            System.out.print(dlNode.value + " ");
            dlNode = dlNode.next;
        }
        System.out.println();
    }

    private static void result() {
        result4Sl();
        result4Dl();
    }

    private static void result4Dl() {
        DlNode temp = dlNode;
        while (temp != null) {
            DlNode next = temp.next;
            DlNode pre = temp.pre;
            temp.next = pre;
            temp.pre = next;
            if (next == null) {
                dlNode = temp;
            }
            temp = next;
        }
    }

    private static void init() {
        scanner = new Scanner(System.in);
        init4Sl();
        init4Dl();
    }

    private static void init4Dl() {
        dlListLength = scanner.nextInt();
        dlNode = new DlNode();

        DlNode temp;
        DlNode pre = dlNode;
        for (int i = 0; i < dlListLength; i++) {
            temp = new DlNode();
            temp.value = scanner.nextInt();
            if (temp != null) {
                temp.pre = pre;
            }
            if (pre != null) {
                pre.next = temp;
            }
            pre = temp;
            temp = temp.next;
        }
        DlNode next = dlNode.next;
        dlNode.next = null;
        dlNode = next;
        dlNode.pre = null;
    }

    private static void deInit() {
        scanner.close();
    }

    private static void printResult4Sl() {
        SlNode temp = slHead;
        while (temp != null) {
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    private static void result4Sl() {
        if (slHead.next == null) {
            return;
        } else {
            SlNode pre = slHead;
            slHead = slHead.next;
            pre.next = null;
            while (slHead.next != null) {
                SlNode tempPre = pre;
                SlNode tempSlNode = slHead;
                pre = slHead;
                slHead = slHead.next;
                tempSlNode.next = tempPre;
            }
            slHead.next = pre;
        }
    }

    private static void init4Sl() {
        slHead = new SlNode();
        slListLength = scanner.nextInt();

        SlNode temp = slHead;
        SlNode pre = slHead;
        for (int i = 0; i < slListLength; i++) {
            temp = new SlNode();
            temp.value = scanner.nextInt();
            pre.next = temp;
            pre = temp;
        }
        slHead = slHead.next;
    }
}