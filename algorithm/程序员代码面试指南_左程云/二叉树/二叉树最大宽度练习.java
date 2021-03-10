/*
二叉树最大宽度
 */

import java.util.*;

public class Main {
    static class Node {
        Node left;
        Node right;
        int value;
        int level;
        int[] widthMap;

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
            int maxWidth = maxWidth(randomNode);
            int realMax = 0;
            for (int j = 0; j < randomNode.widthMap.length; j++) {
                if (randomNode.widthMap[j] > realMax) {
                    realMax = randomNode.widthMap[j];
                }
            }
            if (maxWidth != realMax) {
                flag = false;
                System.out.println("gg");
                break;
            }
        }
        if (flag) {
            System.out.println("nice!");
        }

    }

    private static int maxWidth(Node root) {
        int result;
        Queue<Node> queue = new LinkedList<>();
        Map<Node, Integer> levelMap = new HashMap<>();
        Map<Integer, Integer> widthMap = new HashMap<>();
        queue.offer(root);
        levelMap.put(root, 0);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int level = levelMap.get(node);

            if (widthMap.containsKey(level)) {
                widthMap.put(level, widthMap.get(level) + 1);
            } else {
                widthMap.put(level, 1);
            }
            if (node.left != null) {
                levelMap.put(node.left, node.level + 1);
                queue.offer(node.left);
            }
            if (node.right != null) {
                levelMap.put(node.right, node.level + 1);
                queue.offer(node.right);
            }
        }

        result = widthMap.values().stream()
                .max(Integer::compareTo)
                .get();
        return result;
    }

    private static Node randomNode() {
        Node result;
        Random random = new Random();
        int maxSize = 1000;
        int size = random.nextInt(maxSize) + 1;
        int maxNum = Integer.MAX_VALUE;

        Node root = new Node(random.nextInt(maxNum));
        root.widthMap = new int[1001];
        root.widthMap[0] = 1;
        result = root;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        int index = 0;
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            if (random.nextBoolean()) {
                node.left = new Node(random.nextInt(maxNum));
                node.left.level = node.level + 1;
                stack.push(node.left);
                root.widthMap[node.left.level]++;
            }
            if (random.nextBoolean()) {
                node.right = new Node(random.nextInt(maxNum));
                node.right.level = node.level + 1;
                stack.push(node.right);
                root.widthMap[node.right.level]++;
            }
            index++;
            if (index > size) {
                break;
            }
        }
        return result;
    }
}