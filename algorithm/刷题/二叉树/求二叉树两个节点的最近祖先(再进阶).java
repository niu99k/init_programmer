/*
题目描述
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

41 41
41 24 7
24 2 6
2 29 0
29 0 10
10 22 14
22 0 3
3 0 0
14 0 39
39 8 30
8 37 15
37 36 0
36 34 0
34 19 31
19 0 0
31 0 0
15 23 0
23 0 0
30 0 27
27 21 28
21 0 0
28 40 38
40 35 0
35 0 0
38 0 0
6 4 12
4 25 1
25 0 26
26 0 9
9 33 0
33 0 0
1 32 18
32 0 0
18 0 5
5 0 0
12 0 17
17 0 0
7 0 13
13 0 20
20 0 11
11 0 16
16 0 0
32
2 15
4 41
24 13
9 1
14 17
12 11
39 38
7 8
8 36
20 8
19 7
25 12
19 3
28 17
11 39
10 32
14 26
11 15
7 24
3 29
5 41
22 8
36 8
34 29
7 39
1 19
7 27
39 24
38 22
41 12
33 7
5 11
输出
复制
2
2
3
1
备注:
1 \leq n \leq 1000001≤n≤100000
1 \leq m \leq 1000001≤m≤100000
1 \leq fa,lch,rch,root,o_1,o_2 \leq n1≤fa,lch,rch,root,o
1
​
 ,o
2
​
 ≤n
o_1 \ne o_2o
1
​


​
 =o
2
​
 */

import java.util.*;

public class Main {
    static Scanner scanner;
    static int nodeCount;
    static int rootId;
    static int queryCount;
    static Map<Integer, Integer> leftIdMap;
    static Map<Integer, Integer> rightIdMap;
    static Map<Integer, Integer> parentId4DSUMap;
    static Map<Integer, LinkedList> queryMap;
    static Map<Integer, Map<Integer, Integer>> resultIndex;
    static int[] result;

    public static void main(String[] args) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        solve(rootId);
        for (int i = 0; i < queryCount; i++) {
            System.out.println(result[i]);
        }
    }

    private static void solve(int rootId) {
        if (rootId == 0) {
            return;
        } else {
            int leftId = leftIdMap.get(rootId);
            int righId = rightIdMap.get(rootId);
            solve(leftId);
            parentId4DSUMap.put(leftId, rootId);

            parentId4DSUMap.put(rootId, rootId);
            judgeQueryAndRefreshResult(rootId);

            solve(righId);
            parentId4DSUMap.put(righId, rootId);
        }
    }

    private static void judgeQueryAndRefreshResult(int rootId) {
        if (queryMap.containsKey(rootId)) {
            LinkedList<Integer> list = queryMap.get(rootId);
            LinkedList<Integer> newList = new LinkedList<>();
            for (int i = 0; i < list.size(); i++) {
                int queryNodeId = list.get(i);
                if (parentId4DSUMap.containsKey(queryNodeId)) {
                    int ancestor = mostParent(queryNodeId);
                    if (resultIndex.containsKey(rootId) && resultIndex.get(rootId).containsKey(queryNodeId)) {
                        result[resultIndex.get(rootId).get(queryNodeId)] = ancestor;
                    }
                    if (resultIndex.containsKey(queryNodeId) && resultIndex.get(queryNodeId).containsKey(rootId)) {
                        result[resultIndex.get(queryNodeId).get(rootId)] = ancestor;
                    }
                } else {
                    newList.add(list.get(i));
                }
            }
            queryMap.put(rootId, newList);
        }
    }

    private static int mostParent(int queryNodeId) {
        int result;
        Stack<Integer> stack = new Stack<>();
        while (true) {
            if (queryNodeId == parentId4DSUMap.get(queryNodeId)) {
                result = queryNodeId;
                break;
            } else {
                queryNodeId = parentId4DSUMap.get(queryNodeId);
                stack.push(queryNodeId);
            }
        }

        while (!stack.isEmpty()) {
            parentId4DSUMap.put(stack.pop(), result);
        }
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        leftIdMap = new HashMap<>();
        rightIdMap = new HashMap<>();
        parentId4DSUMap = new HashMap<>();
        queryMap = new HashMap<>();
        resultIndex = new HashMap<>();

        nodeCount = scanner.nextInt();
        rootId = scanner.nextInt();
        for (int i = 0; i < nodeCount; i++) {
            int nodeId = scanner.nextInt();
            leftIdMap.put(nodeId, scanner.nextInt());
            rightIdMap.put(nodeId, scanner.nextInt());
        }

        queryCount = scanner.nextInt();
        result = new int[queryCount];
        LinkedList<Integer> linkedList;
        for (int i = 0; i < queryCount; i++) {
            int nodeId1 = scanner.nextInt();
            int nodeId2 = scanner.nextInt();

            if (queryMap.containsKey(nodeId1)) {
                linkedList = queryMap.get(nodeId1);
                linkedList.offer(nodeId2);
            } else {
                linkedList = new LinkedList<>();
                linkedList.offer(nodeId2);
                queryMap.put(nodeId1, linkedList);
            }
            if (queryMap.containsKey(nodeId2)) {
                linkedList = queryMap.get(nodeId2);
                linkedList.offer(nodeId1);
            } else {
                linkedList = new LinkedList<>();
                linkedList.offer(nodeId1);
                queryMap.put(nodeId2, linkedList);
            }

            Map<Integer, Integer> tempMap = resultIndex.containsKey(nodeId1) ? resultIndex.get(nodeId1) : new HashMap<>();
            tempMap.put(nodeId2, i);
            resultIndex.put(nodeId1, tempMap);

//            tempMap = resultIndex.containsKey(nodeId2) ? resultIndex.get(nodeId2) : new HashMap<>();
//            tempMap.put(nodeId1, i);
//            resultIndex.put(nodeId2, tempMap);
        }
    }
}