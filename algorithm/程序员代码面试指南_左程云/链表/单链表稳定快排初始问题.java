/*
链表实现快排原始问题
 */

import java.util.*;

public class Main {
    static class Node {
        Node next;
        int value;

        Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            Node cur = this;
            while (cur != null) {
                stringBuffer.append(cur.value + " ");
                cur = cur.next;
            }
            return stringBuffer.toString();
        }
    }

    public static void main(String[] args) {
        myCheck();
    }

    private static void myCheck() {
        boolean flag = true;
        for (int i = 0; i < 1000; i++) {
            int randomNum = new Random().nextInt(1000) + 1;
            Node node = randomNode();
            Node result2 = solve2(node, randomNum);

            if (!isResultCorrect(result2, randomNum)) {
                System.out.println(node.toString());
                System.out.println(result2.toString());
                System.out.println("gg");
                flag = false;
                break;
            }

        }
        if (flag) {
            System.out.println("nice!");
        }
    }

    private static boolean isResultCorrect(Node result2, int randomNum) {
        boolean result = true;
        boolean equalFlag = false;
        boolean biggerFlag = false;
        Node cur = result2;
        while (cur != null) {
            if (cur.value < randomNum) {
                if (equalFlag || biggerFlag) {
                    result = false;
                    break;
                }
            } else if (cur.value == randomNum) {
                if (biggerFlag) {
                    result = false;
                    break;
                } else {
                    equalFlag = true;
                }
            } else {
                equalFlag = true;
                biggerFlag = true;
            }
            cur = cur.next;
        }
        return result;
    }

    private static Node solve2(Node node, int randomNum) {
        Node result = new Node(node.value);
        Node begin1 = null;
        Node begin2 = null;
        Node begin3 = null;
        Node end1 = null;
        Node end2 = null;
        Node end3 = null;
        Node cur1 = null;
        Node cur2 = null;
        Node cur3 = null;

        Node cur = node;
        while (cur != null) {
            if (cur.value < randomNum) {
                if (begin1 == null) {
                    begin1 = new Node(cur.value);
                    cur1 = begin1;
                    end1 = cur1;
                } else {
                    Node temp = new Node(cur.value);
                    cur1.next = temp;
                    cur1 = temp;
                    end1 = cur1;
                }
            } else if (cur.value == randomNum) {
                if (begin2 == null) {
                    begin2 = new Node(cur.value);
                    cur2 = begin2;
                    end2 = cur2;
                } else {
                    Node temp = new Node(cur.value);
                    cur2.next = temp;
                    cur2 = temp;
                    end2 = cur2;
                }
            } else {
                if (begin3 == null) {
                    begin3 = new Node(cur.value);
                    cur3 = begin3;
                    end3 = cur3;
                } else {
                    Node temp = new Node(cur.value);
                    cur3.next = temp;
                    cur3 = temp;
                    end3 = cur3;
                }
            }
            cur = cur.next;
        }
        Node begin;
        if (begin1 != null) {
            begin = begin1;
            if (begin2 != null) {
                end1.next = begin2;
                if (begin3 != null) {
                    end2.next = begin3;
                }
            } else if (begin3 != null) {
                end1.next = begin3;
            }
        } else if (begin2 != null) {
            begin = begin2;
            if (begin3 != null) {
                end2.next = begin3;
            }
        } else {
            begin = begin3;
        }
        result = begin;
        return result;
    }

    private static Node randomNode() {
        Node result;
        int maxSize = 1000;
        int maxNum = 1000;
        Random random = new Random();
        int size = random.nextInt(maxNum) + 1;
        Node head = new Node(random.nextInt(maxNum) + 1);
        Node cur = head;
        for (int i = 1; i < size; i++) {
            Node temp = new Node(random.nextInt(maxNum) + 1);
            cur.next = temp;
            cur = temp;
        }
        result = head;
        return result;
    }
}