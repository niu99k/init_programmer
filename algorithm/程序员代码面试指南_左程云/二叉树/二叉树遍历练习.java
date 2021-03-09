/*
二叉树前中后遍历练习
 */

import java.util.*;

public class Main {
    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        mycheckPreOrder();
        mycheckMidOrder();
        mycheckPostOrder();
    }

    private static void mycheckPostOrder() {
        boolean flag = true;
        for (int i = 0; i < 5000; i++) {
            Node root = randomNode();
            String treeString1 = postOrderSolve1(root);
            String treeString2 = postOrderSolve2(root);
            if (!treeString1.equals(treeString2)) {
                flag = false;
                System.out.println("gg");
                break;
            }
        }
        if (flag) {
            System.out.println("nice!");
        }
    }

    private static String postOrderSolve2(Node root) {
        String res;
        StringBuffer stringBuffer = new StringBuffer();
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();

        stack1.push(root);
        while (!stack1.isEmpty()) {
            Node node = stack1.pop();
            if (node == null) {
                stack2.push(node);
            } else {
                stack2.push(node);
                stack1.push(node.left);
                stack1.push(node.right);
            }
        }
        while (!stack2.isEmpty()) {
            Node node = stack2.pop();
            if (node == null) {
                stringBuffer.append("#" + "_");
            } else {
                stringBuffer.append(node.value + "_");
            }
        }
        res = stringBuffer.toString();
        return res;
    }

    private static String postOrderSolve1(Node root) {
        String res;
        StringBuffer stringBuffer = new StringBuffer();
        if (root == null) {
            stringBuffer.append("#_");
        } else {
            stringBuffer.append(postOrderSolve1(root.left));
            stringBuffer.append(postOrderSolve1(root.right));
            stringBuffer.append(root.value + "_");
        }
        res = stringBuffer.toString();
        return res;
    }

    private static void mycheckMidOrder() {
        boolean flag = true;
        for (int i = 0; i < 5000; i++) {
            Node root = randomNode();
            String treeString2 = midOrderSolve2(root);
            String treeString1 = midOrderSolve1(root);
            if (!treeString1.equals(treeString2)) {
                flag = false;
                System.out.println("gg");
                break;
            }
        }
        if (flag) {
            System.out.println("nice!");
        }
    }

    private static String midOrderSolve2(Node root) {
        String res;
        StringBuffer stringBuffer = new StringBuffer();
        Stack<Node> stack = new Stack<>();
        Node node = root;
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
        stack.push(node);

        while (!stack.isEmpty()) {
            node = stack.pop();
            if (node == null) {
                stringBuffer.append("#_");
            } else {
                stringBuffer.append(node.value + "_");
                Node right = node.right;

                while (right != null) {
                    stack.push(right);
                    right = right.left;
                }
                stack.push(right);
            }
        }
        res = stringBuffer.toString();
        return res;
    }

    private static String midOrderSolve1(Node root) {
        String res;
        StringBuffer stringBuffer = new StringBuffer();
        if (root == null) {
            stringBuffer.append("#_");
        } else {
            stringBuffer.append(midOrderSolve1(root.left));
            stringBuffer.append(root.value + "_");
            stringBuffer.append(midOrderSolve1(root.right));
        }
        res = stringBuffer.toString();
        return res;
    }

    private static void mycheckPreOrder() {
        boolean flag = true;
        for (int i = 0; i < 5000; i++) {
            Node root = randomNode();
            String treeString1 = preOrderSolve1(root);
            String treeString2 = preOrderSolve2(root);
            if (!treeString1.equals(treeString2)) {
                flag = false;
                System.out.println("gg");
                break;
            }
        }
        if (flag) {
            System.out.println("nice!");
        }
    }

    private static String preOrderSolve2(Node root) {
        String res;
        StringBuffer stringBuffer = new StringBuffer();
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            if (node == null) {
                stringBuffer.append("#_");
            } else {
                stringBuffer.append(node.value + "_");
                stack.push(node.right);
                stack.push(node.left);
            }
        }
        res = stringBuffer.toString();
        return res;
    }

    private static String preOrderSolve1(Node root) {
        String res;
        StringBuffer stringBuffer = new StringBuffer();
        if (root == null) {
            stringBuffer.append("#_");
        } else {
            stringBuffer.append(root.value + "_");
            stringBuffer.append(preOrderSolve1(root.left));
            stringBuffer.append(preOrderSolve1(root.right));
        }
        res = stringBuffer.toString();
        return res;
    }

    private static Node randomNode() {
        Node res;
        Random random = new Random();
        int maxCount = 1000;
        int count = random.nextInt(maxCount) + 1;
        int maxNum = Integer.MAX_VALUE;
        int index = 0;
        Deque<Node> queue = new ArrayDeque<>();
        Node root = new Node(random.nextInt(maxNum));
        res = root;
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (random.nextBoolean()) {
                Node temp = new Node(random.nextInt(maxNum));
                node.left = temp;
                queue.offer(temp);
            } else if (random.nextBoolean()) {
                Node temp = new Node(random.nextInt(maxNum));
                node.right = temp;
                queue.offer(temp);
            }
            index++;
            if (index > count) {
                break;
            }
        }
        return res;
    }
}