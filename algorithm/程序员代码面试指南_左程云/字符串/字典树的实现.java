/*
题目描述
字典树又称为前缀树或者Trie树，是处理字符串常用的数据结构。假设组成所有单词的字符仅是‘a’～‘z’，请实现字典树的结构，并包含以下四个主要的功能。
void insert(String word)：添加word，可重复添加；
void delete(String word)：删除word，如果word添加过多次，仅删除一次；
oolean search(String word)：查询word是否在字典树中出现过(完整的出现过，前缀式不算)；
int prefixNumber(String pre)：返回以字符串pre作为前缀的单词数量。现在给定一个m，表示有m次操作，每次操作都为以上四种操作之一。每次操作会给定一个整数op和一个字符串word，op代表一个操作码，
如果op为1，则代表添加word，op为2则代表删除word，op为3则代表查询word是否在字典树中，op为4代表返回以word为前缀的单词数量（数据保证不会删除不存在的word）。
输入描述:
输入包含多行，第一行一个整数m(1\leq m\leq 10^5)(1≤m≤10
5
 )，代表操作次数。接下来m行，每行包含一个整数op(1 \leq op \leq 4)(1≤op≤4)，和一个字符串word(1 \leq length_{word} \leq 20)(1≤length
word
​
 ≤20)。
输出描述:
对于每次操作，如果op为3时，如果word在字典树中，请输出“YES”，否则输出“NO”；如果op为4时，请输出返回以word为前缀的单词数量，其它情况不输出。
示例1
输入
复制
7
1 qwer
1 qwe
3 qwer
4 q
2 qwer
3 qwer
4 q
输出
复制
YES
2
NO
1
备注:
要求时空复杂度均为O(m * max(length_{word}))O(m∗max(length
word
​
 ))
 1 aba
 1 aaa
 1 aaa
 2 aaaaa
 */

import java.util.*;

public class Main {
    static class TNode {
        int passCount;
        int endCount;
        TNode[] children;

        TNode() {
            passCount = 0;
            endCount = 0;
            children = new TNode[26];
        }
    }

    static Scanner scanner;
    static TNode root;
    static int opCount;

    public static void main(String[] args) {
        init();
        result();
        deInit();

    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        int index = 0;
        while (index < opCount) {
            String op = scanner.next();
            String str = scanner.next();
            switch (op) {
                case "1":
                    insert(str);
                    break;
                case "2":
                    delete(str);
                    break;
                case "3":
                    System.out.println(search(str) ? "YES" : "NO");
                    break;
                case "4":
                    System.out.println(prefix(str));
                    break;
            }
            index++;
        }
    }

    private static int prefix(String str) {
        int result = 0;
        int index = 0;
        TNode node = root;
        boolean searchFlag = true;
        while (index < str.length()) {
            final int chr = str.charAt(index) - 'a';
            index++;

            TNode nextNode;
            if (node.children[chr] == null) {
                result = 0;
                searchFlag = false;
                break;
            } else {
                nextNode = node.children[chr];
            }
            node = nextNode;
        }
        if (searchFlag) {
            result = node.passCount;
        }
        return result;
    }

    private static boolean search(String str) {
        boolean result = true;
        int index = 0;
        TNode node = root;
        while (index < str.length()) {
            final int chr = str.charAt(index) - 'a';
            index++;

            TNode nextNode;
            if (node.children[chr] == null) {
                result = false;
                break;
            } else {
                nextNode = node.children[chr];
            }
            node = nextNode;
        }
        if (node.endCount == 0) {
            result = false;
        }
        return result;
    }

    private static void delete(String str) {
        if (search(str)) {
            int index = 0;
            TNode node = root;
            while (index < str.length()) {
                final int chr = str.charAt(index) - 'a';
                index++;

                TNode nextNode = node.children[chr];
                nextNode.passCount--;
                if (nextNode.passCount == 0) {
                    node.children[chr] = null;
                    return;
                }
                node = nextNode;
            }
            node.endCount--;
        }

    }

    private static void insert(String str) {
        int index = 0;
        TNode node = root;
        while (index < str.length()) {
            final int chr = str.charAt(index) - 'a';
            index++;

            TNode nextNode;
            if (node.children[chr] == null) {
                nextNode = new TNode();
                node.children[chr] = nextNode;
            } else {
                nextNode = node.children[chr];
            }
            nextNode.passCount++;
            node = nextNode;
        }
        node.endCount++;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        root = new TNode();

        opCount = scanner.nextInt();
    }
}