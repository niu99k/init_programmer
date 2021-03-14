/*
判断是否是完全二叉树
 */

import java.util.*;

public class Main {
    static class Node {
        int value;
        Node left;
        Node right;
        boolean isBanlance = true;

        Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        myCheck();
    }

    private static void myCheck() {
        boolean flag = true;
        for (int i = 0; i < 5000; i++) {
            Node node = randomNode();
            if (node.isBanlance != isBalance(node)) {
                flag = false;
                System.out.println("gg");
                break;
            }
        }
        if (flag) {
            System.out.println("nice!");
        }
    }

    private static boolean isBalance(Node root) {
        boolean result = true;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        boolean flag = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (flag) {
                if (node.left != null && node.right != null) {
                    queue.offer(node.left);
                    queue.offer(node.right);
                } else if (node.left == null && node.right != null) {
                    result = false;
                    break;
                } else if (node.left != null && node.right == null) {
                    flag = false;
                    queue.offer(node.left);
                } else {
                    flag = false;
                }
            } else if (node.left != null || node.right != null) {
                result = false;
                break;
            }

        }
        return result;
    }

    private static Node randomNode() {
        Node result;
        Random random = new Random();
        boolean isBt = random.nextBoolean();
        int maxSize = 1000;
        int size = random.nextInt(maxSize) + 1;
        int maxNum = Integer.MAX_VALUE;
        if (isBt) {
            int[] array = new int[size];
            for (int i = 0; i < size; i++) {
                array[i] = random.nextInt(maxNum);
            }

            Node[] nodes = new Node[size];
            for (int i = 0; i < size; i++) {
                nodes[i] = new Node(array[i]);
            }

            for (int i = 0; i < size; i++) {
                if ((i + 1) * 2 - 1 < size) {
                    nodes[i].left = nodes[(i + 1) * 2 - 1];
                }
                if ((i + 1) * 2 < size) {
                    nodes[i].right = nodes[(i + 1) * 2];
                }
            }
            result = nodes[0];
            result.isBanlance = true;
        } else {
            Node head = new Node(random.nextInt(maxNum));
            head.isBanlance = false;
            result = head;

            Stack<Node> stack = new Stack<>();
            stack.push(head);

            int index = 0;
            Node last = head;
            while (index++ < size && !stack.isEmpty()) {
                Node node = stack.pop();
                last = node;
                if (random.nextBoolean()) {
                    Node left = new Node(random.nextInt(maxNum));
                    node.left = left;
                    stack.push(left);
                }
                if (random.nextBoolean()) {
                    Node right = new Node(random.nextInt(maxNum));
                    node.right = right;
                    stack.push(right);
                }
            }
            last.left = null;
            last.right = new Node(random.nextInt(maxNum));
        }
        return result;
    }
}