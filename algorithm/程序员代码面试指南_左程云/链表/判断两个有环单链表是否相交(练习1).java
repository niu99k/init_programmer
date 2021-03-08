/*
判断两个有环单链表是否相交
 */

import java.util.*;

public class Main {
    static class Node {
        int value;
        Node next;
        Node firstLoopNode;
        Node firstMeetNode;

        Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
//        myCheckIsLoop();
        myCheckIsTwoNodeInLoop();
    }

    private static void myCheckIsTwoNodeInLoop() {
        boolean flag = true;
        for (int i = 0; i < 4000; i++) {
            Node[] randomNodes = randomNodes();
            Node[] result = firstMeetNode(randomNodes[0], randomNodes[1]);
            if (result[0] != randomNodes[0].firstMeetNode && result[1] != randomNodes[0].firstMeetNode) {
                flag = false;
                System.out.println("gg");
                break;
            }
        }
        if (flag) {
            System.out.println("nice!");
        }
    }

    private static Node[] firstMeetNode(Node node1, Node node2) {
        Node[] result = new Node[2];
        result[0] = null;
        result[1] = null;
        Node fistNodeInLoop1 = firstLoopNode(node1);
        Node fistNodeInLoop2 = firstLoopNode(node2);
        if (fistNodeInLoop1 == fistNodeInLoop2) {
            int len1 = len(node1, fistNodeInLoop1);
            int len2 = len(node2, fistNodeInLoop2);
            if (len1 < len2) {
                for (int i = 0; i < len2 - len1; i++) {
                    node2 = node2.next;
                }
            } else {
                for (int i = 0; i < len1 - len2; i++) {
                    node1 = node1.next;
                }
            }
            while (true) {
                if (node1 == node2) {
                    result[0] = node1;
                    result[1] = new Node(0);
                    break;
                }
                node1 = node1.next;
                node2 = node2.next;
            }
        } else {
            Node temp = fistNodeInLoop1 == null ? fistNodeInLoop2 : fistNodeInLoop1;
            temp = temp.next;
            while (true) {
                if (temp == fistNodeInLoop2) {
                    result[0] = fistNodeInLoop1;
                    result[1] = fistNodeInLoop2;
                    break;
                } else if (temp == fistNodeInLoop1) {
                    result[0] = null;
                    result[1] = null;
                    break;
                }
                temp = temp.next;
            }
        }
        return result;
    }

    private static int len(Node node, Node endNode) {
        int result = 0;
        while (node != endNode) {
            node = node.next;
            result++;
        }
        return result;
    }

    private static Node[] randomNodes() {
        Node[] result = new Node[2];
        Random random = new Random();
        int maxSize = 1000;
        int maxNum = 200;
        int size = random.nextInt(maxSize) + 1;
        Node head = null;
        HashMap<Integer, Node> map1 = new HashMap<>();
        for (int i = 0; i < size; i++) {
            if (i == 0) {
                int value = random.nextInt(maxNum);
                head = new Node(value);
                result[0] = head;
                map1.put(value, head);
            } else {
                int value = random.nextInt(maxNum);
                if (!map1.containsKey(value)) {
                    Node temp = new Node(value);
                    map1.put(value, temp);
                    head.next = temp;
                    head = temp;
                } else {
                    head.next = map1.get(value);
                    break;
                }
            }
        }
        head = null;
        HashMap<Integer, Node> map2 = new HashMap<>();
        for (int i = 0; i < size; i++) {
            if (i == 0) {
                int value = random.nextInt(maxNum);
                if (map1.containsKey(value)) {
                    head = map1.get(value);
                    result[0].firstMeetNode = head;
                    result[1] = head;
                    break;
                } else {
                    head = new Node(value);
                    result[1] = head;
                    map2.put(value, head);
                }
            } else {
                int value = random.nextInt(maxNum);
                if (map1.containsKey(value)) {
                    head.next = map1.get(value);
                    result[0].firstMeetNode = map1.get(value);
                    break;
                } else {
                    if (!map2.containsKey(value)) {
                        Node temp = new Node(value);
                        map2.put(value, temp);
                        head.next = temp;
                        head = temp;
                    } else {
                        head.next = map2.get(value);
                        break;
                    }
                }

            }
        }
        return result;
    }

    private static void myCheckIsLoop() {
        boolean flag = true;
        for (int i = 0; i < 4000; i++) {
            Node randomNode = randomNode();
            Node firstLoopNode = firstLoopNode(randomNode);
            if (firstLoopNode != randomNode.firstLoopNode) {
                flag = false;
                System.out.println("gg");
                break;
            }
        }
        if (flag) {
            System.out.println("nice!");
        }
    }

    private static Node firstLoopNode(Node head) {
        Node result = null;
        Node slow = head;
        Node fast = head;
        if (head != null && head.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            while (fast != null && fast.next != null) {
                if (slow == fast) {
                    slow = head;
                    while (true) {
                        if (slow == fast) {
                            return slow;
                        }
                        slow = slow.next;
                        fast = fast.next;
                    }
                } else {
                    fast = fast.next.next;
                    slow = slow.next;
                }
            }
        }
        return result;
    }

    private static Node randomNode() {
        Node result = null;
        Random random = new Random();
        int maxSize = 1000;
        int maxNum = 200;
        int size = random.nextInt(maxSize) + 1;
        Node head = null;
        HashMap<Integer, Node> map = new HashMap<>();
        for (int i = 0; i < size; i++) {
            if (i == 0) {
                int value = random.nextInt(maxNum);
                head = new Node(value);
                result = head;
                map.put(value, head);
            } else {
                int value = random.nextInt(maxNum);
                if (!map.containsKey(value)) {
                    Node temp = new Node(value);
                    map.put(value, temp);
                    head.next = temp;
                    head = temp;
                } else {
                    head.next = map.get(value);
                    result.firstLoopNode = map.get(value);
                    break;
                }
            }
        }
        return result;
    }
}