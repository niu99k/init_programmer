/*
题目描述
从二叉树的节点 A 出发，可以向上或者向下走，但沿途的节点只能经过一次，当到达节点 B 时，路径上的节点数叫作 A 到 B 的距离。
现在给出一棵二叉树，求整棵树上每对节点之间的最大距离。
输入描述:
第一行输入两个整数 n 和 root，n 表示二叉树的总节点个数，root 表示二叉树的根节点。
以下 n 行每行三个整数 fa，lch，rch，表示 fa 的左儿子为 lch，右儿子为 rch。(如果 lch 为 0 则表示 fa 没有左儿子，rch同理)
最后一行为节点 o1 和 o2。
输出描述:
输出一个整数表示答案。
示例1
输入
复制
7 1
1 2 3
2 4 5
4 0 0
5 0 0
3 6 7
6 0 0
7 0 0
输出
复制
5
备注:
1 \leq n \leq 5000001≤n≤500000
1 \leq fa,lch,rch,root \leq n1≤fa,lch,rch,root≤n
 */

import java.util.*;

public class Main {
    static Scanner scanner;
    static int nodeCount;
    static int rootId;
    static Map<Integer, Integer> leftMap;
    static Map<Integer, Integer> rightMap;

    static class ReturnType {
        int maxLevel;
        int maxDis;
    }

    public static void main(String[] args) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        System.out.println(maxLen(rootId).maxDis);
    }

    private static ReturnType maxLen(int rootId) {
        ReturnType result = new ReturnType();
        if (rootId == 0) {
            result.maxLevel = 0;
            result.maxDis = 0;
        } else {
            ReturnType leftResult = maxLen(leftMap.get(rootId));
            ReturnType rightResult = maxLen(rightMap.get(rootId));
            int maxDisInLeftAndRight = Math.max(leftResult.maxDis, rightResult.maxDis);

            int maxLenWithRootId = leftResult.maxLevel + rightResult.maxLevel;
            result.maxDis = Math.max(maxDisInLeftAndRight, maxLenWithRootId);
            result.maxDis = Math.max(result.maxDis, leftResult.maxLevel + rightResult.maxLevel + 1);
            result.maxLevel = Math.max(leftResult.maxLevel, rightResult.maxLevel) + 1;
        }
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        leftMap = new HashMap<>();
        rightMap = new HashMap<>();

        nodeCount = scanner.nextInt();
        rootId = scanner.nextInt();
        for (int i = 0; i < nodeCount; i++) {
            int nodeId = scanner.nextInt();
            leftMap.put(nodeId, scanner.nextInt());
            rightMap.put(nodeId, scanner.nextInt());
        }
    }
}