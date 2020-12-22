/*
题目描述
一棵二叉树原本是搜索二叉树，但是其中有两个节点调换了位置，使得这棵二叉树不再是搜索二叉树，请按升序输出这两个错误节点的值。(每个节点的值各不相同)

输入描述:
第一行输入两个整数 n 和 root，n 表示二叉树的总节点个数，root 表示二叉树的根节点。

以下 n 行每行三个整数 fa，lch，rch，表示 fa 的左儿子为 lch，右儿子为 rch。(如果 lch 为 0 则表示 fa 没有左儿子，rch同理)

ps:节点的编号就是该节点的值。
输出描述:
请按升序输出这两个错误节点的值。
示例1
输入
复制
3 1
1 2 3
2 0 0
3 0 0
输出
复制
1 2
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
        int[] result = solve();
        if (result[0] > result[1]) {
            System.out.println(result[0] + " " + result[1]);
        } else {
            System.out.println(result[0] + " " + result[1]);
        }
    }

    private static int[] solve() {
        int[] result = new int[2];
        result[0] = 0;
        result[1] = 0;
        Stack<Integer> stack = new Stack<>();
        preOrder2InitStack(rootId, stack);

        int pre = Integer.MAX_VALUE;
        int nodeId;
        while (!stack.isEmpty()) {
            nodeId = stack.pop();
            if (pre < nodeId) {
                result[0] = result[0] == 0 ? pre : result[0];
                result[1] = nodeId;
            }
            pre = nodeId;
        }
        return result;
    }

    private static void preOrder2InitStack(int rootId, Stack<Integer> stack) {
        if (rootId == 0) {
            return;
        }
        preOrder2InitStack(treeMap.get(rootId).leftId, stack);
        stack.push(rootId);
        preOrder2InitStack(treeMap.get(rootId).rightId, stack);
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