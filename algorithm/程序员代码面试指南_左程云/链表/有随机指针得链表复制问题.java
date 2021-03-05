/*
有随机指针得链表复制问题
 */

import java.util.*;

public class Main {
    public static void main(String[] args) {
        myCheck();
    }

    static class Node {
        Node next;
        Node random;
        int value;

        Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            Node cur = this;
            while (cur != null) {
                stringBuffer.append(cur.value + "(" + (cur.random == null ? "" : cur.random.value) + ")" + " ");
                cur = cur.next;
            }
            return stringBuffer.toString();
        }
    }

    private static void myCheck() {
        boolean flag = true;
        for (int i = 0; i < 1000; i++) {
            Node randomNode = randomNode();
            Node copyNode = copyNode(randomNode);
            if (!isSame(randomNode, copyNode)) {
                System.out.println(randomNode.toString());
                System.out.println(copyNode.toString());
                System.out.println("gg");
                flag = false;
                break;
            }
        }
        if (flag) {
            System.out.println("nice!");
        }

    }

    private static boolean isSame(Node node1, Node node2) {
        boolean result = true;
        Node cur1 = node1;
        Node cur2 = node2;

        while (cur1 != null) {
            if (cur1.value != cur2.value) {
                return false;
            }
            if (cur1.random.value != cur2.random.value) {
                return false;
            }

            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return result;
    }

    private static Node copyNode(Node randomNode) {
        Node result;

        Node head;
        Node cur = randomNode;
        while (cur != null) {
            Node temp = new Node(cur.value);
            Node tempNext = cur.next;
            cur.next = temp;
            temp.next = tempNext;
            cur = tempNext;
        }
        head = randomNode.next;

        cur = randomNode;
        while (cur != null) {
            Node next = cur.next;
            next.random = cur.random.next;

            cur = cur.next.next;
        }

        cur = randomNode;
        while (cur != null && cur.next.next != null) {
            Node pre = cur;
            Node next = cur.next;

            cur = cur.next.next;
            pre.next = cur;
            next.next = cur.next;
        }
        cur.next = null;
        result = head;

        cur = randomNode;
        return result;
    }

    private static Node randomNode() {
        Node result;
        int maxSize = 10;
        int maxNum = 1000;
        int size = new Random().nextInt(maxSize) + 1;
        Node[] array = new Node[size];
        for (int i = 0; i < size; i++) {
            array[i] = new Node(new Random().nextInt(maxNum) + 1);
        }
        Node head = array[0];
        Node cur = head;
        int index = 1;
        while (index < size) {
            cur.next = array[index];
            cur = cur.next;
            index++;
        }
        cur = head;
        while (cur != null) {
            cur.random = array[new Random().nextInt(array.length)];
            cur = cur.next;
        }
        result = head;
        return result;
    }
}