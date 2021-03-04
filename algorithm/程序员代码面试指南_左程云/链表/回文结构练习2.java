/*
回文单链表
 */

import java.util.*;

public class Main {
    public static void main(String[] args) {
        mycheck();
    }

    static class Node {
        Node next;
        int value;

        Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            String result;
            StringBuffer stringBuffer = new StringBuffer();
            Node temp = this;
            while (temp != null) {
                stringBuffer.append(temp.value + " ");
                temp = temp.next;
            }
            result = stringBuffer.toString();
            return result;
        }
    }

    private static void mycheck() {
        boolean flag = true;
        for (int i = 0; i < 1000; i++) {
            Node head;
            head = palindrom(new Random().nextBoolean());
            boolean result1 = solve1(head);
            boolean result2 = solve2(head);
            if (result1 != result2) {
                System.out.println(head.toString());
                System.out.println(result1);
                System.out.println(result2);
                System.out.println("gg");
                flag = false;
                break;
            }
        }
        if (flag) {
            System.out.println("nice!");
        }
    }

    private static boolean solve2(Node head) {
        boolean result;
        int len = 0;
        Node temp = head;
        while (temp != null) {
            temp = temp.next;
            len++;
        }
        if (len == 1) {
            result = true;
        } else {
            Node slow = head;
            Node fast = head;
            Node leftPartEnd = head;
            if (len % 2 == 0) {
                while (fast != null) {
                    leftPartEnd = slow;
                    slow = slow.next;
                    fast = fast.next.next;
                }
                Node cur = slow;
                Node pre = null;
                while (cur.next != null) {
                    Node next = cur.next;
                    cur.next = pre;
                    pre = cur;
                    cur = next;
                }
                cur.next = pre;

                Node end = cur;
                result = true;
                Node left = head;
                while (cur != null) {
                    if (left.value != cur.value) {
                        result = false;
                        break;
                    }
                    left = left.next;
                    cur = cur.next;
                }

                cur = end;
                pre = null;
                while (cur.next != null) {
                    Node next = cur.next;
                    cur.next = pre;
                    pre = cur;
                    cur = next;
                }
                cur.next = pre;
                leftPartEnd.next = cur;
            } else {
                while (fast.next != null) {
                    leftPartEnd = slow;
                    slow = slow.next;
                    fast = fast.next.next;
                }
                leftPartEnd = leftPartEnd.next;
                Node cur = slow.next;
                Node pre = null;
                while (cur.next != null) {
                    Node next = cur.next;
                    cur.next = pre;
                    pre = cur;
                    cur = next;
                }
                cur.next = pre;

                Node end = cur;
                Node left = head;
                result = true;
                while (cur != null) {
                    if (left.value != cur.value) {
                        result = false;
                        break;
                    }
                    left = left.next;
                    cur = cur.next;
                }

                cur = end;
                pre = null;
                while (cur.next != null) {
                    Node next = cur.next;
                    cur.next = pre;
                    pre = cur;
                    cur = next;
                }
                cur.next = pre;
                leftPartEnd.next = cur;
            }
        }
        return result;
    }

    private static boolean solve1(Node head) {
        boolean result = true;
        Stack<Node> stack = new Stack<>();
        Node begin = head;
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        while (!stack.isEmpty()) {
            if (begin.value != stack.pop().value) {
                result = false;
                break;
            } else {
                begin = begin.next;
            }
        }
        return result;
    }

    private static Node palindrom(boolean nextBoolean) {
        Node result;
        final int maxSize = 10;
        final int maxNum = 10;
        final int size = (int) (Math.random() * maxSize) + 1;
        int[] arrayTemp = new int[size];
        for (int i = 0; i < size; i++) {
            arrayTemp[i] = (int) (Math.random() * maxNum) + 1;
        }
        if (nextBoolean) {
            if (size % 2 == 0) {
                Node head = new Node(arrayTemp[0]);
                result = head;
                Node cur = head;
                for (int i = 1; i < arrayTemp.length; i++) {
                    Node temp = new Node(arrayTemp[i]);
                    cur.next = temp;
                    cur = temp;
                }
                for (int i = arrayTemp.length - 1; i >= 0; i--) {
                    Node temp = new Node(arrayTemp[i]);
                    cur.next = temp;
                    cur = temp;
                }
            } else {
                Node head = new Node(arrayTemp[0]);
                result = head;
                Node cur = head;
                for (int i = 1; i < arrayTemp.length; i++) {
                    Node temp = new Node(arrayTemp[i]);
                    cur.next = temp;
                    cur = temp;
                }
                for (int i = arrayTemp.length - 2; i >= 0; i--) {
                    Node temp = new Node(arrayTemp[i]);
                    cur.next = temp;
                    cur = temp;
                }
            }
        } else {
            Node head = new Node(arrayTemp[0]);
            result = head;
            Node cur = head;
            for (int i = 1; i < arrayTemp.length; i++) {
                Node temp = new Node(arrayTemp[i]);
                cur.next = temp;
                cur = temp;
            }
        }
        return result;
    }
}