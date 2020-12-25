/*
目描述
给定一棵二叉树，多次给出这棵树上的两个节点 o1 和 o2，请对于每次询问，找到 o1 和 o2 的最近公共祖先节点。
输入描述:
第一行输入两个整数 n 和 root，n 表示二叉树的总节点个数，root 表示二叉树的根节点。

以下 n 行每行三个整数 fa，lch，rch，表示 fa 的左儿子为 lch，右儿子为 rch。(如果 lch 为 0 则表示 fa 没有左儿子，rch同理)

第 n+2 行输入一个整数 m，表示询问的次数。

以下 m 行每行两个节点 o1 和 o2。
输出描述:
对于每组询问每行输出一个整数表示答案。
示例1
输入
复制
8 1
1 2 3
2 4 5
4 0 0
5 0 0
3 6 7
6 0 0
7 8 0
8 0 0
4
4 5
5 2
6 8
5 8
输出
复制
2
2
3
1
备注:
1 \leq n \leq 10001≤n≤1000
1 \leq m \leq 10000001≤m≤1000000
1 \leq fa,lch,rch,root,o_1,o_2\leq n1≤fa,lch,rch,root,o
1
​
 ,o
2
​
 ≤n
o1 \ne o2o1

​
 =o2
 */

import java.util.*;


public class Main {

    static class NodePair {
        int nodeId1;
        int nodeId2;

        NodePair(int nodeId1, int nodeId2) {
            this.nodeId1 = nodeId1;
            this.nodeId2 = nodeId2;
        }
    }

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
    static NodePair[] problem;
    static int problemCount;
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
        Map<Integer, Map<Integer, Integer>> ancestorMap = new HashMap<>();
        ancestorMap(ancestorMap, rootId);
        for (int i = 0; i < problemCount; i++) {
            int node1 = problem[i].nodeId1;
            int node2 = problem[i].nodeId2;

            if (ancestorMap.containsKey(node1) && ancestorMap.get(node1).containsKey(node2)) {
                System.out.println(ancestorMap.get(node1).get(node2));
            } else {
                System.out.println(ancestorMap.get(node2).get(node1));
            }
        }
    }

    private static void ancestorMap(Map<Integer, Map<Integer, Integer>> ancestorMap, int rootId) {
        if (rootId == 0) {
            return;
        } else {
            addInfoWithAncestorRootId(ancestorMap, rootId);

            ancestorMap(ancestorMap, treeMap.get(rootId).leftId);
            ancestorMap(ancestorMap, treeMap.get(rootId).rightId);
        }
    }

    private static void addInfoWithAncestorRootId(Map<Integer, Map<Integer, Integer>> ancestorMap, int rootId) {
        List<Integer> leftNodeIdList = new ArrayList<>();
        List<Integer> rightNodeIdList = new ArrayList<>();
        nodeIdList(leftNodeIdList, treeMap.get(rootId).leftId);
        nodeIdList(rightNodeIdList, treeMap.get(rootId).rightId);

        for (int i = 0; i < leftNodeIdList.size(); i++) {
            Map<Integer, Integer> map;
            if (ancestorMap.containsKey(leftNodeIdList.get(i))) {
                map = ancestorMap.get(leftNodeIdList.get(i));
            } else {
                map = new HashMap<>();
            }
            for (int j = 0; j < rightNodeIdList.size(); j++) {
                map.put(rightNodeIdList.get(j), rootId);
            }
            map.put(rootId, rootId);
            ancestorMap.put(leftNodeIdList.get(i), map);
        }
        for (int i = 0; i < rightNodeIdList.size(); i++) {
            Map<Integer, Integer> map;
            if (ancestorMap.containsKey(rightNodeIdList.get(i))) {
                map = ancestorMap.get(rightNodeIdList.get(i));
            } else {
                map = new HashMap<>();
            }
            map.put(rootId, rootId);
            ancestorMap.put(rightNodeIdList.get(i), map);
        }

    }

    private static void nodeIdList(List<Integer> nodeList, int rootId) {
        if (rootId == 0) {
            return;
        } else {
            nodeList.add(rootId);
            nodeIdList(nodeList, treeMap.get(rootId).leftId);
            nodeIdList(nodeList, treeMap.get(rootId).rightId);
        }
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

        problemCount = scanner.nextInt();
        problem = new NodePair[problemCount];

        for (int i = 0; i < problemCount; i++) {
            problem[i] = new NodePair(scanner.nextInt(), scanner.nextInt());
        }
    }
}