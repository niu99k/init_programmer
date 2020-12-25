
/*
题目描述
给定一棵二叉树以及这棵树上的两个节点 o1 和 o2，请找到 o1 和 o2 的最近公共祖先节点。
输入描述:
第一行输入两个整数 n 和 root，n 表示二叉树的总节点个数，root 表示二叉树的根节点。

以下 n 行每行三个整数 fa，lch，rch，表示 fa 的左儿子为 lch，右儿子为 rch。(如果 lch 为 0 则表示 fa 没有左儿子，rch同理)

最后一行为节点 o1 和 o2。
输出描述:
输出一个整数表示答案。
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
4 5

44 27
27 32 34
32 19 41
19 9 14
9 0 0
14 0 2
2 0 1
1 10 15
10 29 0
29 0 0
15 0 0
41 44 39
44 7 42
7 0 0
42 0 31
31 37 12
37 40 0
40 0 0
12 0 0
39 28 36
28 16 4
16 8 0
8 0 0
4 35 3
35 0 0
3 0 0
36 22 33
22 0 23
23 13 43
13 25 20
25 38 0
38 0 0
20 26 0
26 0 0
43 0 0
33 21 0
21 0 0
34 17 18
17 0 0
18 24 30
24 0 0
30 11 6
11 0 0
6 0 5
5 0 0
32 38
输出
复制
2
备注:
1 \leq n \leq 5000001≤n≤500000
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
    static int nodeId1;
    static int nodeId2;
    static int ancestor;
    static boolean isResultFound;

    public static void main(String[] args) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        ancestor(rootId);
        System.out.println(ancestor);
    }

    private static int ancestor(int rootId) {
        int result = -1;
        if (rootId == 0) {
            result = -1;
        } else {
            int leftResult = ancestor(treeMap.get(rootId).leftId);
            int rightResult = ancestor(treeMap.get(rootId).rightId);
            if (isResultFound) {
                result = -1;
            } else if ((leftResult == nodeId1 || rightResult == nodeId1 || rootId == nodeId1) &&
                    (leftResult == nodeId2 || rightResult == nodeId2 || rootId == nodeId2)
            ) {
                ancestor = rootId;
                isResultFound = true;
            } else if (leftResult == nodeId1 || rightResult == nodeId1 || rootId == nodeId1) {
                result = nodeId1;
            } else if (leftResult == nodeId2 || rightResult == nodeId2 || rootId == nodeId2) {
                result = nodeId2;
            }
        }
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        treeMap = new HashMap<>();
        isResultFound = false;

        nodeCount = scanner.nextInt();
        rootId = scanner.nextInt();
        for (int i = 0; i < nodeCount; i++) {
            int nodeId = scanner.nextInt();
            treeMap.put(nodeId, new TreeNode(nodeId, scanner.nextInt(), scanner.nextInt()));
        }
        nodeId1 = scanner.nextInt();
        nodeId2 = scanner.nextInt();
    }
}