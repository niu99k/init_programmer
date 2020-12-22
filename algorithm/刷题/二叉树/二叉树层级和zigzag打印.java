/*
题目描述
给定一颗二叉树，分别实现按层和 ZigZag 打印二叉树。
ZigZag遍历: 意思是第一层从左到右遍历，第二层从右到左遍历，依次类推。
输入描述:
第一行输入两个整数 n 和 root，n 表示二叉树的总节点个数，root 表示二叉树的根节点。
以下 n 行每行三个整数 fa，lch，rch，表示 fa 的左儿子为 lch，右儿子为 rch。(如果 lch 为 0 则表示 fa 没有左儿子，rch同理)
输出描述:
先输出按层打印，再输出按ZigZag打印。
示例1
输入
复制
8 1
1 2 3
2 4 0
4 0 0
3 5 6
5 7 8
6 0 0
7 0 0
8 0 0
输出
复制
Level 1 : 1
Level 2 : 2 3
Level 3 : 4 5 6
Level 4 : 7 8
Level 1 from left to right: 1
Level 2 from right to left: 3 2
Level 3 from left to right: 4 5 6
Level 4 from right to left: 8 7
备注:
1 \leq n \leq 5000001≤n≤500000
1 \leq fa,lch,rch,root \leq n1≤fa,lch,rch,root≤n
 */

import java.util.*;

public class Main {
    static class TreeNode {
        int id;
        int leftId;
        int rightId;

        TreeNode(int id, int leftId, int rightId) {
            this.id = id;
            this.leftId = leftId;
            this.rightId = rightId;
        }
    }

    static Scanner scanner;
    static int nodeCount;
    static int rootId;
    static Map<Integer, TreeNode> treeMap;

    public static void main(String[] args) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        System.out.println(printByLevel());
        System.out.println(printByZigZagLevel());
    }

    private static String printByZigZagLevel() {
        String result;
        StringBuffer stringBuffer = new StringBuffer();
        Deque<Integer> queue = new LinkedList<>();
        int endNodeIdThisLevel = rootId;
        int endNodeIdThisLevelTemp = endNodeIdThisLevel;
        int level = 1;
        queue.offer(rootId);
        boolean shouldOfferFirstSide = true;
        stringBuffer.append("Level " + level + " from left to right: ");
        while (!queue.isEmpty()) {
            int nodeId;
            if (shouldOfferFirstSide) {
                nodeId = queue.pollFirst();
                int leftId = treeMap.get(nodeId).leftId;
                int rightId = treeMap.get(nodeId).rightId;

                if (leftId != 0) {
                    queue.offerLast(leftId);
                }
                if (rightId != 0) {
                    queue.offerLast(rightId);
                }
                if (nodeId == endNodeIdThisLevel) {
                    stringBuffer.append(nodeId + " " + "\n");
                    level++;
                    if (!queue.isEmpty()) {
                        endNodeIdThisLevel = queue.peekFirst();
                        stringBuffer.append("Level " + level + " from right to left: ");
                    }
                } else {
                    stringBuffer.append(nodeId + " ");
                }
            } else {
                nodeId = queue.pollLast();
                int leftId = treeMap.get(nodeId).leftId;
                int rightId = treeMap.get(nodeId).rightId;
                if (rightId != 0) {
                    queue.offerFirst(rightId);
                }
                if (leftId != 0) {
                    queue.offerFirst(leftId);
                }
                if (nodeId == endNodeIdThisLevel) {
                    stringBuffer.append(nodeId + " ");
                    level++;
                    if (!queue.isEmpty()) {
                        endNodeIdThisLevel = queue.peekLast();
                        stringBuffer.append("\nLevel " + level + " from left to right: ");
                    }
                } else {
                    stringBuffer.append(nodeId + " ");
                }
            }
            if (nodeId == endNodeIdThisLevelTemp) {
                shouldOfferFirstSide = !shouldOfferFirstSide;
                endNodeIdThisLevelTemp = endNodeIdThisLevel;
            }
        }
        result = stringBuffer.toString();
        return result;
    }

    private static String printByLevel() {
        String result;
        StringBuffer stringBuffer = new StringBuffer();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(rootId);
        int endNodeIdThisLevel = rootId;
        int endNodeIdNextLevel = -1;
        int level = 1;
        stringBuffer.append("Level " + level + " : ");
        while (!queue.isEmpty()) {
            int nodeId = queue.poll();
            int leftId = treeMap.get(nodeId).leftId;
            int rightId = treeMap.get(nodeId).rightId;
            if (leftId != 0) {
                queue.offer(leftId);
                endNodeIdNextLevel = leftId;
            }
            if (rightId != 0) {
                queue.offer(rightId);
                endNodeIdNextLevel = rightId;
            }

            if (nodeId == endNodeIdThisLevel) {
                endNodeIdThisLevel = endNodeIdNextLevel;
                stringBuffer.append(nodeId + " ");
                level++;
                if (!queue.isEmpty()) {
                    stringBuffer.append("\nLevel " + level + " : ");
                }
            } else {
                stringBuffer.append(nodeId + " ");
            }

        }
        result = stringBuffer.toString();
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        treeMap = new HashMap<>();

        nodeCount = scanner.nextInt();
        rootId = scanner.nextInt();
        for (int i = 0; i < nodeCount; i++) {
            int nodeId = scanner.nextInt();
            treeMap.put(nodeId, new TreeNode(nodeId, scanner.nextInt(), scanner.nextInt()));
        }
    }
}