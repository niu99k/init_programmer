/*
题目描述
给定一颗二叉树和一个整数 sum，求累加和为 sum 的最长路径长度。路径是指从某个节点往下，每次最多选择一个孩子节点或者不选所形成的节点链。
输入描述:
第一行输入两个整数 n 和 root，n 表示二叉树的总节点个数，root 表示二叉树的根节点。
以下 n 行每行四个整数 fa，lch，rch，val，表示 fa 的左儿子为 lch，右儿子为 rch。val 表示 fa 节点的值(如果 lch 为 0 则表示 fa 没有左儿子，rch同理)
输出描述:
输出一个整数表示最长链的长度。
示例1
输入
复制
9 1
1 2 3 -3
2 4 5 3
4 0 0 1
5 8 9 0
8 0 0 1
9 0 0 6
3 6 7 -9
6 0 0 2
7 0 0 1
6
输出
复制
4
示例2
输入
复制
9 1
1 2 3 -3
2 4 5 3
4 0 0 1
5 8 9 0
8 0 0 1
9 0 0 6
3 6 7 -9
6 0 0 2
7 0 0 1
-9
输出
复制
1
备注:
1 \leq n \leq 2000001≤n≤200000
1 \leq fa,lch,rch,root \leq n1≤fa,lch,rch,root≤n
-1000000 \leq val \leq 1000000−1000000≤val≤1000000
 */

import java.util.*;

public class Main {
    static class TreeNode {
        int id;
        int value;
        int leftId;
        int rightId;

        TreeNode(int id, int leftId, int rightId, int value) {
            this.id = id;
            this.value = value;
            this.leftId = leftId;
            this.rightId = rightId;
        }
    }

    static int nodeCount;
    static int rootId;
    static int limitSum;
    static Scanner scanner;
    static Map<Integer, TreeNode> treeMap;
    static int maxLen = 0;
    static int sum4SumMap = 0;
    static Map<Integer, Integer> sumMap;

    public static void main(String[] args) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        int maxLen = solve();
        System.out.println(maxLen);
    }

    private static int solve() {
        int result;
        Map<Integer, Integer> nodeLevelMap = new HashMap<>();
        initNodeLevelMapByPreOrderTranverse(rootId, nodeLevelMap, 1);
        solveByPreOrderTranverse(rootId, nodeLevelMap);
        result = maxLen;
        return result;
    }

    private static void initNodeLevelMapByPreOrderTranverse(int rootId, Map<Integer, Integer> nodeLevelMap, int level) {
        if (rootId == 0) {
            return;
        } else {
            nodeLevelMap.put(rootId, level);
            initNodeLevelMapByPreOrderTranverse(treeMap.get(rootId).leftId, nodeLevelMap, level + 1);
            initNodeLevelMapByPreOrderTranverse(treeMap.get(rootId).rightId, nodeLevelMap, level + 1);
        }
    }

    private static void solveByPreOrderTranverse(int rootId, Map<Integer, Integer> nodeLevelMap) {
        if (rootId == 0) {
            return;
        } else {
            sum4SumMap += treeMap.get(rootId).value;
            int maxLen4midNode = maxLenByEndNode(rootId, nodeLevelMap.get(rootId));
            boolean removeFlag;
            if (!sumMap.containsKey(sum4SumMap)) {
                sumMap.put(sum4SumMap, nodeLevelMap.get(rootId));
                removeFlag = true;
            } else {
                removeFlag = false;
            }
            if (maxLen4midNode > maxLen) {
                maxLen = maxLen4midNode;
            }
            solveByPreOrderTranverse(treeMap.get(rootId).leftId, nodeLevelMap);
            solveByPreOrderTranverse(treeMap.get(rootId).rightId, nodeLevelMap);
            if (removeFlag) {
                removeSumWithRootNodeInSumMap();
            }
            sum4SumMap -= treeMap.get(rootId).value;
        }
    }

    private static void removeSumWithRootNodeInSumMap() {
        sumMap.remove(sum4SumMap);
    }

    private static int maxLenByEndNode(int rootId, int level) {
        int result;
        if (sumMap.containsKey(sum4SumMap - limitSum)) {
            result = level - sumMap.get(sum4SumMap - limitSum);
        } else {
            result = 0;
        }
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        treeMap = new HashMap<>();
        sumMap = new HashMap<>();

        sumMap.put(0, 0);
        nodeCount = scanner.nextInt();
        rootId = scanner.nextInt();
        for (int i = 0; i < nodeCount; i++) {
            int nodeId = scanner.nextInt();
            treeMap.put(nodeId, new TreeNode(nodeId, scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
        }
        limitSum = scanner.nextInt();
    }

}