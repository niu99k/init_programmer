/*
题目描述
给出一个整数 n，如果 n < 1，代表空树，否则代表中序遍历的结果为 {1, 2, 3... n}。请输出可能的二叉树结构有多少。
输入描述:
第一行输入一个整数 n。
输出描述:
输出一个整数对 1e9 + 7 取模的值表示答案。
示例1
输入
复制
7
输出
复制
429
备注:
1 \leq n \leq 100001≤n≤10000
 */

import java.util.*;

public class Main {
    static Scanner scanner;
    static int nodeCount;
    static int[] array;
    static Map<Integer, Long> memo;
    final static int mod = 1000000007;

    public static void main(String[] args) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        initMemo();
        System.out.println(memo.get(nodeCount));
    }

    private static void initMemo() {
        memo.put(0, 1L);
        for (int i = 1; i <= nodeCount; i++) {
            possiblityCount(i);
        }
    }

    private static void possiblityCount(int arrayLen) {
        long result = 0;
        for (int i = 0; i < arrayLen; i++) {
            long possibilities4RootIdI = memo.get(i) * memo.get(arrayLen - i - 1);
            possibilities4RootIdI = possibilities4RootIdI % mod;
            result += possibilities4RootIdI;
            result = result % mod;
            memo.put(arrayLen, result);
        }
    }

    private static void init() {
        scanner = new Scanner(System.in);
        memo = new HashMap<>();
        nodeCount = scanner.nextInt();
        array = new int[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            array[i] = i;
        }
    }
}