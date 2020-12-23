/*
题目描述
给定彼此独立的两棵二叉树，判断 t1 树是否有与 t2 树拓扑结构完全相同的子树。
设 t1 树的边集为 E1，t2 树的边集为 E2，若 E2 等于 E1 ，则表示 t1 树和t2 树的拓扑结构完全相同。
输入描述:
第一行输入两个整数 n 和 root，n 表示二叉树 t1 的总节点个数，root 表示二叉树 t1 的根节点。
以下 n 行每行三个整数 fa，lch，rch，表示 fa 的左儿子为 lch，右儿子为 rch。(如果 lch 为 0 则表示 fa 没有左儿子，rch同理)
第 n+2 行输入两个整数 m 和 root，n 表示二叉树 t2 的总节点个数，root 表示二叉树 t2 的根节点。
以下 m 行每行三个整数 fa，lch，rch，表示 fa 的左儿子为 lch，右儿子为 rch。(如果 lch 为 0 则表示 fa 没有左儿子，rch同理)
输出描述:
如果 t1 树有与 t2 树拓扑结构完全相同的子树，则输出 "true"，否则输出 "false"。
示例1
输入
复制
9 1
1 2 3
2 4 5
4 0 8
8 0 0
5 9 0
9 0 0
3 6 7
6 0 0
7 0 0
5 2
2 4 5
4 0 8
8 0 0
5 9 0
9 0 0
输出
复制
true
备注:
1 \leq n, m \leq 5000001≤n,m≤500000
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
    static int nodeCount1;
    static int nodeCount2;
    static int rootId1;
    static int rootId2;
    static Map<Integer, TreeNode> treeMap1;
    static Map<Integer, TreeNode> treeMap2;

    public static void main(String[] args) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        StringBuffer stringBuffer1 = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        preOrderSequence(treeMap1, rootId1, stringBuffer1);
        preOrderSequence(treeMap2, rootId2, stringBuffer2);
        String str1 = stringBuffer1.toString();
        String str2 = stringBuffer2.toString();

        System.out.println(isStr2PartOfStr1(str1, str2));
    }

    private static boolean isStr2PartOfStr1(String str, String match) {
        boolean result;
        int[] nextArray = nextArray(match);
        result = isMatchedWithKMS(str, match, nextArray);
        return result;
    }

    private static boolean isMatchedWithKMS(String str, String match, int[] nextArray) {
        boolean result;
        int strBeginIndex = 0;
        int matchBeginIndex = 0;
        while (true) {
            if (str.charAt(strBeginIndex) == match.charAt(matchBeginIndex)) {
                strBeginIndex++;
                matchBeginIndex++;
            } else {
                if (matchBeginIndex == 0) {
                    strBeginIndex++;
                } else {
                    matchBeginIndex = nextArray[matchBeginIndex];
                }
            }

            if (matchBeginIndex >= match.length()) {
                result = true;
                break;
            }
            if (strBeginIndex >= str.length()) {
                result = false;
                break;
            }
        }
        return result;
    }

    private static int[] nextArray(String match) {
        int[] result = new int[match.length()];
        for (int i = 0; i < match.length(); i++) {
            if (i == 0) {
                result[i] = -1;
            } else if (i == 1) {
                result[i] = 0;
            } else {
                int rightEndIndex = i;
                int leftEndIndex = result[i - 1];
                result[i] = match.charAt(rightEndIndex) == match.charAt(leftEndIndex) ? leftEndIndex + 1 : 0;
                while (match.charAt(rightEndIndex) != match.charAt(leftEndIndex) && leftEndIndex != 0) {
                    leftEndIndex = result[leftEndIndex];
                }
                result[i] = match.charAt(rightEndIndex) == match.charAt(leftEndIndex) ? leftEndIndex + 1 : 0;
            }
        }
        return result;
    }

    private static void preOrderSequence(Map<Integer, TreeNode> treeMap, int rootId, StringBuffer stringBuffer) {
        if (rootId == 0) {
            stringBuffer.append("!#");
        } else {
            stringBuffer.append("!" + rootId);
            preOrderSequence(treeMap, treeMap.get(rootId).leftId, stringBuffer);
            preOrderSequence(treeMap, treeMap.get(rootId).rightId, stringBuffer);
        }
    }

    private static void init() {
        scanner = new Scanner(System.in);
        treeMap1 = new HashMap<>();
        treeMap2 = new HashMap<>();

        nodeCount1 = scanner.nextInt();
        rootId1 = scanner.nextInt();
        for (int i = 0; i < nodeCount1; i++) {
            int nodeId = scanner.nextInt();
            treeMap1.put(nodeId, new TreeNode(nodeId, scanner.nextInt(), scanner.nextInt()));
        }

        nodeCount2 = scanner.nextInt();
        rootId2 = scanner.nextInt();
        for (int i = 0; i < nodeCount2; i++) {
            int nodeId = scanner.nextInt();
            treeMap2.put(nodeId, new TreeNode(nodeId, scanner.nextInt(), scanner.nextInt()));
        }
    }
}