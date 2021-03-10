/*
搜索二叉树
 */

import java.util.*;

public class Main {
    static class Node {
        Node left;
        Node right;
        int value;

        boolean isST;

        Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        mycheck();
    }

    private static void mycheck() {
        boolean flag = true;
        for (int i = 0; i < 5000; i++) {
            Node randomNode = randomNode();
            boolean realIsST = randomNode.isST;
            boolean isST = isST(randomNode);
            if (realIsST && realIsST != isST) {
                flag = false;
                System.out.println("gg");
                break;
            }
        }
        if (flag) {
            System.out.println("nice!");
        }

    }

    private static boolean isST(Node node) {
        boolean result;
        boolean headResult;
        boolean leftResult;
        boolean rightResult;
        if (node == null) {
            return true;
        }
        boolean headResult1;
        boolean headResult2;

        if (node.left != null && node.left.value >= node.value) {
            headResult1 = false;
        } else {
            headResult1 = true;
        }

        if (node.right != null && node.right.value <= node.value) {
            headResult2 = false;
        } else {
            headResult2 = true;
        }

        headResult = headResult1 && headResult2;
        leftResult = isST(node.left);
        rightResult = isST(node.right);
        result = headResult && leftResult && rightResult;
        return result;
    }


    private static Node randomNode() {
        Node result;
        Random random = new Random();
        int maxSize = 1000;
        int size = random.nextInt(maxSize) + 1;
        int maxNum = 100;

        Node root = new Node(random.nextInt(maxNum));
        boolean isST = random.nextBoolean();
        result = root;
        root.isST = isST;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        int index = 0;
        if (isST) {
            while (!stack.isEmpty()) {
                Node node = stack.pop();
                if (random.nextBoolean()) {
                    Node temp = new Node(random.nextInt(node.value + 1));
                    if (temp.value != node.value && temp.value != 0) {
                        node.left = temp;
                        stack.push(temp);
                    }
                }
                if (random.nextBoolean()) {
                    Node temp = new Node(node.value + random.nextInt(maxNum - node.value));
                    if (temp.value != node.value) {
                        node.right = temp;
                        stack.push(temp);
                    }
                }
                index++;
                if (index > size) {
                    break;
                }
            }
        } else {
            while (!stack.isEmpty()) {
                Node node = stack.pop();
                if (random.nextBoolean()) {
                    Node temp = new Node(random.nextInt(maxNum));
                    node.left = temp;
                    stack.push(temp);
                }
                if (random.nextBoolean()) {
                    Node temp = new Node(random.nextInt(maxNum));
                    node.right = temp;
                    stack.push(temp);
                }
                index++;
                if (index > size) {
                    break;
                }
            }
        }

        return result;
    }
}