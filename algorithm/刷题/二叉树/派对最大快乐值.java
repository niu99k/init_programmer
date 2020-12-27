/*
题目描述
整个公司的人员结构可以看作是一棵标准的多叉树。树的头节点是公司唯一的老板，除老板外，每个员工都有唯一的直接上级，叶节点是没有任何下属的基层员工，除基层员工外，每个员工都有一个或多个直接下级，另外每个员工都有一个快乐值。
这个公司现在要办 party，你可以决定哪些员工来，哪些员工不来。但是要遵循如下的原则：
1.如果某个员工来了，那么这个员工的所有直接下级都不能来。
2.派对的整体快乐值是所有到场员工快乐值的累加。
3.你的目标是让派对的整体快乐值尽量大。
给定一棵多叉树，请输出派对的最大快乐值。
输入描述:
第一行两个整数 n 和 root，n 表示公司的总人数，root 表示公司的老板。

第二行 n 个整数 happy_i 表示员工 i 的快乐值。

接下来 n - 1 行每行两个整数 u_i 和 v_i 表示 u_i 是 v_i 的直接上级。
输出描述:
输出一个整数表示最大快乐值。
示例1
输入
复制
3 1
5 1 1
1 2
1 3

5 1
5 6 1 2 9
1 2
1 3
1 4
2 5
输出
复制
5
备注:
1 \le n \le 5000001≤n≤500000
0 \le happy_i \le 10000≤happy
i
​
 ≤1000
输入保证是一棵树
 */

import java.util.*;

public class Main {
    static Scanner scanner;
    static int nodeCount;
    static int rootId;
    static List<Integer>[] childrenMap;
    static int[] happyMap;

    static class ReturnType {
        int withRootMaxHappies;
        int withoutRootMaxHappies;
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
        ReturnType result = maxHappies(rootId);
        System.out.println(Math.max(result.withoutRootMaxHappies, result.withRootMaxHappies));
    }

    private static ReturnType maxHappies(int rootId) {
        ReturnType result = new ReturnType();
        List<ReturnType> childrenResult = new ArrayList<>();
        for (int childId : childrenMap[rootId]) {
            childrenResult.add(maxHappies(childId));
        }
        for (ReturnType childResult : childrenResult) {
            result.withoutRootMaxHappies += Math.max(childResult.withoutRootMaxHappies, childResult.withRootMaxHappies);
        }
        for (ReturnType childResult : childrenResult) {
            result.withRootMaxHappies += childResult.withoutRootMaxHappies;
        }
        result.withRootMaxHappies += happyMap[rootId];
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        nodeCount = scanner.nextInt();
        childrenMap = new List[nodeCount];
        happyMap = new int[nodeCount];
        rootId = scanner.nextInt() - 1;

        for (int i = 0; i < nodeCount; i++) {
            happyMap[i] = scanner.nextInt();
            childrenMap[i] = new ArrayList<>();
        }
        for (int i = 0; i < nodeCount - 1; i++) {
            childrenMap[scanner.nextInt() - 1].add(scanner.nextInt() - 1);
        }
    }
}