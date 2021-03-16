/*
trie tree
 */

import java.util.*;


public class Solution {
    static class Node {
        int end;
        int path;
        Map<Character, Node> next = new HashMap<>();
    }

    public String[] trieU(String[][] operators) {
        Node root = new Node();
        List<String> result = new ArrayList<>();
        for (int i = 0; i < operators.length; i++) {
            String op = operators[i][0];
            String str = operators[i][1];
            if (str == null || str.equals("")) {
                continue;
            }
            switch (op) {
                case "1":
                    add(root, str);
                    break;
                case "2":
                    delete(root, str);
                    break;
                case "3":
                    result.add(isExist(root, str) ? "YES" : "NO");
                    break;
                case "4":
                    result.add(preCount(root, str) + "");
                    break;
            }
        }
        return result.toArray(new String[result.size()]);
    }

    private int preCount(Node node, String str) {
        int result;
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            final char chr = chars[i];
            if (!node.next.containsKey(chr)) {
                return 0;
            }
            node = node.next.get(chr);
        }
        result = node.path;
        return result;
    }

    private void delete(Node node, String str) {
        char[] chars = str.toCharArray();
        if (!isExist(node, str)) {
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            final char chr = chars[i];
            Node next = node.next.get(chr);
            next.path--;
            if (next.path == 0) {
                node.next = new HashMap<>();
                return;
            }
            node = next;
        }
        node.end--;
    }

    private boolean isExist(Node node, String str) {
        boolean result = true;
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            final char chr = chars[i];
            if (!node.next.containsKey(chr)) {
                result = false;
                break;
            }
            node = node.next.get(chr);
        }
        if (node.end == 0) {
            result = false;
        }
        return result;
    }

    private void add(Node node, String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            final char chr = chars[i];
            if (node.next.containsKey(chr)) {
                final Node next = node.next.get(chr);
                next.path++;
                node = next;
            } else {
                final Node next = new Node();
                node.next.put(chr, next);
                next.path++;
                node = next;
            }
        }
        node.end++;
    }
}